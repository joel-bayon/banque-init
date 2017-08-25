package fr.orsys.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import util.JpaUtil;
import fr.orsys.dao.CompteDao;
import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;


//@Component
@Repository("compteDao")
public class CompteDaoJpaWithSpring implements CompteDao {
	@PersistenceContext
	EntityManager em;
	@Override
	public Integer save(Compte entity) {
		em.persist(entity);
		return entity.getNumero();
	}

	@Override
	public Compte load(Integer primaryKey) {
		return em.find(Compte.class, primaryKey);
	}

	@Override
	public List<Compte> LoadAll() {
		return em.createQuery("select c from Compte c", Compte.class)
				 .getResultList() ;
	}

	@Override
	public void delete(Compte entity) {
		em.remove(em.merge(entity));
		
	}

	@Override
	public void deleteAll(Collection<Compte> entities) {
		for(Compte cpt : entities)
			delete(cpt);
		
	}

	@Override
	public Compte update(Compte entity) {
		return em.merge(entity);
		
	}

	@Override
	public List<CompteEpargne> loadCompteEpargne() {
		// TODO Auto-generated method stub
		return em.createQuery("select c from CompteEpargne c", CompteEpargne.class)
				 .getResultList() ;
	}
}
