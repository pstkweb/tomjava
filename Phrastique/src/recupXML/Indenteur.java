package recupXML;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class Indenteur extends DefaultHandler {

    private static String retrait = "  "; // 2 espaces

    private int prof = 0; // globale, gérée avec "effet de bord"
   
    private void indent() {
        for( int i = 0; i < prof; i++ ) {
            System.out.print(Indenteur.retrait);
        }
    }

    public Indenteur() {
        super();
    }// constructeur

    public void startElement(String uri, String name, String qualif, Attributes at) {
        indent();
        System.out.print("<"+name);
        for( int i = 0; i < at.getLength(); i++ ){
            System.out.print(" "+ at.getLocalName(i)+":"+at.getValue(i));
        }
        System.out.println(">");
        prof++;
    }

    public void endElement(String uri, String name, String qualif){
        prof--;       
        indent();
        System.out.println("</"+name+">");

    }

    public void characters(char[] ch, int start, int length) {
        indent();
        System.out.print(Indenteur.retrait); // un de plus
        for( int i = 0; i < length; i++ ){
            System.out.print(ch[start+i]);
        }
        System.out.println();
    }
//------------------------------------------------------------------------------------

    public static void main(String[] args) throws Exception {
           String uri = "exemple1.xml";
           System.out.println("Analyse du fichier XML : "+uri);
  
           XMLReader parseur = XMLReaderFactory.createXMLReader();
           Indenteur handler = new Indenteur();
           parseur.setContentHandler(handler);
           parseur.setErrorHandler(handler);
       
           try {
               parseur.parse(uri);
           } catch (SAXException e) {
               System.out.println(e.getMessage());
           }
           System.out.println("Termine' !");
    }//main
}//Indenteur
