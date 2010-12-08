package vue;

import javax.swing.JTextPane;

import modele.Phrase;

@SuppressWarnings("serial")
public class JTextPanePhrase extends JTextPane {
	private Phrase phrase;
	
	public JTextPanePhrase(Phrase phrase){
		super();
		this.phrase = phrase;
	}

	public Phrase getPhrase() {
		return phrase;
	}

	public void setPhrase(Phrase phrase) {
		this.phrase = phrase;
	}
}
