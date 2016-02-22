package mypackage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoEntityToMultipleTablesJpa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();

		// Demo retrieving an entity from multiple tables.
		getEmployeesAndContracts(em);

		// Close session when we're finished.
		em.close();
	}

	public static void getEmployeesAndContracts(EntityManager em) {

		String sql = "from Employee e";
		Query query = em.createQuery(sql);
	
		List<Employee> emps = query.getResultList();
		System.out.println("Employees and contracts");
		for (Employee emp : emps) {
			System.out.println("Employee name: "  + emp.getName() + 
			           		   "\tStart date: "   + emp.getStartDate() + 
			                   "\tStart salary: " + emp.getStartSalary());
		}
	}
}