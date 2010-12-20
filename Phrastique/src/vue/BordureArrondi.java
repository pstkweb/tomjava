package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;
 
 
 
 
/**
 * Classe permettant de créer une bordure arrondie qui implemente Border
 * @author Thomas
 *
 */
public class BordureArrondi implements Border {  
	
	private Color couleur;
	
	
	/**
	 * Constructeur d'une bordure arrondie avec une couleur
	 * @param couleur
	 * couleur de la bordure
	 */
	public BordureArrondi(Color couleur)  {
		this.couleur = couleur;
	}  
 	
	
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)  {   
        int adjust = 10;
        //pour eviter les escalier sur l'arrondi
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillOval(x, y-adjust, width, height+adjust);
        g2.setColor(this.couleur);//ou une autre couleur que tu peux rendre paramétrable
        g2.drawOval(x, y-adjust, width, height+adjust);
	}  
	
	
	
	public Insets getBorderInsets(Component c)  {  
		return new Insets(0,0,0,0); 
	}  
	
	
	public boolean isBorderOpaque()  {
		return true; 
	} 
	
	public void setCouleur(Color couleur){
		this.couleur = couleur;
	}
}
