package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import lancement.CreerFenetre;

/**
 * Classe controleur de la fenetre, elle implemente ActionListener pour reagir
 * aux actions sur le menu.
 * 
 * @author Thomas
 */
public class ControleFenetre implements ActionListener {
	/**
	 * fenetre de navigation.
	 */
	private CreerFenetre fenetre;

	/**
	 * Permet de creer un controleur d'un objet CreerFenetre qui contient cet
	 * objet.
	 * 
	 * @param fenetre
	 *            l'objet CreerFenetre a mettre en place
	 */
	public ControleFenetre( CreerFenetre fenetre ) {
		this.fenetre = fenetre;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if ( ( ( JMenuItem ) e.getSource() ).getName().equals( "vueGraphe" ) ) {
			fenetre.setContenu( fenetre.getVueGraphe() );
		} else if ( ( ( JMenuItem ) e.getSource() ).getName().equals( "vueTexte" ) ) {
			fenetre.setContenu( fenetre.getVueTextuelle() );
		}
	}

}
