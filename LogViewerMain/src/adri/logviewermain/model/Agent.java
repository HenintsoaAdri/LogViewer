package adri.logviewermain.model;

import java.util.Date;
import java.util.Set;

import adri.logviewermain.exception.InputException;
import adri.logviewermain.util.StringUtil;

public class Agent extends BaseModel{
	
	private String nom;
	private String adresse;
	private int port;
	private String details;
	private String repertoire;
	private String syntaxe;
	private Utilisateur createur;
	private Date dateCreation = new Date();
	private Set<Groupe> listeGroupe;

	public Agent(){}
	public Agent(int id) {
		super(id);
	}
	public Agent(int id, String nom, String adresse, int port, String details, String repertoire,
			String syntaxe, Utilisateur createur, Date dateCreation) throws InputException {
		super(id);
		this.setNom(nom);
		this.setAdresse(adresse);
		this.setPort(port);
		this.setDetails(details);
		this.setRepertoire(repertoire);
		this.setSyntaxe(syntaxe);
		this.setCreateur(createur);
		this.setDateCreation(dateCreation);
	}
	public String getNom() {
		if(nom == null){
			return "";
		}
		return nom;
	}
	public void setNom(String nom) throws InputException {
		if(nom == null || nom.isEmpty()){
			throw new InputException("Un nom est requis");
		}
		this.nom = nom;
	}

	public String getAdresse() {
		if(adresse == null){
			return "";
		}
		return adresse;
	}
	public void setAdresse(String adresse) throws InputException {
		if(adresse == null || adresse.isEmpty()){
			throw new InputException("Une adresse est requise");
		}
		this.adresse = adresse;
	}

	public String getPortString() {
		if(port == 0){
			return "";
		}
		return String.valueOf(port);
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) throws InputException {
		this.port = port;
	}

	public String getDetails() {
		if(details == null){
			return "";
		}
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	public String getRepertoire() {
		if(repertoire == null){
			return "";
		}
		return repertoire;
	}
	public void setRepertoire(String repertoire) {
		this.repertoire = repertoire;
	}
	
	public String getSyntaxe() {
		if(syntaxe == null){
			return "";
		}
		return syntaxe;
	}
	public void setSyntaxe(String syntaxe) {
		this.syntaxe = syntaxe;
	}
	
	public Utilisateur getCreateur() {
		return createur;
	}
	public void setCreateur(Utilisateur createur) {
		this.createur = createur;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	public String getDateCreationString() {
		return StringUtil.getInstance().getDateString(getDateCreation());
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public Set<Groupe> getListeGroupe() {
		return listeGroupe;
	}
	public void setListeGroupe(Set<Groupe> listeGroupe) {
		this.listeGroupe = listeGroupe;
	}
	public boolean insideGroupe(Groupe groupe){
		return getListeGroupe() != null && getListeGroupe().contains(groupe);
	}
	public void addGroupe(Groupe groupe){
		if(groupe != null){
			this.getListeGroupe().add(groupe);
			groupe.getListeAgent().add(this);
		}		
	}
	@Override
	public String getName() {
		return "l'agent #" + getId() + " "
		+ getNom()
		+ " [ adresse&port : " + getAdresse() + ":" + getPort() + "]";
	}
	public String getPathName() {
		return "Agent" + getId() + "-" + getNom();
	}
}