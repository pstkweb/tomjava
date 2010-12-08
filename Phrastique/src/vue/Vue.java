package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
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
			JTextPane textPanePhrase = new JTextPane();
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
			JTextPane textPaneRel = new JTextPane();
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
	
	public LinkedList<JTextPane> getComposantsGraphiqueRelation(){
		LinkedList<JTextPane> listeRelation = new LinkedList<JTextPane>();
		for(Component e : this.getComponents()){
			if(e.getName().equals("Relation")){
				listeRelation.add((JTextPane)e);
			}
		}
		
		return listeRelation;
	}
		
	public LinkedList<JTextPane> getComposantsGraphiquePhrases(){
		LinkedList<JTextPane> listePhrases = new LinkedList<JTextPane>();
		for(Component e :this.getComponents()){
			if(e.getName().startsWith("P")){
				listePhrases.add((JTextPane)e);
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
