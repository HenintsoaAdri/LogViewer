package agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class LogFileSearcher {
	
	private Path path;
	private LogFileSearcher(){
		InputStream in = null;
		Properties properties = new Properties();
		try {
			File p = new File("app.properties");
			in = new FileInputStream(p);
		    properties.load(in);
			setPath(properties.getProperty("path"));
		}catch(Exception e){
			e.printStackTrace();
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
	public LogFile search(File f) throws Exception{
		LogFile listeLog = new LogFile(getPath().relativize(f.toPath()).toString(), f);
		filterFile(listeLog, f.listFiles());
		return listeLog;
	}
	private void filterFile(LogFile file, File[] listeFile) throws IOException{
		file.setFileName(file.getFileName()+File.separator);
		for(File f : listeFile){
			LogFile log = new LogFile(file.getFileName()+f.getName(), f);
			if(f.isDirectory()){
				filterFile(log, f.listFiles());
			}
			file.addChild(log);
		}
	}
	
	public File getFile(String file) throws Exception{
		File f = getPath().resolve(file.replaceFirst("^\\\\(.*)", "$1")).toFile();
		if(!f.exists()){
	    	throw new Exception("Lien erron�. Ce dossier/fichier n'existe pas : " + f.getAbsolutePath());
		}
		return f;
	}

	public Path getPath() {
		return path;
	}
	protected void setPath(Path path){
		this.path = path;
	}
	protected void setPath(String path) {
		setPath(Paths.get(path));
	}
}