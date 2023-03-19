package wholesalepublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import myposdb.Database;
import myposdb.DatabaseImpl;

public class WholesaleImpl implements WholesaleInterface {
	
	private Connection connection = null;
	private Statement statement = null;
	private Database database;
	private ResultSet resultset;

	public WholesaleImpl() {
		super();
		database = new DatabaseImpl();
		connection = database.getDatabaseConnection();
	}
	
	Scanner sc = new Scanner(System.in);

	@Override
	public void addNewWholesaler() {
	     Wholesale wholesale = new Wholesale();
			
			System.out.println("Enter Wholesaler company Name: ");
			wholesale.setName(sc.nextLine().trim());
			
			System.out.println("Enter Contact Number: ");
			wholesale.setMobile(Integer.parseInt(sc.nextLine().trim()));
			
			
			String sqlQueryWholesaler = "INSERT INTO wholesaler(name, mobile) "
					+ "VALUES('"+ wholesale.getName() +"', '"+ wholesale.getMobile() +"')";
			
			try {
				statement = connection.createStatement();
				statement.executeUpdate(sqlQueryWholesaler);
				System.out.println("Wholesaler successfully inserted ...");
			} catch (SQLException exc) {
				System.out.println("Database Error");
				System.out.println(exc.getMessage());
			}
	}

	@Override
	public void searchWholesalerUsingId() {
		
		int id;
		
		System.out.println("Enter Wholesaler Id : ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		String sqlQueryWholesaler = "SELECT * FROM wholesaler WHERE id = '"+ id +"'";
		
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQueryWholesaler);
			while (resultset.next()) {  
				  System.out.println("\t\tWholesaler id\t\tWholesaler Name Price \t\tContact Number");
		    	  System.out.printf("%20d %20s %20d\n",resultset.getInt("id"),resultset.getString("name"),resultset.getInt("mobile"));		    	
		      }		    	

		} catch (SQLException exc) {
			System.out.println("Error with get Wholesaler by Id");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void getWholesalers() {
		
		String sqlQueryWholesaler = "SELECT * FROM wholesaler";

		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(sqlQueryWholesaler);
			  System.out.println("\t\tWholesaler id\tWholesaler Name \tContact Number");		    	  

		      while (resultset.next()) {  
		    	  System.out.printf("%20d %20s %20d\n",resultset.getInt("id"),resultset.getString("name"),resultset.getInt("mobile"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Database Errors");
			System.out.println(exc.getMessage());
		}
	}

	@Override
	public void deleteaWholesaler() {
		
		int id;
		
		System.out.println("Enter Wholesaler Id: ");
		id = (Integer.parseInt(sc.nextLine().trim()));
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM wholesaler WHERE id = '"+ id +"'"); 
		    	  System.out.printf("Successfully Deleted wholesaler");

		} catch (SQLException exc) {
			System.out.println("Database Error");
			System.out.println(exc.getMessage());
		}
	}

}
