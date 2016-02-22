package mypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.Query;

public class DemoEntityToMultipleTablesHql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Demo retrieving an entity from multiple tables.
		getEmployeesAndContracts(session);

		// Close session when we're finished.
		session.close();
	}

	public static void getEmployeesAndContracts(Session session) {

		Query query = session.createQuery("from Employee");

		List<Employee> emps = query.list();
		System.out.println("Employees and contracts");
		for (Employee emp : emps) {
			System.out.println("Employee name: "  + emp.getName() + 
			           		   "\tStart date: "   + emp.getStartDate() + 
			                   "\tStart salary: " + emp.getStartSalary());
		}
	}
}