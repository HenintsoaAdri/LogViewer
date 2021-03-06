package adri.logviewermain.action;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewermain.exception.PermissionException;
import adri.logviewermain.model.Groupe;
import adri.logviewermain.service.GroupeService;
import adri.logviewermain.service.UtilisateurService;

public class GroupeAction extends BaseAction {
	private Groupe groupe;
	
	public String findByGroupe(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setListe(GroupeService.getInstance(context).getListeGroupe(getGroupe(), getItem().getClass()));
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
	public String findByGroupePaginate(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(GroupeService.getInstance(context).getListeGroupe(getGroupe(), getItem().getClass(), getPage()));
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
	
	public String selectGroupe(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			if(!(getItem() instanceof Groupe)){
				setListe(UtilisateurService.getInstance(context).selectGroupe(getItem().getClass(),getUser()));
			}
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
	
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
}
