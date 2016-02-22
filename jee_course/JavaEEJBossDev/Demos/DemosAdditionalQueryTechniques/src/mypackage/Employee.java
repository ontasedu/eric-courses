package mypackage;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.CascadeType;

@Entity
@Table(name="EMPLOYEES", schema="MYSCHEMA") 
@SecondaryTable(name="CONTRACTS", schema="MYSCHEMA",
		        pkJoinColumns=@PrimaryKeyJoinColumn(name="EMPLOYEEID")) 
public class Employee {

	private int employeeId = -1;
	
	private String name;
	private double dosh;
	private String region;
	private Set<Skill> skills = new HashSet<Skill>();
	
	private Date startDate;
	private double startSalary;
	
	public Employee() {
		// Empty no-arg constructor.
	}
	
	public Employee(String name, double dosh, String region) {
		this.name = name;
		this.dosh = dosh;
		this.region = region;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getEmployeeId() {
		return employeeId;
	}

    public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
    public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    @Column(name="Salary")
    public double getDosh() {
		return dosh;
	}
	
    public void setDosh(double dosh) {
		this.dosh = dosh;
	}
	
    public String getRegion() {
		return region;
	}
	
    public void setRegion(String region) {
		this.region = region;
	}
	
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="EMPLOYEEID")
    public Set<Skill> getSkills() {
		return skills;
	}
	
    public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@Column(table="CONTRACTS")
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
      return startDate;
    }

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(table="CONTRACTS")
	public double getStartSalary() {
		return startSalary;
	}

	public void setStartSalary(double startSalary) {
		this.startSalary = startSalary;
	}

	@Override
	public String toString() {
		return String.format("Employee Id %d\t %s\t %.2f\t %s",
							  employeeId, name, dosh, region);	
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other instanceof Employee) {
			Employee otherEmp = (Employee)other;
			result = (this.employeeId == otherEmp.employeeId);
		}
		return result;
	}
	
	@Override 
	public int hashCode() {
		return employeeId;
	}
}
