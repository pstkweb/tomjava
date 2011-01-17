package test;


import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import vue.VueGraphe;
import vue.VueTextuelle;

/**
 * Classe de Test de validation qui verifie tout les test unitaires de l'application
 * 
 * @author Thomas
 *
 */
public class TestValidation {
	private static VueTextuelle vTexte;
	private static VueGraphe vGraphe;
	
	@BeforeClass
	public static void initTest(){
		vTexte = new VueTextuelle();
		vGraphe = new VueGraphe();
	}
	
	@Test
	public void TestDeValidation(){
		//les listes venant du modele ne doivent pas être vide
		assertTrue(vTexte.getDonnees().getTrans().getPhrases().size() != 0);
		assertTrue(vTexte.getDonnees().getTrans().getRelations().size() != 0);
		assertTrue(vGraphe.getDonnees().getTrans().getPhrases().size() != 0);
		assertTrue(vGraphe.getDonnees().getTrans().getRelations().size() != 0);
		//les relations doivent avoir des couleurs
		assertTrue(vTexte.getDonnees().getTrans().getRelations().getLast().getCouleur() != null);
		assertTrue(vGraphe.getDonnees().getTrans().getRelations().getLast().getCouleur() != null);
	}
}
