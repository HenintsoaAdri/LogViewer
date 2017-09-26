package adri.logviewer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
	private LogFile log;
	private boolean force;
	private String level;
	
	public String connexion(){

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setLog(FileService.getInstance(context, getUser()).connect((Agent)getItem()));
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
			FileService.getInstance(context, getUser()).openFile((Agent)getItem(), getLog());
			setFileInputStream(new FileInputStream(getLog().getTempFile()));
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
	public String view() {
		try{
			String tempDir = System.getProperty("java.io.tmpdir");
			getLog().setTempFile(new File(tempDir + getFile()));
			setFileInputStream(new FileInputStream(getLog().getTempFile()));
			return SUCCESS;
		}catch(FileNotFoundException e){
			setException(e);
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}
	}

	public String parse(){
	
		ConfigurableApplicationContext context = null;
		try{
			String tempDir = System.getProperty("java.io.tmpdir");
			getLog().setTempFile(new File(tempDir + getFile()));
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setFichier(FileService.getInstance(context, getUser()).parseFile((Agent)getItem(), getLog().getTempFile(), getPage(),10, isForce(),getLevel()));		
			return SUCCESS;
		}catch(FileNotFoundException e){
			setException(e);
			return NONE;
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

	public String save() throws IOException {
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			String tempDir = System.getProperty("java.io.tmpdir");
			getLog().setTempFile(new File(tempDir + getFile()));
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			FileService.getInstance(context, getUser()).saveFile(getUser(), (Agent)getItem(), getLog());
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			setFileInputStream(new FileInputStream(getLog().getTempFile()));
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}

	public String download() throws IOException {
		try{
			String tempDir = System.getProperty("java.io.tmpdir");
			getLog().setTempFile(new File(tempDir + getFile()));
			if(getLog().getTempFile().exists() && getLog().getTempFile().isFile()){
				fileInputStream = new FileInputStream(getLog().getTempFile());
				setFileLength(getLog().getTempFile().length());
				return SUCCESS;
			}
			return view();
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}
	}

	@Override
	public String start() {
		try{
			setPath(getFile());
			if(path.isFile()){
				setFileInputStream(new FileInputStream(getPath()));
				return "file";
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}
	}

	public String parseFile(){

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			setPath(getFile());
			try {
				setItem(FileService.getInstance(context, getUser()).getAgent(getPath()));
			} catch (Exception e) {
				try{
					setException(new Exception("Parsage impossible.", e));
					setFileInputStream(new FileInputStream(getPath()));
					return "file";
				}catch(FileNotFoundException ex){
					setException(ex);
					return NONE;
				}
			}
			UtilisateurService.getInstance(context).find(getItem(), getUser());		
			setFichier(FileService.getInstance(context, getUser()).parseFile((Agent)getItem(), getPath(), getPage(),10, isForce(), getLevel()));			
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
	public String downloadFile() throws IOException {
		try{
			setPath(getFile());
			if(getPath().exists() && getPath().isFile()){
				setFileInputStream(new FileInputStream(getPath()));
				setFileLength(getPath().length());
				return SUCCESS;
			}
			return open();
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}
	}
	public String deleteFile() throws Exception{
		try{
			setPath(getFile());
			if(getPath().exists() && getPath().isFile()){
				if(!getPath().delete()){
					setFileInputStream(new FileInputStream(getPath()));
					throw new Exception("Ce fichier n'a pu etre supprimé");
				}
				return SUCCESS;
			}
			return open();
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			return ERROR;
		}
	}
	public File getPath() {
		return path;
	}
	public void setPath(File path) {
		this.path = path;
	}

	private void setPath(String folder) throws Exception{
		ConfigurableApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			String base = FileService.getInstance(context, getUser()).getFilePath(getUser());
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

	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		if(file == null || file.isEmpty()){
			return;
		}
		this.file = file;
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

	public LogFile getLog() {
		return log;
	}
	public void setLog(LogFile log) {
		this.log = log;
	}

	@Override
	public void setPage(int page) {
		super.setPage(page+1);
	}

	public boolean isForce() {
		return force;
	}

	public void setForce(boolean force) {
		this.force = force;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
