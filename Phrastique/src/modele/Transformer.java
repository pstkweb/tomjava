package modele;
import java.util.Hashtable;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Transformer extends DefaultHandler {

	private Hashtable<String, String> phrases;
	private LinkedList<Relation> relations;
	private String id;
	private String phrase;
	private String idSource;
	private String idCible;
	private String tags;

	public Transformer() {
		super();
		this.phrases = new Hashtable<String, String>();
		this.relations = new LinkedList<Relation>();
		this.id = "";
		this.phrase = "";
		this.idSource = "";
		this.idCible = "";
		this.tags = "";
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

	public void setPhrases(Hashtable<String, String> phrases) {
		this.phrases = phrases;
	}

	public Hashtable<String, String> getPhrases() {
		return phrases;
	}

	public String getIdSource() {
		return idSource;
	}

	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}

	public String getIdCible() {
		return idCible;
	}

	public void setIdCible(String idCible) {
		this.idCible = idCible;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public LinkedList<Relation> getRelations() {
		return relations;
	}

	public void setRelations(LinkedList<Relation> relations) {
		this.relations = relations;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
}
