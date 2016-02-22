package mypackage;

public class EmployeeIdNameSummary {

	private int empId;
	private String empName;

	public EmployeeIdNameSummary() {
		// Criteria API requires a no-arg ctor.
	}

	public EmployeeIdNameSummary(int empId, String empName) {
		this.empId = empId;
		this.empName = empName;
	}

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String name) {
		this.empName = name;
	}

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int id) {
		this.empId = id;
	}
}
