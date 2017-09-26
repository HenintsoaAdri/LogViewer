package adri.logviewer.agent.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import adri.logviewer.agent.file.LogFile;

public class LogFileSearcher {
	
	private String path;
	private LogFileSearcher(){
		InputStream in = null;
		Properties properties = new Properties();
		try {
			File p = new File("app.properties");
			in = new FileInputStream(p);
		    properties.load(in);
			setPath(properties.getProperty("path"));
		}catch(Exception e){
			throw new ExceptionInInitializerError("Erreur d'initialisation");
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					throw new ExceptionInInitializerError("Erreur d'initialisation");
				}
			}
		}
		this.setPath(properties.getProperty("path"));
	}
	
	private static class Holder
	{		
		private final static LogFileSearcher instance = new LogFileSearcher();
	}
	public static LogFileSearcher getInstance(){
		return Holder.instance;
	}
	public LogFile search(File f) throws Exception{
		LogFile listeLog = new LogFile(f.getAbsolutePath().replace(getPath(), "/"), f);
		filterFile(listeLog, f.listFiles());
		return listeLog;
	}
	private void filterFile(LogFile file, File[] listeFile) throws IOException{
		for(File f : listeFile){
			if(f.isDirectory()){
				filterFile(file, f.listFiles());
			}
			else{
				file.addChild(new LogFile(f.getName(), f));
			}
		}
	}
	
	public File getFile(String file) throws Exception{
		File f = new File(getPath() + File.separator + file);
		if(!f.exists()){
	    	throw new Exception("Lien erron�. Ce dossier/fichier n'existe pas : " + file);
		}
		return f;
	}

	public String getPath() {
		return path;
	}
	protected void setPath(String path){
		this.path = path;
	}
}