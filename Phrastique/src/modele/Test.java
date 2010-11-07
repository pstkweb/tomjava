package modele;

import java.io.IOException;

import org.xml.sax.SAXException;

public class Test {
	public static void main(String args[]) throws SAXException, IOException{
		Recuperation test = new Recuperation("exemple1.xml");
		System.out.println(test.getTrans().getPhrases().toString());
		System.out.println(test.getTrans().getRelations().toString());
	}
}
