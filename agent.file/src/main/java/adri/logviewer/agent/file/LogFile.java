package adri.logviewer.agent.file;

import java.io.Serializable;

public class LogFile implements Serializable{
	private String tempName;
	private String filename;
	private String distantName;
	private long length;
	private byte[] file;
		
	public LogFile(String filename, byte[] file) {
		setFilename(filename);
		setFile(file);
	}
	public LogFile(String distantName, String filename, long length) {
		setDistantName(distantName);
		setFilename(filename);
		setLength(length);
	}
	
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getName(){
		return getFilename().substring(0, getFilename().indexOf(".log"));
	}
	
	public String getDistantName() {
		return distantName;
	}
	public void setDistantName(String distantName) {
		this.distantName = distantName;
	}
	
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public float getMBLenght(){
		return getLength() / (1024*1024F);
	}
	
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("LogFile [name = ");
		return builder.append(filename).append("]").toString();
	}
}