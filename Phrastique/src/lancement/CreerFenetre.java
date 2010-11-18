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

/**
 * @author Thomas
 *
 */
public class CreerFenetre extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel vueTextuelle;
	
	/**
	 * créer un objet vueTextuelle, le met dans un JScrollPane et defini sa taille
	 */
	public CreerFenetre(){
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
	/**
	 * @param args
	 * 
	 * le main qui créer une JFrame et qui prend comme contentPane un objet CreerFenetre
	 */
	public static void main(String args[]){
		JFrame fenetre = new JFrame("phrasatique");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent contentPane = new CreerFenetre();
		fenetre.setContentPane(contentPane);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
	}
}
