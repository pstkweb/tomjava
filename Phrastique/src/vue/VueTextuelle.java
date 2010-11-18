package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Hashtable;

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


public class VueTextuelle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Recuperation donnees = new Recuperation("exemple1.xml");
	private static final Dimension taillePanel = new Dimension(2000,1000);
	private static final int largeurPhrase = 500;
	private JPanel phrases = new JPanel(null);
	private JPanel relations = new JPanel(null);
	
	
	/**
	 * Creer le JPanel qui contient la vue Textuelle, ce JPanel a une taille defini en statique
	 * ainsi que les deux JPanel qui contiendront les phrases et les relations
	 * 
	 */
	public VueTextuelle(){
		super(null);
		dessinerPhrases(donnees);
		dessinerRelations(donnees);
		this.setPreferredSize(taillePanel);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	/**
	 * @param donnees
	 * 
	 * rempli le JPanel phrases contenant la liste des phrases du modele, ce JPanel n'utilise pas de 
	 * layout manager il est positionner statiquement au mileu du JPanel vueTextuelle, les phrases 
	 * sont misent dans des JTextPane.
	 */
	public void dessinerPhrases(Recuperation donnees){
		phrases.setBackground(Color.LIGHT_GRAY);
		int posY = 50;
		
		for(Phrase phrase : donnees.getTrans().getPhrases()){
			JTextPane t = new JTextPane();
			StyledDocument doc = t.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 14);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			t.setText(phrase.getId()+" : "+phrase.getContenu());
			t.setEditable(false);
			t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
			phrases.add(t);
			int nbLignes = t.getText().length()/76 + 1;
			t.setBounds(0, posY, largeurPhrase, t.getPreferredSize().height*nbLignes - (nbLignes-1)*12);
			posY += t.getBounds().height+10;
		}
		this.add(phrases);
		
		phrases.setBounds(taillePanel.width/2-largeurPhrase/2, 0, largeurPhrase, taillePanel.height);
	}
	
	public void dessinerRelations(Recuperation donnees){
		Hashtable<String, Component> phrase = new Hashtable<String, Component>();
		
		for(Component ph : phrases.getComponents()){
			phrase.put(ph.getName(), ph);
		}
		
		for(Relation rel : donnees.getTrans().getRelations()){
			JTextPane p = new JTextPane();
			StyledDocument doc = p.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 14);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			p.setText(rel.getTags());
			relations.add(p);
			if(phrases.getComponentCount() > 0){
				p.setBounds(0, phrase.get(rel.getIdSource()).getY(), 100, 100);
			}
		}
		this.add(relations);
		
		relations.setBounds(taillePanel.width/4, 0, taillePanel.width/4, taillePanel.height);
	}
}
