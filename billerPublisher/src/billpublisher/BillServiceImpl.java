package billpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import myposdb.Database;
import myposdb.DatabaseImpl;

public class BillServiceImpl implements BillInterface {

	private Connection connection = null;
	private Statement statement = null;
	private Database database;
	private ResultSet resultSet;

	public BillServiceImpl() {
		super();
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
	}

	Scanner sc = new Scanner(System.in);

	@Override
	public void createBill() {

		Bill bill = new Bill();

		String empName = "";
		while (empName == "") {
			System.out.println("Enter Employee Id : ");
			empName = getEmployeeNameUsingId(Integer.parseInt(sc.nextLine().trim()));
		}
		bill.setEmployee(empName);

		double tot = 0.0;
		String answer = "y";
		while (answer.equals("y")) {
			System.out.println("Enter Item Id : ");
			double price = 0.0;
			int quantity =0;
			while (price == 0.0) {
				price = getPriceUsingItemId(Integer.parseInt(sc.nextLine().trim()));
			}
			System.out.println("Enter Quantity :");
			quantity = Integer.parseInt(sc.nextLine().trim());
			tot += price*quantity;
			System.out.println("Do you want to add more item? (Y/N): ");
			answer = sc.nextLine().trim();
		}
		String discountAnswer ="";
		System.out.println("Do you want to add a discount? (y/n)");
		discountAnswer = sc.nextLine().trim();
		if(discountAnswer.equalsIgnoreCase("y")) {
			int discount;
			System.out.println("Enter Discount Amount");
			discount = Integer.parseInt(sc.nextLine().trim());
			tot = tot*(100-discount)/100;
		}
		bill.setTotal(tot);

		String sqlQueryBiller = "INSERT INTO biller(total, employee) " + "VALUES('" + bill.getTotal()
				+ "', '" + bill.getEmployee() + "')";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(sqlQueryBiller);
			System.out.println("Bill successfully inserted ...");
		} catch (SQLException exc) {
			System.out.println("Database Error");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void searchBill() {
		
		int id;
		
		System.out.println("Input Bill Id : ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		String sqlQueryBiller = "SELECT * FROM biller WHERE id = '"+ id +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryBiller);
			while (resultSet.next()) {
				  System.out.println("\t\tbill id\t\tTotal Price \t\temployee");
		    	  System.out.printf("%20d %20.2f %20s\n",resultSet.getInt("id"),resultSet.getDouble("total"),resultSet.getString("employee"));		    	
		      }		    	

		} catch (SQLException exc) {
			System.out.println("Database Error");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void getAllBills() {
		
		String sqlQueryBiller = "SELECT * FROM biller";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQueryBiller);
			System.out.println("\t\tbill id\t\tTotal Price \t\temployee");
		      while (resultSet.next()) { 
		    	  
		    	  System.out.printf("%20d %20.2f %20s\n",resultSet.getInt("id"),resultSet.getDouble("total"),resultSet.getString("employee"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Database Error");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public void deleteBill() {
		
		int id;
		
		System.out.println("Enter Id: ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM biller WHERE id = '"+ id +"'"); 
		    	  System.out.printf("Successfully Deleted!");

		} catch (SQLException exc) {
			System.out.println("Database Error");
			System.out.println(exc.getMessage());
		}

	}

	@Override
	public double getPriceUsingItemId(int id) {

		double price = 0.0;
		String sqlQuery = "SELECT price FROM items WHERE id = '" + id + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()) {
				price = resultSet.getDouble("price");
			}

		} catch (SQLException exc) {
			System.out.println("Database Error");
			System.out.println(exc.getMessage());
			price = 0.0;
		}

		return price;
	}

	@Override
	public String getEmployeeNameUsingId(int id) {

		String name = "";
		String sqlQuery = "SELECT name FROM employee WHERE id = '" + id + "'";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()) {
				name = resultSet.getString("name");
			}

		} catch (SQLException exc) {
			System.out.println("Database Error");
			System.out.println(exc.getMessage());
			name = "";
		}

		return name;
	}

}
