package adri.logviewermain.model;

public class ProfilView extends Profil{
	private int nombreUtilisateur;
	
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
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
}
