package demos.sessionbeans.ejb;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

@Stateful
public class ShoppingCartBean implements ShoppingCartBeanLocal, ShoppingCartBeanRemote {

	private ArrayList<String> items = new ArrayList<String>();

	public void addItem(String productName) {
		items.add(productName);
	}

	public void removeItem(String productName) {
		items.remove(productName);
	}

	public ArrayList<String> getItems() {
		return items;
	}

    @PostConstruct
    public void myPostConstructMethod() {
        System.out.println("Bean created at: " + new Date());
    }

    @PreDestroy
    public void myPreDestroyMethod() {
        System.out.println("Bean destroyed at: " + new Date());
    }

    @PrePassivate
    public void myPrePassivateMethod() {
        System.out.println("Bean passivated at : " + new Date());
    }

    @PostActivate
    public void myPostActivateMethod() {
        System.out.println("Bean activated at: " + new Date());
    }
}
