package adri.logviewer.agent.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import adri.logviewer.agent.file.LogFile;

import java.util.ArrayList;
import java.util.Arrays;

public class LogFileSearcher {
	
	private String path;
	private LogFileSearcher(){
		InputStream in = null;
		Properties properties = new Properties();
		try {
			File p = new File("app.properties");
			in = new FileInputStream(p);
//			in = this.getClass().getClassLoader().getResourceAsStream("app.properties");
			properties.load(in);
			this.setPath(properties.getProperty("path"));
		} catch(Exception e){
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
	}
	
	private static class Holder
	{		
		private final static LogFileSearcher instance = new LogFileSearcher();
	}
	public static LogFileSearcher getInstance(){
		return Holder.instance;
	}
	public List<LogFile> search(String path) throws Exception{
		List<LogFile> listeLog = new ArrayList<LogFile>();
		File dossier = getFile(path);
		if(!dossier.exists()){
			throw new Exception("Lien erron�. Ce dossier/fichier n'existe pas : " + path);
		}
		else if(dossier.isFile()){
			return Arrays.asList(getFile(dossier, path));
		}
		filterFile(listeLog, dossier.listFiles(), "");
		return listeLog;
	}
	private void filterFile(List<LogFile> listeLog, File[] listeFile, String root) throws IOException{
		for(File f : listeFile){
			if(f.isDirectory()){
				filterFile(listeLog, f.listFiles(), root + f.getName()+"/");
			}
			else{
				listeLog.add(new LogFile(root + f.getName(), f.getName(), f.length()));
			}
		}
	}
	private void getFile(LogFile message, File f) throws Exception {
		FileInputStream fis = null;
		byte[] bytesArray = null;
		try{
			bytesArray = new byte[(int) f.length()];
			fis = new FileInputStream(f);
			fis.read(bytesArray);
			message.setFile(bytesArray);
		}catch(IOException e){
			throw e;
		}finally{
			if(fis != null){
				fis.close();
			}
		}
	}
	private LogFile getFile(File f, String name) throws Exception {
		LogFile log = new LogFile(name, f.getName(), f.length());
		getFile(log, f);
		return log;
	}
	private File getFile(String file){
		if(file != null) return new File(getPath() + File.separator + file);
		return new File(getPath());
	}

	public String getPath() {
		return path;
	}
	protected void setPath(String path){
		this.path = path;
	}
}
