package mariadb;

import java.sql.*;

/**
 * @author Adam Solloway
 *
 */
public class Addemp {
	private String fname = "";
	private String lname = "";
	private Connection myConn = null;

	/**
	 * @param args
	 */
	public Addemp(Connection myConn, String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
		this.myConn = myConn;
	}

	public void addEmployee() {
		try {
			String query = " insert into employees (fname, lname)" + " values (?, ?)";
			PreparedStatement prepStmt = myConn.prepareStatement(query);
			// prepStmt.setInt(1, id);
			prepStmt.setString(1, fname);
			prepStmt.setString(2, lname);

			prepStmt.execute();
			System.out.printf("%s %s added\n\n", fname, lname);
		}
		catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Entry already exists");
		}

		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
