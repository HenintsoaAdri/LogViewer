package adri.logviewer.dao;

import java.util.Base64;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import adri.logviewer.model.BaseModel;
import adri.logviewer.model.BaseModelPagination;
import adri.logviewer.model.Utilisateur;

public class InitiateDao {
	private SessionFactory sessionFactory;
    
    public InitiateDao(){
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void init() throws Exception{
        Session session = null;
        Transaction tr = null;
        try{
            session = getSessionFactory().openSession();
            BaseModelPagination page = new BaseModelPagination(Utilisateur.class, 30, 0);
	        page.setTotalResult(session.createQuery("select count(distinct id) from "+ Utilisateur.class.getName(), Long.class).uniqueResult());

	        for(int i =0; i<page.getNombrePage(); i++){
	        	page.setPage(i);
	        	try {
		        	tr = session.beginTransaction();
		        	page.setListe(session.createQuery("FROM "+Utilisateur.class.getName(), Utilisateur.class).list());
		        	for (BaseModel j : page.getListe()) {
						Utilisateur u = (Utilisateur)j;
						try{
							Base64.getDecoder().decode(u.getPassword());
						}catch(Exception e){
							u.validate();
						}
						session.update(u);
					}
			        tr.commit();
				} catch (Exception e) {
					System.err.println("Utilisateurs non modifi�s :" + page.getFirstResult() + " � " + page.getLastResult() + "�me r�sultat");
					System.err.println("Cause :" + e.getMessage());
				}
	        }
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session != null)
                session.close();
        }	
    }
    public static void main(String[] args) {
    	InitiateDao dao = null;
    	try {
    		System.out.println("D�but de Mise � jour");
    		dao = new InitiateDao();
    		dao.setSessionFactory(new Configuration().configure().buildSessionFactory());
    		dao.init();
    		System.out.println("Mise � jour faite");
    	}catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
	}
}
