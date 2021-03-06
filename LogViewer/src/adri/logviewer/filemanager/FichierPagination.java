package adri.logviewer.filemanager;

import java.util.ArrayList;
import java.util.List;

public class FichierPagination {
	private Fichier fichier;
	private int previewStart;
	private List<Log> listeLog;
	private int totalLine;
	private int startLine;
	private int currentEnd;
	private int maxLength;
	private boolean start;
	
	public FichierPagination(Fichier fichier, int startLine, int maxLength){
		setFichier(fichier);
		setStartLine(startLine);
		setMaxLength(maxLength);
	}
	
	public Fichier getFichier() {
		return fichier;
	}
	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}

	public int getPreviewStart() {
		return previewStart;
	}
	public void setPreviewStart(int previewStart) {
		this.previewStart = previewStart;
	}
		
	public List<Log> getListeLog() {
		return listeLog;
	}
	public void setListeLog(List<Log> listeLog) {
		this.listeLog = listeLog;
	}
	public void addLog(Log log, Log previous){
		try{
			this.getListeLog().add(log);
		}catch(NullPointerException e){
			this.setListeLog(new ArrayList<Log>());
			setPreviewStart(previous.getLine());
			start = getPreviewStart() == log.getLine();
			this.getListeLog().add(log);
		}
		this.currentEnd = Math.max(this.currentEnd, log.getLine());
	}
	
	public int getTotalLine() {
		return totalLine;
	}
	public void setTotalLine(int totalLine) {
		this.totalLine = totalLine;
	}
	
	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public int getCurrentEnd() {
		return currentEnd;
	}
	
	public int getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public Log getLastLog() throws Exception{
		try{
			return get(getListeLog().size()-1);
		}catch(NullPointerException e){
			return null;
		}catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	public Log get(int i)throws Exception {
		try{
			return getListeLog().get(i);
		}catch(NullPointerException e){
			throw e;
		}
	}

	public boolean isFull() {
		return getListeLog() != null && getListeLog().size() == getMaxLength();
	}

	public boolean isStart() {
		return start;
	}

	public void checkLastLog(SampleLog sample) throws Exception {
		try {
			if(!sample.is(getLastLog())){
				getListeLog().remove(getLastLog());
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

}
