package lancement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vue.VueTextuelle;

public class Lancer extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel vueTextuelle;
	
	public Lancer(){
		super(new BorderLayout());
		vueTextuelle = new VueTextuelle();
		vueTextuelle.setBackground(Color.LIGHT_GRAY);
		JScrollPane scroller = new JScrollPane(vueTextuelle);
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight();
		double largeur = tailleEcran.getWidth();
        scroller.setPreferredSize(new Dimension((int)(largeur*0.6),(int)(hauteur*0.9)));
        scroller.getViewport().setViewPosition(new Point(550,0));
        add(scroller, BorderLayout.CENTER);
	}
	public static void main(String args[]){
		JFrame fenetre = new JFrame("phrasatique");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent contentPane = new Lancer();
		fenetre.setContentPane(contentPane);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
	}
}
