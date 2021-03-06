package adri.logviewermain.model;

import java.util.HashSet;
import java.util.Set;

import adri.logviewermain.exception.InputException;
import adri.logviewermain.exception.PermissionException;

public class Profil extends BaseModel{
	
	private String nom;
	private String description = "Aucune description";
	private Groupe groupe;
	private Set<Permission> listePermission;
	private Set<Utilisateur> listeUtilisateur;
	private Set<Permission> effectiveListePermission;
	private boolean superProfil;
	public Profil(){}
	public Profil(int id) {
		super(id);
	}	
	public Profil(int id, String nom, Set<Permission> listePermission, Groupe groupe)throws InputException {
		super(id);
		this.setNom(nom);
		this.setListePermission(listePermission);
		this.setGroupe(groupe);
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
	
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
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
		autoSetEffectiveListePermission();
	}
	
	public boolean isSuperProfil() {
		return superProfil;
	}
	public void setSuperProfil(boolean superProfil) {
		this.superProfil = superProfil;
	}
	public void allow(Permission permission) throws PermissionException{
		if(isSuperProfil()){
			throw new PermissionException("Les permissions du super profil ne peuvent etre modifi�es");
		}
		if(this.getListePermission() == null){
			setListePermission(new HashSet<Permission>());
		}
		if(!this.hasParentPermission(permission)){
			this.getListePermission().add(permission);
		}
	}
	public void allow(PermissionType permission) throws PermissionException{
		allow(new Permission(permission));
	}
	
	public void disallow(Permission permission) throws Exception{
		if(this.getListePermission() != null){
			if(this.getGroupe().isSuperAdmin()){
				throw new PermissionException("Les permissions du super utilisateur ne peuvent etre retir�es");
			}
			else{
				this.getListePermission().remove(permission);
			}
		}
	}
	public boolean hasPermission(PermissionType permission){
		return  hasPermission(new Permission(permission));
	}
	public boolean hasPermission(Permission permission){
		return getListePermission() != null && getListePermission().contains(permission);
	}
	public boolean hasParentPermission(Permission permission){
		return hasParentPermission(permission.getPermission());
	}
	public boolean hasParentPermission(PermissionType permission){
		return getListePermission() != null && getListePermission().contains(permission.getParent());
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
	public Set<Permission> getEffectiveListePermission(){
		return effectiveListePermission;
	}
	private void setEffectiveListePermission(Set<Permission> effectiveListePermission){
		this.effectiveListePermission = effectiveListePermission;
	}
	public void autoSetEffectiveListePermission() {
		if(getEffectiveListePermission() == null){
			setEffectiveListePermission(new HashSet<Permission>());
		}
		for(Permission p : getListePermission()){
			getEffectiveListePermission().add(p);
			getEffectiveListePermission().addAll(p.getPermission().getChild());
		}
	}
	public boolean isAllowed(Permission permission) {
		return getEffectiveListePermission()!=null && getEffectiveListePermission().contains(permission);
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
	public String getName() {
		return "le profil " + getNom();
	}
	
}