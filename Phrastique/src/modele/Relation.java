package modele;

import java.awt.Color;

public class Relation {
	private String idSource;
	private String idCible;
	private String tags;
	private Color couleur;
	
	
	public Relation(String idSource, String idCible, String tags, Color couleur){
		this.idSource = idSource;
		this.idCible = idCible;
		this.tags = tags;
		this.couleur = couleur;
	}
	

	public String getIdSource() {
		return idSource;
	}


	
	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}


	public String getIdCible() {
		return idCible;
	}


	public void setIdCible(String idCible) {
		this.idCible = idCible;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public Color getCouleur() {
		return couleur;
	}


	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}


	public String toString() {
		return "Relation [idCible=" + idCible + ", idSource=" + idSource
				+ ", tags=" + tags + "]";
	}
}

