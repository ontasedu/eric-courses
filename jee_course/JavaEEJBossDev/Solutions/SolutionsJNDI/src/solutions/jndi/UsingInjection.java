package solutions.jndi;

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

	@Resource(mappedName="java:/DerbyDS")
	private DataSource ds;
	
	private Connection connection= null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbHelper db = new DbHelper(ds, response.getWriter());
		db.doDbOperations();
	}
}
