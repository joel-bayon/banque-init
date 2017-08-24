package fr.orsys.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.persister.entity.Loadable;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.JpaUtil;
import fr.orsys.dao.CompteDao;
import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;

public class CompteDaoJpaTest {
	static CompteDao cptDao = new CompteDaoJpa();
	
	@Before
	public void beginTransaction() {
		JpaUtil.getCurrentEntityManager()
		.getTransaction().begin();
		cptDao.save( new Compte(100));
		cptDao.save( new CompteEpargne(100, 0.5f));
		JpaUtil.getCurrentEntityManager()
		.getTransaction().commit();
		JpaUtil.closeCurrentEntityManager();
		JpaUtil.getCurrentEntityManager()
		.getTransaction().begin();
	}
	
	@After
	public void endTransaction() {
		cptDao.deleteAll(cptDao.LoadAll());
		JpaUtil.getCurrentEntityManager().getTransaction().commit();
	}

	@Test
	public void testSaveAndLoad() {
		Compte cpt = new Compte(100);
		cptDao.save(cpt);
		Compte cpt2 = cptDao.load(cpt.getNumero());
		assertTrue(cpt.equals(cpt2));
	}

	@Test
	public void testLoadAll() {
		int nbCpt = cptDao.LoadAll().size();
		Compte cpt = new Compte(100);
		cptDao.save(cpt);
		List<Compte> list = cptDao.LoadAll();
		assertTrue(list.size() == nbCpt+1);
		for(Compte c : list)
			assertTrue(c instanceof Compte);
	}

	@Test
	public void testDelete() {
		Compte cpt = new Compte(100);
		int nbCpt = cptDao.LoadAll().size();
		cptDao.save(cpt);
		JpaUtil.getCurrentEntityManager().getTransaction().commit();
		JpaUtil.closeCurrentEntityManager();
		JpaUtil.getCurrentEntityManager().getTransaction().begin();
		cptDao.delete(cpt);
		JpaUtil.getCurrentEntityManager().getTransaction().commit();
		assertTrue(cptDao.LoadAll().size() == nbCpt);
		JpaUtil.getCurrentEntityManager().getTransaction().begin();
		
	}

	@Test
	public void testDeleteAll() {
		cptDao.deleteAll(cptDao.LoadAll());
		JpaUtil.getCurrentEntityManager().getTransaction().commit();
		assertTrue(cptDao.LoadAll().size() == 0);
		JpaUtil.getCurrentEntityManager().getTransaction().begin();
	}
//
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testLoadCompteEpargne() {
		assertEquals(1,cptDao.loadCompteEpargne().size());
	}

}
