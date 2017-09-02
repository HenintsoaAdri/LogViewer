package adri.logviewermain.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import adri.logviewermain.filemanager.Fichier;
import adri.logviewermain.service.FileService;

public class FileAction extends BaseAction{
	private String base;
	private File filepath;
	private String file = "\\";
	private String fileStructure;
	private Fichier fichier;
	private InputStream fileInputStream;
	private long fileLength;
	
	@Override
	public String start() {
		ConfigurableApplicationContext context = null;
		try{
			context = new ClassPathXmlApplicationContext("list-beans.xml");
			base = FileService.getInstance(context).getFilePath();
			return setFilepath(getPath(getFile()));
		}catch(FileNotFoundException e){
			setFileStructure("<p class=\"text-danger\">"+e.getMessage()+"</p>");
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
			base = FileService.getInstance(context).getFilePath();
			File f = getPath(getFile());
			if(f.exists() && f.isFile()){
				fileInputStream = new FileInputStream(f);
				setFileLength(f.length());
				return SUCCESS;
			}
			return setFilepath(getPath(getFile()));
		}catch(FileNotFoundException e){
			setFileStructure("<p class=\"text-danger\">"+e.getMessage()+"</p>");
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
	
	public File getFilepath() {
		return filepath;
	}
	public String setFilepath(File filepath) throws Exception {
		this.filepath = filepath;
		if(filepath.isFile()){
			setFichier(new Fichier("TIMESTAMP{HH:mm:ss.SSS} THREAD LEVEL CLASS - MESSAGE", filepath, getPage(),10));
			return "file";
		}
		setFileStructure();
		return SUCCESS;
	}
	private void setFileStructure(){
		StringBuilder builder = new StringBuilder();
		try{
			File[] liste = filepath.listFiles();
			if(liste.length > 0){
				builder.append("<ul class=\"list-group list-inline\">");
				listFile(liste, builder);
				builder.append("</ul>");
			}else{
				throw new Exception();
			}
		}catch(Exception e){
			builder.append("<p class=\"text-danger\">Aucun fichier n'a �t� enregistr�.</p>");
		}
		setFileStructure(builder.toString());
	}
	private void listFile(File[] file, StringBuilder builder){
		for(File f : file){
			builder.append("<li class=\"text-center p-20\">");
			builder.append("<a href=\"?file="+ getName(f) +"\">");
			String icone = "<i class=\"fa fa-file-text-o fa-5x text-info\" aria-hidden=\"true\"></i> ";
			if(f.isDirectory()){
				icone = "<i class=\"fa fa-folder-o fa-5x text-primary\" aria-hidden=\"true\"></i> ";
			}
			builder.append(icone);
			builder.append("<p>"+f.getName()+"</p>");
			builder.append("</a>");
			builder.append("</li>");
		}
	}
	private void listFile(File[] file, StringBuilder builder, boolean parent){
		String filetext = "fa-file-text-o";
		for(File f : file){
			builder.append("<a href=\"?file="+ getName(f) +"\">");
			builder.append("<li>");
			String icone = "<i class=\"fa fa-file-text-o\" aria-hidden=\"true\"></i> ";
			if(parent){
				icone = "<i class=\"fa fa-file-text-o fa-2x\" aria-hidden=\"true\"></i> ";
			}
			builder.append(icone);
			int start = builder.lastIndexOf(filetext);
			builder.append(f.getName());
			if(f.isDirectory()){
				builder.replace(start, start + filetext.length(), "fa-folder-o");
				builder.append("<ul class=\"list-icons collapse m-l-20\">");
				listFile(f.listFiles(), builder, false);
				builder.append("</ul>");
			}
			builder.append("</li>");
			builder.append("</a>");
		}
	}
	public String getFileStructure() {
		return fileStructure;
	}
	public void setFileStructure(String fileStructure) {
		this.fileStructure = fileStructure;
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
	private File getPath(String folder) throws Exception{
		File f = new File(base + folder);
		if(!f.exists()){
			throw new FileNotFoundException((f.isDirectory()?"Dossier":"Fichier")+" inexistant");
		}
		return f;
	}
	private String getName(File f){
		return f.getAbsolutePath().replace(base, "");
	}
	public String getFileString(){
		return (getFilepath().isDirectory()? "<i class=\"fa fa-folder-open-o\" aria-hidden=\"true\"></i> " : "<i class=\"fa fa-file-text-o\" aria-hidden=\"true\"></i> ") + getFile();
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
	
}