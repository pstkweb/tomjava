package lancement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vue.VueGraphe;
import vue.VueTextuelle;
import controleur.ControleFenetre;
import controleur.ControleTextuelle;;

/**
 * @author Thomas
 *
 */
public class CreerFenetre extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VueTextuelle vueTextuelle;
	private VueGraphe vueGraphe;
	private ControleTextuelle contTexte;
	private ControleFenetre contFenetre;
	private JScrollPane scroller;
	
	/**
	 * créer un objet vueTextuelle, le met dans un JScrollPane et defini sa taille
	 */
	public CreerFenetre(){
		super(new BorderLayout());
		vueTextuelle = new VueTextuelle();
		setVueGraphe(new VueGraphe());
		contTexte = new ControleTextuelle(vueTextuelle);
		contFenetre = new ControleFenetre(this);
		vueTextuelle.setBackground(Color.LIGHT_GRAY);
		scroller = new JScrollPane(vueTextuelle);
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double hauteur = tailleEcran.getHeight();
		double largeur = tailleEcran.getWidth();
        scroller.setPreferredSize(new Dimension((int)(largeur*0.6),(int)(hauteur*0.88)));
        scroller.getViewport().setViewPosition(new Point(550,0));
        scroller.getVerticalScrollBar().setUnitIncrement(10);
        add(scroller, BorderLayout.CENTER);
	}
	
	

	public void setContenu(JPanel contenu){
		this.scroller.setViewportView(contenu);
	}
	
	public JMenuBar creerMenu(){
		JMenuBar menuBar  = new JMenuBar();
		JMenu menu = new JMenu("Choix Vue");
        menuBar.add(menu);
        JMenuItem menuItemVue1 = new JMenuItem("Vue Textuelle");
        menuItemVue1.setName("vueTexte");
        menuItemVue1.addActionListener(contFenetre);
        menu.add(menuItemVue1);
        JMenuItem menuItemVue2 = new JMenuItem("Vue Graphe");
        menuItemVue2.setName("vueGraphe");
        menuItemVue2.addActionListener(contFenetre);
        menu.add(menuItemVue2);
        return menuBar;
	}
	
	/**
	 * @param args
	 * 
	 * le main qui créer une JFrame et qui prend comme contentPane un objet CreerFenetre
	 */
	public static void main(String args[]){
		JFrame fenetre = new JFrame("phrasatique");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CreerFenetre contentPane = new CreerFenetre();
		fenetre.setContentPane(contentPane);
		
		//creation du Menu
		JMenuBar menuBar = contentPane.creerMenu(); 		
		fenetre.setJMenuBar(menuBar);
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
	}

	public void setVueGraphe(VueGraphe vueGraphe) {
		this.vueGraphe = vueGraphe;
	}

	public VueGraphe getVueGraphe() {
		return vueGraphe;
	}
	public VueTextuelle getVueTextuelle() {
		return vueTextuelle;
	}

	public void setVueTextuelle(VueTextuelle vueTextuelle) {
		this.vueTextuelle = vueTextuelle;
	}
}
