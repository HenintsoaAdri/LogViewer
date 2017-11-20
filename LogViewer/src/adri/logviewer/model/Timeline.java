package adri.logviewer.model;

import java.util.Date;

import adri.logviewer.util.StringUtil;

public class Timeline extends BaseModel {
	private String action;
	private String model;
	private String nomutilisateur;
	private Date date;
	private Utilisateur utilisateur;
	private String details;
	
	public Timeline(String action, BaseModel model, Utilisateur utilisateur){
		setUtilisateur(utilisateur);
		setAction(action);
		setModel(model);
	}
	public Timeline(){}
	
	public String getAction() {
		return action;
	}
	public String getActionString(){
		switch(getAction()){
			case "save": return "a créé";
			case "update": return "a mis à jour";
			case "delete": return "a supprimé";
			case "connect" : return "s'est connecté à";
			case "openFile" : return "a récupéré le fichier de l'agent";
			default : return getAction();
		}
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	private void setModel(BaseModel model) {
		setModel(model.getNomString());
	}
	public Timeline addModel(String model){
		setModel(getModel() + model);
		return this;
	}
	
	public String getNomutilisateur() {
		return nomutilisateur;
	}
	public void setNomutilisateur(String nomutilisateur) {
		this.nomutilisateur = nomutilisateur;
	}
	public Date getDate() {
		return date;
	}
	public String getDateString(){
		return StringUtil.getInstance().getDateTimeString(getDate());
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
		setNomutilisateur(getUtilisateur().getFullName());
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setDetails(BaseModel before, BaseModel model) {
		StringBuilder builder = new StringBuilder(before.getDetail());
		builder.append(" => ").append(model.getDetail());
		setDetails(builder.toString());
	}
	public void setDetails(BaseModel before){
		setDetails(before.getDetail());
	}
}
