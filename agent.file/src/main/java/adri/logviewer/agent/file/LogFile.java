package adri.logviewer.agent.file;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LogFile implements Serializable{
	private String fileName = "";
	private File file;
	private List<LogFile> child;
	public LogFile(String filename, File file) {
		setFileName(filename);
		setFile(file);
	}
	public LogFile(File file) {
		setFile(file);
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
	public void setFile(File file) {
		this.file = file;
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
		return file.getName().replaceFirst("\\.(.*)$", "");
	}
	
	public float getMBLenght(){
		return file.length() / (1024*1024F);
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("LogFile [name = ");
		return builder.append(getFileName()).append("]").toString();
	}
}