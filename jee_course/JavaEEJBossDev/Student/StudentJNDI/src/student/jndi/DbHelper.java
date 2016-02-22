package student.jndi;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

// This class is already 100% complete :-)
public class DbHelper {

	private DataSource ds;
	private Writer out;
	private Connection connection;
	
	
	public DbHelper(DataSource ds, Writer out) {
		this.ds = ds; 
		this.out = out;
	}
	

	public void doDbOperations() throws IOException {
		
		try {
			
			// Use the data source to get a connection.
			connection = ds.getConnection();

			// Perform some data operations.
			createTable();
			insertData();
			queryData();
			dropTable();
		} 
		catch (SQLException ex) {
			out.write("<h3>SQL Exception occurred: " + ex.getMessage() + "</h3>");
		}
		finally {
			if (connection != null) { 
				try { connection.close(); } catch (SQLException ex) {} 
			}
		}
	}
	
	
	private void createTable() throws SQLException, IOException {

		String sql = 
				"CREATE TABLE MySchema.Books ( " + "Isbn  INTEGER NOT NULL, " +
				"Title VARCHAR(50) NOT NULL, " +
				"Price DOUBLE NOT NULL, " +
				"CONSTRAINT PK_Books PRIMARY KEY (Isbn) " + ")";

		Statement st = connection.createStatement();
		st.executeUpdate(sql);
		out.write("<h3>MySchema.Books table created!</h3>");
	}


	private void insertData() throws SQLException, IOException {

		out.write("<h3>Inserting books...</h3>");

		String sql = "INSERT INTO MySchema.Books VALUES (?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setInt(1, 1111);
		ps.setString(2, "Book 1111");
		ps.setDouble(3, 1.11);
		ps.executeUpdate();
		out.write("Inserted item 1111<br>");

		ps.setInt(1, 2222);
		ps.setString(2, "Book 2222");
		ps.setDouble(3, 2.22);
		ps.executeUpdate();
		out.write("Inserted item 2222<br>");

		ps.setInt(1, 3333);
		ps.setString(2, "Book 3333");
		ps.setDouble(3, 3.33);
		ps.executeUpdate();
		out.write("Inserted item 3333<br>");
	}


	private void queryData() throws SQLException, IOException {

		out.write("<h3>Querying books...</h3>");

		String sql = "SELECT Isbn, Title, Price FROM MySchema.Books";

		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next() != false) {
			out.write("ISBN:  " + rs.getInt(1));
			out.write(" Title: " + rs.getString(2));
			out.write(" Price: " + rs.getDouble(3));
			out.write("<br>");
		}

	}


	private void dropTable() throws SQLException, IOException {
		Statement st = connection.createStatement();
		st.executeUpdate("DROP TABLE MySchema.Books");
		out.write("<h3>MySchema.Books table dropped!</h3>");
	}
}
