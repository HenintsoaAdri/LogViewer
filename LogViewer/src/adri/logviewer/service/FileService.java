package adri.logviewer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import adri.logviewer.agent.client.Client;
import adri.logviewer.agent.file.LogFile;
import adri.logviewer.exception.PermissionException;
import adri.logviewer.filemanager.Fichier;
import adri.logviewer.filemanager.SampleLog;
import adri.logviewer.model.Agent;
import adri.logviewer.model.Timeline;
import adri.logviewer.model.Utilisateur;

public class FileService {
	private String filePath = "LogViewer-Fichiers";
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

	public LogFile connect(Agent agent, ApplicationContext context, Utilisateur utilisateur) throws Exception{
		Client client = null;
		try{
			client = new Client();
			LogFile file = (LogFile)client.connect(agent.getAdresse(), agent.getPort());
			UtilisateurService.getInstance(context).timeline(new Timeline("connect", agent, utilisateur));
			return file;
		}finally{
			if(client != null){
				client.dispose();
			}
		}
	}
	public void openFile(Agent agent, LogFile file, ApplicationContext context, Utilisateur utilisateur) throws Exception{
		Client client = null;
		try{
			client = new Client(file);
			if(file.getFile() == null){
				try {
					String extension = file.getFileName().substring(file.getFileName().lastIndexOf('.'));
					File tempFile = File.createTempFile(file.getFileName(), extension);
					tempFile.deleteOnExit();
					file.setFile(tempFile);
					client.connect(agent.getAdresse(), agent.getPort());
					UtilisateurService.getInstance(context).timeline(new Timeline("openFile", agent, utilisateur).addModel("(" + file.getFileName() + ")"));
				} catch (StringIndexOutOfBoundsException e) {
					LogFile list = (LogFile)client.connect(agent.getAdresse(), agent.getPort());
					file.setChild(list.getChild());
					file.setFileName(list.getFileName());
					throw new IllegalArgumentException("Ce fichier correspond � un dossier");
				}
			}
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
			if(!logfile.getFile().exists()){
				throw new FileNotFoundException("Ce fichier n'est plus en cache ou a �t� d�plac�");
			}
			String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String name = date + logfile.getFileName().replaceAll("(\\\\|/|:|\\*|\\?|\"|<|>|\\|)", "~");
			save = new File(path.getAbsolutePath() + File.separator + name);
			if(!logfile.getFile().renameTo(save)){
				throw new Exception("Le fichier n'a pas pu etre sauvegard�");
			}
			logfile.setFileName(save.getAbsolutePath().replace(getFilePath(user), ""));
			logfile.setFile(save);
		} catch (Exception e) {
			throw e;	
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
			File base = getRootPathFile(user);
			if(!base.exists()){
				throw new Exception("Aucun fichier n'a encore �t� enregistr� dans votre profil");
			}
			return base.getAbsolutePath();
		}
		throw new PermissionException("Vous n'etes rattach� � aucun profil");
	}
	public String getRootPath(Utilisateur user){
		return filePath + File.separator + user.getProfil().getNom();
	}
	public File getRootPathFile(Utilisateur user){
		return new File(getRootPath(user));
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
	public Fichier parseFile(Agent agent, File file, int line, int maxLine, boolean force, SampleLog sample)throws Exception{
		Fichier f = new Fichier(agent.getSyntaxe(), file, line, maxLine, force, sample);
		f.parseFile();
		return f;
	}
}
