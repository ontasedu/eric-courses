package mypackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

public class DemoMetamodelJpa {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
		EntityManager em = emf.createEntityManager();

		Metamodel mm = em.getMetamodel();
		EntityType<Employee> type = mm.entity(Employee.class);
		
		// Display Metamodel info for the Employee entity type. 
		displayMetamodelInfo(type);
	}

	
	// Display Metamodel info for an entity type.
	public static <T> void displayMetamodelInfo(EntityType<T> type) {
		
		System.out.printf("Metamodel info for the %s type...\n", type.getName());
		System.out.printf("%-20s %-20s %s\n", "Attribute name", "Java data type", "Persistence type");
		System.out.printf("----------------------------------------------------------------\n");
	
		for (Attribute<? super T, ?> attr : type.getAttributes()) {
			System.out.printf("%-20s %-20s %s\n", attr.getName(), attr.getJavaType().getName(), attr.getPersistentAttributeType());
		}
	}
}