package mypackage;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;
	
	
	// Constructor, hooks up repository for use by service layer.
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	
	// Demo making entities persistent.
	public void insertEmployee(String name, double salary, String region) {

		System.out.println("\n===Service message: Persisting entity into database ==========");
		repository.persistEmployee(name, salary, region);
	}

	
	// Demo getting entities.
	public void displayEmployees(int... employeeIds) {

		System.out.println("\n===Service message: Getting objects from database ==================");
		for(int employeeId : employeeIds) {
			Employee emp = repository.getEmployee(employeeId);
			safeDisplayEmployee(emp);
		}
	}

	
	// Demo modifying and persisting entities.
	public void scoreEmployee(int employeeId, int reviewScore) {

		System.out.println("\n===Service message: Modifying and persisting objects ===============");

		System.out.println("Current employee details: ");
		Employee emp = repository.getEmployee(employeeId);
		safeDisplayEmployee(emp);

		repository.updateSalary(employeeId, reviewScore*500);
		
		System.out.println("Employee details after salary review: ");
		emp = repository.getEmployee(employeeId);
		safeDisplayEmployee(emp);
	}

	
	// Demo removing an entity.
	public void sackEmployee(int employeeId) {
		
		System.out.println("\n===Service message: Removing an entity =============================");

		System.out.println("\nAbout to remove employee: " + employeeId);

		repository.removeEmployee(employeeId);

		System.out.println("Trying to get employee now...");
		Employee emp = repository.getEmployee(employeeId);
		safeDisplayEmployee(emp);
	}
	

	// Demo transactions.
	public void reallocateSalaries(int fromEmployeeId, int toEmployeeId, double amount) {
		
		System.out.println("\n===Service message: Performing transactional work ==================");

		System.out.println("Before reallocating salaries...");
		safeDisplayEmployee(repository.getEmployee(1));
		safeDisplayEmployee(repository.getEmployee(2));

		// Call a transactional method, which will commit.
		repository.reallocateSalary(fromEmployeeId, toEmployeeId, amount, true);
	
		System.out.println("After reallocating salaries [with commit]...");
		safeDisplayEmployee(repository.getEmployee(1));
		safeDisplayEmployee(repository.getEmployee(2));

		// Call a transactional method, which will not commit.
		repository.reallocateSalary(1, 2, 1000, false);
			
		System.out.println("After reallocating salaries [without commit]...");
		safeDisplayEmployee(repository.getEmployee(1));
		safeDisplayEmployee(repository.getEmployee(2));
	}

	
	// Demo working with detached entities. 
	public void workWithDetachedEmployee(int employeeId) {
	
		System.out.println("\n===Service message: Working with detached entities =================");

		Employee emp = repository.getEmployee(employeeId);
		safeDisplayEmployee(emp);
		
		emp.setDosh(emp.getDosh() * 10);
		repository.mergeEmployee(emp);
		
		Employee empNow = repository.getEmployee(employeeId);
		safeDisplayEmployee(empNow);
	}


	// Helper method.
	private void safeDisplayEmployee(Employee emp) {
		if (emp != null) {
			System.out.println(emp);	
		}
	}
}
