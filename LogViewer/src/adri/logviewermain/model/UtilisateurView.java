package adri.logviewermain.model;

public class UtilisateurView extends Utilisateur {
	
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
	
}