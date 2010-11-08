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
			String phrase = e.nextElement();
			if(phrase.length() > 95){
				int j = 95;
				while(phrase.charAt(j) != ' '){
					j--;
				}
				String phrase1 = phrase.substring(0, j);
				String phrase2 = phrase.substring(j+1);
				g.drawString(phrase1, 100, 100+i);
				g.drawRoundRect(95, 85+i, g.getFontMetrics().stringWidth(phrase1)+8, g.getFontMetrics().getHeight()*2+3, 15, 20);
				i += g.getFontMetrics().getHeight();
				g.drawString(phrase2, 100, 100+i);
				i += g.getFontMetrics().getHeight()+15;
			}else{
				g.drawString(phrase ,100, 100+i);
				g.drawRoundRect(95, 85+i,g.getFontMetrics().stringWidth(phrase)+8, g.getFontMetrics().getHeight()+3, 15, 20);
				i += g.getFontMetrics().getHeight()+15;
			}
		}
	}
}
