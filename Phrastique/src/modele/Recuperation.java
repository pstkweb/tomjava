package modele;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Classe qui effectue le parse d'un fichier XML
 * @author Thomas
 */
public class Recuperation {
	private String fichier;
	private Transformation trans;
	
	/**
	 * Constructeur de Recuperation qui effectue le parse sur le fichier en suivant 
	 * le handler trans qui est un objet de la classe Transformation, il definit 
	 * également les couleurs des relations.
	 * @param fichier
	 * le fichier XML
	 */
	public Recuperation(String fichier) {
		this.fichier = fichier;
		XMLReader parseur = null;
		try {
			parseur = XMLReaderFactory.createXMLReader();
		} catch (SAXException e1) {
			e1.printStackTrace();
		}
		trans = new Transformation();
		parseur.setContentHandler(trans);
		
		try{
			try {
				parseur.parse(fichier);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SAXException e){
			System.out.println(e.getMessage());
		}
		definirCouleur(trans);
		
	}
	
	/**
	 * Defini les couleurs selon une liste de couleurs puis si les couleurs
	 * sont déjà utilisés elles sont définis aléatoirement.
	 * @param trans
	 * la Transformation contenant les données du fichier XML
	 */
	public void definirCouleur(Transformation trans){
		LinkedList<Color> couleurs = new LinkedList<Color>();
		couleurs.add(Color.BLACK);
		couleurs.add(Color.BLUE);
		couleurs.add(Color.CYAN);
		couleurs.add(Color.DARK_GRAY);
		couleurs.add(Color.GREEN);
		couleurs.add(Color.MAGENTA);
		couleurs.add(Color.ORANGE);
		couleurs.add(Color.PINK);
		couleurs.add(Color.RED);
		couleurs.add(Color.YELLOW);
		couleurs.add(new Color(0,128,0));
		couleurs.add(new Color(128,0,0));
		couleurs.add(new Color(128,0,128));
		couleurs.add(new Color(128,128,0));
		couleurs.add(new Color(0,0,128));
		
		Color couleur;
		Random ran = new Random();
		for(Relation rel : trans.getRelations()){
			if(couleurs.isEmpty()){
				couleur = new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255));
			}else{
				couleur = couleurs.getFirst();
			}
			for(Relation test : trans.getRelations()){
				if(rel.getTags().equals(test.getTags()) && rel!=test && rel.getCouleur()==null){
					rel.setCouleur(couleur);
					test.setCouleur(couleur);			
				}
			}
			if(rel.getCouleur()==null){
				rel.setCouleur(couleur);	
			}
			if(!couleurs.isEmpty()){
				couleurs.removeFirst();
			}
		}
	}

	/**
	 * Donne la Transformation qui contient les données du fichier XML
	 * @return
	 * la transformation
	 */
	public Transformation getTrans() {
		return trans;
	}

	/**
	 * Modifie la Transformation
	 * @param trans
	 * la nouvelle transformation
	 */
	public void setTrans(Transformation trans) {
		this.trans = trans;
	}

	/**
	 * Modifie le chemin du fichier XML 
	 * @param fichier
	 * le nouveau chemin du fichier
	 */
	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	/**
	 * Donne le chemin du fichier XML
	 * @return
	 * le chemin du fichier XML
	 */
	public String getFichier() {
		return fichier;
	}
}
