package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Enumeration;
import javax.swing.JPanel;

import modele.Recuperation;

@SuppressWarnings("serial")
public class VueTextuelle extends JPanel {
	Recuperation donnees = new Recuperation("exemple1.xml");
	
	public VueTextuelle(){
		super();
		FlowLayout bl=new FlowLayout(FlowLayout.CENTER);
		this.setLayout(bl);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(800,800));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		dessinerPhrases(g, donnees);
	}
	
	public void dessinerPhrases(Graphics g, Recuperation donnees){
		int i = 0;
		int debutRect = 0;
		for( Enumeration<String> e = donnees.getTrans().getPhrases().elements() ; e.hasMoreElements();){
			String phrase = e.nextElement();
			int nbLignes = 0;
			if(g.getFontMetrics().stringWidth(phrase) > super.getWidth()*0.6){
				String testTaille = phrase;
				while(g.getFontMetrics().stringWidth(phrase) > super.getWidth()*0.6){
					while(g.getFontMetrics().stringWidth(testTaille)-g.getFontMetrics().charWidth(phrase.charAt(testTaille.length()-1)) 
							> super.getWidth()*0.6 || phrase.charAt(testTaille.length()-1) != ' ')
					{
						String temp = testTaille.substring(0, testTaille.length()-1);
						testTaille = temp;
					}
					String phrase1 = phrase.substring(0, testTaille.length()-1);
					g.drawString(phrase1, super.getWidth()/2-g.getFontMetrics().stringWidth(phrase1)/2, 100+i);
					nbLignes++;
					i += g.getFontMetrics().getHeight();
					String phrase2 = phrase.substring(testTaille.length());
					phrase = phrase2;
				}
				g.drawString( phrase,  super.getWidth()/2-g.getFontMetrics().stringWidth(phrase)/2, 100+i);
				nbLignes++; 
				g.drawRoundRect((int) (super.getWidth()/2-super.getWidth()*0.3-2) , 85+debutRect , 
						(int) (super.getWidth()*0.6+2) , g.getFontMetrics().getHeight()*nbLignes+3 , 15 , 20);
				debutRect += nbLignes*g.getFontMetrics().getHeight()+15;
				i += g.getFontMetrics().getHeight()+15;
			}
			else{
				g.drawString( phrase , super.getWidth()/2-g.getFontMetrics().stringWidth(phrase)/2, 100+i);
				g.drawRoundRect((int) (super.getWidth()/2-super.getWidth()*0.3-2) , 85+i,
						(int) (super.getWidth()*0.6+2),g.getFontMetrics().getHeight()+3, 15, 20);
				debutRect += g.getFontMetrics().getHeight()+15;
				i += g.getFontMetrics().getHeight()+15;
			}
		}
	}
}
