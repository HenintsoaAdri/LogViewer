package adri.logviewer.filemanager;

import java.util.List;

public class SampleLog {
	private List<String> level;
	private String search;
	public SampleLog() {
		super();
	}

	public List<String> getLevel() {
		return level;
	}
	public void setLevel(List<String> level) {
		this.level = level;
	}
	public boolean levelContains(String level){
		if(getLevel() == null)return true; 
		for(String s : getLevel()){
			if(s.equalsIgnoreCase(level)) return true;
		}
		return false;
	}
	
	public boolean is(Log item) {
		if(item == null) return false;
		else if(getLevel() == null && getSearch() == null) return true;
		else if(!levelContains(item.getPriority())) return false;
		else if(item.getLigne() != null && item.getLigne().toLowerCase().contains(getSearch().toLowerCase())) return true; 
		else if(item.getDetails()!= null && item.getDetails().toLowerCase().contains(getSearch().toLowerCase())) return true; 
		return false;
	}

	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
}
