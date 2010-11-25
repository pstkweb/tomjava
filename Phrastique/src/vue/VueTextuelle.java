package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import modele.BordureArrondi;
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
		for(Component comp : this.getComponents()){
			if(comp.getName().equals("Relation")){
				BordureArrondi bord = new BordureArrondi(Color.BLACK, 200, 100);
				bord.paintBorder(comp, g, comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight());
				passerPointille(g);
				g.drawLine(comp.getX(), comp.getY()+comp.getHeight()/2-5, 600, 50);
				passerPlein(g);
			}
		}
		
	}
	
	public Graphics2D passerPointille(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		float epaisseur = 3; /** taille de la ligne */
		float[] style = {10,5}; /** les pointillés seront 2 fois plus long que les blancs */
		g2d.setStroke( new BasicStroke(epaisseur, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
				10.0f, style, 0 ));
		return g2d;
	}
	
	public Graphics passerPlein(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke());
		return g;
	}
	
	/**
	 * @param donnees
	 * 
	 * rempli le JPanel phrases contenant la liste des phrases du modele, ce JPanel n'utilise pas de 
	 * layout manager il est positionner statiquement au mileu du JPanel vueTextuelle, les phrases 
	 * sont misent dans des JTextPane.
	 */
	public void dessinerPhrases(Recuperation donnees){
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
			this.add(textPanePhrase);
			int nbLignes = textPanePhrase.getText().length()/76 + 1;
			textPanePhrase.setBounds(taillePanel.width/2-largeurPhrase/2, posY, largeurPhrase, textPanePhrase.getPreferredSize().height*nbLignes - (nbLignes-1)*12);
			posY += textPanePhrase.getBounds().height+espacementPhrase;
		}
		
	}
	
	public void dessinerRelations(Recuperation donnees){
		Hashtable<String, Component> phrases = new Hashtable<String, Component>();
		LinkedList<Liaison> liaisons = new LinkedList<Liaison>();
				
		for(Component ph : this.getComponents()){
			phrases.put(ph.getName(), ph);
		}
		boolean position = true; // position a gauche
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
			int nbLignes = textPaneRel.getText().length()/30 + 1;
			if(position == true){
				this.add(textPaneRel);
				textPaneRel.setBounds(taillePanel.width/4-largeurRelation/2,((phrases.get(rel.getIdCible()).getY()-espacementPhrase)
						+phrases.get(rel.getIdCible()).getY())/2
						-(textPaneRel.getPreferredSize().height*nbLignes-(nbLignes-1)*4)/2 , largeurRelation, 
						textPaneRel.getPreferredSize().height*nbLignes - (nbLignes-1)*4);
				position = false;
			}else{
				this.add(textPaneRel);
				textPaneRel.setBounds(taillePanel.width*3/4-largeurRelation/2,((phrases.get(rel.getIdCible()).getY()-espacementPhrase)
						+phrases.get(rel.getIdCible()).getY())/2
						-(textPaneRel.getPreferredSize().height*nbLignes-(nbLignes-1)*4)/2 , largeurRelation, 
						textPaneRel.getPreferredSize().height*nbLignes - (nbLignes-1)*4);
				position = true;
			}
		}
	}
}
