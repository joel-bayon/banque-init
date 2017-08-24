package fr.orsys.banque;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import fr.orsys.banque.Compte;
import fr.orsys.banque.DebitNonAutoriseException;

public class CompteTest {

	Compte cpt = null;
	
	@Before
	public void setUp() throws Exception {
		cpt = new Compte(200f);
		assertNotNull(cpt);
	}

	@Test
	public void testCompte() {
		assertEquals(200.0f, cpt.getSolde(), 0.0f);
		assertEquals(200.0f, cpt.getDecouvertAutorise(), 0.0f);
		assertEquals(1, cpt.getLesOperations().size());
		assertEquals(Compte.getCompteurCompte(), cpt.getNumero()+1);
	}

	@Test
	public void testCrediter() {
		cpt.crediter(100);
		assertEquals(300.0f, cpt.getSolde(), 0.0f);
		assertEquals(200.0f, cpt.getDecouvertAutorise(), 0.0f);
		assertEquals(2, cpt.getLesOperations().size());
	}

	
	@Test
	public void testDebiter() {
		try {
			cpt.debiter(200);
			assertEquals(0.0f, cpt.getSolde(), 0.0f);
			assertEquals(2, cpt.getLesOperations().size());
		} catch (DebitNonAutoriseException e) {
			// TODO Auto-generated catch block
			fail("c'est une erreur ...");
		}
	}
	
	
	@Test(expected=DebitNonAutoriseException.class)
	public void testTraiterDecouvertNonAutorise() throws DebitNonAutoriseException {
		try {
			cpt.debiter(401);
		} catch (DebitNonAutoriseException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getCompte(), cpt);
			assertEquals(401.0f, e.getMontant(), 0.0f);
			assertEquals(200.0f, cpt.getSolde(), 0.0f);
			assertEquals(200.0f, cpt.getDecouvertAutorise(), 0.0f);
			assertEquals(1, cpt.getLesOperations().size());
			throw e;
		}
	}

}
