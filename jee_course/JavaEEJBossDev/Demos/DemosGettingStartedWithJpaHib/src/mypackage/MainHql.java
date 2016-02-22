package mypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.stat.Statistics;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.Query;

public class MainHql {

	public static void main(String[] args) {   
	
		// Create SessionFactory.
		SessionFactory sessionFactory = new Configuration()
												.configure()
												.addResource("mypackage/Employee.hbm.xml")
												.buildSessionFactory();  

		// Enable statistics.
		Statistics stats = sessionFactory.getStatistics();
		stats.setStatisticsEnabled(true);
		
		// Unit of work...
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// Create/execute a query.
		Query query = session.createQuery("from Employee");                    
		List<Employee> emps = query.list();   

		// Iterate through results.
		for (Employee emp: emps) {   
			System.out.println(emp.getName() + "\t" + emp.getSalary() + "\t" + emp.getRegion());
		}   

		// Also update an entity.
		emps.get(0).setName("Paulo");
		
		// Get metadata from SessionFactory.
		ClassMetadata meta = sessionFactory.getClassMetadata(Employee.class);

		// Get property names, and values for a specific employee bean.
		String[] metaPropertyNames = meta.getPropertyNames();
		Object[] propertyValues = meta.getPropertyValues(emps.get(0), EntityMode.POJO);

		System.out.println("\nDetails for employee bean, via metadata:");
		for (int i = 0; i < metaPropertyNames.length; i++) {
			System.out.println(metaPropertyNames[i] + ": " + propertyValues[i]);
		}
		
		// Terminate the unit of work
		session.getTransaction().commit();
		session.close();
		
		// View statistics.
		stats.logSummary();
		EntityStatistics employeeStats = stats.getEntityStatistics("mypackage.Employee");
		System.out.println("Employees fetched: " + employeeStats.getLoadCount());
		System.out.println("Employees updated: " + employeeStats.getUpdateCount());
				
	}
}