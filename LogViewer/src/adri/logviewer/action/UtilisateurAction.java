package adri.logviewer.action;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewer.model.PermissionType;
import adri.logviewer.model.Utilisateur;
import adri.logviewer.service.UtilisateurService;

public class UtilisateurAction extends BaseAction{
	
	private String email, password;
	
	@Override
	public String start() {

		ConfigurableApplicationContext context = null;
		try{
			if(getUser() != null){
				context =  new ClassPathXmlApplicationContext("list-beans.xml");
				setPagination(UtilisateurService.getInstance(context).getDetails(getUser(), getPage(), getNbItem()));
				return SUCCESS;
			}
			return LOGIN;
		}catch(Exception e){
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String login() throws Exception{
		
		ConfigurableApplicationContext context = null;
		try{
			if(getSession().containsKey("user")){
				return SUCCESS;
			}
			context =  new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService service = (UtilisateurService)context.getBean("utilisateurService");
			Utilisateur u = service.login(getEmail(), getPassword());
			getSession().put("user", u);
			if(u.isAllowed(PermissionType.CRUDUTILISATEUR)||u.isSuperUtilisateur()){
				return "timeline";
			}
			return SUCCESS;
		}catch(Exception e){
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
public String reinit() throws Exception{
		
		ConfigurableApplicationContext context = null;
		try{
			if(getSession().containsKey("user")){
				setException(new Exception("Modifier votre mot de passe via l'onglet Mes Informations"));
				return ERROR;
			}
			context =  new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService service = (UtilisateurService)context.getBean("utilisateurService");
			service.reinitPassword(getEmail());
			setException(new Exception("Demande de réinitialisation soumise. Contactez l'administrateur"));
			return SUCCESS;
		}catch(Exception e){
			setException(e);
			return SUCCESS;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String logout(){
		try {
			getSession().invalidate();
			return SUCCESS;
		} catch (Exception e) {
			setException(e);
			return ERROR;
		}
	}
	public String update(){
		
		ConfigurableApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService service = (UtilisateurService)context.getBean("utilisateurService");
			service.editUtilisateur(getUser());
			return SUCCESS;
		} catch (Exception e) {
			setException(e);
			return ERROR;
		}finally{
			context.close();
		}
	}
	public String getEmail() {
		if(email != null)return email;
		return "";
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
