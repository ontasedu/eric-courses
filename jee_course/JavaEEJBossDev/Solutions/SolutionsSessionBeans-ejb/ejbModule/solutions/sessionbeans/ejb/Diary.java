package solutions.sessionbeans.ejb;

import java.util.Date;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class Diary implements DiaryLocal {

    private String name;
    private String location;
    private HashMap<Date, String> entries;

    @Override
    public void init(String name, String location) {
        this.name = name;
        this.location = location;
        this.entries = new HashMap<Date, String>();
    }

    @Override
    public void addEntry(Date when, String what) {
        entries.put(when, what);
    }

    @Override
    public void removeEntry(Date when) {
        entries.remove(when);
    }

    @Override
    public String getEntry(Date when) {
        return entries.get(when);
    }
}
