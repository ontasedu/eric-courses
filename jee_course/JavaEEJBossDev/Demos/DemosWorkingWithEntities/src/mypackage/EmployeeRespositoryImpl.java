package mypackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeRespositoryImpl implements EmployeeRepository {

	private EntityManagerFactory factory;
	
	
	// Constructor, creates EntityManagerFactory for specified persistence unit name.
	public EmployeeRespositoryImpl(String puName) {
		this.factory = Persistence.createEntityManagerFactory(puName);
	}

	
	// Persist an Employee entity.
	public void persistEmployee(String name, double salary, String region) {
		
		EntityManager em = factory.createEntityManager();
		Employee emp = new Employee(name, salary, region);
	
		em.getTransaction().begin();
		em.persist(emp);
		em.getTransaction().commit();
	
		System.out.println("Persisted employee, in-memory state: " + emp);
		em.close();
	}
	
	
	// Get an Employee entity by PK.
	public Employee getEmployee(int employeeId) {
	
		EntityManager em = factory.createEntityManager();
		Employee emp = em.find(Employee.class, employeeId);
	
		em.close();

		return emp;
	}
	
	
	// Get an Employee entity and modify it.
	public void updateSalary(int employeeId, double payRaiseAmount) {

		EntityManager em = factory.createEntityManager();
		
		Employee emp = em.find(Employee.class, employeeId);

		if (emp == null) {
			System.out.println("No employee with ID " + employeeId);
		}
		else {
			System.out.println("Got employee with ID " + employeeId);

			em.getTransaction().begin();

			Skill s = new Skill();
			s.setDescription("My skill");
			s.setLevel(5);
			emp.getSkills().add(s);

			double sal = emp.getDosh();
			emp.setDosh(sal + payRaiseAmount);

			System.out.println("Updated employee... Employee in persistence context? " + em.contains(emp));
	
			em.getTransaction().commit();
			System.out.println("Committed tx... Employee in persistence context? " + em.contains(emp));
		}
		em.close();
	}
	

	// Remove an Employee entity.
	public void removeEmployee(int employeeId) {
	
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Employee emp = em.find(Employee.class, employeeId);
		if (emp != null) {
			em.remove(emp);
			em.getTransaction().commit();
		}
		
		em.close();
	}


	// Do transactional work.
	public void reallocateSalary(int fromEmployeeId, int toEmployeeId, double amount, boolean willCommit) {
	
		EntityManager em = factory.createEntityManager();
	
		Employee emp1 = em.find(Employee.class, fromEmployeeId);
		Employee emp2 = em.find(Employee.class, toEmployeeId);
		
		if (emp1 != null && emp2 != null) {

			em.getTransaction().begin();
			emp1.setDosh(emp1.getDosh() - amount);
			emp2.setDosh(emp2.getDosh() + amount);
	
			if (willCommit) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}
		}
		em.close();
	}


	// Merge an Employee entity into persistence context.
	public void mergeEmployee(Employee emp) {
		
		EntityManager em = factory.createEntityManager();

		em.merge(emp);

		em.close();
	}
}
