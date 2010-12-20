package vue;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JTextPane;


/**
 * Classe définissant une vue graphe qui hérite de Vue
 * @author Thomas
 *
 */
public class VueGraphe extends Vue{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Creer le JPanel qui contient la vue graphe, ce JPanel a une taille defini en statique
	 * on utilise les méthodes reDessinerPhrases et reDessinerRelations pour placer correctement 
	 * les composants
	 */
	public VueGraphe(){
		super();
		reDessinerPhrases();
		reDessinerRelations();
		for(JTextPaneRelation rel : this.getComposantsGraphiqueRelation()){
			while( seTouche((JTextPaneRelation)rel) ){
				rel.setLocation(rel.getX(), rel.getY()+1);
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
		for(Component comp : this.getComponents()){
			if(comp instanceof JTextPaneRelation){
									
				/**
				 * Créer une bordure arrondi pour chaque JTextPane de relation
				 */
				BordureArrondi bord = new BordureArrondi(((JTextPaneRelation) comp).getRelation().getCouleur());
				bord.paintBorder(comp, g, comp.getX(), comp.getY(), comp.getWidth(), comp.getHeight());
				/***/
				passerPointille(g);
				int j = 0;
				Component phrase1 = null;
				Component phrase2 = null;
				/**
				 * recherche les phrases 
				 */
				do {
					if(this.getComponent(j) instanceof JTextPanePhrase){
						if (((JTextPanePhrase)this.getComponent(j)).getPhrase().getId().equals(((JTextPaneRelation)comp).getRelation().getIdCible())) {
							phrase2 = this.getComponent(j);
						}
						if (((JTextPanePhrase)this.getComponent(j)).getPhrase().getId().equals(((JTextPaneRelation)comp).getRelation().getIdSource())) {
							phrase1 = this.getComponent(j);
						}
					}
					j++;
				}while(!(this.getComponent(j) instanceof JTextPane) || phrase1 == null || phrase2 == null );
				
				if(comp.getX() < phrase1.getX()){
					g.drawLine(comp.getX()+comp.getWidth(), comp.getY()+comp.getHeight()/2-5,
							phrase1.getX(), phrase1.getY()+phrase1.getHeight()/2);
				}
				else{
					g.drawLine(comp.getX(), comp.getY()+comp.getHeight()/2-5,
							phrase1.getX()+phrase1.getWidth(), phrase1.getY()+phrase1.getHeight()/2);
				}
				
				if(comp.getX() < phrase2.getX()){
					g.drawLine(comp.getX()+comp.getWidth(), comp.getY()+comp.getHeight()/2-5,
							phrase2.getX(), phrase2.getY()+phrase2.getHeight()/2);
				}
				else{
					g.drawLine(comp.getX(), comp.getY()+comp.getHeight()/2-5,
							phrase2.getX()+phrase2.getWidth(), phrase2.getY()+phrase2.getHeight()/2);
				}
				passerPlein(g);
			}
		}
		
	}
	
	/**
	 * Passe l'objet Graphics g pour qu'il dessine plus large et en pointillé
	 * @param g
	 * l'objet graphics
	 * @return
	 * un objet graphics2D
	 */
	public Graphics2D passerPointille(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		float epaisseur = 3; /** taille de la ligne */
		float[] style = {10,5}; /** les pointillés seront 2 fois plus long que les blancs */
		g2d.setStroke( new BasicStroke(epaisseur, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
				10.0f, style, 0 ));
		return g2d;
	}
	
	/**
	 * passe l'objet Graphics g en mode normal
	 * @param g
	 * @return
	 * un objet graphics
	 */
	public Graphics passerPlein(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke());
		return g;
	}
	
	/**
	 *  positionne les JTextPanePhrase au centre du JPanel les un en dessous 
	 *  des autres
	 */
	public void reDessinerPhrases(){
		LinkedList<JTextPanePhrase> listePhrase = this.getComposantsGraphiquePhrases();
		int posY = 50;
		for(int i=0; i<listePhrase.size(); i++){
			int nbLignes = listePhrase.get(i).getText().length()/81 + 1;
			listePhrase.get(i).setBounds(this.getTaillePanel().width/2-this.getLargeurPhrase()/2, posY, this.getLargeurPhrase(),
					listePhrase.get(i).getPreferredSize().height*nbLignes - (nbLignes-1)*12);
			posY += listePhrase.get(i).getBounds().height+getEspacementPhrase();
		}
	}
	
	/**
	 * positionne les relations altérnativement à gauche et à droite des phrases  
	 */
	public void reDessinerRelations(){
		LinkedList<JTextPaneRelation> listerelation = this.getComposantsGraphiqueRelation();
		LinkedList<JTextPanePhrase> listePhrase = this.getComposantsGraphiquePhrases();
		JTextPanePhrase phraseSource = listePhrase.get(0);
		JTextPanePhrase phraseCible = listePhrase.get(0);
		
		boolean position = true; // position a gauche
		for(int i=0;i<listerelation.size(); i++){
			for(int j=0;j<listePhrase.size(); j++){
				if(listerelation.get(i).getRelation().getIdSource().equals(listePhrase.get(j).getPhrase().getId())){
					phraseSource = listePhrase.get(j);
				}
				if(listerelation.get(i).getRelation().getIdCible().equals(listePhrase.get(j).getPhrase().getId())){
					phraseCible = listePhrase.get(j);
				}
		
			}
			int nbLignes = listerelation.get(i).getText().length()/30 + 1;
			if(position == true){
				listerelation.get(i).setBounds(this.getTaillePanel().width/4-this.getLargeurRelation()/2,
						(phraseSource.getY()+phraseSource.getHeight()+phraseCible.getY())/2
						-(listerelation.get(i).getPreferredSize().height*nbLignes-(nbLignes-1)*4)/2 , this.getLargeurRelation(), 
						listerelation.get(i).getPreferredSize().height*nbLignes - (nbLignes-1)*4);
				position = false;
				
			}else{
				listerelation.get(i).setBounds(this.getTaillePanel().width*3/4-this.getLargeurRelation()/2,
						(phraseSource.getY()+phraseSource.getHeight()+phraseCible.getY())/2
						-(listerelation.get(i).getPreferredSize().height*nbLignes-(nbLignes-1)*4)/2 , this.getLargeurRelation(), 
						listerelation.get(i).getPreferredSize().height*nbLignes - (nbLignes-1)*4+5);
				position = true;
			}
		}
		
	}
	
	/**
	 * Verifie si le JTextPaneRelation passé en parametre touche à un autre JTextPaneRelation
	 * @param relation
	 * la relation à tester
	 * @return
	 * vrai si le composant touche un autre
	 */
	public boolean seTouche(JTextPaneRelation relation){
		LinkedList<JTextPaneRelation> listeRelation = this.getComposantsGraphiqueRelation();
		for(JTextPaneRelation rel : listeRelation){
			if(!rel.getName().equals(relation.getName()) && ((rel.getX()+rel.getWidth()<this.getTaillePanel().width/2 &&
										relation.getX()+relation.getWidth()<this.getTaillePanel().width/2)||
									(rel.getX()+rel.getWidth()>this.getTaillePanel().width/2 &&
										relation.getX()+relation.getWidth()>this.getTaillePanel().width/2))){
				if(rel.getY()+rel.getHeight() > relation.getY()-5 && rel.getY() < relation.getY()-5){
					return true;
				}
				if(rel.getY() < relation.getY()-5+relation.getHeight()+10 && rel.getY() > relation.getY()-5){
					return true;
				}
				if(rel.getY() < relation.getY() && rel.getY()+rel.getHeight() > relation.getY()+relation.getHeight()){
					return true;
				}
			}
		}
		return false;
	}
}
