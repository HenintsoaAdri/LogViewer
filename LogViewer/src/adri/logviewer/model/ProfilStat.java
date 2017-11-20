package adri.logviewer.model;

import java.text.NumberFormat;

public class ProfilStat extends Profil{
	private float valeur;
	private int nombre;
	
	public ProfilStat() {
		super();
	}

	public float getValeur() {
		return valeur;
	}
	public String getValeurPourcent() {
		return NumberFormat.getPercentInstance().format(valeur);
	}
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

	public int getNombre() {
		return nombre;
	}
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	@Override
	public String instance() {
		return getClass().getSuperclass().getSimpleName();
	}
}
