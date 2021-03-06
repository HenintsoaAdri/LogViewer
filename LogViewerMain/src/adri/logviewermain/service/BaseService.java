package adri.logviewermain.service;

import java.util.List;

import adri.logviewermain.dao.HibernateDao;
import adri.logviewermain.exception.PermissionException;
import adri.logviewermain.model.BaseModel;
import adri.logviewermain.model.BaseModelPagination;
import adri.logviewermain.model.Groupe;
import adri.logviewermain.model.Profil;
import adri.logviewermain.model.Utilisateur;

public class BaseService {
	
	private HibernateDao dao;
	
	public HibernateDao getDao() {
		return dao;
	}
	public void setDao(HibernateDao dao) {
		this.dao = dao;
	}
	
	public void findById(BaseModel model) throws Exception{
		try{
			if(model != null && model.getId()>0){
				this.getDao().findById(model);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Informations non retrouv�es. " + e.getMessage());
		}
	}
	public void save(BaseModel model) throws Exception{
		try {
			this.getDao().save(model);			
		} catch (Exception e) {
			throw new Exception("Informations non enregistr�es. " + e.getMessage());
		}
	}
	public void update(BaseModel model) throws Exception{
		try {
			if(model.getId()>0){
				this.getDao().update(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non chang�es. " + e.getMessage());
		}
	}
	public void delete(BaseModel model) throws Exception{
		try {
			if(model.getId()>0){
				boolean isSuper = false;
				switch(model.instance()){
					case "Utilisateur": isSuper = ((Utilisateur)model).isSuperUtilisateur();break;
					case "Groupe": isSuper = ((Groupe)model).isSuperAdmin();break;
					case "Profil": isSuper = ((Profil)model).isSuperProfil();break;
				}
				if(isSuper){
					throw new PermissionException("Acc�s refus�. Donn�es prot�g�es");
				}
				this.getDao().delete(model);
			}
		} catch (Exception e) {
			throw new Exception("Informations non retir�es. " + e.getMessage());			
		}
	}
	public List<? extends BaseModel> findAll(BaseModel model) throws Exception{
		try {
			return this.getDao().findAll(model);
		} catch (Exception e) {
			throw new Exception("Informations non retrouv�es. /n" + e.getMessage());
		}
	}
	public void findAll(BaseModelPagination pagination)throws Exception{
		try {
			this.getDao().findAll(pagination);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Informations non retrouv�es. \n" + e.getMessage());
		}
	}
	public List<? extends BaseModel> findAllByBaseModel(Class<? extends BaseModel> many, BaseModel one) throws Exception{
		try {
			return this.getDao().findAllByBaseModel(many, one);
		} catch (Exception e) {
			throw new Exception("Informations non retrouv�es. /n" + e.getMessage());
		}
	}
	public void findAllByBaseModel(BaseModelPagination pagination, BaseModel one) throws Exception{
		try {
			this.getDao().findAllByBaseModel(pagination, one);
		} catch (Exception e) {
			throw new Exception("Informations non retrouv�es. /n" + e.getMessage());
		}
	}
}
