package mypackage;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String line1;
	private String line2;
	private String line3;

	public Address() {
		// Empty no-arg constructor.
	}
	
	public Address(String line1, String line2, String line3) {
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
	}

	@Column(name="ADDRESS1")
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	@Column(name="ADDRESS2")
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	@Column(name="ADDRESS3")
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s", line1, line2, line3);	
	}
}
