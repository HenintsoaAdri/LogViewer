package adri.logviewermain.action;

import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.interceptor.ValidationErrorAware;

import adri.logviewermain.exception.PermissionException;
import adri.logviewermain.model.Agent;
import adri.logviewermain.model.Groupe;
import adri.logviewermain.model.Permission;
import adri.logviewermain.service.UtilisateurService;

public class CrudAction extends BaseAction implements ValidationErrorAware{
	private Set<Permission> listePermission;
	public String save() throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			if(getItem() instanceof Agent){
				((Agent)getItem()).setCreateur(getUser());
			}
			UtilisateurService.getInstance(context).crud(getItem(), "save", getUser());
			return SUCCESS;
		}catch(PermissionException e){
			return "permission";
		}catch(NullPointerException e){
			e.printStackTrace();
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			if(!(getItem() instanceof Groupe) && context != null){
				setListe(UtilisateurService.getInstance(context).selectGroupe(getItem().getClass(), getUser()));
			}
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
			return SUCCESS;
		}catch(PermissionException e){
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
	public String delete(){
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;
			
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).crud(getItem(), "findById", getUser());
			UtilisateurService.getInstance(context).crud(getItem(), "delete", getUser());
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

	public String find(){
		ConfigurableApplicationContext context = null;
		try{
			if(getItem() == null || getItem().getId() <= 0) return NONE;		
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setPagination(UtilisateurService.getInstance(context).getDetails(getItem(), getPage()));
			return SUCCESS;
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

	public String findAllPaginate()throws Exception{
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

	public String findAllByBaseModel()throws Exception{
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(UtilisateurService.getInstance(context).findAll(getItem().getClass(), 10, getPage(), getUser()));
			return SUCCESS;
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
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			if(!(getItem() instanceof Groupe)){
				setListe(UtilisateurService.getInstance(context).selectGroupe(getItem().getClass(), getUser()));
			}
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
	@Override
	public String actionErrorOccurred(String result) {

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			if(!(getItem() instanceof Groupe)){
				setListe(UtilisateurService.getInstance(context).selectGroupe(getItem().getClass(),getUser()));
			}
			return result;
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
	public Set<Permission> getListePermission() {
		return listePermission;
	}
	public void setListePermission(Set<Permission> listePermission) {
		this.listePermission = listePermission;
	}
	
}
