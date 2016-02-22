package demos.sessionbeans.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demos.sessionbeans.ejb.TempConverterBeanLocal;

@WebServlet(name="TempConverterServlet", urlPatterns={"/TempConverterServlet"})
public class TempConverterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            InitialContext context = new InitialContext();
            TempConverterBeanLocal bean = (TempConverterBeanLocal) context.lookup("java:app/DemosSessionBeans-ejb/TempConverterBean!demos.sessionbeans.ejb.TempConverterBeanLocal");

            out.println("212F = " + bean.fToC(212) + "C<br>");
            out.println("100C = " + bean.cToF(100) + "F");

        } catch (Exception ex) {
            out.println("Exception occurred: " + ex.getMessage());
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
