package mypackage;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoMaintainabilityJpa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();

		// Demo executing named queries.
		getEmployeesInSalaryRange(em, 20000, 50000);
	}

	
	// Demo executing named queries.
	public static void getEmployeesInSalaryRange(EntityManager em, double min, double max) {
		
		Query query = em.createNamedQuery("getEmployeesInSalaryRangeJpa");
		query.setParameter(1, min);
		query.setParameter(2, max);
	
		List<Employee> emps = query.getResultList();
		System.out.printf("Employees earning between %.2f and %.2f\n", min, max);
		for (Employee emp : emps) {
			System.out.printf("\t%s\t%.2f\n", emp.getName(),emp.getSalary());
		}
	}
}