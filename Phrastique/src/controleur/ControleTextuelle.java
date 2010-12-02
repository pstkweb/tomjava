package controleur;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import vue.VueTextuelle;

public class ControleTextuelle implements MouseListener, MouseMotionListener {
	private VueTextuelle texte;
	private LinkedList<Component> relations;
	private Point positionDepart;
	private Point positionClick;
	private Point positionAnterieure = null;
	
	public ControleTextuelle(VueTextuelle texte){
		this.texte = texte;
		this.relations = texte.getComposantsGraphique();
		
		this.positionDepart = new Point();
		this.positionClick = new Point();
		
		for ( Component comp : relations){
			comp.addMouseListener(this);
			comp.addMouseMotionListener(this);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		/**
		 * si composant au meme endroit qu'un autre composant
		 * alors replacer à l'endroit de départ
		 */
		boolean possible = true;
		Point positionRel = new Point(e.getComponent().getX()+e.getX()-positionClick.x, 
				e.getComponent().getY()+e.getY()-positionClick.y);
		Point positionComp;
		int i = 0;
		int relX = positionRel.x;
		int relY = positionRel.y;
		int relWidth = e.getComponent().getWidth();
		int relHeight = e.getComponent().getHeight();
		
		do{
			positionComp = new Point(texte.getComponents()[i].getX(), texte.getComponents()[i].getY());
			int compX = positionComp.x;
			int compY = positionComp.y;
			int compWidth = texte.getComponents()[i].getWidth();
			int compHeight = texte.getComponents()[i].getHeight();
			
			/**
			 * combinaisons possible pour le que le placement ne soit pas autorisé
			 */
			if((relX+relWidth > compX && relY+relHeight > compY && relY+relHeight < compY+compHeight) ||
					(relX+relWidth > compX && relY > compY && relY < compY+compHeight) ||
					(relX+relWidth > compX && relY > compY && relY+relHeight < compY+compHeight) ||
					(relX > compX+compWidth && relY+relHeight > compY && relY+relHeight < compY+compHeight) ||
					(relX > compX+compWidth && relY > compY && relY < compY+compHeight) ||
					(relX > compX+compWidth && relY > compY && relY+relHeight < compY+compHeight)){
				possible = false;	
			}
			i++;
		}while(i<texte.getComponents().length && possible);
		if(!possible){
			e.getComponent().setLocation(positionDepart);
			texte.repaint();
		}
		positionAnterieure = null;
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
		int relX = positionRel.x;
		int relY = positionRel.y;
		int relWidth = e.getComponent().getWidth();
		int relHeight = e.getComponent().getHeight();
		
		do{
			positionComp = new Point(texte.getComponents()[i].getX(), texte.getComponents()[i].getY());
			int compX = positionComp.x;
			int compY = positionComp.y;
			int compWidth = texte.getComponents()[i].getWidth();
			int compHeight = texte.getComponents()[i].getHeight();
			
			/**
			 * combinaisons possible pour le que le placement ne soit pas autorisé
			 */
			if((relX+relWidth > compX && relY+relHeight > compY && relY+relHeight < compY+compHeight) ||
					(relX+relWidth > compX && relY > compY && relY < compY+compHeight) ||
					(relX+relWidth > compX && relY > compY && relY+relHeight < compY+compHeight) ||
					(relX > compX+compWidth && relY+relHeight > compY && relY+relHeight < compY+compHeight) ||
					(relX > compX+compWidth && relY > compY && relY < compY+compHeight) ||
					(relX > compX+compWidth && relY > compY && relY+relHeight < compY+compHeight)){
				possible = false;
			}
			if(e.getComponent().equals(texte.getComponents()[i])){
				possible = true;
			}
			i++;
		}while(i<texte.getComponents().length && possible);
		if(!possible){
			if(positionAnterieure == null){
				System.out.println("impossible Départ");
				e.getComponent().setLocation(positionDepart);
			}
			else{
				System.out.println("impossible Antérieure");
				e.getComponent().setLocation(positionAnterieure);
			}
		}
		else{
			System.out.println("possible");
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
