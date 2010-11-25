package modele;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;
 
 
 
 
public class BordureArrondi implements Border {  
	
	private Color couleur;
	
	
	
	
	public BordureArrondi(Color couleur)  {
		this.couleur = couleur;
	}  
 	
	
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)  {
       
        int adjustX = 1;
        //int adjustY = 5;//pour ajuster le dessin en x et y
        int adjustW = 1;//idem pour width et height
        int adjustH = 10;
        //pour eviter les escalier sur l'arrondi
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.WHITE);
        g2.fillOval(x, y-adjustH, width, height+adjustH);
        g.setColor(this.couleur);//ou une autre couleur que tu peux rendre paramétrable
        g2.drawOval(x, y-adjustH, width, height+adjustH);
	}  
	
	
	
	public Insets getBorderInsets(Component c)  {  
		return new Insets(0,0,0,0); 
	}  
	
	
	public boolean isBorderOpaque()  {
		return true; 
	} 
	
}
