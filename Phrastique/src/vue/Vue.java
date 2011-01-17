package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import modele.Phrase;
import modele.Recuperation;
import modele.Relation;

/**
 * Classe définissant une Vue qui est un JPanel contenant les données du modele misent
 * dans des JTextPane.
 * @author Thomas
 *
 */
public  class Vue extends JPanel {
	private static final long serialVersionUID = 1L;
	private Recuperation donnees;
	private static final Dimension taillePanel = new Dimension(2000,1500);
	private static final int largeurPhrase = 500;
	private static final int largeurRelation = 200;
	private static final int espacementPhrase = 20;
	
	/**
	 *  Constructeur d'une Vue en utilisant les méthodes dessinerPhrases et dessinnerRelations
	 */
	public Vue(){
		super(null);
		donnees  = new Recuperation("exemple1.xml");
		this.setPreferredSize(taillePanel);
		dessinerPhrases(donnees);
		dessinerRelations(donnees);
	}
	
	/**
	 * Méthode permettant d'ajouter les phrases au JPanel en mettant chaque phrase du modele
	 * dans des JTextPanePhrase et en ajoutant ces JTextPanePhrase au JPanel
	 * @param donnees
	 * les données contenant les phrases et les relations du modele
	 */
	public void dessinerPhrases(Recuperation donnees){
		for(Phrase p : donnees.getTrans().getPhrases()){
			JTextPanePhrase textPanePhrase = new JTextPanePhrase(p);
			StyledDocument doc = textPanePhrase.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 14);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			textPanePhrase.setText(p.getId()+" : "+p.getContenu());
			textPanePhrase.setEditable(false);
			textPanePhrase.setName(p.getId());
			textPanePhrase.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createLoweredBevelBorder()),BorderFactory.createEmptyBorder(2, 2, 2, 2)));
			this.add(textPanePhrase);
		}
	}
	
	/**
	 * Méthode permettant d'ajouter les relations au JPanel en mettant chaque relation du modele
	 * dans des JTextPaneRelation et en ajoutant ces JTextPaneRelation au JPanel
	 * @param donnees
	 * les données contenant les phrases et les relations du modele
	 */
	public void dessinerRelations(Recuperation donnees){
		int i = 1;
		for(Relation rel : donnees.getTrans().getRelations()){
			JTextPaneRelation textPaneRel = new JTextPaneRelation(rel);
			textPaneRel.setName("Relation "+i);
			i++;
			textPaneRel.setOpaque(false);
			StyledDocument doc = textPaneRel.getStyledDocument();	
			MutableAttributeSet center = new SimpleAttributeSet();		
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			MutableAttributeSet taille = new SimpleAttributeSet();
			StyleConstants.setFontSize(taille, 12);
			doc.setParagraphAttributes(0, 0, center, false);
			doc.setParagraphAttributes(0, 0, taille, false);
			textPaneRel.setEditable(false);
			textPaneRel.setText(rel.getTags());
			this.add(textPaneRel);
		}
	}
	
	/**
	 * Donne la liste des JTextPaneRelation du JPanel.
	 * @return
	 * liste de JTextPaneRelation
	 */
	public LinkedList<JTextPaneRelation> getComposantsGraphiqueRelation(){
		LinkedList<JTextPaneRelation> listeRelation = new LinkedList<JTextPaneRelation>();
		for(Component e : this.getComponents()){
			if(e instanceof JTextPaneRelation){
				listeRelation.add((JTextPaneRelation) e);
			}
		}
		return listeRelation;
	}
		
	/**
	 * Donne la liste des JTextPanePhrase du JPanel
	 * @return
	 * liste de JTextPanePhrase
	 */
	public LinkedList<JTextPanePhrase> getComposantsGraphiquePhrases(){
		LinkedList<JTextPanePhrase> listePhrases = new LinkedList<JTextPanePhrase>();
		for(Component e :this.getComponents()){
			if(e instanceof JTextPanePhrase){
				listePhrases.add((JTextPanePhrase)e);
			}
		}
		return listePhrases;
	}
		
	

	/**
	 * Donne les données
	 * @return
	 * les données
	 */
	public Recuperation getDonnees() {
		return donnees;
	}

	/**
	 * Modifie les données
	 * @param donnees
	 * les nouvelles données
	 */
	public void setDonnees(Recuperation donnees) {
		this.donnees = donnees;
	}

	/**
	 * Donne la taille du JPanel
	 * @return
	 * la taille du JPanel 
	 */
	public Dimension getTaillePanel() {
		return taillePanel;
	}

	/**
	 * Donne la largeur des phrases
	 * @return
	 * largeur des phrases
	 */
	public int getLargeurPhrase() {
		return largeurPhrase;
	}

	/**
	 * Donne la largeur des relations
	 * @return
	 * largeur relation
	 */
	public int getLargeurRelation() {
		return largeurRelation;
	}
	
	/**
	 * Donne l'espacement entre les phrases
	 * @return
	 * espacement entre les phrases
	 */
	public int getEspacementPhrase() {
		return espacementPhrase;
	}
}
