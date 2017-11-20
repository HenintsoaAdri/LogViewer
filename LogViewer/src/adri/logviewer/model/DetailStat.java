package adri.logviewer.model;

public class DetailStat {
	private int jour = 1;
	private int mois;
	private int annee;
	
	private long nombre;
	
	public DetailStat(){}
	
	public DetailStat(long nombre, int jour, int mois, int annee) {
		super();
		setNombre(nombre);
		setJour(jour);
		setMois(mois);
		setAnnee(annee);
	}
	public DetailStat(long nombre, int mois, int annee) {
		super();
		setNombre(nombre);
		setMois(mois);
		setAnnee(annee);
	}
	public DetailStat(long nombre, int annee) {
		super();
		setNombre(nombre);
		setAnnee(annee);
	}

	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public long getNombre() {
		return nombre;
	}
	public void setNombre(long nombre) {
		this.nombre = nombre;
	}
}
