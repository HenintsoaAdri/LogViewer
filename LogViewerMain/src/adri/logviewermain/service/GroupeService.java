package adri.logviewermain.service;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewermain.exception.PermissionException;
import adri.logviewermain.model.BaseModel;
import adri.logviewermain.model.BaseModelPagination;
import adri.logviewermain.model.Groupe;
import adri.logviewermain.model.PermissionType;
import adri.logviewermain.model.Profil;
import adri.logviewermain.model.Utilisateur;

public class GroupeService {

	private BaseService service;
	private static GroupeService groupe;
	
	private GroupeService(){}
	public static GroupeService getInstance(){

		ConfigurableApplicationContext context = null;
		try{
			context =  new ClassPathXmlApplicationContext("list-beans.xml");
			groupe = (GroupeService)context.getBean("groupeService");
			return groupe;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public static GroupeService getInstance(ApplicationContext context){

		try{
			groupe = (GroupeService)context.getBean("groupeService");
			return groupe;
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
	public void editGroupe(Utilisateur user) throws Exception{
		if(user.getProfil().isAllowed(PermissionType.GESTIONGROUPE)){
			throw new PermissionException("Vous n'etes pas autoris� � g�rer ce groupe");
		}
		this.getService().update(user.getProfil().getGroupe());
	}
	protected void crudGroupe(BaseModel item, String method, Utilisateur user) throws Exception{
		boolean authorization = false;
		switch(item.instance()){
			case "Utilisateur":
				authorization = user.getProfil().isAllowed(PermissionType.UTILISATEURGROUPE);
				break;
			case "Agent":
				authorization = user.getProfil().isAllowed(PermissionType.AGENTGROUPE);
				break;
			case "Profil":
				authorization = user.getProfil().isAllowed(PermissionType.PROFILGROUPE);
				break;
			default : throw new Exception("Action interdite");
		}
		if(!authorization){
			throw new PermissionException("Vous n'etes pas autoris� � faire une modification les "+ item.instance() +"s du groupe");
		}
		Method function = this.getService().getClass().getMethod(method, item.getClass());
		function.invoke(this, item);
	}
	public List<? extends BaseModel> getListeGroupe(Groupe groupe, Class<? extends BaseModel> item) throws Exception{
		return this.getService().findAllByBaseModel(item, groupe);
	}
	public BaseModelPagination getListeGroupe(Groupe groupe, Class<? extends BaseModel> item, int page) throws Exception{
		BaseModelPagination pagination = new BaseModelPagination(item, 10, page);
		this.getService().findAllByBaseModel(pagination, groupe);
		return pagination;
	}
	public void createProfil(Profil profil, Utilisateur u) throws Exception{
		profil.setGroupe(u.getProfil().getGroupe());
		crudGroupe(profil, "save", u);
	}
}
