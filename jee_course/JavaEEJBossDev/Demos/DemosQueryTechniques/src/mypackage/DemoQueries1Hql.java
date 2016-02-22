package mypackage;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Query;

public class DemoQueries1Hql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Demo retrieving an object by primary key.
		getEmployee(session, 7);

		// Demo querying for objects using HQL.
		getEmployeesEarning(session, 40000);

		// Demo WHERE clause operators.
		getEmployeesIn(session, "Kent", "Northern Ireland");

		// Demo ordering rows.
		getSortedEmployees(session);

		// Demo paging results.
		getPagedEmployees(session, 0, 5);
		getPagedEmployees(session, 5, 5);

		// Demo projections 1.
		getEmployeeProjections(session);

		// Demo projections 2.
		getEmployeeSummaries(session);

		// Close session when we're finished.
		session.close();
	}

	// Demo retrieving an object by primary key.
	public static void getEmployee(Session session, int employeeID) {

		Employee emp = (Employee) session.get(Employee.class, employeeID);
		if (emp == null) {
			System.out.println("No employee with ID " + employeeID);
		} else {
			System.out.println("Employee with ID " + emp.getEmployeeId()
					+ " is " + emp.getName());
		}
	}

	// Demo querying for objects using HQL.
	public static void getEmployeesEarning(Session session, double minSalary) {

		Query query = session
				.createQuery("from Employee e where e.salary >= :minSalary");
		query.setDouble("minSalary", minSalary);

		// Or using positional parameters... HQL positional parameters start at
		// 0, and are not numbered.
		// Query query =
		// session.createQuery("from Employee e where e.Salary >= ?");
		// query.setDouble(0, minSalary);

		List<Employee> emps = query.list();
		System.out.println("Using list(), employees earning at least " + minSalary);
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getSalary());
		}
		
		// Or you can use an iterator:
		Iterator<Employee> iter = query.iterate();
		System.out.println("Using iterator(), employees earning at least " + minSalary);
		while (iter.hasNext()) {
			Employee e = iter.next();
			System.out.println("\t" + e.getName() + "\t" + e.getSalary());
		}
	}

	// Demo WHERE clause operators.
	public static void getEmployeesIn(Session session, String... regions) {

		Query query = session
				.createQuery("from Employee e where e.region in (:regions)");
		query.setParameterList("regions", regions);

		List<Employee> emps = query.list();
		System.out.println("Employees in one of regions ");
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getRegion());
		}
	}

	// Demo ordering rows.
	public static void getSortedEmployees(Session session) {

		Query query = session
				.createQuery("from Employee e order by e.region, e.name");

		List<Employee> emps = query.list();
		System.out.println("Employees sorted by region, then by name ");
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getRegion());
		}
	}

	// Demo paging results.
	public static void getPagedEmployees(Session session, int startAt, int max) {

		Query query = session.createQuery("from Employee");
		query.setFirstResult(startAt);
		query.setMaxResults(max);

		List<Employee> emps = query.list();
		System.out.println("Page of Employees, starting at " + startAt);
		for (Employee emp : emps) {
			System.out.println("\t" + emp.getName() + "\t" + emp.getRegion());
		}
	}

	// Demo projections 1.
	public static void getEmployeeProjections(Session session) {

		String sql = "select e.name, e.salary from Employee e";
		Query query = session.createQuery(sql);

		List<Object[]> emps = query.list();
		System.out.println("Employee projection data");
		for (Object[] info : emps) {
			System.out.printf("\t%s earns %.2f\n", info[0], info[1]);
		}
	}

	// Demo projections 2.
	public static void getEmployeeSummaries(Session session) {

		String sql = "select new mypackage.EmployeeSummary(e.name, e.salary) from Employee e";
		Query query = session.createQuery(sql);

		List<EmployeeSummary> empSums = query.list();
		System.out.println("Employee summaries");
		for (EmployeeSummary empSum : empSums) {
			System.out.printf("\t%s earns %.2f\n", empSum.getName(), empSum
					.getSalary());
		}
	}
}