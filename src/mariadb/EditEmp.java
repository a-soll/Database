package mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author Adam Solloway
 *
 */
public class EditEmp {
	private Connection myConn;
	private int data = 0;
	private int id = 0;
	Scanner in = new Scanner(System.in);

	public EditEmp(Connection myConn, int data, int id) {
		this.myConn = myConn;
		this.id = id;
		this.data = data;
	}

	public void empEdit() {
		try {
			String check = "select * from employees where id = ?";
			PreparedStatement pst = myConn.prepareStatement(check);
			pst.setInt(1, id);
			boolean rs = pst.execute();

			if(rs == true) {
				switch (data) {
				case 1:
					editFname(pst);
					break;
				
				case 2:
					editLname(pst);
				}
			}

			else {
				System.out.println("No employee with that ID");
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

	public void editFname(PreparedStatement pst){
		String s = "";
		System.out.print("Enter new first name: ");
		s = in.next();

		try {
			String edit = "update employees set fname = ? where id = ?";
			pst = myConn.prepareStatement(edit);
			pst.setInt(2, id);
			pst.setString(1, s);
			pst.executeUpdate();
			System.out.println();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void editLname(PreparedStatement pst) {
		String s = "";
		System.out.print("Enter new last name: ");
		s = in.next();

		try {
			String edit = "update employees set lname = ? where id = ?";
			pst = myConn.prepareStatement(edit);
			pst.setInt(2, id);
			pst.setString(1, s);
			pst.executeUpdate();
			System.out.println();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
}
