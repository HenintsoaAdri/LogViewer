package adri.logviewer.service;

import java.util.Collection;
import java.util.List;

import adri.logviewer.dao.HibernateDao;
import adri.logviewer.exception.PermissionException;
import adri.logviewer.model.BaseModel;
import adri.logviewer.model.BaseModelPagination;
import adri.logviewer.model.Timeline;
import adri.logviewer.model.Utilisateur;

public class BaseService {
	
	private HibernateDao dao;
	private Timeline timeline;
	
	public HibernateDao getDao() {
		return dao;
	}
	public void setDao(HibernateDao dao) {
		this.dao = dao;
	}
	public Timeline getTimeline() {
		return timeline;
	}
	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	public void findById(BaseModel model) throws Exception{
		try{
			if(model != null && model.getId()>0){
				this.getDao().findById(model);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. ", e);
		}
	}
	public void findById(BaseModel model, String... field) throws Exception{
		try{
			if(model != null && model.getId()>0){
				this.getDao().findById(model, field);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. ", e);
		}
	}
	public void save(BaseModel model) throws Exception{
		try {
			getTimeline().setDetails(model);			
			this.getDao().save(model, getTimeline());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non enregistrées. ", e);
		}
	}
	public void update(BaseModel model) throws Exception{
		try {
			if(model.getId()>0){
				BaseModel before = model.getClass().newInstance();
				before.setId(model.getId());
				this.getDao().findById(before);
				getTimeline().setDetails(before, model);
				this.getDao().update(model, getTimeline());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non changées. ", e);
		}
	}
	public void delete(BaseModel model) throws Exception{
		try {
			if(model.getId()>0){
				try{
					if(((Utilisateur)model).isSuperUtilisateur()){
						throw new PermissionException("Accès refusé. Données protégées");
					}
				}catch(ClassCastException e){}
				getTimeline().setDetails(model);	
				this.getDao().delete(model, getTimeline());
			}
		} catch (Exception e) {
			throw new Exception("Informations non retirées. ", e);			
		}
	}
	public List<? extends BaseModel> findAll(BaseModel model) throws Exception{
		try {
			return this.getDao().findAll(model);
		} catch (Exception e) {
			throw new Exception("Informations non retrouvées. /n", e);
		}
	}
	public void findAll(BaseModelPagination pagination)throws Exception{
		try {
			this.getDao().findAll(pagination);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
	}
	public List<? extends BaseModel> findAllByBaseModel(Class<? extends BaseModel> many, BaseModel one) throws Exception{
		try {
			return this.getDao().findAllByBaseModel(many, one);
		} catch (Exception e) {
			throw new Exception("Informations non retrouvées. /n", e);
		}
	}
	public void findAllByBaseModel(BaseModelPagination pagination, BaseModel one) throws Exception{
		try {
			this.getDao().findAllByBaseModel(pagination, one);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. /n", e);
		}
	}
	public List<? extends BaseModel> findAllByBaseModel(Class<? extends BaseModel> many, BaseModel one, String field)throws Exception{
		try {
			return this.getDao().findAllByBaseModel(many, one, field);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
	}
	public void findAllByBaseModel(BaseModelPagination pagination, BaseModel one, String field)throws Exception{
		try {
			this.getDao().findAllByBaseModel(pagination, one, field);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
	}
	public void findAllByBaseModel(BaseModelPagination pagination, Collection<? extends BaseModel> one, String field)throws Exception{
		try {
			this.getDao().findAllByBaseModel(pagination, one, field);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
	}
	public List<? extends BaseModel> findAllByBaseModel(Class<? extends BaseModel> many, Collection<? extends BaseModel> one, String field) throws Exception {
		try {
			return this.getDao().findAllByBaseModel(many, one, field);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
	}
	public List<? extends BaseModel> search(BaseModel model) throws Exception {
		try {
			return this.getDao().search(model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
	}
	public void search(BaseModelPagination pagination, BaseModel model) throws Exception {
		try {
			this.getDao().search(pagination, model);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
	}
    public void findAllByCritere(BaseModelPagination pagination, String field, Object value) throws Exception{
    	try {
			this.getDao().findAllByCritere(pagination, field, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouvées. \n", e);
		}
    }
}
