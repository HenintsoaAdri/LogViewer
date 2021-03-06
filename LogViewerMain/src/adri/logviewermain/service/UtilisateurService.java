package adri.logviewermain.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewermain.exception.PermissionException;
import adri.logviewermain.model.*;
import adri.logviewermain.util.StringUtil;

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
		StringUtil.getInstance().checkEmail(email);
		StringUtil.getInstance().checkPassword(password);
		return this.getService().getDao().loginUtilisateur(email, password);
	}
	
	public void crud(BaseModel model, String method, Utilisateur user) throws Exception{
		try {
			boolean authorization = false;
			Groupe groupe = null;
			switch(model.instance()){
				case "Groupe":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDGROUPE);
					groupe = (Groupe)model;
					break;
				case "Agent":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDAGENT);
					if(((Agent)model).insideGroupe(user.getProfil().getGroupe())){
						groupe = user.getProfil().getGroupe();
					}
					break;
				case "Profil":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDPROFIL);
					groupe = ((Profil)model).getGroupe();
					break;
				case "Utilisateur":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDUTILISATEUR);
					try{
						groupe = ((Utilisateur)model).getProfil().getGroupe();
					}catch(NullPointerException e){
						groupe = null;
					}
					break;
			}
			if(authorization){
				Method function = this.getService().getClass().getMethod(method, BaseModel.class);
				function.invoke(this.getService(), model);
				return;
			}
			else if(user.getProfil().getGroupe().equals(groupe)){
				GroupeService.getInstance().crudGroupe(model, method, user);
				return;
			}
			throw new PermissionException("Vous n'etes pas autoris� � faire des modifications sur les "
					+ model.getClass().getSimpleName()
					+ "s ext�rieurs � votre groupe");
		} catch (InvocationTargetException e) {
			throw new Exception(e.getCause().getMessage());
		} catch (Exception e) {
			throw e;
		}
	}
	public void find(BaseModel model, Utilisateur user) throws Exception{
		boolean authorizationAll = false;
		boolean authorizationGroupe = false;
		Groupe groupe = null;
		this.getService().findById(model);
		switch (model.instance()) {
		case "Utilisateur":
			authorizationAll = user.isAllowed(PermissionType.CRUDUTILISATEUR);
			authorizationGroupe = user.isAllowed(PermissionType.UTILISATEURGROUPE);
			try{
				groupe = ((Utilisateur)model).getProfil().getGroupe();
			}catch(NullPointerException e){
				groupe = null;
			}
			break;
		case "Agent":
			authorizationAll = user.isAllowed(PermissionType.CRUDAGENT);
			authorizationGroupe = user.isAllowed(PermissionType.AGENTGROUPE)||user.isAllowed(PermissionType.LECTURETELECHARGEMENT);
			if(((Agent)model).insideGroupe(user.getProfil().getGroupe())){
				groupe = user.getProfil().getGroupe();
			}
			break;
		case "Profil":
			authorizationAll = user.isAllowed(PermissionType.CRUDPROFIL);
			authorizationGroupe = user.isAllowed(PermissionType.PROFILGROUPE);
			groupe = ((Profil)model).getGroupe();
			break;
		case "Groupe":
			authorizationAll = user.isAllowed(PermissionType.CRUDGROUPE);
			authorizationGroupe = user.isAllowed(PermissionType.GESTIONGROUPE);
			groupe = (Groupe)model;
			break;
		}
		if(authorizationAll||(authorizationGroupe && user.getProfil().getGroupe().equals(groupe))){
			return;
		}
			throw new PermissionException("Vous n'avez pas acc�s � ces informations");
	}
	public BaseModelPagination findAll(Class<? extends BaseModel> model, int maxResult, int page, Utilisateur user) throws Exception{
		BaseModelPagination pagination = new BaseModelPagination(model, maxResult, page);
		boolean authorizationAll = false;
		boolean autorizationGroupe = false;
		switch (model.newInstance().instance()) {
		case "Utilisateur":
			authorizationAll = user.isAllowed(PermissionType.CRUDUTILISATEUR);
			autorizationGroupe = user.isAllowed(PermissionType.UTILISATEURGROUPE);
			break;
		case "Agent":
			authorizationAll = user.isAllowed(PermissionType.CRUDAGENT);
			autorizationGroupe = user.isAllowed(PermissionType.AGENTGROUPE)||user.isAllowed(PermissionType.LECTURETELECHARGEMENT);
			break;
		case "Profil":
			authorizationAll = user.isAllowed(PermissionType.CRUDPROFIL);
			autorizationGroupe = user.isAllowed(PermissionType.PROFILGROUPE);
			break;
		case "Groupe":
			authorizationAll = user.isAllowed(PermissionType.CRUDGROUPE);
			autorizationGroupe = user.isAllowed(PermissionType.GESTIONGROUPE);
			break;
		}
		if(authorizationAll){
			this.getService().findAll(pagination);
		}else if(autorizationGroupe){
			this.getService().findAllByBaseModel(pagination, user.getProfil().getGroupe());
		}
		return pagination;
	}
	public void editUtilisateur(Utilisateur user, String confirm) throws Exception{
		this.getService().update(user);
	}
	public List<? extends BaseModel> selectGroupe(Class<? extends BaseModel> classe, Utilisateur user) throws Exception{
		boolean authorizationAll = false;
		boolean autorizationGroupe = false;
		switch (classe.getSimpleName()) {
		case "Utilisateur":
			authorizationAll = user.isAllowed(PermissionType.CRUDUTILISATEUR);
			autorizationGroupe = user.isAllowed(PermissionType.UTILISATEURGROUPE);
			break;
		case "Agent":
			authorizationAll = user.isAllowed(PermissionType.CRUDAGENT);
			autorizationGroupe = user.isAllowed(PermissionType.AGENTGROUPE);
			break;
		case "Profil":
			authorizationAll = user.isAllowed(PermissionType.CRUDPROFIL);
			autorizationGroupe = user.isAllowed(PermissionType.PROFILGROUPE);
			break;
			
		}
		if(authorizationAll){
			return this.getService().findAll(new GroupeView());
		}
		else if(autorizationGroupe){
			List<Groupe> groupe = new ArrayList<Groupe>();
			groupe.add(user.getProfil().getGroupe());
			return groupe;
		}
		throw new PermissionException("Vous n'avez pas acc�s � la liste des groupes");
	}
	public BaseModelPagination getDetails(BaseModel item, int page) throws Exception {
		BaseModelPagination pagination = null;
		switch(item.instance()){
			case "Profil" :  pagination = new BaseModelPagination(UtilisateurView.class, 10, page); break;
			default : return null;
		}
		getService().findAllByBaseModel(pagination, item);
		return pagination;
	}
}
