package modele;

import java.io.IOException;

import org.xml.sax.SAXException;

public class Test {
	public static void main(String args[]){
		try {
			Recuperer test = new Recuperer("exemple1.xml");
			System.out.println(test.getTrans().getPhrases().toString());
			System.out.println(test.getTrans().getRelations().toString());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
