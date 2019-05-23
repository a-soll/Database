package mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Adam Solloway
 *
 */
public class EditEmp {
	private Connection myConn = null;
	private String fname = "";
	private int id = 0;
	
	public EditEmp(Connection myConn, String fname, int id) {
		this.myConn = myConn;
		this.id = id;
		this.fname = fname;
	}

	public void empEdit() {
		try {
			String check = "select * from employees where id = ?";
			PreparedStatement pst = myConn.prepareStatement(check);
			pst.setInt(1, id);
			boolean rs = pst.execute();
			
			if(rs == true) {
				String edit = "update employees set fname = ? where id = ?";
				pst = myConn.prepareStatement(edit);
				pst.setInt(2, id);
				pst.setString(1, fname);
				pst.executeUpdate();
				System.out.println();
			}
			
			else {
				System.out.println("No employee with that ID");
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
