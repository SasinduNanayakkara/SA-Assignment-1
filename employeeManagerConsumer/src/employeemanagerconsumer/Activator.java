package employeemanagerconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import employeemanagerpublisher.EmployeeInterface;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Employee Management Service");
		serviceReference = context.getServiceReference(EmployeeInterface.class.getName());
		@SuppressWarnings("unchecked")
		EmployeeInterface employeeInterface = (EmployeeInterface)context.getService(serviceReference);	
		displayMainMenu(employeeInterface);
	}


	public void stop(BundleContext context) throws Exception {
		System.out.println("Consumer Stopped!");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(EmployeeInterface employeeInterface) {
		
		int option;
		String suboption = "y";
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("---------- Point Of Sale System - Employee Manager----------");
		System.out.println("1  - Create Employee");
		System.out.println("2  - Get all Employees");
		System.out.println("3  - Search Employee using employee Id");
		System.out.println("4  - Delete Employee using employee Id");
		System.out.print("Select an option : ");
		
		option = Integer.parseInt(scanner.nextLine().trim());
		switch(option) {
			case 1:
				employeeInterface.createEmployee();
				
				while(suboption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Employee (y/n)");
					suboption = scanner.nextLine().trim();
		
					if(suboption.equals("y")) {
						employeeInterface.createEmployee();
					}
				}
				displayMainMenu(employeeInterface);
				break;
			case 2:
				employeeInterface.getallemployee();
				displayMainMenu(employeeInterface);
				break;
			case 3:
				employeeInterface.searchEmployee();
				displayMainMenu(employeeInterface);
				break;
			case 4:
				employeeInterface.deleteemployee();
				displayMainMenu(employeeInterface);
				break;
			default:
				System.out.println("Incorrect Input... Try Again");
				displayMainMenu(employeeInterface);
		}
		
		
	}

}
