package mypackage;

public class RegionSummary {

	private String regionName;
	private long employeeCount;

	public RegionSummary(String regionName, long employeeCount) {
		this.regionName= regionName;
		this.employeeCount = employeeCount;
	}

	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public long getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(long employeeCount) {
		this.employeeCount = employeeCount;
	}
}
