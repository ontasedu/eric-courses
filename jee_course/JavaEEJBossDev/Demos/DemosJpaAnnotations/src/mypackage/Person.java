package mypackage;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSONS", schema="MYSCHEMA")
public class Person {

	private int personId = -1;
	private String firstName;
	private String lastName;

	@Embedded
	private Address address;

	public Person() {
		// Empty no-arg constructor.
	}

	public Person(int personId, String firstName, String lastName, String addressLine1, String addressLine2, String addressLine3) {
		this.personId  = personId;
		this.firstName = firstName;
		this.lastName  = lastName;
		this.address   = new Address(addressLine1, addressLine2, addressLine3);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s %s: %s", 
				personId, firstName, lastName, address.toString());
	}
}