package adri.logviewer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewer.agent.file.LogFile;
import adri.logviewer.filemanager.Fichier;
import adri.logviewer.model.Agent;
import adri.logviewer.service.FileService;
import adri.logviewer.service.UtilisateurService;

public class FileAction extends BaseAction{
	private File path;
	private String file = "";
	private Fichier fichier;
	private InputStream fileInputStream;
	private long fileLength;
	private List<LogFile> listeLog;
	
	@Override
	public String start() {
		try{
			setPath(getFile());
			if(path.isFile()){
				return "file";
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}
	}
	
	public String connexion(){

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setListeLog(FileService.getInstance(context).connect((Agent)getItem()));
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}

	public String open(){

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setListeLog(FileService.getInstance(context).connect((Agent)getItem(), getFile()));
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String parseFile(){

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPath(getFile());
			try {
				setItem(FileService.getInstance(context).getAgent(getPath()));
			} catch (Exception e) {
				setException(e);
				return "file";
			}
			UtilisateurService.getInstance(context).find(getItem(), getUser());				
			setFichier(new Fichier(((Agent)getItem()).getSyntaxe(), getPath(), getPage(),10));			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String parse(){

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());				
			setFichier(new Fichier(((Agent)getItem()).getSyntaxe(), getPath(), getPage(),10));			
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	public String download() throws IOException {
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPath(getFile());
			if(getPath().exists() && getPath().isFile()){
				fileInputStream = new FileInputStream(getPath());
				setFileLength(getPath().length());
				return SUCCESS;
			}
			return open();
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}
	
	public File getPath() {
		return path;
	}
	public void setPath(File path) {
		this.path = path;
	}

	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		if(file == null || file.isEmpty()){
			return;
		}
		this.file = file;
	}
	private void setPath(String folder) throws Exception{
		ConfigurableApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			String base = FileService.getInstance(context).getFilePath(getUser());
			File f = new File(base + folder);
			if(!f.exists()){
				throw new FileNotFoundException((f.isDirectory()?"Dossier":"Fichier")+" inexistant");
			}
			setPath(f);
		}finally{
			if(context != null){
				context.close();
			}
		}
	}

	public Fichier getFichier() {
		return fichier;
	}
	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public long getFileLength() {
		return fileLength;
	}
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

	public List<LogFile> getListeLog() {
		return listeLog;
	}
	public void setListeLog(List<LogFile> listeLog) {
		this.listeLog = listeLog;
	}

}
