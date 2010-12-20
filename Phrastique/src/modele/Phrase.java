package modele;

/**
 * Classe définisant une phrase qui est un id et un contenu (la phrase).
 * @author Thomas
 */
public class Phrase {
	private String id;
	private String contenu;
	
	/**
	 * Constructeur d'une phrase
	 * @param id
	 * le numero de la phrase
	 * @param contenu
	 * la chaine de caractère correspondant à la phrase
	 */
	public Phrase(String id, String contenu){
		this.setId(id);
		this.setContenu(contenu);
	}

	/**
	 * Modifie l'identifiant de la phrase
	 * @param id
	 * Le nouveau numero de la phrase
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Donne le numero de la phrase
	 * @return
	 * numero de la phrase
	 */
	public String getId() {
		return id;
	}

	/**
	 * Modifie la phrase
	 * @param contenu
	 * la nouvelle phrase
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * Donne la phrase
	 * @return
	 * la phrase
	 */
	public String getContenu() {
		return contenu;
	}
}
