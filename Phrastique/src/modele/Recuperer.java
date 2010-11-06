package modele;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Recuperer {
	private String fichier;
	private Transformer trans;
	
	public Recuperer(String fichier) throws SAXException, IOException{
		this.setFichier(fichier);
		XMLReader parseur = XMLReaderFactory.createXMLReader();
		trans = new Transformer();
		parseur.setContentHandler(trans);
		parseur.setErrorHandler(trans);
		
		try{
			parseur.parse(fichier);
		} catch (SAXException e){
			System.out.println(e.getMessage());
		}
	}

	public Transformer getTrans() {
		return trans;
	}

	public void setTrans(Transformer trans) {
		this.trans = trans;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public String getFichier() {
		return fichier;
	}
}
