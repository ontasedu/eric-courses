package demos.sessionbeans.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import demos.sessionbeans.ejb.ShoppingCartBeanLocal;

@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Get existing ShoppingCart bean from HTTP session if it exists,
            // or get a new one via JNDI lookup.
            HttpSession session = request.getSession();
            ShoppingCartBeanLocal bean = (ShoppingCartBeanLocal) session.getAttribute("Cart");
            if (bean == null) {
                InitialContext context = new InitialContext();
                bean = (ShoppingCartBeanLocal) context.lookup("java:app/DemosSessionBeans-ejb/ShoppingCartBean!demos.sessionbeans.ejb.ShoppingCartBeanLocal");
                session.setAttribute("Cart", bean);
            } 

            // Add item to shopping cart bean.
            String item = request.getParameter("txtItem");
            if (item != null && item.length() != 0) {
                bean.addItem(item);
            }

            // Redisplay HTML form.
            RequestDispatcher rd = request.getRequestDispatcher("/ManageShoppingCart.html");
            rd.include(request, response);
            
            // Display shopping cart items.
            rd = request.getRequestDispatcher("/DisplayShoppingCart.jsp");
            rd.include(request, response);

        } catch (Exception ex) {
            out.println("Exception occurred: " + ex.getMessage());
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
