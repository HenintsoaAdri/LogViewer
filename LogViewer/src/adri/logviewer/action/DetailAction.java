package adri.logviewer.action;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewer.model.BaseModel;
import adri.logviewer.service.UtilisateurService;

public class DetailAction extends BaseAction{
	private String detail;
	
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
	
	

	public String getDetail() {
		return detail;
	}
	private Class<? extends BaseModel> getDetailClass() throws Exception {
		return (Class<? extends BaseModel>)Class.forName(getDetail());
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public boolean acceptableParameterName(String arg0) {
		return super.acceptableParameterName(arg0);
	}
	
}
