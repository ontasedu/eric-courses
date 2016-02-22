package mypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;

public class DemoAggregatesHql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Demo aggregate functions.
		getAverageSalary(session);

		// Demo grouping aggregate results.
		getEmployeeCountPerRegion(session);

		// Demo restricting groups with having.
		getEmployeeCountPerRegionStartingWithS(session);

		// Demo using projections with aggregates.
		getRegionSummaries(session);
	}

	
	// Demo aggregate functions.
	public static void getAverageSalary(Session session) {

		Query query = session.createQuery("select avg(e.dosh) from Employee e");
		double avgSal = (Double) query.uniqueResult();
		
		System.out.printf("Average salary: %.2f\n", avgSal);
	}

	
	// Demo grouping aggregate results.
	public static void getEmployeeCountPerRegion(Session session) {
	
		Query query = session.createQuery("select e.region, count(e) from Employee e group by e.region");
		
		List<Object[]> results = query.list();
		System.out.println("Employee count per region:");
		for (Object[] result: results) {
			System.out.printf("\t%s: %d\n", result[0], result[1]);
		}
	}

	
	// Demo restricting groups with having.
	public static void getEmployeeCountPerRegionStartingWithS(Session session) {
		
		Query query = session.createQuery("select e.region, count(e) from Employee e group by e.region having e.region like 'S%'");
		
		List<Object[]> results = query.list();
		System.out.println("Employee count per region that starts with S:");
		for (Object[] result: results) {
			System.out.printf("\t%s: %d\n", result[0], result[1]);
		}
	}


	public static void getRegionSummaries(Session session) {
		
		Query query = session.createQuery("select new mypackage.RegionSummary(e.region, count(e)) from Employee e group by e.region having e.region like 'S%'");
		
		List<RegionSummary> results = query.list();
		System.out.println("Using projections, employee count per region that starts with S:");
		for (RegionSummary result: results) {
			System.out.printf("\t%s: %d\n", result.getRegionName(), result.getEmployeeCount());
		}
	}
}