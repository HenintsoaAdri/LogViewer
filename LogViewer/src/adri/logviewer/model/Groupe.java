package adri.logviewer.model;

import java.util.Set;

import adri.logviewer.exception.InputException;

public class Groupe extends BaseModel{
	
	private String nom;
	private String description = "Aucune description";
	private Set<Agent> listeAgent;
	private Set<Profil> listeProfil;
	
	public Groupe(){}
	public Groupe(int id) {
		super(id);
	}	
	public Groupe(int id, String nom, String description, Set<Agent> listeAgent) throws InputException {
		super(id);
		this.setNom(nom);
		this.setDescription(description);
		this.setListeAgent(listeAgent);
	}

	public String getNom() {
		if(nom == null){
			return "";
		}
		return nom;
	}
	public void setNom(String nom) throws InputException {
		if(nom == null || nom.isEmpty()){
			throw new InputException("Un nom est requis");
		}
		this.nom = nom;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if(description != null){
			this.description = description;
		}
	}
	
	public Set<Agent> getListeAgent() {
		return listeAgent;
	}
	public void setListeAgent(Set<Agent> listeAgent) {
		this.listeAgent = listeAgent;
	}
	
	public Set<Profil> getListeProfil() {
		return listeProfil;
	}
	public void setListeProfil(Set<Profil> listeProfil) {
		this.listeProfil = listeProfil;
	}

	@Override
	public String toString() {
		return "Groupe #" + getId()
				+" [nom = " + getNom() + "]";
	}
	@Override
	public String getName() {
		return "le groupe " + getNom();
	}
}