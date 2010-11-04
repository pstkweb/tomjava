package recupXML;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;




public class RecupXML extends DefaultHandler {
	
	
    public void startElement(String uri, String name, String qualif, Attributes at) {
        System.out.print("<"+name);
        for( int i = 0; i < at.getLength(); i++ ){
            System.out.print(" "+ at.getLocalName(i)+":"+at.getValue(i));
        }
        System.out.println(">");
    }

    public void endElement(String uri, String name, String qualif){
       System.out.println("</"+name+">");

    }
}
