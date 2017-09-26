package adri.logviewer.dao;

import org.hibernate.Filter;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import adri.logviewer.exception.PermissionException;
import adri.logviewer.model.BaseModel;
import adri.logviewer.model.BaseModelPagination;
import adri.logviewer.model.Permission;
import adri.logviewer.model.PermissionType;
import adri.logviewer.model.Profil;
import adri.logviewer.model.Utilisateur;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
            	session.createQuery("DELETE FROM Permission WHERE idprofil = :id")
            			.setParameter("id", model.getId()).executeUpdate();
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
	public void findById(BaseModel model, String... listeField) throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.load(model,model.getId());
            Method getter = null;
            if(listeField != null){
                for(String f : listeField){
                    getter = model.getClass().getMethod("get" + f);
                    System.out.println(getter.invoke(model));
                }
            }
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
	    	long total = session.createQuery("SELECT COUNT(id) " + fromClause, Long.class)
	    				.uniqueResult();
	    	pagination.setTotalResult(total);
	    	pagination.setListe(session.createQuery(fromClause , pagination.getClasse())
	        		.setFirstResult(pagination.getFirstResult())
	        		.setMaxResults(pagination.getMaxResult())
	        		.list());
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
	    	StringBuilder builder = new StringBuilder("FROM ");
	    	String fromClause = builder.append(many.getName()).append(" WHERE id").append(one.instance()).append(" = :one").toString();
	    	
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

	    	StringBuilder builder = new StringBuilder("FROM ");
	    	String fromClause = builder.append(pagination.getClasse().getName()).append(" WHERE id").append(one.instance()).append(" = :one").toString();

	    	long total = session.createQuery("SELECT COUNT(id) " + fromClause, Long.class)
            			.setParameter("one", one.getId())
	    				.uniqueResult();
	    	pagination.setTotalResult(total);
	    	pagination.setListe(session.createQuery(fromClause , pagination.getClasse())
            		.setParameter("one", one.getId())
	        		.setFirstResult(pagination.getFirstResult())
	        		.setMaxResults(pagination.getMaxResult())
	        		.list());
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
	    }
    }
    public void findAllByBaseModel(BaseModelPagination pagination, BaseModel one, String field)throws Exception{
    	Session session = null;
	    try{
	    	session = getSessionFactory().openSession();
	    	StringBuilder builder = new StringBuilder("FROM ");
	    	String fromClause = builder.append(pagination.getClasse().getSimpleName())
	    					.append(" m JOIN m.")
	    	    			.append(field)
	    	    			.append(" o WHERE o.id = :one")
	    	    			.toString();
	    	pagination.setTotalResult(session.createQuery("SELECT COUNT(m.id) " + fromClause, Long.class)
            			.setParameter("one", one.getId())
	    				.uniqueResult());
	    	pagination.setListe(session.createQuery("SELECT m " + fromClause + " GROUP BY m.id" , pagination.getClasse())
            		.setParameter("one", one.getId())
	        		.setFirstResult(pagination.getFirstResult())
	        		.setMaxResults(pagination.getMaxResult())
	        		.list());
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
        }
    }
	public List<? extends BaseModel> findAllByBaseModel(Class<? extends BaseModel> many, BaseModel one, String field) throws Exception {
		Session session = null;
	    try{
	    	session = getSessionFactory().openSession();
	    	StringBuilder builder = new StringBuilder("FROM ");
	    	String fromClause = builder.append(many.getSimpleName())
	    					.append(" m JOIN m.")
	    	    			.append(field)
	    	    			.append(" o WHERE o.id = :one GROUP BY m.id")
	    	    			.toString();
	    	    	
	    	return session.createQuery("SELECT m " + fromClause , many)
            		.setParameter("one", one.getId())
	        		.list();
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
        }
	}

    public void findAllByBaseModel(BaseModelPagination pagination, Collection<? extends BaseModel> one, String field)throws Exception{
    	Session session = null;
	    try{
	    	session = getSessionFactory().openSession();
	    	StringBuilder builder = new StringBuilder("FROM ");
	    	String fromClause = builder.append(pagination.getClasse().getSimpleName())
	    					.append(" m JOIN m.")
	    	    			.append(field)
	    	    			.append(" o WHERE o IN :one")
	    	    			.toString();
	    	pagination.setTotalResult(session.createQuery("SELECT COUNT(DISTINCT m.id)" + fromClause, Long.class)
            		.setParameterList("one", one)
            		.uniqueResult());
	    	pagination.setListe(session.createQuery("SELECT m " + fromClause + " GROUP BY m.id", pagination.getClasse())
	    			.setParameterList("one", one)
	        		.setFirstResult(pagination.getFirstResult())
	        		.setMaxResults(pagination.getMaxResult())
	        		.list());
	    }catch (Exception ex){
	        throw ex;
	    }finally {
	        if(session!=null)
	            session.close();
        }
    }
	public List<? extends BaseModel> findAllByBaseModel(Class<? extends BaseModel> many, Collection<? extends BaseModel> one, String field)throws Exception {
		Session session = null;
	    try{
	    	session = getSessionFactory().openSession();

	    	StringBuilder builder = new StringBuilder("FROM ");
	    	String fromClause = builder.append(many.getSimpleName())
	    					.append(" m JOIN m.")
	    	    			.append(field)
	    	    			.append(" o WHERE o IN :one")
	    	    			.toString();
	    	
	    	return session.createQuery("SELECT m " + fromClause , many)
            		.setParameterList("one", one)
	        		.list();
	    }catch (Exception e){
	        throw e;
	    }finally {
	        if(session!=null)
	            session.close();
        }
	}
    public List<? extends BaseModel> search(BaseModel model) throws Exception{
    	Session session = null;
        try{
            session = getSessionFactory().openSession();
            Filter filter = session.enableFilter("search"+model.instance());
            Object obj = null;
            Set<String> param = filter.getFilterDefinition().getParameterNames();
            for(String f : param){
            	obj = new PropertyDescriptor(f, model.getClass()).getReadMethod().invoke(model);
            	if(obj != null){
            		if(obj instanceof Collection<?>){
            			filter.setParameterList(f, (Collection<?>) obj);
            			continue;
            		}
        			String wild = "%";
        			obj = wild + obj.toString().toLowerCase() + wild;
            		filter.setParameter(f, obj);
            	}
            }
            return session.createQuery("FROM "+model.getClass().getSimpleName(), model.getClass()).list();
        }catch (Exception ex){
        	ex.printStackTrace();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
	public void search(BaseModelPagination pagination, BaseModel model) throws Exception {
		Session session = null;
        try{
        	String fromClause = "FROM " + model.getClass().getSimpleName();
            session = getSessionFactory().openSession();
            Filter filter = session.enableFilter("search"+model.instance());
            Object obj = null;
            Set<String> param = filter.getFilterDefinition().getParameterNames();
            for(String f : param){
            	obj = new PropertyDescriptor(f, model.getClass()).getReadMethod().invoke(model);
            	if(obj != null){
            		if(obj instanceof Collection<?> && !((Collection<?>)obj).isEmpty()){
                		filter.setParameterList(f, (Collection<?>) obj);
            			continue;
            		}
        			String wild = "%";
        			obj = wild + obj.toString().toLowerCase().trim() + wild;
            		filter.setParameter(f, obj);
            	}
            }
            pagination.setTotalResult(session.createQuery("SELECT COUNT(id) " + fromClause, Long.class)
            		.uniqueResult());
            pagination.setListe(session.createQuery(fromClause, model.getClass())
            		.setFirstResult(pagination.getFirstResult())
	        		.setMaxResults(pagination.getMaxResult())
	        		.list());
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
            Utilisateur user = session.createQuery("FROM Utilisateur WHERE emailutilisateur = :email AND password = :password"
            										, Utilisateur.class)
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
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
}
