package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Position;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;

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
		this.setLayout(new BorderLayout());
		//dessinerRelations(donnees);
		dessinerPhrases(donnees);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	public void dessinerPhrases(Recuperation donnees){
		JPanel phrases = new JPanel();
		phrases.setLayout(null);
		int posY = 0;
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
			phrases.add(t);
			//test de ouf
			Position end = t.getDocument().getEndPosition();
			String mama;
			try {
				mama = t.getText(0,t.getText().length());
				System.out.println(mama);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(end);
			
			//fin
			t.setBounds(0, posY, 500, 30);
			posY += 35;
		}
		this.add(phrases, BorderLayout.CENTER);
	}
	
	public void dessinerRelations(Recuperation donnees){
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
			
			this.add(p, BorderLayout.LINE_START);
		}
	}
}
