package mariadb;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Adam Solloway
 *
 */
public class Driver {
	private static Scanner in = new Scanner(System.in);
	private static int userin = 0;

	public static void main(String[] args) {
		do {
			try {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC", "root", "alpine");

				System.out.println("What would you like to do?");
				System.out.println("1. Add employee");
				System.out.println("2. Edit employee info");
				System.out.println("3. Remove employee");
				System.out.println("4. Display employees");
				System.out.println("5. Exit");

				userin = in.nextInt();
				System.out.println();

				if(userin == 1) {
					addEmp(myConn);
				}

				else if (userin == 2) {
					edit(myConn);
				}

				else if (userin == 3) {
					empRem(myConn);
				}

				else if (userin == 4) {
					Statement myStmt = myConn.createStatement();
					ResultSet myRs = myStmt.executeQuery("select * from employees");

					while(myRs.next()) {
						System.out.println(myRs.getInt("id") + " " + myRs.getString("fname") + " " + myRs.getString("lname"));
					}
					System.out.println();
				}
			}

			catch (Exception exc) {
				exc.printStackTrace();
			}
		} while(userin != 5);
	}

	public static void edit(Connection myConn) {
		int id = 0;
		String fname = "";
		
		System.out.print("What is the employee's ID? ");
		id = in.nextInt();
		System.out.print("What is the new first name? ");
		fname = in.next();
		EditEmp emp = new EditEmp(myConn, fname, id);
		emp.empEdit();
	}
	
	public static void addEmp(Connection myConn) {
		int id = 0;
		String fname = "";
		String lname = "";

		System.out.print("What is the employee's ID? ");
		id = in.nextInt();

		System.out.print("What is their first name? ");
		fname = in.next();

		System.out.print("What is their last name? ");
		lname = in.next();

		Addemp emp = new Addemp(myConn, id, fname, lname);
		emp.addEmployee();
	}

	public static void empRem(Connection myConn) {
		int id = 0;
		System.out.print("What is the employee's ID? ");
		id = in.nextInt();
		RemEmp emp = new RemEmp(myConn, id);
		emp.delete();
	}
}
