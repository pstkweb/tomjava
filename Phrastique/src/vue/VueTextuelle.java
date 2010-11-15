package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import modele.Recuperation;
import modele.Relation;


public class VueTextuelle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Recuperation donnees = new Recuperation("exemple1.xml");
	
	
	public VueTextuelle(){
		super();
		this.setBackground(Color.WHITE);
		//this.setPreferredSize(new Dimension(500,500));
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		dessinerRelations(donnees);
		dessinerPhrases(donnees);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	public void dessinerPhrases(Recuperation donnees){
		Box bPhrase = new Box(BoxLayout.Y_AXIS);
		for(Enumeration<String> e = donnees.getTrans().getPhrases().keys(); e.hasMoreElements();){
			String key = e.nextElement();
			JTextPane t = new JTextPane();
			

			StyledDocument doc = t.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 14);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			t.setText(donnees.getTrans().getPhrases().get(key));
			t.setEditable(false);
			t.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
			
			bPhrase.add(t);
			bPhrase.add(Box.createVerticalStrut(20));
		}
		this.add(bPhrase);
	}
	
	public void dessinerRelations(Recuperation donnees){
		Box bRelation = new Box(BoxLayout.Y_AXIS);
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
			
			bRelation.add(p);
			bRelation.add(Box.createVerticalStrut(50));
		}
		this.add(bRelation);
	}
}
