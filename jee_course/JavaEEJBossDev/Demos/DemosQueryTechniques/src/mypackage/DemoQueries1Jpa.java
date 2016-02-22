package mypackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoQueries1Jpa {

	public static void main(String[] args) {

		// Create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Demo retrieving an object by primary key.
		getEmployee(em, 7);

		// Demo querying for objects using JPA QL.
		getEmployeesEarning(em, 40000);
		
		// Demo WHERE clause operators.
		getEmployeesIn(em, "Kent", "Northern Ireland");

		// Demo ordering rows.
		getSortedEmployees(em);

		// Demo paging results.
		getPagedEmployees(em, 0, 5);
		getPagedEmployees(em, 5, 5);

		// Demo projections 1.
		getEmployeeProjections(em);
		
		// Demo projections 2.
		getEmployeeSummaries(em);

		// Close session when we're finished.
 		em.close();
	}

	
	// Demo retrieving an object by primary key.
	public static void getEmployee(EntityManager em, int employeeID) {

		Employee emp = (Employee) em.find(Employee.class, employeeID);
		if (emp == null) {
			System.out.println("No employee with ID " + employeeID);
		} else {
			System.out.println("Employee with ID " + emp.getEmployeeId()
					+ " is " + emp.getName());
		}
	}

	// Demo querying for objects using JPA QL.
	public static void getEmployeesEarning(EntityManager em, double minSalary) {

		Query query = em.createQuery("from Employee e where e.salary >= :minSalary");
		query.setParameter("minSalary", minSalary);

		// Or using positional parameters... JPA QL positional parameters start at 1, and must be numbered, e.g. ?1
		// Query query = em.createQuery("from Employee e where e.Salary >= ?1");
		// query.setParameter(1, minSalary);
		
		List<Employee> emps = query.getResultList();
		System.out.println("Employees earning at least " + minSalary);
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getSalary());
		}
	}
	
	
	// Demo WHERE clause operators.
	public static void getEmployeesIn(EntityManager em, String... regions) {

		Query query = em.createQuery("from Employee e where e.region in (:regions)");
		
		List<String> regionsList = new ArrayList<String>();
		Collections.addAll(regionsList, regions);
		query.setParameter("regions", regionsList);

		List<Employee> emps = query.getResultList();
		System.out.println("Employees in one of regions ");
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getRegion());
		}
	}

	// Demo ordering rows.
	public static void getSortedEmployees(EntityManager em) {

		Query query = em.createQuery("from Employee e order by e.region, e.name");

		List<Employee> emps = query.getResultList();
		System.out.println("Employees sorted by region, then by name ");
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getRegion());
		}
	}

	
	// Demo paging results.
	public static void getPagedEmployees(EntityManager em, int startAt, int max) {

		Query query = em.createQuery("from Employee");
		query.setFirstResult(startAt);
		query.setMaxResults(max);

		List<Employee> emps = query.getResultList();
		System.out.println("Page of Employees, starting at " + startAt);
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getRegion());
		}
	}

	// Demo projections 1.
	public static void getEmployeeProjections(EntityManager em) {
	
		String sql = "select e.name, e.salary from Employee e";
		Query query = em.createQuery(sql);
	
		List<Object[]> emps = query.getResultList();
		System.out.println("Employee projection data");
		for (Object[] info : emps) {
			System.out.printf("\t%s earns %.2f\n", info[0], info[1]);
		}
	}

	// Demo projections 2.
	public static void getEmployeeSummaries(EntityManager em) {
		
		String sql = "select new mypackage.EmployeeSummary(e.name, e.salary) from Employee e";
		Query query = em.createQuery(sql);
	
		List<EmployeeSummary> empSums = query.getResultList();
		System.out.println("Employee summaries");
		for (EmployeeSummary empSum: empSums) {
			System.out.printf("\t%s earns %.2f\n", empSum.getName(), empSum.getSalary());
		}
	}
}