package adri.logviewermain.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewermain.model.BaseModel;
import adri.logviewermain.model.BaseModelPagination;
import adri.logviewermain.model.Groupe;

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
	public List<? extends BaseModel> getListeGroupe(Groupe groupe, Class<? extends BaseModel> item) throws Exception{
		return this.getService().findAllByBaseModel(item, groupe);
	}
	public BaseModelPagination getListeGroupe(Groupe groupe, Class<? extends BaseModel> item, int page) throws Exception{
		BaseModelPagination pagination = new BaseModelPagination(item, 10, page);
		this.getService().findAllByBaseModel(pagination, groupe);
		return pagination;
	}
}
