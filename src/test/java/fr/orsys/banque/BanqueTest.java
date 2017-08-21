package fr.orsys.banque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BanqueTest {
	Banque banque = new Banque("", "");
	
	@BeforeClass 
	static public void setupClass() {
		System.out.println("@BeforeClass");
	}
	
	@Before 
	public void  publicsetup() {
		System.out.println("@Before");
	}
	
	@After
	public void  tear() {
		System.out.println("@After");
	}
	

	@Test
	public void testOuvrirCompte() {
		int nbCptPred = banque.getLesCompte().size();
		int numero = banque.ouvrirCompte(100);
		assertEquals(nbCptPred +1, banque.getLesCompte().size());
		assertTrue(banque.getCompte(numero) instanceof Compte);
	}
//
//	@Test
//	public void testFermerCompte() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testOuvrirCompteEpargne() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testEffectuerVirement() throws DebitNonAutoriseException {
		int numero1 = banque.ouvrirCompte(100);
		int numero2 = banque.ouvrirCompte(100);
		banque.effectuerVirement(numero1, 100, numero2);
		assertEquals(0.0f, banque.getCompte(numero1).getSolde(), 0.0f);
		assertEquals(200.0f, banque.getCompte(numero2).getSolde(), 0.0f);
		
	}

}
