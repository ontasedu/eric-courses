package mypackage;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoNativeQueriesJpa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();

		// Demo executing native SQL query.
		getSkillsForEmployee(em, 1);
	}

	
	// Demo executing native SQL query.
	public static void getSkillsForEmployee(EntityManager em, int employeeId) {
		
		Query query = em.createNativeQuery("select * from MYSCHEMA.SKILLS where EMPLOYEEID=?", Skill.class);
		query.setParameter(1, employeeId);
		
		List<Skill> skills = query.getResultList();
		System.out.printf("Skills for employee %d\n", employeeId);
		for (Skill skill : skills) {
			System.out.printf("\t%s\t%d\n", skill.getDescription(), skill.getLevel());
		}
	}
}