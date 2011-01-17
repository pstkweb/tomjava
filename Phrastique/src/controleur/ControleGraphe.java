package controleur;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import vue.JTextPanePhrase;
import vue.JTextPaneRelation;
import vue.VueGraphe;

/**
 * Classe controleur de la vue graphe, elle implemente MouseListener et MouseMotionListener afin 
 * de permettre a l'utilisateur d'interagir avec les composants contenus dans cette vue. 
 * L'interaction se fait par glisser-deposer.
 * @author Thomas
 */
public class ControleGraphe implements MouseListener, MouseMotionListener {
	private VueGraphe graphe;
	private LinkedList<JTextPaneRelation> relations;
	private LinkedList<JTextPanePhrase> phrases;
	private Point positionDepart;
	private Point positionClick;
	private Point positionAnterieure = null;
	private static final int margeSuperposition = 5;
	
	/**
	 * Creer un controleGraphe contenant un objet VueGraphe passe en parametre. Contient
	 * egalement deux listes (les relations et les phrases) pour lesquels on ajoute 
	 * des listeners sur chacun de leurs elements.
	 * @param graphe
	 * la vue graphe a mettre en place
	 */
	public ControleGraphe(VueGraphe graphe){
		this.graphe = graphe;
		this.relations = graphe.getComposantsGraphiqueRelation();
		this.phrases = graphe.getComposantsGraphiquePhrases();
		this.positionDepart = new Point();
		this.positionClick = new Point();
		
		for ( Component comp : relations){
			comp.addMouseListener(this);
			comp.addMouseMotionListener(this);
		}
		for (Component comp : phrases){
			comp.addMouseListener(this);
			comp.addMouseMotionListener(this);
		}
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		/**
		 * si composant au meme endroit qu'un autre composant
		 * alors replacer a l'endroit precedent
		 */
		boolean possible = true;
		Point positionRel = new Point(e.getComponent().getX()+e.getX()-positionClick.x, 
				e.getComponent().getY()+e.getY()-positionClick.y);
		Point positionComp;
		int i = 0;
		int relX = positionRel.x-margeSuperposition;
		int relY = positionRel.y-margeSuperposition;
		int relWidth = e.getComponent().getWidth()+2*margeSuperposition;
		int relHeight = e.getComponent().getHeight()+2*margeSuperposition;
		
		do{
			positionComp = new Point(graphe.getComponents()[i].getX(), graphe.getComponents()[i].getY());
			int compX = positionComp.x-margeSuperposition;
			int compY = positionComp.y-margeSuperposition;
			int compWidth = graphe.getComponents()[i].getWidth()+2*margeSuperposition;
			int compHeight = graphe.getComponents()[i].getHeight()+2*margeSuperposition;
			
			/**
			 * combinaisons possible pour le que le placement ne soit pas autorisé
			 */
			if((relX+relWidth > compX && relX+relWidth < graphe.getTaillePanel().width/2 
					&& relY+relHeight >= compY && relY+relHeight <= compY+compHeight) ||
					(relX+relWidth > compX && relX+relWidth < graphe.getTaillePanel().width/2 
							&& relY >= compY && relY <= compY+compHeight) ||
					(relX+relWidth > compX && relX+relWidth < graphe.getTaillePanel().width/2 
							&& relY <= compY && relY+relHeight >= compY+compHeight) ||
					(relX < compX+compWidth && relX+relWidth > graphe.getTaillePanel().width/2 
							&& relY+relHeight >= compY && relY+relHeight <= compY+compHeight) ||
					(relX < compX+compWidth && relX+relWidth > graphe.getTaillePanel().width/2 
							&& relY >= compY && relY <= compY+compHeight)||
						(relX < compX+compWidth && relX+relWidth > graphe.getTaillePanel().width/2 
								&& relY <= compY && relY+relHeight >= compY+compHeight)){
				possible = false;
			}
			if(e.getComponent().equals(graphe.getComponents()[i])){
				possible = true;
			}
			i++;
		}while(i<graphe.getComponentCount() && possible);
		if(!possible){
			if(positionAnterieure == null){
				e.getComponent().setLocation(positionDepart);
			}
			else{
				e.getComponent().setLocation(positionAnterieure);
			}
		}
		else{
			positionAnterieure = positionRel;
			e.getComponent().setLocation(positionRel);
		}
		graphe.repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		/**
		 * stocke la position de départ dans une variable pour être réutiliser si besoin
		 */
		positionDepart.move(e.getComponent().getX(), e.getComponent().getY());
		positionClick.move(e.getX(),e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
