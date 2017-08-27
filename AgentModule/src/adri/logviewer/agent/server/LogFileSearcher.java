package adri.logviewer.agent.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import adri.logviewer.agent.file.LogFile;

import java.util.ArrayList;
import java.util.Arrays;

public class LogFileSearcher {
	
	private LogFileSearcher(){}
	
	private static class Holder
	{		
		private final static LogFileSearcher instance = new LogFileSearcher();
	}
	public static LogFileSearcher getInstance(){
		return Holder.instance;
	}
	
	public List<LogFile> search(String path) throws Exception{
		List<LogFile> listeLog = new ArrayList<LogFile>();
		File dossier = new File(path);
		if(!dossier.exists()){
			throw new Exception("Lien erron�. Ce dossier n'existe pas.");
		}
		else if(dossier.isFile()){
			return Arrays.asList(getFile(dossier));
		}
		filterFile(listeLog, dossier.listFiles());
		return listeLog;
	}
	private void filterFile(List<LogFile> listeLog, File[] listeFile) throws IOException{
		for(File f : listeFile){
			if(f.isDirectory()){
				filterFile(listeLog, f.listFiles());
			}
			if(f.getName().endsWith(".log")){
				listeLog.add(new LogFile(f.getAbsolutePath(), f.getName(), f.length()));
			}
		}
	}
	private void getFile(LogFile message, File f) throws Exception {
		if(!f.getName().endsWith(".log")){
			throw new Exception("Lien erron�. Ce fichier n'est pas un fichier Log");
		}
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
	private LogFile getFile(File f) throws Exception {
		LogFile log = new LogFile(f.getAbsolutePath(), f.getName(), f.length());
		getFile(log, f);
		return log;
	}
}
