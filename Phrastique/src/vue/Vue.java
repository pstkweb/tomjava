package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import modele.Phrase;
import modele.Recuperation;
import modele.Relation;

public  class Vue extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Recuperation donnees = new Recuperation("exemple1.xml");
	private static final Dimension taillePanel = new Dimension(2000,1500);
	private static final int largeurPhrase = 500;
	private static final int largeurRelation = 200;
	private static final int espacementPhrase = 20;
	
	public Vue(){
		super(null);
		this.setPreferredSize(taillePanel);
		dessinerPhrases(donnees);
		dessinerRelations(donnees);
	}
	
	public void dessinerPhrases(Recuperation donnees){
		for(Phrase p : donnees.getTrans().getPhrases()){
			JTextPanePhrase textPanePhrase = new JTextPanePhrase(p);
			StyledDocument doc = textPanePhrase.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 14);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			textPanePhrase.setText(p.getId()+" : "+p.getContenu());
			textPanePhrase.setEditable(false);
			textPanePhrase.setName(p.getId());
			textPanePhrase.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
			this.add(textPanePhrase);
		}
	}
	
	public void dessinerRelations(Recuperation donnees){
		for(Relation rel : donnees.getTrans().getRelations()){
			JTextPaneRelation textPaneRel = new JTextPaneRelation(rel);
			textPaneRel.setName("Relation");
			textPaneRel.setOpaque(false);
			StyledDocument doc = textPaneRel.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 12);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			textPaneRel.setEditable(false);
			textPaneRel.setText(rel.getTags());
			this.add(textPaneRel);
		}
	}
	
	public LinkedList<JTextPaneRelation> getComposantsGraphiqueRelation(){
		LinkedList<JTextPaneRelation> listeRelation = new LinkedList<JTextPaneRelation>();
		for(Component e : this.getComponents()){
			if(e instanceof JTextPaneRelation){
				listeRelation.add((JTextPaneRelation) e);
			}
		}
		
		return listeRelation;
	}
		
	public LinkedList<JTextPanePhrase> getComposantsGraphiquePhrases(){
		LinkedList<JTextPanePhrase> listePhrases = new LinkedList<JTextPanePhrase>();
		for(Component e :this.getComponents()){
			if(e instanceof JTextPanePhrase){
				listePhrases.add((JTextPanePhrase)e);
			}
		}
		return listePhrases;
	}
		
	

	public Recuperation getDonnees() {
		return donnees;
	}

	public void setDonnees(Recuperation donnees) {
		this.donnees = donnees;
	}

	public Dimension getTaillePanel() {
		return taillePanel;
	}

	public int getLargeurPhrase() {
		return largeurPhrase;
	}

	public int getLargeurRelation() {
		return largeurRelation;
	}
	
	public int getEspacementPhrase() {
		return espacementPhrase;
	}
}
