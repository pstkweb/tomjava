package lancement;

import java.awt.Color;
import javax.swing.JFrame;

import vue.VueTextuelle;

public class Lancer{
	public static void main(String args[]){
		VueTextuelle conteneur = new VueTextuelle();
		JFrame fenetre = new JFrame();
		fenetre.setBackground(Color.BLACK);
		fenetre.setContentPane(conteneur);
		fenetre.setTitle("phrasatique");
		fenetre.setSize(conteneur.getPreferredSize());
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
	}
}
