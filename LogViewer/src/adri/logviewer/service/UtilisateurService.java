package adri.logviewer.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewer.exception.PermissionException;
import adri.logviewer.model.*;
import adri.logviewer.util.StringUtil;

public class UtilisateurService {

	private BaseService service;
	private static UtilisateurService utilisateur;
	
	private UtilisateurService(){}
	public static UtilisateurService getInstance(){

		ConfigurableApplicationContext context = null;
		try{
			context =  new ClassPathXmlApplicationContext("list-beans.xml");
			utilisateur = (UtilisateurService)context.getBean("utilisateurService");
			return utilisateur;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public static UtilisateurService getInstance(ApplicationContext context){
		try{
			utilisateur = (UtilisateurService)context.getBean("utilisateurService");
			return utilisateur;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	public BaseService getService() {
		return service;
	}
	public void setService(BaseService service) {
		this.service = service;
	}

	public Utilisateur login(String email, String password) throws Exception{
		return this.getService().getDao().loginUtilisateur(StringUtil.getInstance().checkEmail(email), StringUtil.getInstance().checkPassword(password));
	}

	private boolean getAuthorization(Class<? extends BaseModel> model, Utilisateur user) throws Exception{
		return getAuthorization(model.newInstance(), user);
	}
	private boolean getAuthorization(BaseModel model, Utilisateur user) {
		boolean authorization = false;
		switch (model.instance()) {
		case "Utilisateur":
			authorization = user.isAllowed(PermissionType.CRUDUTILISATEUR);
			break;
		case "Agent":
			authorization = user.isAllowed(PermissionType.CRUDGROUPE) || user.isAllowed(PermissionType.CRUDAGENT);
			break;
		case "Profil":
			authorization = user.isAllowed(PermissionType.CRUDUTILISATEUR);
			break;
		case "Groupe":
			authorization = user.isAllowed(PermissionType.CRUDGROUPE);
			break;
		case "Timeline" :
			authorization = user.isAllowed(PermissionType.CRUDUTILISATEUR);
		}
		return authorization;
	}
	public void crud(BaseModel model, String method, Utilisateur user) throws Exception{
		try {
			if(getAuthorization(model, user)){
				try{
					Utilisateur temoin = new Utilisateur(((Utilisateur)model).getId());
					getService().findById(temoin);
					if(temoin.isSuperUtilisateur()&& !user.equals(model)){
						throw new PermissionException("Acc�s refus�. Donn�es prot�g�es");
					}else if(!user.equals(model) 
							&& temoin.getPassword() != ((Utilisateur)model).getPassword() 
							&& !temoin.isReinitPassword()
							&& method.contentEquals("update")){
						throw new PermissionException("Ce mot de passe ne peut �tre chang� que par son utilisateur");
					}
				}catch(PermissionException e){
					throw e;
				}catch(Exception e){}
				Method function = this.getService().getClass().getMethod(method, BaseModel.class);
				this.getService().setTimeline(new Timeline(method, model, user));
				function.invoke(this.getService(), model);
				return;
			}
			throw new PermissionException("Vous n'etes pas autoris� � faire des modifications sur les "
					+ model.instance()
					+ "s.");
		} catch (InvocationTargetException e) {
			throw (Exception)e.getCause();
		} catch (Exception e) {
			throw e;
		}
	}
	public void find(BaseModel model, Utilisateur user) throws Exception{
		if(user.isSuperUtilisateur() || getAuthorization(model, user)){
			this.getService().findById(model);
		}
		else{
			this.findById(model, user);
		}
	}
	private void findById(BaseModel model, Utilisateur user) throws Exception{
		boolean correct = false;
		try {
			switch(model.instance()){
				case "Profil" : correct = user.getProfil().equals(model);
					break; 
				case "Groupe" : 
					correct = findAllByBaseModel(user.getProfil(), GroupeView.class).contains(model);
					break;
				case "Agent" : this.getService().findById(user.getProfil(), "ListeGroupe");
					correct = findAllAgentByGroupe(user.getProfil().getListeGroupe(), AgentView.class).contains(model);
					break;
				case "Utilisateur" : correct = user.equals(model);					
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(!correct){
			throw new PermissionException("Vous n'avez pas l'autorisation d'acc�der � ces informations");
		}
		this.getService().findById(model);
	}
	public BaseModelPagination findAll(Class<? extends BaseModel> model, int maxResult, int page, Utilisateur user) throws Exception{
		BaseModelPagination pagination = new BaseModelPagination(model, maxResult, page);
		if(user.isSuperUtilisateur() || getAuthorization(model, user)){
			this.getService().findAll(pagination);
		}
		else{
			this.findAll(pagination, user);
		}
		return pagination;
	}
	private void findAll(BaseModelPagination pagination, Utilisateur user) {
		BaseModel model;
		try {
			model = pagination.getClasse().newInstance();
			switch(model.instance()){
				case "Profil" : pagination.setTotalResult(1);
					pagination.setListe(Arrays.asList(user.getProfil()));
					break; 
				case "Groupe" : 
					findAllByBaseModel(user.getProfil(), pagination);
					break;
				case "Agent" : this.getService().findById(user.getProfil(), "ListeGroupe");
					findAllAgentByGroupe(user.getProfil().getListeGroupe(), pagination);
					break;
				case "Utilisateur" : pagination.setTotalResult(1);
					pagination.setListe(Arrays.asList(user));					
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public void editUtilisateur(Utilisateur user) throws Exception{
		Utilisateur temoin = new Utilisateur(user.getId());
		this.getService().findById(temoin);
		if(!temoin.getProfil().equals(user.getProfil()) && !temoin.isAllowed(PermissionType.CRUDUTILISATEUR)){
			user.setProfil(temoin.getProfil());
		}
		this.getService().update(user);
	}
	public BaseModelPagination getDetails(BaseModel model, int page, int maxResult) throws Exception {
		BaseModelPagination pagination = null;
		switch(model.instance()){
			case "Profil" : pagination = new BaseModelPagination(UtilisateurView.class, maxResult, page);
							getService().findAllByBaseModel(pagination, model);
							break;
			case "Groupe" : pagination = findAllByBaseModel(model, AgentView.class, page, maxResult);break;
			case "Agent"  : pagination = findAllByBaseModel(model, GroupeView.class, page, maxResult);break;
			case "Utilisateur" : if(((Utilisateur)model).getProfil() != null) pagination = findAllByBaseModel(((Utilisateur)model).getProfil(), GroupeView.class, page, maxResult);break;
		}
		return pagination;
	}
	public void edit(BaseModel model, Utilisateur user) throws Exception {
		String[] field = null;
		if(!getAuthorization(model, user) && !user.isSuperUtilisateur()){
			throw new PermissionException("Vous n'�tes pas autoris� � faire des modidications sur " + model.getNomString());
		}
		switch(model.instance()){
			case "Profil" : field = new String[]{"ListeGroupe"};
			case "Agent"  : field = new String[]{"ListeGroupe"};
		}
		getService().findById(model, field);
	}
	public List<? extends BaseModel> findAllByBaseModel(BaseModel model, Class<? extends BaseModel> classe) throws Exception{
		return this.getService().findAllByBaseModel(classe, model, "liste".concat(model.instance()));
	}
	public void findAllByBaseModel(BaseModel model, BaseModelPagination pagination) throws Exception{
		this.getService().findAllByBaseModel(pagination, model, "liste".concat(model.instance()));
	}
	public BaseModelPagination findAllByBaseModel(BaseModel model, Class<? extends BaseModel> classe, int page, int maxResult) throws Exception{
		BaseModelPagination pagination = new BaseModelPagination(classe, maxResult, page);
		this.getService().findAllByBaseModel(pagination, model, "liste".concat(model.instance()));
		return pagination;
	}
	public void findAllAgentByGroupe(Set<Groupe> groupe, BaseModelPagination pagination) throws Exception{
		this.getService().findAllByBaseModel(pagination, groupe, "listeGroupe");
	}
	public List<? extends BaseModel> findAllAgentByGroupe(Set<Groupe> groupe, Class<? extends BaseModel> classe) throws Exception{
		return this.getService().findAllByBaseModel(classe, groupe, "listeGroupe");
	}
	public List<? extends BaseModel> selectEntities(BaseModel model, Utilisateur user) throws Exception {

		BaseModel entity = null;
		if(getAuthorization(model, user)){
			switch(model.instance()){
				case "Utilisateur": entity = new ProfilView();break;
				case "Agent" : entity = new GroupeView();break;
				case "Groupe" : entity = new ProfilView();break;
				case "Profil" : entity = new GroupeView();break;
				default : return null;
			}
			return getService().findAll(entity);
		}
		throw new PermissionException("Vous n'etes pas autoris� � faire des modifications sur les "
				+ model.instance()
				+ "s.");
	}
	public BaseModelPagination search(BaseModel model, int maxResult, int page, Utilisateur user) throws Exception{

		if(user.isSuperUtilisateur() || getAuthorization(model, user)){
			BaseModelPagination pagination = new BaseModelPagination(model.getClass(), maxResult, page);
			this.getService().search(pagination, model);
			return pagination;
		}
		throw new PermissionException("Vous n'etes pas autoris� � rechercher les "
				+ model.instance()
				+ "s.");
	}
	public void timeline(Timeline timeline) {
		try {
			this.getService().getDao().save(timeline, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public BaseModelPagination getStat(BaseModel statType, int top, Utilisateur user)throws Exception{
		if(user.isSuperUtilisateur() || getAuthorization(statType, user)){
			BaseModelPagination pagination = new BaseModelPagination(statType.getClass(), top, 0);
			getService().findAll(pagination);
			return pagination;
		}
		throw new PermissionException("Vous n'avez pas acc�s � ces donn�es");
	}
	public void getStatActivite(StatActivite stat ,Utilisateur user)throws Exception{
		if(!user.isSuperUtilisateur() && !getAuthorization(Utilisateur.class, user)){
			throw new PermissionException("Vous n'avez pas acc�s � ces donn�es");
		}
		getService().getDao().getStatActivite(stat);
	}
	public void reinitPassword(String email) throws Exception{
		try{
			getService().getDao().reinitPassword(email);
		}catch(Exception e){
			throw new Exception("Le mot de passe n'a pu etre r�initialis�");
		}
	}
	public BaseModelPagination findAllReinitUtilisateur(int page, int maxResult) throws Exception{
		BaseModelPagination pagination = new BaseModelPagination(UtilisateurView.class, maxResult, page);
		this.getService().findAllByCritere(pagination, "reinitPassword", true);
		return pagination;
	}
}
