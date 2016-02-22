package demos.sessionbeans.ejb;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ShoppingCartBeanRemote {
	public void addItem(String productName);    
	public void removeItem(String productName);
	public ArrayList<String> getItems();
}