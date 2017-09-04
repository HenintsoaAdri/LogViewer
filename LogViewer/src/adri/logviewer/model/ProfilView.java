package adri.logviewer.model;

public class ProfilView extends Profil{
	private int nombreUtilisateur;
	private int nombreGroupe;
	
	public ProfilView() {
		super();
	}
	public ProfilView(int id) {
		super(id);
	}

	public int getNombreUtilisateur() {
		return nombreUtilisateur;
	}
	public void setNombreUtilisateur(int nombreUtilisateur) {
		this.nombreUtilisateur = nombreUtilisateur;
	}
	public int getNombreGroupe() {
		return nombreGroupe;
	}
	public void setNombreGroupe(int nombreGroupe) {
		this.nombreGroupe = nombreGroupe;
	}
	
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
}
