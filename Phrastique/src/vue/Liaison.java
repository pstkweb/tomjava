package vue;

import java.awt.Color;

import javax.swing.JTextPane;


public class Liaison {
	private JTextPane phraseSource;
	private JTextPane phraseCible;
	private JTextPane relation;
	private Color couleur;
	
	public Liaison (JTextPane phraseSource, JTextPane phraseCible, JTextPane relation, Color couleur){
		this.phraseSource = phraseSource;
		this.phraseCible = phraseCible;
		this.relation = relation;
		this.couleur = couleur;
	}

	public JTextPane getPhraseSource() {
		return phraseSource;
	}

	public void setPhraseSource(JTextPane phraseSource) {
		this.phraseSource = phraseSource;
	}

	public JTextPane getPhraseCible() {
		return phraseCible;
	}

	public void setPhraseCible(JTextPane phraseCible) {
		this.phraseCible = phraseCible;
	}

	public JTextPane getRelation() {
		return relation;
	}

	public void setRelation(JTextPane relation) {
		this.relation = relation;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
}
