package solutions.sessionbeans.ejb;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface DiaryLocal {
	void init(String name, String location);
	public void addEntry(Date when, String what);
	public void removeEntry(Date when);
	public String getEntry(Date when);
}
