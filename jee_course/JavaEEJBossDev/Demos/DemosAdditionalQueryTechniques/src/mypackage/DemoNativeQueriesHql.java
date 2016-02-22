package mypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class DemoNativeQueriesHql {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Demo executing native SQL query.
		getSkillsForEmployee(session, 1);
	}

	// Demo executing native SQL query.
	public static void getSkillsForEmployee(Session session, int employeeId) {
	
		SQLQuery query = session.createSQLQuery("select * from MYSCHEMA.SKILLS where EMPLOYEEID=?");
		query.setInteger(0, employeeId);
		query.addEntity(Skill.class);
		
		List<Skill> skills = query.list();
		System.out.printf("Skills for employee %d\n", employeeId);
		for (Skill skill : skills) {
			System.out.printf("\t%s\t%d\n", skill.getDescription(), skill.getLevel());
		}
	}
}