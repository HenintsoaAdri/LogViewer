package adri.logviewer.service;

import java.io.File;
import java.io.FileInputStream;
//import java.io.OutputStream;
//import java.util.List;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import adri.logviewer.agent.client.MinaClient;
import adri.logviewer.agent.file.LogFile;
import adri.logviewer.exception.PermissionException;
import adri.logviewer.model.Agent;
import adri.logviewer.model.Utilisateur;

public class FileService {
	private String filePath = "LogViewer-Fichiers/";
	private static FileService service;
	
	private FileService(){}
	public static FileService getInstance(ApplicationContext context){
		try{
			if(service == null){
				service = (FileService)context.getBean("fileService");
			}
			return service;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public List<LogFile> connect(Agent agent) throws Exception{
		return connect(agent, "");
	}
	public List<LogFile> connect(Agent agent, String file) throws Exception{
		MinaClient client = null;
		try{
			client = new MinaClient();
			return (List<LogFile>)client.connect(agent.getAdresse(), agent.getPort(), file);
		}finally{
			if(client != null){
				client.dispose();
			}
		}
	}
	public LogFile openFile(Agent agent, String file) throws Exception{
		List<LogFile> result = (List<LogFile>)connect(agent, file);
		if(result.size() == 1){
			return result.get(0);
		}
		throw new Exception("Fichier introuvable");
	}
	public LogFile openFile(Agent agent, LogFile file) throws Exception{
		return openFile(agent, file.getDistantName());
	}
	public void saveFile(Utilisateur user, Agent agent, LogFile logfile) throws Exception{
		File temp = null;
		File save = null;
		File path = null;
		try {
			path = new File(filePath + File.separator + user.getProfil().getName() + File.separator + agent.getPathName());
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
	public String getFilePath(Utilisateur user) throws PermissionException {
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdir();
		}
		if(user.isSuperUtilisateur()){
			return f.getAbsolutePath();
		}
		else if(user.getProfil() != null){
			return f.getAbsolutePath() + File.separator + user.getProfil().getId();
		}
		throw new PermissionException("Vous n'etes rattach� � aucun profil");
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Agent getAgent(File file) throws Exception{
		try{
			String nameAgent = file.getParentFile().getName();
			Pattern p = Pattern.compile("Agent([0-9]+)-(.*)");
			Matcher m = p.matcher(nameAgent);
			if(m.matches()){
				String id = m.group(1);
				return new Agent(Integer.parseInt(id));
			}
			throw new Exception("Agent introuvable");
		}catch(NumberFormatException e){
			throw new Exception("Agent invalide");
		}
	}
}
