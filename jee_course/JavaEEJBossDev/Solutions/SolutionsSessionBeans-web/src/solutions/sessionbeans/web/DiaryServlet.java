package solutions.sessionbeans.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import solutions.sessionbeans.ejb.DiaryLocal;

@WebServlet("/DiaryServlet")
public class DiaryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            InitialContext context = new InitialContext();
            DiaryLocal diary = (DiaryLocal) context.lookup("java:app/SolutionsSessionBeans-ejb/Diary!solutions.sessionbeans.ejb.DiaryLocal");

            // Initialize the diary bean.
	        diary.init("Tom", "London");
	
	        // Insert 3 entries into the diary
	        Calendar date1 = Calendar.getInstance();
	        date1.set(2012, 12, 3, 9, 00);    // 9am, 3rd December 2012
	
	        diary.addEntry(date1.getTime(), "Meeting in Canary Wharf");
	
	        Calendar date2 = Calendar.getInstance();
	        date2.set(2012, 12, 6, 10, 00);   // 10.00am, 6th December 2012
	
	        diary.addEntry(date2.getTime(), "Business Update Meeting");
	
	        Calendar date3 = Calendar.getInstance();
	        date3.set(2012, 12, 8, 12, 00);   // 12.00 noon, 8th December 2012
	
	        diary.addEntry(date3.getTime(), "Munchies with Claire");
	
	        // Remove an entry from the diary      
	        diary.removeEntry(date2.getTime());
	
	        // Get some entries in the diary
	        String entry1 = diary.getEntry(date1.getTime());
	        out.println("Entry1: " + entry1 + "<br>");
	
	        String entry3 = diary.getEntry(date3.getTime());
	        out.println("Entry3: " + entry3 + "<br>");
        }
        catch (Exception ex) {
        	out.println("Exception occurred: " + ex.getMessage());
        }
	}
}
