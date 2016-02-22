package mypackage;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.Query;

public class DemoCollectionsHql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Demo getting collections.
		getEmployeeSkills(session);
	}

	public static void getEmployeeSkills(Session session) {
		
		String sql = "from Employee e";
		Query query = session.createQuery(sql);
	
		List<Employee> emps = query.list();
		for (Employee emp : emps) {
			System.out.printf("\nSkills for %s\n", emp.getName());
			for (Skill skill: emp.getSkills()) {
				System.out.printf("\t%s %d\n", skill.getDescription(), skill.getLevel());
			}
		}
	}
}