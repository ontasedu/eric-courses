package student.jndi;

import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/UsingInjection")
public class UsingInjection extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Declare a DataSource variable here, and use JNDI resource injection to populate it. 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Create a DbHelper object, and pass 2 parameters into the constructor:
		//   - the DataSource object (you got this via JNDI resource injection above)
		//   - a Writer object that can write to the HTML response buffer (response.getWriter())

		
		// Invoke doDbOperations() on the DbHelper object.
		
		
	}
}
