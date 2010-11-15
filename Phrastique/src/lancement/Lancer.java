package lancement;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import vue.VueTextuelle;

public class Lancer{
	public static void main(String args[]){
		VueTextuelle conteneur = new VueTextuelle();
		JFrame fenetre = new JFrame();
		fenetre.setBackground(Color.BLACK);
		JScrollPane scroll = new JScrollPane(conteneur);
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight();
		double largeur = tailleEcran.getWidth();
		conteneur.setPreferredSize(new Dimension((int)(largeur*0.6),(int)(hauteur*0.95)));
		fenetre.setContentPane(scroll);
		fenetre.setTitle("phrasatique");
		fenetre.setSize(conteneur.getPreferredSize());
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		
		fenetre.setVisible(true);
	}
}
