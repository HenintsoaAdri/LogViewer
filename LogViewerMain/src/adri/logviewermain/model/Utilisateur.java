package adri.logviewermain.model;

import java.util.Date;

import adri.logviewermain.exception.InputException;
import adri.logviewermain.util.StringUtil;

public class Utilisateur extends BaseModel{

	private String nom;
	private String prenom;
	private char sexe;
	private String email;
	private String password;
	private String confirm;
	private Profil profil;
	private String poste;
	private Date lastLogged;
	private boolean superUtilisateur;
	
	public Utilisateur(){}
	public Utilisateur(int id) {
		super(id);
	}		
	public Utilisateur(int id, String nom, String prenom, char sexe, String email, String password, 
			Profil Profil, String poste, Date lastLogged) throws InputException {
		super(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setSexe(sexe);
		this.setEmail(email);
		this.setPassword(password);
		this.setProfil(Profil);
		this.setPoste(poste);
		this.setLastLogged(lastLogged);
	}

	public String getNom() {
		if(nom == null){
			return "";
		}
		return nom;
	}
	public void setNom(String nom) throws InputException {
		if(nom == null|| nom.isEmpty()){
			throw new InputException("Un nom est requis");
		}
		this.nom = nom;
	}

	public String getPrenom() {
		if(prenom == null){
			return "";
		}
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getFullName(){
		return getNom()+" "+getPrenom();
	}
	
	public char getSexe() {
		return sexe;
	}
	public void setSexe(char sexe) {
		switch(sexe){
			case 'F' : break;
			case 'M' : break;
			case 'G' : sexe = 'M';
			case 'H' : sexe = 'M';
			default : sexe = 0;
		}
		this.sexe = sexe;
	}
	public String getSexeString(){
		switch(getSexe()){
			case 'F' : return "Femme";
			case 'M' : return "Homme";
		}
		return "";
	}
	public String getSexeIcon(){
		switch(getSexe()){
			case 'F' : return "fa-female";
			case 'M' : return "fa-male";
		}
		return "";
	}

	public String getEmail() {
		if(email == null){
			return "";
		}
		return email;
	}
	public void setEmail(String email) throws InputException {
		StringUtil.getInstance().checkEmail(email);
		this.email = email.trim().toLowerCase();
	}

	public String getPassword() {
		if(password == null){
			return "";
		}
		return password;
	}
	public void setPassword(String password) throws InputException {
		StringUtil.getInstance().checkPassword(password);
		this.password = password;
	}
	public void setConfirm(String confirm) throws InputException{
		this.confirm = confirm;
		if(confirm == null || !confirm.contentEquals(getPassword())){
			throw new InputException("Le mot de passe de confirmation ne correspond pas au mot de passe entr�");
		}
	}
	public String getConfirm(){
		return this.confirm;
	}
	public void setPassword(String password, String confirm) throws InputException{
		if(password == null || !password.contentEquals(confirm)){
			throw new InputException("Le mot de passe de confirmation ne correspond pas au mot de passe entr�");
		}
		setPassword(password);
	}

	public Profil getProfil() {
		return profil;
	}
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	public String getPoste() {
		if(poste == null){
			return "";
		}
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	
	public Date getLastLogged() {
		return lastLogged;
	}
	public String getLastLoggedString(){
		return StringUtil.getInstance().durationInLetter(getLastLogged());
	}
	public void setLastLogged(Date lastLogged) {
		this.lastLogged = lastLogged;
	}
	public void loggedIn() {
		setLastLogged(new Date());		
	}
	
	public boolean isSuperUtilisateur() {
		return superUtilisateur;
	}
	public void setSuperUtilisateur(boolean superUtilisateur) {
		this.superUtilisateur = superUtilisateur;
	}
	
	public boolean isAllowed(PermissionType permission){
		if(getProfil()!= null){
			return getProfil().isAllowed(permission);
		}
		return false;
	}
	public boolean isGenerallyAllowed(String permission){
		switch (permission) {
		case "Agent":
			return isAllowed(PermissionType.CRUDAGENT) || isAllowed(PermissionType.AGENTGROUPE) || isAllowed(PermissionType.LECTURETELECHARGEMENT);
		case "Profil":
			return isAllowed(PermissionType.CRUDPROFIL) || isAllowed(PermissionType.PROFILGROUPE);
		case "Groupe":
			return isAllowed(PermissionType.CRUDGROUPE) || isAllowed(PermissionType.GESTIONGROUPE);
		case "Utilisateur":
			return isAllowed(PermissionType.CRUDUTILISATEUR) || isAllowed(PermissionType.UTILISATEURGROUPE);
		default:
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Utilisateur #"+ getId()
				+": [nom&pr�nom = "+ getFullName()+ "],"
				+" [sexe = "+ getSexeString() + "],"
				+" [email = "+ getEmail() +"],"
				+" [Profil = "+ getProfil() + "]";
	}
	@Override
	public String getName() {
		return "l'utilisateur : " + getFullName();
	}
	
}