package fr.orsys.service.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sandbox.Client;
import util.JpaUtil;
import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;
import fr.orsys.entity.DebitNonAutoriseException;
import fr.orsys.service.Banque;

public class BanqueWithAopTransactionTest {
	Banque banque;
	static BeanFactory spring;
	
	@BeforeClass 
	static public void setupClass() {
		spring = new ClassPathXmlApplicationContext("/spring/spring.banque.xml");
		System.out.println("@BeforeClass");
	}
	
	@Before 
	public void  publicsetup() {
		//JpaUtil.getCurrentEntityManager().getTransaction().begin();
		banque = spring.getBean(Banque.class);
		System.out.println("@Before -> banque : " + banque.getNom() + " " + banque.getCodeBanque());
	}
	
	@After
	public void  tear() {
		System.out.println("@After");
		//JpaUtil.getCurrentEntityManager().getTransaction().commit();
	}
	

	@Test
	public void testOuvrirCompte() {
		int nbCptPred = banque.getLesComptes().size();
		int numero = banque.ouvrirCompte(100);
		assertEquals(nbCptPred +1, banque.getLesComptes().size());
		assertTrue(banque.rechercherCompte(numero) instanceof Compte);
	}

	@Test
	@Ignore
	public void testFermerCompte() {
		for(Compte c : banque.getLesComptes())
			banque.fermerCompte(c.getNumero());
		assertEquals(0,  banque.getLesComptes().size());
	}

	@Test
	public void testOuvrirCompteEpargne() {
		int nbCptPred = banque.getLesComptes().size();
		int numero = banque.ouvrirCompteEpargne(100,0.2f);
		assertEquals(nbCptPred +1, banque.getLesComptes().size());
		assertTrue(banque.rechercherCompte(numero) instanceof CompteEpargne);
	}

	@Test
	public void testEffectuerVirement() throws DebitNonAutoriseException {
		int numero1 = banque.ouvrirCompte(100);
		int numero2 = banque.ouvrirCompte(100);
		banque.effectuerVirement(numero1, 100, numero2);
		assertEquals(0.0f, banque.rechercherCompte(numero1).getSolde(), 0.0f);
		assertEquals(200.0f, banque.rechercherCompte(numero2).getSolde(), 0.0f);
		
	}

}
