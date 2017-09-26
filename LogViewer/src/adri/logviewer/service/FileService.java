package adri.logviewer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import adri.logviewer.agent.client.Client;
import adri.logviewer.agent.file.LogFile;
import adri.logviewer.exception.PermissionException;
import adri.logviewer.filemanager.Fichier;
import adri.logviewer.model.Agent;
import adri.logviewer.model.Utilisateur;

public class FileService {
	private String filePath = "LogViewer-Fichiers/";
	private static FileService service;
	
	private FileService(){}
	public static FileService getInstance(ApplicationContext context, Utilisateur user) throws Exception{
		try{
			if(!user.isGenerallyAllowed("Fichier")){
				throw new PermissionException("Vous n'etes pas autoris� � effectuer ces op�rations");
			}
			if(service == null){
				service = (FileService)context.getBean("fileService");
			}
			return service;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	public LogFile connect(Agent agent) throws Exception{
		Client client = null;
		try{
			client = new Client();
			return (LogFile)client.connect(agent.getAdresse(), agent.getPort());
		}finally{
			if(client != null){
				client.dispose();
			}
		}
	}
	public void openFile(Agent agent, LogFile file) throws Exception{
		Client client = null;
		try{
			client = new Client(file);
			if(file.getTempFile() == null){
				String extension = file.getFileName().substring(file.getFileName().lastIndexOf('.'));
				file.setTempFile(File.createTempFile(file.getFileName(), extension));
			}
			client.connect(agent.getAdresse(), agent.getPort());
		}finally{
			if(client != null){
				client.dispose();
			}
		}
	}
	
	public void saveFile(Utilisateur user, Agent agent, LogFile logfile) throws Exception{
		File save = null;
		File path = null;
		try {
			path = new File(getRootPath(user) + File.separator + agent.getPathName());
			if(!path.exists()){
				path.mkdirs();
			}
			if(!logfile.getTempFile().exists()){
				throw new Exception("Ce fichier n'est plus en cache ou a �t� d�plac�");
			}
			String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			save = new File(path.getAbsolutePath() + File.separator + date + logfile.getFileName().replace("/", "~~"));
			if(!logfile.getTempFile().renameTo(save)){
				throw new Exception("Le fichier n'a pas pu etre sauvegard�");
			}
			logfile.setTempFile(save);
		} catch (Exception e) {
			throw e;	
		}
	}
	public void downloadFile(LogFile logfile, OutputStream out)throws Exception{
		FileInputStream read = null;
		byte[] buffer = new byte[1024];
		try {
			int reader;
			read = new FileInputStream(logfile.getTempFile());
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
	public String getFilePath(Utilisateur user) throws Exception {
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdir();
		}
		if(user.isSuperUtilisateur()){
			return f.getAbsolutePath();
		}
		else if(user.getProfil() != null){
			String base = getRootPath(user);
			if(!new File(base).exists()){
				throw new Exception("Aucun fichier n'a encore �t� enregistr� dans votre profil");
			}
			return base;
		}
		throw new PermissionException("Vous n'etes rattach� � aucun profil");
	}
	public String getRootPath(Utilisateur user){
		return new File(filePath).getAbsolutePath() + File.separator + user.getProfil().getNom();
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
			throw new Exception("Agent introuvable.");
		}catch(NumberFormatException e){
			throw new Exception("Agent invalide");
		}
	}
	public Fichier parseFile(Agent agent, File file, int line, int maxLine, boolean force, String level)throws Exception{
		Fichier f = new Fichier(agent.getSyntaxe(), file, line, maxLine, force, level);
		f.parseFile();
		return f;
	}
}
