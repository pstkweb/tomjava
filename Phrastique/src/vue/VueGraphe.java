package vue;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import modele.Phrase;
import modele.Recuperation;

public class VueGraphe extends Vue{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Recuperation donnees = new Recuperation("exemple1.xml");
	private static final int espacementPhrase = 20;
	private static final int largeurPhrase = 500;
	private static final int largeurRelation = 200;
	private static final Dimension taillePanel = new Dimension(2000,1500);
	
	
	public VueGraphe(){
		super();
		dessinerPhrases(donnees);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
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
			posY += textPanePhrase.getBounds().height+espacementPhrase ;
		}
		
	}
}
