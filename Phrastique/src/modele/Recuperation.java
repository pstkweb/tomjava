package modele;
import java.util.Hashtable;
import java.util.LinkedList;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class Recuperation extends DefaultHandler {

	private static Hashtable<String, String> phrases;
	private static LinkedList<Relation> relations = new LinkedList<Relation>();
	private static String id = new String();
	private static String phrase = new String();
	private static String idSource = new String();
	private static String idCible = new String();
	private static String tags = new String();

	public Recuperation() {
		super();
		phrases = new Hashtable<String, String>();
	}// constructeur

	public void startElement(String uri, String name, String qualif, Attributes at) {
		if(name.equals("phrase")){
			id = at.getValue(0);
		}
		if(name.equals("relation")){
			relations.add(new Relation(at.getValue(0), at.getValue(1), at.getValue(2)));
		}
	}

	public void endElement(String uri, String name, String qualif){
		if(name.equals("phrase")){
			phrases.put(id, phrase);
			id = "";
			phrase = "";
		}
		if(name.equals("relation")){
			idSource = "";
			idCible = "";
			tags = "";
		}
	}

	public void characters(char[] ch, int start, int length) {
		if(id.equals("")){
		}else{
			char[] temp = new char[length];
			for( int i = 0; i < length; i++ ){
				temp[i] = ch[start+i];
			}
			phrase = new String(temp);
		}
	}

	public static void setPhrases(Hashtable<String, String> phrases) {
		Recuperation.phrases = phrases;
	}

	public static Hashtable<String, String> getPhrases() {
		return phrases;
	}

	public static String getIdSource() {
		return idSource;
	}

	public static void setIdSource(String idSource) {
		Recuperation.idSource = idSource;
	}

	public static String getIdCible() {
		return idCible;
	}

	public static void setIdCible(String idCible) {
		Recuperation.idCible = idCible;
	}

	public static String getTags() {
		return tags;
	}

	public static void setTags(String tags) {
		Recuperation.tags = tags;
	}


	//-----------------------------------------------------------------------------------------------------
	public static void main(String[] args) throws Exception {
		String uri = "exemple1.xml";
		System.out.println("Analyse du fichier XML : "+uri);

		XMLReader parseur = XMLReaderFactory.createXMLReader();
		Recuperation handler = new Recuperation();
		parseur.setContentHandler(handler);
		parseur.setErrorHandler(handler);

		try {
			parseur.parse(uri);
		} catch (SAXException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Termine' !");
		System.out.println(phrases);
		System.out.println(relations);
	}
}
