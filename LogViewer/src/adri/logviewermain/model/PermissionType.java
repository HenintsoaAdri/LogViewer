package adri.logviewermain.model;

public enum PermissionType {
	CONNEXION("Connexion",0),
	CRUDUTILISATEUR("Cr�er / Modifier / Supprimer Profil et Utilisateur",1),
	LECTURETELECHARGEMENT("Lecture et t�l�chargement des logs", 2),
	CRUDGROUPE("Cr�er / Modifier / Supprimer Groupe d'agent",3),
	CRUDAGENT("Cr�er / Modifier / Supprimer Agent",4);

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
	public static PermissionType getValueOf(int value){
		for(PermissionType p : values()){
			if(value == p.getValue()) return p;
		}
		return null;
	}
}
