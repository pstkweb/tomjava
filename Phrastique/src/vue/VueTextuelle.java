package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JPanel;

import modele.Recuperation;

@SuppressWarnings("serial")
public class VueTextuelle extends JPanel {
	Recuperation donnees = new Recuperation("exemple1.xml");
	
	public VueTextuelle(){
		super();
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(800,500));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		dessinerPhrases(g, donnees);
	}
	
	public void dessinerPhrases(Graphics g, Recuperation donnees){
		int i = 0; 
		for( Enumeration<String> e = donnees.getTrans().getPhrases().elements() ; e.hasMoreElements();){
			g.drawString(e.nextElement(),100, 100+i);
			g.drawRoundRect(95, 85+i,g.getFontMetrics().stringWidth(e.nextElement())+8, g.getFontMetrics().getHeight()+3, 15, 20);
			i += g.getFontMetrics().getHeight()+3;
		}
	}
}
