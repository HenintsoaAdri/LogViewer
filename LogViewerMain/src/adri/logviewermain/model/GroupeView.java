package adri.logviewermain.model;

public class GroupeView extends Groupe{
	private int nombreUtilisateur;
	private int nombreProfil;
	
	public GroupeView() {
		super();
	}
	public GroupeView(int id) {
		super(id);
	}
	public int getNombreProfil() {
		return nombreProfil;
	}
	public void setNombreProfil(int nombreProfil) {
		this.nombreProfil = nombreProfil;
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
