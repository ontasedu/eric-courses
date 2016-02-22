package mypackage;

import java.util.HashSet;
import java.util.Set;

public class Employee {

	private int employeeId = -1;
	private String name;
	private double salary;
	private String region;
	private Set<Skill> skills = new HashSet<Skill>();
	
	public Employee() {
		// Empty no-arg constructor.
	}
	
	public Employee(String name, double salary, String region) {
		this.name = name;
		this.salary = salary;
		this.region = region;
	}

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

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

	
	// getSkills() version 1 - simple getter.
	public Set<Skill> getSkills() {
		return skills;
	}

	// getSkills() version 2 - returns a copy of the collection, changes won't be detected!
	/* 
	 public Set<Skill> getSkills() {
		Set<Skill> copy = new HashSet<Skill>();
		copy.addAll(skills);
		System.out.println("Called get, skills size is " + this.skills.size() + ", copy size is " + copy.size());
		return copy;
	}
	*/
	
	// getSkills() version 3 - returns a readonly collection, prevents client from trying to change collection contents directly.
	/*
	public Set<Skill> getSkills() {
		return Collections.unmodifiableSet(skills);
	}
	*/
	

	private void setSkills(Set<Skill> skills) {
		this.skills = skills;
		System.out.println("Called set, skills size is " + this.skills.size());
	}
	
	public void addSkill(Skill skill) {
		// Some pseudo-business logic
		if (skill.getLevel() < 3) {
			throw new IllegalArgumentException("Skill " + skill.getDescription() + 
					                           " not added, level is too low!");
		}
		this.skills.add(skill);	
	}
}
