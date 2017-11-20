package adri.logviewer.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseModelPagination {
	Set<? extends BaseModel> liste;
	Class<? extends BaseModel> classe;
	int maxResult;
	int page;
	long totalResult;
	
	public BaseModelPagination(Class<? extends BaseModel> model, int maxResult, int page){
		setClasse(model);
		setMaxResult(maxResult);
		setPage(page);
	}
	
	public Set<? extends BaseModel> getListe() {
		return liste;
	}
	public void setListe(Set<? extends BaseModel> liste) {
		this.liste = liste;
	}
	public void setListe(List<? extends BaseModel> liste) {
		this.liste = new HashSet<>(liste);
	}
	
	public Class<? extends BaseModel> getClasse() {
		return classe;
	}
	public void setClasse(Class<? extends BaseModel> classe) {
		this.classe = classe;
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(long total) {
		this.totalResult = total;
		if(getPage() > getNombrePage()){
			setPage(0);
		}
	}
	
	public int getNombrePage(){
		return (int) Math.ceil((double)getTotalResult()/getMaxResult());
	}
	public int getFirstResult(){
		return getPage()*getMaxResult();
	}

	public int getLastResult() {
		return getFirstResult() + getMaxResult();
	}
	public boolean isWrongPage() {		
		return getPage()<0 || getFirstResult() > getTotalResult();
	}
}
