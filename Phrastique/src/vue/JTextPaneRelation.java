package vue;

import javax.swing.JTextPane;

import modele.Relation;

@SuppressWarnings("serial")
public class JTextPaneRelation extends JTextPane {
	private Relation relation;
	
	public JTextPaneRelation(Relation relation){
		super();
		this.relation = relation;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
}
