package controleur;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JPanel;

import vue.VueTextuelle;

public class ControleTextuelle implements MouseListener, MouseMotionListener {
	private VueTextuelle texte;
	private LinkedList<Component> composants;
	
	public ControleTextuelle(VueTextuelle texte){
		this.texte = texte;
		composants = texte.getComposantsGraphique();
		
		for ( Component comp : composants){
			comp.addMouseListener(this);
			comp.addMouseMotionListener(this);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		e.getComponent().setBounds(e.getXOnScreen(), e.getYOnScreen(), e.getComponent().getWidth(), e.getComponent().getHeight());
		texte.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e){
		e.getComponent().setBounds(e.getXOnScreen(), e.getYOnScreen(), e.getComponent().getWidth(), e.getComponent().getHeight());
		texte.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e){
		System.out.println("Mouse pressed");
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
