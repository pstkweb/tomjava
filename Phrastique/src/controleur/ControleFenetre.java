package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import lancement.CreerFenetre;

/**
 * Classe controleur de la fenetre, elle implemente ActionListener pour r�agir aux 
 * actions sur le menu.
 * @author Thomas
 */
public class ControleFenetre implements ActionListener {
	private CreerFenetre fenetre;
	
	/**
	 * Permet de cr�er un controleur d'un objet CreerFenetre qui contient cet objet
	 * @param fenetre
	 * l'objet CreerFenetre � mettre en place
	 */
	public ControleFenetre(CreerFenetre fenetre){
		this.fenetre = fenetre;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JMenuItem)e.getSource()).getName().equals("vueGraphe")){
			fenetre.setContenu(fenetre.getVueGraphe());
		}
		else if(((JMenuItem)e.getSource()).getName().equals("vueTexte")){
			fenetre.setContenu(fenetre.getVueTextuelle());
		}
	}

}
