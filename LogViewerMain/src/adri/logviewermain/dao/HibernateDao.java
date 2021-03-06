package adri.logviewermain.dao;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import adri.logviewermain.exception.PermissionException;
import adri.logviewermain.model.BaseModel;
import adri.logviewermain.model.BaseModelPagination;
import adri.logviewermain.model.Permission;
import adri.logviewermain.model.PermissionType;
import adri.logviewermain.model.Profil;
import adri.logviewermain.model.Utilisateur;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class HibernateDao {
    private SessionFactory sessionFactory;
    
    public HibernateDao(){
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(BaseModel model) throws Exception{
        Session session = null;
        Transaction tr = null;
        try{
            session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            session.saveOrUpdate(model);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session != null)
                session.close();
        }
    }
    public void delete(BaseModel model) throws Exception{
		Session session = null;
        Transaction tr = null;
	    try{
	        session = getSessionFactory().openSession();
            tr = session.beginTransaction();
	        session.delete(model);
            tr.commit();
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
	    }
	}
	public void update(BaseModel model) throws Exception{
		Session session = null;
        Transaction tr = null;
	    try{
	        session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            if(model instanceof Profil){
            	List<? extends BaseModel> liste = findAllByBaseModel(Permission.class, model);
            	for(BaseModel p : liste){
            		delete(p);
            	}
            }
	        session.update(model);
            tr.commit();
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
	    }
	}
	public void findById(BaseModel model) throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.load(model,model.getId());
        }catch(ObjectNotFoundException e){
        	throw new Exception("Informations introuvables");
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    public List<? extends BaseModel> findAll(BaseModel model)  throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<? extends BaseModel> criteria = builder.createQuery(model.getClass());
            criteria.from(model.getClass());
            return session.createQuery(criteria).getResultList();
        }catch (Exception ex){
        	ex.printStackTrace();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    public void findAll(BaseModelPagination pagination) {
		Session session = null;
	    try{
	    	session = getSessionFactory().openSession();
	    	
	    	String fromClause = "FROM " + pagination.getClasse().getName();
	    	
	    	pagination.setListe(session.createQuery(fromClause , pagination.getClasse())
	        		.setFirstResult(pagination.getFirstResult())
	        		.setMaxResults(pagination.getMaxResult())
	        		.list());
	    	long total = (long)session.createQuery("SELECT COUNT(id) " + fromClause)
	    				.uniqueResult();
	    	pagination.setTotalResult(total);
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
	    }
	}

    public List<? extends BaseModel> findAllByBaseModel(Class<? extends BaseModel> many, BaseModel one)throws Exception{
		Session session = null;
	    try{
	    	session = getSessionFactory().openSession();
	    	String fromClause = "FROM " + many.getName() + " WHERE id" + one.getClass().getSimpleName() + " = :one";
	    	
	    	return session.createQuery(fromClause, many)
            		.setParameter("one", one.getId())
	        		.list();
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
	    }
    }
    public void findAllByBaseModel(BaseModelPagination pagination, BaseModel one)throws Exception{
		Session session = null;
	    try{
	    	session = getSessionFactory().openSession();
	    	
	    	String fromClause = "FROM " + pagination.getClasse().getName() + " WHERE id" + one.getClass().getSimpleName() + " = :one";
	    	
	    	pagination.setListe(session.createQuery(fromClause , pagination.getClasse())
            		.setParameter("one", one.getId())
	        		.setFirstResult(pagination.getFirstResult())
	        		.setMaxResults(pagination.getMaxResult())
	        		.list());
	    	long total = (long)session.createQuery("SELECT COUNT(id) " + fromClause)
            			.setParameter("one", one.getId())
	    				.uniqueResult();
	    	pagination.setTotalResult(total);
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
	    }
    }
    public List<? extends BaseModel> search(BaseModel model) throws Exception{
    	Session session = null;
        try{
            session = getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<? extends BaseModel> criteria = builder.createQuery(model.getClass());
            criteria.from(model.getClass());
            return session.createQuery(criteria).getResultList();
        }catch (Exception ex){
        	ex.printStackTrace();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    public Utilisateur loginUtilisateur(String email, String password)  throws Exception{
        Session session = null;
        Transaction tr = null;
        try{
            session = getSessionFactory().openSession();
            tr = session.beginTransaction();
            Utilisateur user = session.createQuery("FROM Utilisateur "
            		+ "WHERE emailutilisateur = :email AND password = :password", Utilisateur.class)
            		.setParameter("email", email)
            		.setParameter("password", password).uniqueResult();
            if(user == null) throw new Exception("Connexion �chou�e, vos identifiants sont incorrects");
            else if(!user.getProfil().isAllowed(PermissionType.CONNEXION))throw new PermissionException("Vous ne pouvez pas vous connecter. Contactez l'administrateur");
            user.loggedIn();
            session.update(user);
            tr.commit();
            return user;
        }catch (Exception ex){
            if(tr!=null)
            	tr.rollback();
            ex.printStackTrace();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
}
