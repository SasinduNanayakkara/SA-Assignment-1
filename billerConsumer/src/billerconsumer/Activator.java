package billerconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import billpublisher.BillInterface;

public class Activator implements BundleActivator {

	private ServiceReference serviceReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Bill consumer Service");
		serviceReference = context.getServiceReference(BillInterface.class.getName());
		@SuppressWarnings("unchecked")
		BillInterface billInterface = (BillInterface)context.getService(serviceReference);	
		displayMainMenu(billInterface);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopped bill consumer service");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(BillInterface billInterface) {
		
		int input;
		String loopThrough = "y";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("----------Point of Sale System - Billing Management ----------");
		System.out.println("1  - create Bill");
		System.out.println("2  - Display all Bills");
		System.out.println("3  - Search for Bill by using bill Id");
		System.out.println("4  - Remove  a Bill from the system using bill id");
		System.out.print("Choose your option : ");
		
		input = Integer.parseInt(sc.nextLine().trim());
		
		switch(input) {
			case 1:
				billInterface.createBill();
				
				while(loopThrough.equals("y")) {
					System.out.println("\n\nDo you want to Create Another Bill (y/n)");
					loopThrough = sc.nextLine().trim();
		
					if(loopThrough.equals("y")) {
						billInterface.createBill();
					}
				}
				displayMainMenu(billInterface);
				break;
			case 2:
				billInterface.getAllBills();
				displayMainMenu(billInterface);
				break;
			case 3:
				billInterface.searchBill();
				displayMainMenu(billInterface);
				break;
			case 4:
				billInterface.deleteBill();
				displayMainMenu(billInterface);
				break;
			default:
				System.out.println("Invalid Input. Try Again...");
				displayMainMenu(billInterface);
		}
		
		
	}

}
