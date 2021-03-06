package adri.logviewermain.model;

import java.util.HashSet;
import java.util.Set;

public enum PermissionType {
	CONNEXION("Connexion",0),
	CRUDGROUPE("Cr�er / Modifier / Supprimer Groupe",10),
	GESTIONGROUPE("Gestion de groupe",11),
	UTILISATEURGROUPE("Gestion d'utilisateurs de groupe", 111),
	AGENTGROUPE("Gestion d'agents de groupe", 112),
	LECTURETELECHARGEMENT("Lecture et t�l�chargement des logs", 113),
	PROFILGROUPE("Gestion de profils du groupe", 114),
	CRUDAGENT("Cr�er / Modifier / Supprimer Agent",12),
	CRUDPROFIL("Cr�er / Modifier / Supprimer Profil",13),
	CRUDUTILISATEUR("Cr�er / Modifier / Supprimer Utilisateur",14);

	private String libelle;
	private int value;
	
	private PermissionType(String libelle, int value) {
		this.setLibelle(libelle);
		this.setValue(value);
	}
	
	public String getLibelle() {
		return libelle;
	}
	private void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getValue() {
		return value;
	}
	private void setValue(int value) {
		this.value = value;
	}
	public Set<Permission> getChild(){
		Set<Permission> liste = new HashSet<Permission>();
		int initialValue = this.getValue() * 10;
		int finalValue = initialValue + 10; 
		for(PermissionType p : PermissionType.values()){
			if(p.getValue() > initialValue && p.getValue() < finalValue){
				liste.add(new Permission(p));
			}
		}
		return liste;
	}
	public Permission getParent() {
		return new Permission(getValueOf(Math.floorDiv(this.getValue(), 10)));
	}
	public static PermissionType getValueOf(int value){
		for(PermissionType p : values()){
			if(value == p.getValue()) return p;
		}
		return null;
	}
}
