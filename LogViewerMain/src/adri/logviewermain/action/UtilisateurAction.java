package adri.logviewermain.action;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewermain.model.Utilisateur;
import adri.logviewermain.service.UtilisateurService;

public class UtilisateurAction extends BaseAction{
	
	private String email, password;
	
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
			getUser().setEmail(getEmail());
			service.editUtilisateur(getUser(), getPassword());
			return SUCCESS;
		} catch (Exception e) {
			setException(e);
			return ERROR;
		}finally{
			context.close();
		}
	}
	public String getEmail() {
		return email;
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
