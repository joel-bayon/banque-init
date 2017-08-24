package sandbox;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import fr.orsys.entity.Compte;
import fr.orsys.entity.CompteEpargne;
import fr.orsys.entity.Operation;

public class TestHibernate {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sf = new Configuration()
			.configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();
		// exploiter la sessionFactory .....
		Operation operation = new Operation(1000, Operation.CREDIT);
		Session session = sf.openSession();
		session.beginTransaction();
		Integer id = (Integer)session.save(operation);
		session.save(new Operation(111, Operation.DEBIT));
		session.save(new CompteEpargne(3333, 0.5f));
		session.getTransaction().commit();
		session.close();
		
		operation.setMontant(2000);  //detach object ...
	
		session = sf.openSession();
		session.beginTransaction();
		session.merge(operation);
		System.out.println(operation);
		session.getTransaction().commit();
		session.close();
		
		session = sf.openSession();
		session.beginTransaction();
		
//		for(Operation o : session.createQuery("select o.id from Operation o", Operation.class).getResultList())
//			session.delete(o);
		session.createQuery("delete from Operation").executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		session = sf.openSession();
		session.beginTransaction();
		Compte cpt = new Compte(100);
		//session.save(cpt.getLesOperations().get(0));
		int num = (Integer)session.save(cpt);
		session.getTransaction().commit();
		session.close();
		
		session = sf.openSession();
		session.beginTransaction();
		cpt= (Compte)session.merge(cpt);
		//cpt = session.get(Compte.class,num);
		//cpt.getLesOperations().remove(0);
		cpt.crediter(20);
		session.getTransaction().commit();
		cpt.editerReleve();
		session.close();
		
		
		

		sf.close();

	}

}
