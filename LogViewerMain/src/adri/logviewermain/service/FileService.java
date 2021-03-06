package adri.logviewermain.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import adri.logviewer.agent.client.MinaClient;
import adri.logviewer.agent.file.LogFile;
import adri.logviewermain.model.Agent;

public class FileService {
	String filePath = "Fichiers/";
	public List<LogFile> connect(Agent agent) throws Exception{
		MinaClient client = null;
		try{
			client = new MinaClient();
			return (List<LogFile>)client.connect(agent.getAdresse(), agent.getPort(), agent.getRepertoire());
		}finally{
			if(client != null){
				client.dispose();
			}
		}
	}
	public LogFile openFile(Agent agent, String file) throws Exception{
		MinaClient client = null;
		try{
			client = new MinaClient();
			List<LogFile> result = (List<LogFile>)client.connect(agent.getAdresse(), agent.getPort(), file);
			if(result.size() == 1){
				return result.get(0);
			}
			throw new Exception("Fichier introuvable");
		}finally{
			if(client != null){
				client.dispose();
			}
		}
	}
	public LogFile openFile(Agent agent, LogFile file) throws Exception{
		return openFile(agent, file.getDistantName());
	}
	public void saveFile(Agent agent, LogFile logfile, boolean force) throws Exception{
		File temp = null;
		File save = null;
		File path = null;
		try {
			path = new File(filePath + File.separator + agent.getPathName());
			if(!path.exists()){
				path.mkdirs();
			}
			temp = new File(logfile.getFilename());
			if(!temp.exists()){
				throw new Exception("Ce fichier n'est plus en cache ou a �t� d�plac�");
			}
			save = new File(path.getAbsolutePath() + File.separator + logfile.getFilename());
			if(!temp.renameTo(save)){
				throw new Exception("Le fichier n'a pas pu etre sauvegard�");
			}
			logfile.setTempName(save.getAbsolutePath());
			temp.delete();
		} catch (Exception e) {
			throw e;	
		}
	}
	public void downloadFile(LogFile logfile, OutputStream out)throws Exception{
		FileInputStream read = null;
		byte[] buffer = new byte[1024];
		try {
			int reader;
			read = new FileInputStream(new File(logfile.getTempName()));
			while((reader = read.read(buffer)) != -1){
				out.write(reader);	
			}
		} catch (Exception e) {
			System.out.println("T�l�chargement impossible");
		} finally{
			if(read != null){
				read.close();
			}
		}
	}
}
