package adri.logviewer.action;

import java.lang.reflect.Method;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewer.exception.PermissionException;
import adri.logviewer.model.BaseModel;
import adri.logviewer.model.BaseModelPagination;
import adri.logviewer.model.StatActivite;
import adri.logviewer.service.UtilisateurService;

public class DetailAction extends BaseAction{
	private String detail;
	private StatActivite stat;
	
	public String getDetails(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(UtilisateurService.getInstance(context).findAllByBaseModel(getItem(), getDetailClass(), getPage(), getNbItem()));
			return SUCCESS;
		}catch(NullPointerException e){
			e.printStackTrace();
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally {
			if(context != null){
				context.close();
			}
		}
	}
	public String getStatPie(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(UtilisateurService.getInstance(context).getStat(getItem(), getNbItem(), getUser()));
			setDetail(getJSONPie(getPagination()));
			return SUCCESS;
		}catch(NullPointerException e){
			e.printStackTrace();
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally {
			if(context != null){
				context.close();
			}
		}
	}
	public String getStatActivite(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			if(getStat() == null) setStat(new StatActivite());
			UtilisateurService.getInstance(context).getStatActivite(getStat(), getUser());
			return SUCCESS;
		}catch(PermissionException e){
			setException(e);
			return "permission";
		}catch(NullPointerException e){
			e.printStackTrace();
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally {
			if(context != null){
				context.close();
			}
		}
	}
	public String getReinitUtilisateur(){
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPagination(UtilisateurService.getInstance(context).findAllReinitUtilisateur(getPage(), getNbItem()));
			return SUCCESS;
		}catch(NullPointerException e){
			e.printStackTrace();
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally {
			if(context != null){
				context.close();
			}
		}
	}
	private String getJSONPie(BaseModelPagination pagination){
		StringBuilder builder = new StringBuilder();
		try{
			if(pagination.getListe() == null || pagination.getListe().isEmpty()){
				builder.append("Aucune statistique ")
				.append(pagination.getClasse().newInstance().instance())
				.append(" retrouvée");
				return builder.toString();
			}
			Method getNombre = pagination.getClasse().getMethod("getNombre");
			
			builder.append('[');			
			String virgule = "";
			for(BaseModel b : pagination.getListe()){
				builder.append(virgule);
				builder.append("{y:").append(getNombre.invoke(b)).append(',');
				builder.append("indexLabel:'").append(b.instance()).append(" #").append(b.getId()).append("'}");
				virgule = ",";
			}
			builder.append(']');
			return builder.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public String getDetail() {
		return detail;
	}
	private Class<? extends BaseModel> getDetailClass() throws Exception {
		return (Class<? extends BaseModel>)Class.forName(getDetail());
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public StatActivite getStat() {
		return stat;
	}
	public void setStat(StatActivite stat) {
		this.stat = stat;
	}
	@Override
	public boolean acceptableParameterName(String arg0) {
		return super.acceptableParameterName(arg0);
	}
	
}
