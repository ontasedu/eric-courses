package mypackage;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DemoAggregatesJpa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();

		// Demo aggregate functions.
		getAverageSalary(em);

		// Demo grouping aggregate results.
		getEmployeeCountPerRegion(em);

		// Demo restricting groups with having.
		getEmployeeCountPerRegionStartingWithS(em);

		// Demo using projections with aggregates.
		getRegionSummaries(em);
	}

	
	// Demo aggregate functions.
	public static void getAverageSalary(EntityManager em) {

		Query query = em.createQuery("select avg(e.dosh) from Employee e");
		double avgSal = (Double) query.getSingleResult();
		
		System.out.printf("Average salary: %.2f\n", avgSal);
	}

	
	// Demo grouping aggregate results.
	public static void getEmployeeCountPerRegion(EntityManager em) {
	
		Query query = em.createQuery("select e.region, count(e) from Employee e group by e.region");
		
		List<Object[]> results = query.getResultList();
		System.out.println("Employee count per region:");
		for (Object[] result: results) {
			System.out.printf("\t%s: %d\n", result[0], result[1]);
		}
	}

	
	// Demo restricting groups with having.
	public static void getEmployeeCountPerRegionStartingWithS(EntityManager em) {
		
		Query query = em.createQuery("select e.region, count(e) from Employee e group by e.region having e.region like 'S%'");
		
		List<Object[]> results = query.getResultList();
		System.out.println("Employee count per region that starts with S:");
		for (Object[] result: results) {
			System.out.printf("\t%s: %d\n", result[0], result[1]);
		}
	}


	public static void getRegionSummaries(EntityManager em) {
		
		Query query = em.createQuery("select new mypackage.RegionSummary(e.region, count(e)) from Employee e group by e.region having e.region like 'S%'");
		
		List<RegionSummary> results = query.getResultList();
		System.out.println("Using projections, employee count per region that starts with S:");
		for (RegionSummary result: results) {
			System.out.printf("\t%s: %d\n", result.getRegionName(), result.getEmployeeCount());
		}
	}
}