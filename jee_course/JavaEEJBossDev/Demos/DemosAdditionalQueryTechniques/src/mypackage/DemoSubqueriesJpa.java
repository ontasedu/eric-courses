package mypackage;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoSubqueriesJpa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();

		// Demo a subquery that returns a single row.
		demoSingleRowSubquery(em);
		demoMultipleRowsSubquery(em);
	}

	
	// Demo a subquery that returns a single row.
	public static void demoSingleRowSubquery(EntityManager em) {

		Query query = em.createQuery(
				        "from Employee e where 3 <= " +
                        "  (select count(s) from e.skills s where s.level >= 4)");

		List<Employee> emps = query.getResultList();
		System.out.println("Employees with 3 or more skills (at a level >= 4):");
		for (Employee emp : emps) {
			System.out.printf("\t%s, total skills: %d\n", emp.getName(), emp.getSkills().size());
		}
	}


	// Demo a subquery that returns multiple rows, to show how to use quantifiers.
	public static void demoMultipleRowsSubquery(EntityManager em) {

		Query query = em.createQuery("from Employee e where 3 < all (select s.level from e.skills s)");

		List<Employee> emps = query.getResultList();
		System.out.println("Employees with all skill levels are higher than 3:");
		for (Employee emp : emps) {
			System.out.printf("\t%s\n", emp.getName());
		}

		query = em.createQuery("from Employee e where 3 >= any (select s.level from e.skills s)");
		emps = query.getResultList();
		System.out.println("Employees with any skill level 3 or less:");
		for (Employee emp : emps) {
			System.out.printf("\t%s\n", emp.getName());
		}

		query = em.createQuery("from Employee e where 5 in (select s.level from e.skills s)");
		emps = query.getResultList();
		System.out.println("Employees with any skill level of 5:");
		for (Employee emp : emps) {
			System.out.printf("\t%s\n", emp.getName());
		}
	}
}