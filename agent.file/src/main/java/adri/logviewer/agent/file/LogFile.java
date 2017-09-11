package adri.logviewer.agent.file;

import java.io.File;
import java.io.Serializable;

public class LogFile implements Serializable{
	private String fileName = "";
	private File tempFile;
	private File distantFile;
		
	public LogFile(String filename, File distantFile) {
		setFileName(filename);
		setDistantFile(distantFile);
	}
	public LogFile(File tempFile) {
		setTempFile(tempFile);
	}
	
	public LogFile() {
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getTempFile() {
		return tempFile;
	}
	public void setTempFile(File tempFile) {
		this.tempFile = tempFile;
	}
	public File getDistantFile() {
		return distantFile;
	}
	public void setDistantFile(File distantFile) {
		this.distantFile = distantFile;
	}
	public String getName(){
		return distantFile.getName().replace(".log", "");
	}
	
	public float getMBLenght(){
		return distantFile.length() / (1024*1024F);
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("LogFile [name = ");
		return builder.append(getFileName()).append("]").toString();
	}
}
