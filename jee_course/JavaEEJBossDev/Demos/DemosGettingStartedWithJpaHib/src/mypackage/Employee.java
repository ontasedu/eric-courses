package mypackage;

public class Employee {

	private long employeeId = -1;
	private String name;
	private double salary;
	private String region;
	
	public Employee() {
		// Empty no-arg constructor.
	}
	
	public Employee(String name, double salary, String region) {
		this.name = name;
		this.salary = salary;
		this.region = region;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
}
