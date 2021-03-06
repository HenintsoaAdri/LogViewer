package adri.logviewer.model;

public class GroupeView extends Groupe{
	private int nombreAgent;
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
	public int getNombreAgent() {
		return nombreAgent;
	}
	public void setNombreAgent(int nombreAgent) {
		this.nombreAgent = nombreAgent;
	}
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
}
