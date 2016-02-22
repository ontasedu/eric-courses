package mypackage;

public interface EmployeeRepository {

	void persistEmployee(String name, double salary, String region);
	
	Employee getEmployee(int employeeId);
	
	void updateSalary(int employeeId, double payRaiseAmount);
	
	void removeEmployee(int employeeId);

	void reallocateSalary(int fromEmployeeId, int toEmployeeId, double amount, boolean willCommit);
	
	public void mergeEmployee(Employee emp);
	
}

