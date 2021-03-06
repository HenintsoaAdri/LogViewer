package adri.logviewer.filemanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

import adri.logviewer.util.StringUtil;

public class Log {
	private int line;
	private Date date;
	private String classe;
	private String method;
	private String message;
	private String priority;
	private String thread;
	private String details;
	private Fichier fichier;
	private String ligne;
	
	public Log() {}
	public Log(Fichier fichier, Matcher m, int line, String ligne) throws Exception{
		setFichier(fichier);
		setDate(m,fichier.getDatePattern());
		setClasse(m);
		setMethod(m);
		setPriority(m);
		setThread(m);
		setMessage(m);
		setLine(line);
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public Date getDate() {
		return date;
	}
	public String getDateString() {
		try {
			return StringUtil.getInstance().getDateTimeString(getDate(), fichier.getDatePattern());
		} catch (NullPointerException e) {
			return "";
		}
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDate(String string, String pattern) throws ParseException {
		if(pattern != null){
			this.setDate(new SimpleDateFormat(pattern).parse(string));
		}else{
			this.setDate(string);
		}
	}
	public void setDate(Matcher m, String datePattern) throws Exception {
		try {
			setDate(m.group("TIMESTAMP"), datePattern);
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	public void setDate(String string) throws ParseException {
		this.setDate(new SimpleDateFormat().parse(string));
	}
	
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public void setClasse(Matcher m) {
		try {
			setClasse(m.group("CLASS"));
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setMethod(Matcher m) {
		try {
			setMethod(m.group("METHOD"));
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setMessage(Matcher m) {
		try {
			setMessage(m.group("MESSAGE"));
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public void setPriority(Matcher m) {
		try {
			setPriority(m.group("LEVEL"));
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	
	public void setThread(Matcher m) {
		try {
			setThread(m.group("THREAD"));
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void addDetails(String details){
		if(details != null && !details.isEmpty()){
			this.details += " \n ";
			this.details += details;
		}
	}
	
	public Fichier getFichier() {
		return fichier;
	}
	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}
	
	public String getLigne() {
		return ligne;
	}
	public void setLigne(String ligne) {
		this.ligne = ligne;
	}
	
	@Override
	public String toString() {
		return "[ date : "
				+ getDateString()
				+ " - priorit� : "
				+ getPriority()
				+ " - thread : "
				+ getThread()
				+ " - classe : "
				+ getClasse()
				+ " - methode : "
				+ getMethod()
				+ " - message : "
				+ getMessage()
				+ " - "
				+ getDetails()
				+" ]\n";
	}
	
}
