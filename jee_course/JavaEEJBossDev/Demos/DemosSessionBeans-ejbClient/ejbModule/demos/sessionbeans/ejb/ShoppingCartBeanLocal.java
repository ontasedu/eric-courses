package demos.sessionbeans.ejb;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ShoppingCartBeanLocal {
	public void addItem(String productName);    
	public void removeItem(String productName);
	public ArrayList<String> getItems();
}
