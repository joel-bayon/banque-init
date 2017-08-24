package fr.orsys.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

import util.JpaUtil;
import fr.orsys.dao.CompteDao;
import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;

public class CompteDaoJpa implements CompteDao {

	@Override
	public Integer save(Compte entity) {
		EntityManager em = JpaUtil.getCurrentEntityManager();
		em.persist(entity);
		return entity.getNumero();
	}

	@Override
	public Compte load(Integer primaryKey) {
		EntityManager em = JpaUtil.getCurrentEntityManager();
		return em.find(Compte.class, primaryKey);
	}

	@Override
	public List<Compte> LoadAll() {
		EntityManager em = JpaUtil.getCurrentEntityManager();
		return em.createQuery("select c from Compte c", Compte.class)
				 .getResultList() ;
	}

	@Override
	public void delete(Compte entity) {
		EntityManager em = JpaUtil.getCurrentEntityManager();
		em.remove(em.merge(entity));
		
	}

	@Override
	public void deleteAll(Collection<Compte> entities) {
		for(Compte cpt : entities)
			delete(cpt);
		
	}

	@Override
	public Compte update(Compte entity) {
		EntityManager em = JpaUtil.getCurrentEntityManager();
		return em.merge(entity);
		
	}

	@Override
	public List<CompteEpargne> loadCompteEpargne() {
		// TODO Auto-generated method stub
		EntityManager em = JpaUtil.getCurrentEntityManager();
		return em.createQuery("select c from CompteEpargne c", CompteEpargne.class)
				 .getResultList() ;
	}
}
