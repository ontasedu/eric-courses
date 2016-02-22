package mypackage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.stat.Statistics;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainJpa {

	public static void main(String[] args) {   
	
		// Create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");

		// First, cast EntityManagerFactory to a HibernateEntityManagerFactory.
		HibernateEntityManagerFactory hibEmf = (HibernateEntityManagerFactory)emf;
		
		// Then get and use SessionFactory.
		SessionFactory sf = hibEmf.getSessionFactory();
		Statistics stats = sf.getStatistics();
		stats.setStatisticsEnabled(true);
		
		// Start a unit of work...
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Create/execute a query.
		Query query = em.createQuery("from Employee e where e.salary <= 40000");                    
		List<Employee> emps = query.getResultList();   

		// Iterate through results.
		for (Employee emp: emps) {   
			System.out.println(emp.getName() + "\t" + emp.getSalary() + "\t" + emp.getRegion());
		}   

		// Also update an entity.
		emps.get(0).setName("Paulo");

		// Get Hibernate Session (if you want to use HQL).
		Session session = (Session)em.getDelegate();
		org.hibernate.Query hqlQuery = session.createQuery("from Employee e where e.salary >= 60000");                    
		emps = hqlQuery.list();   
		for (Employee emp: emps) {   
			System.out.println(emp.getName() + "\t" + emp.getSalary() + "\t" + emp.getRegion());
		}   


		// Terminate the unit of work
		em.getTransaction().commit();
		em.close();
		
		// View statistics.
		stats.logSummary();
		EntityStatistics employeeStats = stats.getEntityStatistics("mypackage.Employee");
		System.out.println("Employees fetched: " + employeeStats.getLoadCount());
		System.out.println("Employees updated: " + employeeStats.getUpdateCount());
	}
}