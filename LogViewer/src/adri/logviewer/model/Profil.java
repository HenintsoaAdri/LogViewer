package adri.logviewer.model;

import java.util.HashSet;
import java.util.Set;

import adri.logviewer.exception.InputException;
import adri.logviewer.exception.PermissionException;

public class Profil extends BaseModel{
	
	private String nom;
	private String description = "Aucune description";
	private Set<Groupe> listeGroupe;
	private Set<Permission> listePermission;
	private Set<Utilisateur> listeUtilisateur;
	
	public Profil(){
		super();
	}
	public Profil(int id) {
		super(id);
	}	
	public Profil(int id, String nom, Set<Permission> listePermission, Set<Groupe> groupe)throws InputException {
		super(id);
		this.setNom(nom);
		this.setListePermission(listePermission);
		this.setListeGroupe(groupe);
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if(description != null){
			this.description = description;
		}
	}
	
	public Set<Groupe> getListeGroupe() {
		return listeGroupe;
	}
	public void setListeGroupe(Set<Groupe> listeGroupe) {
		this.listeGroupe = listeGroupe;
	}
	public boolean hasGroupe(Groupe groupe) {
		return getListeGroupe() != null && getListeGroupe().contains(groupe);
	}
	
	public Set<Permission> getListePermission() {
		return listePermission;
	}
	public String getListePermissionString() {
		String liste = "";
		for(Permission p : getListePermission()){
			liste += p.getPermission().getValue() + "; ";
		}
		return liste;
	}
	public void setListePermission(Set<Permission> listePermission) {
		this.listePermission = listePermission;
	}
	
	public void allow(Permission permission) throws PermissionException{
		if(this.getListePermission() == null){
			setListePermission(new HashSet<Permission>());
		}
		this.getListePermission().add(permission);
	}
	public void allow(PermissionType permission) throws PermissionException{
		allow(new Permission(permission));
	}
	
	public void disallow(Permission permission) throws Exception{
		this.getListePermission().remove(permission);
	}
	public boolean hasPermission(PermissionType permission){
		return  hasPermission(new Permission(permission));
	}
	public boolean hasPermission(Permission permission){
		return getListePermission() != null && getListePermission().contains(permission);
	}
	
	public Set<Utilisateur> getListeUtilisateur() {
		return listeUtilisateur;
	}
	public void setListeUtilisateur(Set<Utilisateur> listeUtilisateur) {
		this.listeUtilisateur = listeUtilisateur;
	}
	public void addUtilisateur(Utilisateur utilisateur){
		if(!utilisateur.getProfil().equals(this)){
			utilisateur.setProfil(this);
			return;
		}
		this.getListeUtilisateur().add(utilisateur);
	}
	public boolean isAllowed(Permission permission) {
		return getListePermission()!=null && getListePermission().contains(permission);
	}
	public boolean isAllowed(PermissionType type) {
		Permission permission = new Permission(type);
		return isAllowed(permission);
	}
	@Override
	public String toString() {
		return "Profil #" + getId()
				+" [nom = " + getNom() + "]";
	}
	@Override
	public String getNomString() {
		return "le profil " + getNom();
	}
	
}