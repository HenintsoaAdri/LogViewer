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
import adri.logviewer.filemanager.FilePaginate;
import adri.logviewer.filemanager.SampleLog;
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
	private SampleLog event;
	private FilePaginate fileLine;
	private String search = "";
	
	public String connexion(){

		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setLog(FileService.getInstance(context, getUser()).connect((Agent)getItem(), context, getUser()));
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
			FileService.getInstance(context, getUser()).openFile((Agent)getItem(), getLog(), context, getUser());
			setFileLine(new FilePaginate(getLog().getFile(),getNbItem(),getPage(),getSearch()));
			getSession().put("tempFile", getLog().getFile());
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
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			getLog().setFile((File) getSession().get("tempFile"));
			setFileLine(new FilePaginate(getLog().getFile(),getNbItem(),getPage(),getSearch()));
			return SUCCESS;
		}catch(FileNotFoundException e){
			setException(new FileNotFoundException("Le fichier n'est plus en cache."));
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

	public String parse(){
	
		ConfigurableApplicationContext context = null;
		try{
			getLog().setFile((File) getSession().get("tempFile"));
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			setFichier(FileService.getInstance(context, getUser()).parseFile((Agent)getItem(), getLog().getFile(), getPage(),10, isForce(),getEvent()));		
			return SUCCESS;
		}catch(FileNotFoundException e){
			setException(new FileNotFoundException(e.getMessage()+"Il n'est plus en cache."));
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
			getLog().setFile((File) getSession().get("tempFile"));
			UtilisateurService.getInstance(context).find(getItem(), getUser());
			FileService.getInstance(context, getUser()).saveFile(getUser(), (Agent)getItem(), getLog());
			return SUCCESS;
		}catch(FileNotFoundException e){
			setException(e);
			return NONE;
		}catch(Exception e){
			e.printStackTrace();
			setException(e);
			setFileInputStream(new FileInputStream(getLog().getFile()));
			return ERROR;
		}finally{
			if(context != null){
				context.close();
			}
		}
	}

	public String download() throws IOException {
		try{
			getLog().setFile((File) getSession().get("tempFile"));
			if(getLog().getFile().exists() && getLog().getFile().isFile()){
				fileInputStream = new FileInputStream(getLog().getFile());
				setFileLength(getLog().getFile().length());
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
				setFileLine(new FilePaginate(getPath(),getNbItem(),getPage(),getSearch()));
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
			setFichier(FileService.getInstance(context, getUser()).parseFile((Agent)getItem(), getPath(), getPage(),getNbItem(), isForce(), getEvent()));			
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
					throw new Exception("Ce fichier n'a pu etre supprim�");
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
	private void setPath(File path) {
		this.path = path;
	}
	private void setPath(String folder) throws Exception{
		ConfigurableApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			String base = FileService.getInstance(context, getUser()).getFilePath(getUser());
			File f = new File(base + File.separator + folder);
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
		if(file == null){
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
	
	public String getPrevious() {
		return file.replaceFirst(path.getName()+"(\\\\*)", "");
	}
	public String getLogString(){
		try {
			StringBuilder builder = new StringBuilder("[");
			displayAgentTree(builder, getLog());
			builder.append("]");
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "<p class=\"text-danger\">Aucun fichier n'a �t� retourn� par cet agent</p>";
		}
	}
	public void displayAgentTree(StringBuilder builder, LogFile log){
		builder.append("{");
		builder.append("text: \"");
		builder.append(log.getFileName().replaceAll("\\\\", "\\\\\\\\"));
		builder.append("\"");
		if(log.getChild() == null || log.getChild().isEmpty()){
			builder.append(",href:\"open?log.fileName=");
			builder.append(log.getFileName().replaceAll("\\\\", "\\\\\\\\"));
			builder.append("\"");
			builder.append(",icon:\"fa fw fa-file-text-o\"");
		}
		else{
			builder.append(",icon:\"fa fw fa-folder-o\"");
			builder.append(",nodes:[");
			String virgule = "";
			for(LogFile i : log.getChild()){
				builder.append(virgule);
				displayAgentTree(builder, i);
				virgule = ",";
			}
			builder.substring(builder.length()-1);
			builder.append("]");
		}
		builder.append("}");
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

	public SampleLog getEvent() {
		return event;
	}
	public void setEvent(SampleLog event) {
		this.event = event;
	}

	public FilePaginate getFileLine() {
		return fileLine;
	}
	public void setFileLine(FilePaginate fileLine) {
		this.fileLine = fileLine;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
}
