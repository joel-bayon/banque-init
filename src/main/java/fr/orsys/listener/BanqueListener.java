package fr.orsys.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.orsys.dao.impl.CompteDaoJpa;
import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;
import util.JpaUtil;

public class BanqueListener implements ServletContextListener {
	CompteDaoJpa dao = new CompteDaoJpa();
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("*** création des comptes en base *****");
		JpaUtil.getCurrentEntityManager().getTransaction().begin();
		dao.save(new Compte(100));
		dao.save(new Compte(200));
		dao.save(new CompteEpargne(300, 0.2f));
		dao.save(new CompteEpargne(400, 0.5f));
		JpaUtil.getCurrentEntityManager().getTransaction().commit();
		

	}

}
