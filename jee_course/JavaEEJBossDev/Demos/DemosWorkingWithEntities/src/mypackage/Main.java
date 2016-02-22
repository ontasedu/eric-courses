package mypackage;

public class Main {

	public static void main(String[] args) {

		try {
			// Create a service object, passing it a repository object.
			EmployeeRepository repository = new EmployeeRespositoryImpl("MyPU");
			EmployeeService service = new EmployeeServiceImpl(repository);
			
			// Demo persisting (i.e. inserting) new entities.
			service.insertEmployee("Bruce",  12345, "Darwin");
			service.insertEmployee("Shiela", 24680, "Sydney");
			service.insertEmployee("James",  67890, "London");

			// Demo getting entities.
			service.displayEmployees(1, 2, 3, 4, 5);
	
			// Demo modifying entities.
			service.scoreEmployee(4, 10);
			
			// Demonstrate removing an entity.
			service.sackEmployee(3);
			
			// Demo transactional work.
			service.reallocateSalaries(1, 2, 1000);
			
			// Demo working with detached entities.
			service.workWithDetachedEmployee(4);
			
			System.out.println("\nGoodbye!");

		} catch (Exception ex) {
			System.out.println("***** Exception occurred: " + ex.getMessage());
		}
	}
}
