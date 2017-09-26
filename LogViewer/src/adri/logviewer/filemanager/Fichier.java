package adri.logviewer.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Fichier {

	private String datePattern = "yyyy-MM-dd HH:mm:ss,SSS";
	private File fichier;
	private FichierPagination pagination;
	private String pattern = "";
	private String mainPattern = "";
	private String level = null;
	
	public Fichier(String pattern, File chemin,int line, int maxLine, boolean force, String level) throws Exception {
		this.setMainPattern(pattern);
		this.setPagination(new FichierPagination(this, line, maxLine));
		this.setLevel(level);
		this.setFichier(chemin, force);
	}

	public Fichier(String pattern, String chemin,int line, int maxLine, boolean force, String level) throws Exception {
		this.setMainPattern(pattern);
		this.setPagination(new FichierPagination(this, line, maxLine));
		this.setLevel(level);
		this.setFichier(chemin, force);
	}

	public String getPattern() {
		return pattern;
	}
	private void setPattern(String pattern) {
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
		setPattern(mainPattern);
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
	public void setFichier(File fichier, boolean force) throws Exception {
		if(!fichier.exists() || fichier.isDirectory()){
			throw new FileNotFoundException("Fichier invalide");
		}
		if(!force){
			if(!fichier.getName().endsWith(".log"))
			throw new ParseException("Ce fichier n'est pas un fichier .log, il ne peut etre pars�",0);
		}
		this.fichier = fichier;
	}
	public void setFichier(String chemin, boolean force) throws Exception  {
		setFichier(new File(chemin), force);
	}
	
	public FichierPagination getPagination() {
		return pagination;
	}
	public void setPagination(FichierPagination pagination) {
		this.pagination = pagination;
	}
	private void addLog(Log log, Log previous){
		if(log.getLine() >= getPagination().getStartLine()){
			this.getPagination().addLog(log, previous);
		}
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		try {
			if(level.isEmpty()) return;
		} catch (NullPointerException e) {
			return;
		}
		this.level = level;
	}

	public void parseFile() throws Exception{
		FileReader fileReader = null;
		LineNumberReader reader = null;
		try {
			fileReader = new FileReader(this.getFichier());
			reader = new LineNumberReader(fileReader);
			String line = null;
			Pattern	pattern = Pattern.compile(getPattern());
			Log item = null;
			Matcher m = null;
			List<Log> temp = new ArrayList<Log>();
			while ((line = reader.readLine()) != null) {
				try{
					m = pattern.matcher(line.trim());
					if(m.matches()){
						if(getPagination().isFull()){
							return;
						}
						item =  new Log(this, m, reader.getLineNumber());
						try{
							if(!getLevel().equalsIgnoreCase(item.getPriority())){
								continue;
							}
						}catch(NullPointerException e){}
						temp.add(item);
						addLog(item, temp.get(0));
						if(temp.size() == getPagination().getMaxLength()+1){
							temp.remove(0);
						}
					}
					else{
						setPattern(getMainPattern());
						getPagination().getLastLog().addDetails(line);
					}
				}catch (ParseException e) {
					getPagination().getLastLog().addDetails(line);
				}catch(IndexOutOfBoundsException e){
					continue;
				}catch (NullPointerException e) {
					continue;
				}
			}
		} catch(FileNotFoundException e){
			throw new Exception("Fichier introuvable");
		} catch (Exception e) {
			throw e;
		} finally{
			if(fileReader != null) fileReader.close();
			if(reader != null) reader.close();
		}
	}
}
