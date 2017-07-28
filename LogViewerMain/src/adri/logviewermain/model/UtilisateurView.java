package adri.logviewermain.model;

public class UtilisateurView extends Utilisateur {
	private int idGroupe;

	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
	
}
