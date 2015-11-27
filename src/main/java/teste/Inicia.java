package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Inicia {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();

	}

}
