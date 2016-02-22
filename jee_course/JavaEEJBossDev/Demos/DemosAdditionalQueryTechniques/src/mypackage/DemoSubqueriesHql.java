package mypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;

public class DemoSubqueriesHql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Demo a subquery that returns a single row.
		demoSingleRowSubquery(session);
		demoMultipleRowsSubquery(session);
	}

	
	// Demo a subquery that returns a single row.
	public static void demoSingleRowSubquery(Session session) {

		Query query = session.createQuery(
				        "from Employee e where 3 <= " +
                        "  (select count(s) from e.skills s where s.level >= 4)");

		List<Employee> emps = query.list();
		System.out.println("Employees with 3 or more skills (at a level >= 4):");
		for (Employee emp : emps) {
			System.out.printf("\t%s, total skills: %d\n", emp.getName(), emp.getSkills().size());
		}
	}


	// Demo a subquery that returns multiple rows, to show how to use quantifiers.
	public static void demoMultipleRowsSubquery(Session session) {

		Query query = session.createQuery("from Employee e where 3 < all (select s.level from e.skills s)");

		List<Employee> emps = query.list();
		System.out.println("Employees with all skill levels are higher than 3:");
		for (Employee emp : emps) {
			System.out.printf("\t%s\n", emp.getName());
		}

		query = session.createQuery("from Employee e where 3 >= any (select s.level from e.skills s)");
		emps = query.list();
		System.out.println("Employees with any skill level 3 or less:");
		for (Employee emp : emps) {
			System.out.printf("\t%s\n", emp.getName());
		}

		query = session.createQuery("from Employee e where 5 in (select s.level from e.skills s)");
		emps = query.list();
		System.out.println("Employees with any skill level of 5:");
		for (Employee emp : emps) {
			System.out.printf("\t%s\n", emp.getName());
		}
	}
}