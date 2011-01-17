package test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import modele.Recuperation;

/**
 * Classe de test unitaire de la classe recuperation.
 * 
 * test si:
 *  - la liste de phrases est non vide
 *  - la liste de relations est non vide
 *  - si les relations ont des couleurs
 *  
 * @author Thomas
 *
 */
public class TestRecuperation {
	private static Recuperation recup;
	
	/**
	 * Initialisation des donnees de test avant l'ensemble des tests
	 */
	@BeforeClass
	public static void initTests() {
		recup = new Recuperation("exemple1.xml");
	}
	
	/**
	 * Test de la liste de phrases non vide
	 */
	@Test
	public void testNbPhrases() {
		assertTrue( recup.getTrans().getPhrases().size() != 0 );
	}
	
	/**
	 * Test de la liste de relations non vide
	 */
	@Test
	public void testNbRelations() {
		assertTrue( recup.getTrans().getRelations().size() != 0 );
	}
	
	/**
	 * Test de la definition des couleurs pour les relations
	 */
	@Test
	public void testCouleurs(){
		assertTrue( recup.getTrans().getRelations().getLast().getCouleur() != null);
	}
}
