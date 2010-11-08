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
			if(phrase.length() > 50){
				int i = 50;
				while(phrase.charAt(i) != ' '){
					i--;
				}
				String phrase1 = phrase.substring(0, i);
				System.out.println(phrase1);
			}
		}
	}
}
