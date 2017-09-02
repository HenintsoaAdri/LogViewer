package adri.logviewermain.action;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.interceptor.ValidationErrorAware;

import adri.logviewermain.exception.PermissionException;
import adri.logviewermain.model.Agent;
import adri.logviewermain.model.BaseModel;
import adri.logviewermain.model.Utilisateur;
import adri.logviewermain.service.UtilisateurService;

public class CrudAction extends BaseAction implements ValidationErrorAware{
	public String save() throws Exception {
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			if(getItem() instanceof Agent){
				((Agent)getItem()).setCreateur(getUser());
			}
			UtilisateurService.getInstance(context).crud(getItem(), "save", getUser());
			return SUCCESS;
		}catch(PermissionException e){
			e.printStackTrace();
			return "permission";
		}catch(NullPointerException e){
			e.printStackTrace();
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String update(){
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).crud(getItem(), "update", getUser());
			if (getUser().equals(getItem())) {
				UtilisateurService.getInstance(context).crud(getItem(), "findById", getUser());
				Utilisateur u = (Utilisateur)getItem();
				getSession().put("user", u);
			}
			return SUCCESS;
		}catch(PermissionException e){
			e.printStackTrace();
			return "permission";
		}catch(NullPointerException e){
			return NONE;
		}catch(Exception e){
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String delete() throws PermissionException{
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;
			
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).crud(getItem(), "findById", getUser());
			UtilisateurService.getInstance(context).crud(getItem(), "delete", getUser());
			return SUCCESS;
		}catch(PermissionException e){
			e.printStackTrace();
			return "permission";
		}catch(NullPointerException e){
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}

	public String find() throws PermissionException{
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;		
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setPagination(UtilisateurService.getInstance(context).getDetails(getItem(), getPage()));
			return SUCCESS;
		}catch(PermissionException e){
			throw e;
		}catch(NullPointerException e){
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}

	public String findAllPaginate(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(UtilisateurService.getInstance(context).findAll(getItem().getClass(), getNbItem(), getPage(), getUser()));
			return SUCCESS;
		}catch(PermissionException e){
			return "permission";
		}catch(Exception e){
			setException(e);
			e.printStackTrace();
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}

	public String edit(){
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;
			if(getUser().equals(getItem())) return "user";
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).edit(getItem(), getUser());
			setListe((List<? extends BaseModel>)UtilisateurService.getInstance(context).selectEntities(getItem(),getUser()));
			return SUCCESS;
		}catch(PermissionException e){
			return "permission";
		}catch(NullPointerException e){
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String selectEntities(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setListe((List<? extends BaseModel>)UtilisateurService.getInstance(context).selectEntities(getItem(),getUser()));
			return SUCCESS;
		}catch(PermissionException e){
			return "permission";
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}	
	}
	@Override
	public String actionErrorOccurred(String result) {
		try{
			selectEntities();			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}
	}
	
}
