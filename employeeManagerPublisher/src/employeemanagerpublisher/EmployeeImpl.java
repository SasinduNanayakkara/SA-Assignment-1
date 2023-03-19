package employeemanagerpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import myposdb.Database;
import myposdb.DatabaseImpl;

public class EmployeeImpl implements EmployeeInterface {
	
	private Connection connection = null;
	private Statement statement = null;
	private Database database;
	private ResultSet resultset;

	public EmployeeImpl() {
		super();
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
	}
	
	Scanner scanner = new Scanner(System.in);

	@Override
	public void createEmployee() {
	     Employee employee = new Employee();
			
			System.out.println("Enter Employee Name: ");
			employee.setName(scanner.nextLine().trim());
			
			System.out.println("Enter Employee Phone Number: ");
			employee.setMobile(Integer.parseInt(scanner.nextLine().trim()));
			
			System.out.println("Enter Employee Age: ");
			employee.setAge(Integer.parseInt(scanner.nextLine().trim()));
			
			System.out.println("Enter Employee NIC : ");
			employee.setNic(scanner.nextLine().trim());
			
			
			String sqlQueryEmployee = "INSERT INTO employee(name, mobile, age, nic) "
					+ "VALUES('"+ employee.getName() +"', '"+ employee.getMobile() +"', '"+ employee.getAge() + "','"+ employee.getNic() +"')";
			
			try {
				statement = connection.createStatement();
				statement.executeUpdate(sqlQueryEmployee);
				System.out.println("Employee created successfully...");
			} catch (SQLException exc) {
				System.out.println("Error with insert Employee");
				System.out.println(exc.getMessage());
			}
	}

	@Override
	public void searchEmployee() {
		
		int id;
		
		System.out.println("Enter Id : ");
		id = (Integer.parseInt(scanner.nextLine().trim()));
		
		String sqlQueryEmployee = "SELECT * FROM employee WHERE id = '"+ id +"'";
		
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQueryEmployee);
			System.out.println("\t\tEmployee ID\t\tEmployee Name\t\tPhone Number\t\tAge");
			while (resultset.next()) {  
		    	  System.out.printf("%20d %20s %20d %20d\n",resultset.getInt("id"),resultset.getString("name"),resultset.getInt("mobile"),resultset.getInt("age"));		    	
		      }		    	

		} catch (SQLException exc) {
			System.out.println("Error with get Employee by Id");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void getallemployee() {
		
		String sqlQueryEmployee = "SELECT * FROM employee";

		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQueryEmployee);
			System.out.println("\t\tEmployee ID\t\tEmployee Name\t\tPhone Number\t\tAge");
		      while (resultset.next()) {  
		    	  System.out.printf("%20d %20s %20d %20d %20s\n",resultset.getInt("id"),resultset.getString("name"),resultset.getInt("mobile"),resultset.getInt("age"),resultset.getString("nic"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Error with get all Employees");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void deleteemployee() {
		
		int id;
		
		System.out.println("Enter Id: ");
		id = (Integer.parseInt(scanner.nextLine().trim()));
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM employee WHERE id = '"+ id +"'"); 
		    	  System.out.printf("Employee Deleted Successfully!");

		} catch (SQLException exc) {
			System.out.println("Error with delete Employee by Id");
			System.out.println(exc.getMessage());
		}

	}

}
