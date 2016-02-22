package mypackage;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoCollectionsJpa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();

		// Demo getting collections.
		getEmployeeSkills(em);
	}

	public static void getEmployeeSkills(EntityManager em) {
		
		String sql = "from Employee e";
		Query query = em.createQuery(sql);
	
		List<Employee> emps = query.getResultList();
		for (Employee emp : emps) {
			System.out.printf("\nSkills for %s\n", emp.getName());
			for (Skill skill: emp.getSkills()) {
				System.out.printf("\t%s %d\n", skill.getDescription(), skill.getLevel());
			}
		}
	}
}