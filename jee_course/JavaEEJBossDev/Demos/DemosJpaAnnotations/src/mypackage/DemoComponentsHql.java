package mypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.Query;

public class DemoComponentsHql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration()
				                                .configure()
				                                .buildSessionFactory();

		Session session = sessionFactory.openSession();
		getPersons(session, 1);
		session.close();
	}

	public static void getPersons(Session session, int personID) {

		Query query = session.createQuery("from Person");
		List<Person> persons = query.list();

		System.out.println("Dudes:");
		for (Person person : persons) {
			System.out.println(person.toString());
		}
	}
}