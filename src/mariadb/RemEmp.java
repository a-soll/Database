package mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Adam Solloway
 *
 */
public class RemEmp {
	private int id = 0;
	private Connection myConn = null;
	
	public RemEmp(Connection myConn, int id) {
		this.myConn = myConn;
		this.id = id;
	}

	public void delete() {
		try {
			String check = "select * from employees where id = ?";
			PreparedStatement pst = myConn.prepareStatement(check);
			pst.setInt(1, id);
			boolean rs = pst.execute();
			
			if (rs == true) {
				String delete = "delete from employees where id = ?";
				PreparedStatement prepStmt = myConn.prepareStatement(delete);
				prepStmt.setInt(1, id);
				prepStmt.executeUpdate();
				System.out.printf("Employee with ID %d removed\n\n", id);
			}
			else {
				System.out.println("No user with that ID exists");
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
