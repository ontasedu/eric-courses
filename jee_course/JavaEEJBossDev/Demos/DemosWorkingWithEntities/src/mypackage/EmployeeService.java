package mypackage;

public interface EmployeeService {
	
	void insertEmployee(String name, double salary, String region);
	
	void displayEmployees(int... employeeIds);
	
	void scoreEmployee(int employeeId, int reviewScore);
	
	void sackEmployee(int employeeId);
	
	void reallocateSalaries(int fromEmployeeId, int toEmployeeId, double amount);
	
	void workWithDetachedEmployee(int employeeId);
}
