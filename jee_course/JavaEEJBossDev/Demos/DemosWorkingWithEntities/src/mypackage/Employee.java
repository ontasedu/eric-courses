package mypackage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.CascadeType;

@Entity
@Table(name="EMPLOYEES", schema="MYOBJSCHEMA") 
public class Employee {

	private int employeeId = -1;
	
	private String name;
	private double dosh;
	private String region;
	private Set<Skill> skills = new HashSet<Skill>();
	
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
	
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL}, targetEntity=Skill.class)
    @JoinColumn(name="EMPLOYEEID")
    public Set<Skill> getSkills() {
		return skills;
	}
	
    public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return String.format("[%d] %-8s %.2f %s",
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
