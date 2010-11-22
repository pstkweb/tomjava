package vue;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import modele.Recuperation;

public class VueGraphe extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Recuperation donnees = new Recuperation("exemple1.xml");
	private static final Dimension taillePanel = new Dimension(2000,1000);
	private JPanel jPhrases = new JPanel(null);
	private JPanel jRelations = new JPanel(null);
	
	public VueGraphe(){
		super(null);
		this.setPreferredSize(taillePanel);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}	
}
