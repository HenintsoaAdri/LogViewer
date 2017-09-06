package adri.logviewer.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.*;

import adri.logviewer.exception.InputException;

public class Fichier {

	private String datePattern = "yyyy-MM-dd HH:mm:ss,SSS";
	private File fichier;
	private ArrayList<Log> listeLog;
	private String pattern = "";
	private String mainPattern = "";
	private int startLine;
	private int lastLine;
	
	public Fichier(String pattern, File chemin) throws Exception {
		this.setPattern(pattern);
		this.setFichier(chemin);
	}
	public Fichier(String pattern, String chemin) throws Exception {
		this.setPattern(pattern);
		this.setFichier(chemin);
	}
	public Fichier(String pattern, File chemin,int line, int maxLine) throws Exception {
		this.setPattern(pattern);
		this.setFichier(chemin, line, maxLine);
	}

	public Fichier(String pattern, String chemin,int line, int maxLine) throws Exception {
		this.setPattern(pattern);
		this.setFichier(chemin, line, maxLine);
	}
	public String getPattern() {
		return pattern;
	}
	private void setPattern(String pattern) {
		setMainPattern(pattern);
		Pattern datePattern = Pattern.compile("(.*)(TIMESTAMP)\\{(.*)\\}(.*)");
		Matcher m = datePattern.matcher(pattern);
		if(m.find()){
			this.setDatePattern(m.group(3));
			pattern = m.group(1) + m.group(2) + m.group(4);
		}
		pattern = pattern.replaceAll("([\\p{Punct}])", "\\\\$0");
		pattern = pattern.replaceAll("([A-Z]+)", "(?<$0>.+)");
		this.pattern = pattern + "$";
	}

	public String getMainPattern() {
		return mainPattern;
	}
	public void setMainPattern(String mainPattern) {
		this.mainPattern = mainPattern;
	}
	public String getDatePattern() {
		return datePattern;
	}
	public void setDatePattern(String datePattern) {
		if(datePattern != null && !datePattern.isEmpty()){
			this.datePattern = datePattern;
		}
	}
	
	public File getFichier() {
		return fichier;
	}
	public void setFichier(File fichier) throws Exception {
		if(!fichier.exists() || fichier.isDirectory()){
			throw new InputException("Fichier invalide");
		}
		else if(fichier.isFile() && fichier.getName().endsWith(".log")){
			this.fichier = fichier;
			parseFile();
		}
	}
	public void setFichier(File fichier, int line, int maxLine) throws Exception {

		if(!fichier.exists() || fichier.isDirectory()){
			throw new InputException("Fichier invalide");
		}
		else if(fichier.isFile() && fichier.getName().endsWith(".log")){
			this.fichier = fichier;
			setStartLine(line);
			parseFile(maxLine);
		}
	}
	public void setFichier(String chemin) throws Exception  {
		setFichier(new File(chemin));
	}
	public void setFichier(String chemin, int line, int maxLine) throws Exception  {
		setFichier(new File(chemin), line, maxLine);
	}
	
	public ArrayList<Log> getListeLog() {
		return listeLog;
	}
	private void setListeLog(ArrayList<Log> listeLog) {
		this.listeLog = listeLog;
	}
	public void addLog(Log log){
		if(this.getListeLog() == null){
			this.setListeLog(new ArrayList<Log>());
		}
		this.getListeLog().add(log);
	}
	
	public int getStartLine() {
		return startLine;
	}
	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}
	
	public int getLastLine() {
		return lastLine;
	}
	public void setLastLine(int lastLine) {
		this.lastLine = lastLine;
	}
	
	public void parseFile() throws Exception{
		if(this.getFichier() == null){
			throw new Exception("Fichier introuvable");
		}
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(this.getFichier());
			bufferedReader = new BufferedReader(fileReader);
			String line;
			Pattern	pattern = Pattern.compile(getPattern());
			Matcher m = null;
			while ((line = bufferedReader.readLine()) != null) {
				try{
					m = pattern.matcher(line.trim());
					if(m.matches()){
						addLog(new Log(this, m));
					}
					else{
						getListeLog().get(getListeLog().size()-1).addDetails(line);
					}
				}catch (ParseException e) {
					getListeLog().get(getListeLog().size()-1).addDetails(line);
				}catch(ArrayIndexOutOfBoundsException e){
					continue;
				}catch (NullPointerException e) {
					continue;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally{
			if(fileReader != null) fileReader.close();
			if(bufferedReader != null) bufferedReader.close();
		}
	}
	public void parseFile(int maxLines) throws Exception{
		if(this.getFichier() == null){
			throw new Exception("Fichier introuvable");
		}
		FileReader fileReader = null;
		LineNumberReader bufferedReader = null;
		try {
			fileReader = new FileReader(this.getFichier());
			bufferedReader = new LineNumberReader(fileReader);
			bufferedReader.setLineNumber(getStartLine());
			String line;
			Pattern	pattern = Pattern.compile(getPattern());
			int i = 0;
			while ((line = bufferedReader.readLine()) != null && maxLines > i) {
				try{
					Matcher m = pattern.matcher(line.trim());
					if(m.matches()){
						addLog(new Log(this, m));
						i++;
					}
					else{
						getListeLog().get(getListeLog().size()-1).addDetails(line);
					}
				}catch (ParseException e) {
					getListeLog().get(getListeLog().size()-1).addDetails(line);
				}catch(ArrayIndexOutOfBoundsException e){
					continue;
				}catch (NullPointerException e) {
					continue;
				}
			}
			setLastLine(bufferedReader.getLineNumber());
		} catch (Exception e) {
			throw e;
		} finally{
			if(fileReader != null) fileReader.close();
			if(bufferedReader != null) bufferedReader.close();
		}
	}
	
//	public static void main(String[] args) throws Exception {
//		Fichier f = new Fichier("TIMESTAMP{HH:mm:ss.SSS} THREAD LEVEL CLASS - MESSAGE", "A:/eclipse/logs/app.log", 0, 10);
//		System.out.println(f.getListeLog());
//		System.out.println(f.getLastLine());
//		System.out.println(f.getListeLog());
//		String s = "          - EntityAttributeFetchImpl(entity=adri.logviewer.model.Utilisateur, querySpaceUid=<gen:1>, path=adri.logviewer.model.AgentView.createur)";
////		String s= "17:26:55.624 [http-nio-8090-exec-9] TRACE org.hibernate.loader.plan.build.internal.AbstractLoadPlanBuildingAssociationVisitationStrategy -  Finished root entity : adri.logviewer.model.AgentView";
//		Pattern p = Pattern.compile("(?<TIMESTAMP>.+) (?<THREAD>.+) (?<LEVEL>.+) (?<CLASS>.+) \\- (?<MESSAGE>.+)$");
//		Matcher m = p.matcher(s.trim());
//		System.out.println(m.matches());
//		for(int i = 0; m.matches()&&i<m.groupCount(); i++){
//			System.out.println("groupe " + (i+1) + " = " + m.group(i+1)+",");
//		}
//	}
}
