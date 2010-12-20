package vue;

import javax.swing.JTextPane;

import modele.Relation;

/**
 * Classe définissant un JTextPaneRelation qui est un JTextPane plus une 
 * Relation venant du modele
 * @author Thomas
 *
 */
@SuppressWarnings("serial")
public class JTextPaneRelation extends JTextPane {
	private Relation relation;
	
	/**
	 * Constructeur d'un JTextPaneRelation 
	 * @param relation
	 * la Relation qui sera lier au JTextPaneRelation
	 */
	public JTextPaneRelation(Relation relation){
		super();
		this.relation = relation;
	}

	/**
	 * Donne la Relation
	 * @return
	 * la Relation
	 */
	public Relation getRelation() {
		return relation;
	}

	/**
	 * Modifie la Relation
	 * @param relation
	 * la nouvelle Relation
	 */
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
}
