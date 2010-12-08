package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import lancement.CreerFenetre;

public class ControleFenetre implements ActionListener {
	private CreerFenetre fenetre;
	
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
