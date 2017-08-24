package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	static EntityManagerFactory factory = null;
	static {
		factory = Persistence.createEntityManagerFactory("banque");
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return factory;
	}

	static private ThreadLocal<EntityManager> emThreadLocal = new ThreadLocal<EntityManager>();

	static public EntityManager getCurrentEntityManager() {
		EntityManager em = emThreadLocal.get();
		if (em == null) {
			em = factory.createEntityManager();
			emThreadLocal.set(em);
		}
		return em;
	}

	static public void closeCurrentEntityManager() {
		if (emThreadLocal.get() != null) {
			emThreadLocal.get().close();
			emThreadLocal.set(null);
		}
	}
}
