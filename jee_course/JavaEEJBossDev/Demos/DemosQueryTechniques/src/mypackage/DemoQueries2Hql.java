package mypackage;

import java.util.Set;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class DemoQueries2Hql {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		addSkillsForEmp(1);
		displaySkillsForEmp(1);
	}

	
	// Demonstrates persistence by reachability (i.e. no need to explicitly save() reachable objects).
	private static void addSkillsForEmp(int employeeID) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Employee emp = (Employee) session.get(Employee.class, employeeID);
		if (emp == null) {
			System.out.println("No employee with ID " + employeeID);

		} else {

			// Add skills long-hand.
			Set<Skill> empSkills = emp.getSkills();

			Skill s1 = new Skill();
			s1.setDescription("Wii Fit");
			s1.setLevel(5);
			empSkills.add(s1);
			
			Skill s2 = new Skill();
			s2.setDescription("Sudoko");
			s2.setLevel(3);
			empSkills.add(s2);			
			
			// Add skills short-hand.
			/*
			Skill s1 = new Skill();
			s1.setDescription("Rugby");
			s1.setLevel(4);
			emp.addSkill(s1);
			
			Skill s2 = new Skill();
			s2.setDescription("Swimming");
			s2.setLevel(3);
			emp.addSkill(s2);
			*/
			
		}			
		session.getTransaction().commit();
		session.close();
	}
	

	// Demonstrates what happens if a getter returns a copy of a collection. 
	private static void displaySkillsForEmp(int employeeID) {
		
		Session session = sessionFactory.openSession();

		Employee emp = (Employee) session.get(Employee.class, employeeID);
		if (emp == null) {
			System.out.println("No employee with ID " + employeeID);

		} else {

			for (Skill s: emp.getSkills()) {
				System.out.println("Skill: " + s.getDescription() + ", Level: " + s.getLevel());
			}
		}		
		session.close();
	}
}