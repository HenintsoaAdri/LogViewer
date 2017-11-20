package adri.logviewer.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class FilePaginate {
	private int totalLine;
	private int beginLine;
	private int maxLine;
	private String line;
	private File file;
	private String search;
	
	public FilePaginate(File file, int maxLine, int beginLine, String search) throws Exception{
		setFile(file);
		setMaxLine(maxLine);
		setBeginLine(beginLine);
		setSearch(search);
		paginate();
	}
	
	public void paginate() throws Exception{
		FileInputStream stream = null;
		LineNumberReader reader = null;
		try{
			stream = new FileInputStream(getFile());
			reader = new LineNumberReader(new InputStreamReader(stream));
			String line = null;
			StringBuilder builder = new StringBuilder();
			int total = 0;
			int i = 0 ;
			while((line = reader.readLine())!= null){
				line = line.replace("&", "&amp;");
				line = line.replace("<", "&lt;");
				line = line.replace(">", "&gt;");
				if(line.toLowerCase().contains(getSearch().toLowerCase()) && reader.getLineNumber() >= getBeginLine() && i < getMaxLine()){
					builder.append(line).append("\n");
					i++;
				}
				total ++;
			}
			setLine(builder.toString());
			setTotalLine(total);
		}catch(Exception e){
			throw e;
		}finally{
			try{
				if(reader != null)reader.close();
				if(stream != null)stream.close();
			}catch(Exception e){
				throw new Exception("Une erreur est survenue, la lecture n'a pu se faire");
			}
		}
	}
	
	public int getTotalLine() {
		return totalLine;
	}
	public void setTotalLine(int totalLine) {
		this.totalLine = totalLine;
	}
	public int getBeginLine() {
		return beginLine;
	}
	public void setBeginLine(int beginLine) {
		this.beginLine = beginLine * getMaxLine();
	}
	public int getMaxLine() {
		return maxLine;
	}
	public void setMaxLine(int maxLine) {
		this.maxLine = maxLine;
	}
	
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getNumberPage(){
		return (int) Math.ceil(getTotalLine()/getMaxLine());
	}
	public long getFileLength(){
		return getFile().length()/(1024*1024);
	}
}
