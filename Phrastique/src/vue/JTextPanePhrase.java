package vue;

import javax.swing.JTextPane;

import modele.Phrase;

/**
 * Classe définissant un JTextPanePhrase qui est un JTextPane contenant en 
 * plus une Phrase(venant du modele).
 * @author Thomas
 *
 */
@SuppressWarnings("serial")
public class JTextPanePhrase extends JTextPane {
	private Phrase phrase;
	
	/**
	 * Constructeur d'un JTextPanePhrase
	 * @param phrase
	 * la phrase du modele qui sera lier au JTextPanePhrase
	 */
	public JTextPanePhrase(Phrase phrase){
		super();
		this.phrase = phrase;
	}

	/**
	 * Donne la Phrase 
	 * @return
	 * la Phrase
	 */
	public Phrase getPhrase() {
		return phrase;
	}

	/**
	 * Modifie la Phrase
	 * @param phrase
	 * la nouvelle Phrase
	 */
	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}
}
