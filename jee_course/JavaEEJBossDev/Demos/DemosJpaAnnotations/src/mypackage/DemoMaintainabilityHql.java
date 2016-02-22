package mypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.Query;

public class DemoMaintainabilityHql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Demo executing named queries.
		getEmployeesInSalaryRange(session, 20000, 50000);
	}

	
	// Demo executing named queries.
	public static void getEmployeesInSalaryRange(Session session, double min, double max) {
		
		Query query = session.getNamedQuery("getEmployeesInSalaryRangeHql");
		query.setDouble(0, min);
		query.setDouble(1, max);
	
		List<Employee> emps = query.list();
		System.out.printf("Employees earning between %.2f and %.2f\n", min, max);
		for (Employee emp : emps) {
			System.out.printf("\t%s\t%.2f\n", emp.getName(),emp.getDosh());
		}
	}
}