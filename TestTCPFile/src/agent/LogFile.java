package agent;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LogFile implements Serializable{
	private String fileName = "";
	private File file;
	private List<LogFile> child;
	public LogFile(String filename, File File) {
		setFileName(filename);
		setFile(File);
	}
	public LogFile(File File) {
		setFile(File);
	}
	
	public LogFile() {
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File File) {
		this.file = File;
	}
	public List<LogFile> getChild() {
		return child;
	}
	public void setChild(List<LogFile> child) {
		this.child = child;
	}
	public void addChild(LogFile child){
		try {
			getChild().add(child);
		} catch (NullPointerException e) {
			setChild(new ArrayList<LogFile>());
			getChild().add(child);
		}
	}
	public String getName(){
		return file.getName().replace(".log", "");
	}
	
	public float getMBLenght(){
		return file.length() / (1024*1024F);
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("LogFile [name = ");
		 builder.append(getFileName());
		 if(getChild() != null){
			 for(LogFile f : getChild()){
				 builder.append("\n\t-" + f.toString()+",");
			 }
		 }
		 builder.append("]");
		 return builder.toString();
	}
}
