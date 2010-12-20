package controleur;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import vue.JTextPaneRelation;
import vue.VueTextuelle;

/**
 * Classe controleur de la vue textuelle, elle implemente MouseListener, et MouseMotionListener afin
 * de permettre à l'utilisateur d'interagir avec les composants contenus dans cette vue. 
 * L'interaction se fait par glisser-déposer.
 * @author Thomas
 */
public class ControleTextuelle implements MouseListener, MouseMotionListener {
	private VueTextuelle texte;
	private LinkedList<JTextPaneRelation> relations;
	private Point positionDepart;
	private Point positionClick;
	private Point positionAnterieure = null;
	private static final int margeSuperposition = 5;
	
	/**
	 * Creer un controleTextuelle contenant un objet VueTextuelle passé en paramètre. Contient
	 * également une listes de relations pour laquelle on ajoute des listeners sur chacun
	 * de ses éléments.
	 * @param texte
	 * la vue textuelle à mettre en place
	 */
	public ControleTextuelle(VueTextuelle texte){
		this.texte = texte;
		this.relations = texte.getComposantsGraphiqueRelation();
		
		this.positionDepart = new Point();
		this.positionClick = new Point();
		
		for ( Component comp : relations){
			comp.addMouseListener(this);
			comp.addMouseMotionListener(this);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
	}

	@Override
	public void mouseDragged(MouseEvent e){
		/**
		 * si composant au meme endroit qu'un autre composant
		 * alors replacer à l'endroit précédent
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
			positionComp = new Point(texte.getComponents()[i].getX(), texte.getComponents()[i].getY());
			int compX = positionComp.x-margeSuperposition;
			int compY = positionComp.y-margeSuperposition;
			int compWidth = texte.getComponents()[i].getWidth()+2*margeSuperposition;
			int compHeight = texte.getComponents()[i].getHeight()+2*margeSuperposition;
			
			/**
			 * combinaisons possible pour le que le placement ne soit pas autorisé
			 */
			if((relX+relWidth > compX && relX+relWidth < texte.getTaillePanel().width/2 
					&& relY+relHeight >= compY && relY+relHeight <= compY+compHeight) ||
					(relX+relWidth > compX && relX+relWidth < texte.getTaillePanel().width/2 
							&& relY >= compY && relY <= compY+compHeight) ||
					(relX+relWidth > compX && relX+relWidth < texte.getTaillePanel().width/2 
							&& relY <= compY && relY+relHeight >= compY+compHeight) ||
					(relX < compX+compWidth && relX+relWidth > texte.getTaillePanel().width/2 
							&& relY+relHeight >= compY && relY+relHeight <= compY+compHeight) ||
					(relX < compX+compWidth && relX+relWidth > texte.getTaillePanel().width/2 
							&& relY >= compY && relY <= compY+compHeight)||
						(relX < compX+compWidth && relX+relWidth > texte.getTaillePanel().width/2 
								&& relY <= compY && relY+relHeight >= compY+compHeight)){
				possible = false;
			}
			if(e.getComponent().equals(texte.getComponents()[i])){
				possible = true;
			}
			i++;
		}while(i<texte.getComponentCount() && possible);
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
		texte.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e){
		/**
		 * stocke la position de départ dans une variable pour être réutiliser si besoin
		 */
		positionDepart.move(e.getComponent().getX(), e.getComponent().getY());
		positionClick.move(e.getX(),e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e){}
	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
}
