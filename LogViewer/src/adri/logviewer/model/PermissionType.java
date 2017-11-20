package adri.logviewer.model;

public enum PermissionType {
	CONNEXION("Connexion"),
	CRUDUTILISATEUR("Cr�er / Modifier / Supprimer Profil et Utilisateur"),
	LECTURETELECHARGEMENT("Lecture et t�l�chargement des logs"),
	CRUDGROUPE("Cr�er / Modifier / Supprimer Groupe d'agent"),
	CRUDAGENT("Cr�er / Modifier / Supprimer Agent");

	private String libelle;
	
	private PermissionType(String libelle) {
		this.setLibelle(libelle);
	}
	
	public String getLibelle() {
		return libelle;
	}
	private void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getValue() {
		return this.ordinal();
	}
	public static PermissionType getValueOf(int value){
		for(PermissionType p : values()){
			if(value == p.getValue()) return p;
		}
		return null;
	}
}
