package modele;

import java.io.IOException;
import java.util.Enumeration;

import org.xml.sax.SAXException;

public class Test {
	public static void main(String args[]) throws SAXException, IOException{
		Recuperation test = new Recuperation("exemple1.xml");
		System.out.println(test.getTrans().getPhrases().toString());
		System.out.println(test.getTrans().getRelations().toString());
		for(Enumeration<String> e = test.getTrans().getPhrases().elements(); e.hasMoreElements();){
			String phrase = e.nextElement();
			char c = phrase.charAt(phrase.length()-1);
			System.out.println(c);	
		}
	}
}
