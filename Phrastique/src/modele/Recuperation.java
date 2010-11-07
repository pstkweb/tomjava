package modele;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Recuperation {
	private String fichier;
	private Transformation trans;
	
	public Recuperation(String fichier) {
		this.fichier = fichier;
		XMLReader parseur = null;
		try {
			parseur = XMLReaderFactory.createXMLReader();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		trans = new Transformation();
		parseur.setContentHandler(trans);
		parseur.setErrorHandler(trans);
		
		try{
			try {
				parseur.parse(fichier);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SAXException e){
			System.out.println(e.getMessage());
		}
	}

	public Transformation getTrans() {
		return trans;
	}

	public void setTrans(Transformation trans) {
		this.trans = trans;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public String getFichier() {
		return fichier;
	}
}
