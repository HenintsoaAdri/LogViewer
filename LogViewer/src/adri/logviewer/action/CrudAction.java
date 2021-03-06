package adri.logviewer.action;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.interceptor.ValidationErrorAware;

import adri.logviewer.exception.PermissionException;
import adri.logviewer.model.Agent;
import adri.logviewer.model.BaseModel;
import adri.logviewer.service.UtilisateurService;

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
			throw e;
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
	public String update() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).crud(getItem(), "update", getUser());
			return SUCCESS;
		}catch(PermissionException e){
			e.printStackTrace();
			throw e;
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
	public String delete() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;
			
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).crud(getItem(), "findById", getUser());
			UtilisateurService.getInstance(context).crud(getItem(), "delete", getUser());
			return SUCCESS;
		}catch(PermissionException e){
			e.printStackTrace();
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

	public String find() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;		
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setPagination(UtilisateurService.getInstance(context).getDetails(getItem(), getPage(), getNbItem()));
			return SUCCESS;
		}catch(PermissionException e){
			e.printStackTrace();
			throw e;
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

	public String findAllPaginate() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(UtilisateurService.getInstance(context).findAll(getItem().getClass(), getNbItem(), getPage(), getUser()));
			return SUCCESS;
		}catch(PermissionException e){
			throw e;
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

	public String edit() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;
			if(getUser().equals(getItem())) return "user";
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).edit(getItem(), getUser());
			setListe((List<? extends BaseModel>)UtilisateurService.getInstance(context).selectEntities(getItem(),getUser()));
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
	public String selectEntities() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setListe((List<? extends BaseModel>)UtilisateurService.getInstance(context).selectEntities(getItem(),getUser()));
			return SUCCESS;
		}catch(PermissionException e){
			throw e;
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

	public String search() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(UtilisateurService.getInstance(context).search(getItem(), getNbItem(), getPage(), getUser()));
			return SUCCESS;
		}catch(PermissionException e){
			throw e;
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
