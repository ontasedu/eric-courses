package mypackage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoComponentsJpa {

	public static void main(String[] args) {

		// Create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		getPersons(em, 1);
		em.close();
	}

	public static void getPersons(EntityManager em, int personID) {

		Query query = em.createQuery("from Person");
		List<Person> persons = query.getResultList();

		System.out.println("Dudes:");
		for (Person person : persons) {
			System.out.println(person.toString());
		}
	}
}