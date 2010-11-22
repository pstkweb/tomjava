package vue;

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
	private static final int largeurRelation = 200;
	private static final int espacementPhrase = 20;
	private JPanel jPhrases = new JPanel(null);
	private JPanel jRelationsGauche = new JPanel(null);
	private JPanel jRelationsDroite = new JPanel(null);
	
	
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
		jPhrases.setBackground(Color.LIGHT_GRAY);
		int posY = 50;
		
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
			jPhrases.add(textPanePhrase);
			int nbLignes = textPanePhrase.getText().length()/76 + 1;
			textPanePhrase.setBounds(0, posY, largeurPhrase, textPanePhrase.getPreferredSize().height*nbLignes - (nbLignes-1)*12);
			posY += textPanePhrase.getBounds().height+espacementPhrase;
		}
		this.add(jPhrases);
		
		jPhrases.setBounds(taillePanel.width/2-largeurPhrase/2, 0, largeurPhrase, taillePanel.height);
	}
	
	public void dessinerRelations(Recuperation donnees){
		Hashtable<String, Component> phrases = new Hashtable<String, Component>();
		
		for(Component ph : jPhrases.getComponents()){
			phrases.put(ph.getName(), ph);
		}
		for(Relation rel : donnees.getTrans().getRelations()){
			JTextPane textPaneRel = new JTextPane();
			StyledDocument doc = textPaneRel.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 12);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			textPaneRel.setEditable(false);
			textPaneRel.setText(rel.getTags());
			int nbLignes = textPaneRel.getText().length()/30 + 1;
			if(jRelationsGauche.getComponentCount()>jRelationsDroite.getComponentCount()){
				jRelationsDroite.add(textPaneRel);
			}else{
				jRelationsGauche.add(textPaneRel);
			}
			//System.out.println(pos);
			if(jPhrases.getComponentCount() > 0){
				textPaneRel.setBounds(0,((phrases.get(rel.getIdCible()).getY()-espacementPhrase)
						+phrases.get(rel.getIdCible()).getY())/2
						-(textPaneRel.getPreferredSize().height*nbLignes-(nbLignes-1)*4)/2 , largeurRelation, 
						textPaneRel.getPreferredSize().height*nbLignes - (nbLignes-1)*4);
			}
		}
		this.add(jRelationsGauche);
		this.add(jRelationsDroite);
		jRelationsGauche.setBounds(jPhrases.getX()-largeurRelation, 0, largeurRelation, taillePanel.height);
		jRelationsDroite.setBounds(jPhrases.getX()+500, 0, largeurRelation, taillePanel.height);
	}
}
