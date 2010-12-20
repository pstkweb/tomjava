package modele;

import java.awt.Color;

/**
 * Classe définissant une relation, qui est un idSource, un idCible et des tags. 
 * Elle possede aussi une couleur.
 * @author Thomas
 *
 */
public class Relation {
	private String idSource;
	private String idCible;
	private String tags;
	private Color couleur;
	
	
	/**
	 * Constructeur d'une Relation avec couleur
	 * @param idSource
	 * le numero de la phrase source
	 * @param idCible
	 * le numero de la phrase cible
	 * @param tags
	 * la chaine de caractère contenant les mots commun aux deux phrases
	 * @param couleur
	 * la couleur de la relation
	 */
	public Relation(String idSource, String idCible, String tags, Color couleur){
		this.idSource = idSource;
		this.idCible = idCible;
		this.tags = tags;
		this.couleur = couleur;
	}
	
	/**
	 * Constructeur d'une Relation sans couleur
	 * @param idSource
	 * le numero de la phrase source
	 * @param idCible
	 * le numero de la phrase cible
	 * @param tags
	 * la chaine de caractère contenant les mots commun aux deux phrases
	 */
	public Relation(String idSource, String idCible, String tags){
		this.idSource = idSource;
		this.idCible = idCible;
		this.tags = tags;
	}
	

	/**
	 * Donne le numero de phrase source
	 * @return
	 * numero de phrase source
	 */
	public String getIdSource() {
		return idSource;
	}


	
	/**
	 * Modifie le numero de phrase source
	 * @param idSource
	 * le nouveau numero de phrase source
	 */
	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}


	/**
	 * Donne le numero de phrase cible
	 * @return
	 * numero de phrase cible
	 */
	public String getIdCible() {
		return idCible;
	}


	/**
	 * Modifie le numero de phrase cible
	 * @param idCible
	 * le nouveau numero de phrase cible
	 */
	public void setIdCible(String idCible) {
		this.idCible = idCible;
	}


	/**
	 * Donne les mots communs aux deux phrases
	 * @return
	 * une chaine de caractere contenant les mots communs
	 */
	public String getTags() {
		return tags;
	}


	/**
	 * Modifie les mots communs aux deux phrases
	 * @param tags
	 * les nouveaux mots communs aux deux phrases
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	/**
	 * Donne la couleur de la relation
	 * @return
	 * la couleur de la relation
	 */
	public Color getCouleur() {
		return couleur;
	}


	/**
	 * Modifie la couleur de la relation
	 * @param couleur
	 * la nouvelle couleur de la relation
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}


	public String toString() {
		return "Relation [idCible=" + idCible + ", idSource=" + idSource
				+ ", tags=" + tags + "]";
	}
}

