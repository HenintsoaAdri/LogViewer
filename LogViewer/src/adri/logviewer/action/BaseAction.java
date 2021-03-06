package adri.logviewer.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

import adri.logviewer.model.BaseModel;
import adri.logviewer.model.BaseModelPagination;
import adri.logviewer.model.Utilisateur;

public class BaseAction extends ActionSupport implements SessionAware, ParameterNameAware{
	private Exception exception;
	private SessionMap<String, Object> session;
	private Utilisateur user;
	private int page;
	private int nbItem = 5;
	private	String classe;
	private BaseModel item;
	private BaseModelPagination pagination;
	private List<? extends BaseModel> liste;

	public String start() {
		try{
			if(session.containsKey("user")) return SUCCESS;
			else return ERROR;
		}catch(Exception e){
			setException(e);
			return ERROR;
		}
	}
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public void setSession(Map<String, Object> session) {
		setSession((SessionMap<String, Object>) session);	
	}
	public SessionMap<String, Object> getSession() {
		return session;
	}
	public void setSession(SessionMap<String, Object> session) {
		this.session = session;
		if(this.session.containsKey("user")){
			setUser((Utilisateur) this.session.get("user"));
		}
	}
	
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur utilisateur) {
		this.user = utilisateur;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = Math.max(0, page-1);
	}
	public int getNbItem() {
		return nbItem;
	}
	public void setNbItem(int nbItem) {
		this.nbItem = nbItem;
	}
	@Override
	public boolean acceptableParameterName(String arg0) {
		try {
			if(getClasse() != null){
				Class<? extends BaseModel> classe = (Class<? extends BaseModel>) Class.forName(getClasse());
				setItem(classe.newInstance());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public BaseModel getItem() {
		return item;
	}
	public void setItem(BaseModel item) {
		this.item = item;
	}
	public BaseModelPagination getPagination() {
		return pagination;
	}
	public void setPagination(BaseModelPagination pagination) {
		this.pagination = pagination;
	}
	public List<? extends BaseModel> getListe() {
		return liste;
	}
	public void setListe(List<? extends BaseModel> list) {
		this.liste = list;
	}
	
}
