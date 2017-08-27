package adri.logviewermain.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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
	
	public Object crud(BaseModel model, String method, Utilisateur user) throws Exception{
		try {
			boolean authorization = false;
			switch(model.instance()){
				case "Groupe":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDGROUPE);
					break;
				case "Agent":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDAGENT);
					break;
				case "Profil":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDUTILISATEUR);
					break;
				case "Utilisateur":
					authorization = user.getProfil().isAllowed(PermissionType.CRUDUTILISATEUR);
					break;
			}
			if(authorization){
				Method function = this.getService().getClass().getMethod(method, BaseModel.class);
				return function.invoke(this.getService(), model);
			}
			throw new PermissionException("Vous n'etes pas autoris� � faire des modifications sur les "
					+ model.getClass().getSimpleName()
					+ "s.");
		} catch (InvocationTargetException e) {
			throw new Exception(e.getCause().getMessage());
		} catch (Exception e) {
			throw e;
		}
	}
	public BaseModelPagination findAll(Class<? extends BaseModel> model, int maxResult, int page, Utilisateur user) throws Exception{
		BaseModelPagination pagination = new BaseModelPagination(model, maxResult, page);
		boolean authorization = false;
		switch (model.newInstance().instance()) {
		case "Utilisateur":
			authorization = user.isAllowed(PermissionType.CRUDUTILISATEUR);
			break;
		case "Agent":
			authorization = user.isAllowed(PermissionType.CRUDAGENT);
			break;
		case "Profil":
			authorization = user.isAllowed(PermissionType.CRUDUTILISATEUR);
			break;
		case "Groupe":
			authorization = user.isAllowed(PermissionType.CRUDGROUPE);
			break;
		}
		if(authorization){
			this.getService().findAll(pagination);
		}
		return pagination;
	}
	public void editUtilisateur(Utilisateur user, String confirm) throws Exception{
		this.getService().update(user);
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
	public List<? extends BaseModel> selectEntities(BaseModel model, Utilisateur user) throws Exception {

		BaseModel entity = null;
		switch(model.instance()){
			case "Utilisateur": entity = new Profil();break;
			case "Agent" : entity = new Groupe();break;
			case "Groupe" : entity = new Profil();break;
			case "Profil" : entity = new Groupe();break;
			default : return null;
		}
		return getService().findAll(entity);
	}
}