package demos.advancedsessionbeans.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demos.advancedsessionbeans.ejb.AccountManagerBeanRemote;

@WebServlet("/DemoDependenciesAndInjectionsServlet")
public class DemoDependenciesAndInjectionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	@EJB
    private static AccountManagerBeanRemote accountManagerBean;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.print("In DemoDependenciesAndInjectionsServlet, see console for messages");
		accountManagerBean.displayBindings("java:comp");
        accountManagerBean.useBeans();
        accountManagerBean.useEnvironmentEntries();
        accountManagerBean.useExternalResources();
	}
}
