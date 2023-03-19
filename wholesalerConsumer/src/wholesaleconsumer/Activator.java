package wholesaleconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import wholesalepublisher.WholesaleInterface;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Wholesale consumer Service");
		serviceReference = context.getServiceReference(WholesaleInterface.class.getName());
		@SuppressWarnings("unchecked")
		WholesaleInterface wholesaleInterface = (WholesaleInterface) context.getService(serviceReference);	
		displayMainMenu(wholesaleInterface);
	}


	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop wholesale consumer");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(WholesaleInterface wholesaleInterface) {
		
		int select;
		String yesNo = "y";
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("----------Point Of Sale System - Wholesaler Management----------");
		System.out.println("1  - Add a new Wholesaler to system");
		System.out.println("2  - Get all Wholesalers in the system");
		System.out.println("3  - Search Wholesalers using Id");
		System.out.println("4  - Delete a Wholesaler using Id");
		System.out.print("Select an option : ");
		
		select = Integer.parseInt(sc.nextLine().trim());
		switch(select) {
			case 1:
				wholesaleInterface.addNewWholesaler();
				
				while(yesNo.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Wholesaler (y/n)");
					yesNo = sc.nextLine().trim();
		
					if(yesNo.equals("y")) {
						wholesaleInterface.addNewWholesaler();
					}
				}
				displayMainMenu(wholesaleInterface);
				break;
			case 2:
				wholesaleInterface.getWholesalers();
				displayMainMenu(wholesaleInterface);
				break;
			case 3:
				wholesaleInterface.searchWholesalerUsingId();
				displayMainMenu(wholesaleInterface);
				break;
			case 4:
				wholesaleInterface.deleteaWholesaler();
				displayMainMenu(wholesaleInterface);
				break;
			default:
				System.out.println("Incorrect Input... Try Again");
				displayMainMenu(wholesaleInterface);
		}
		
		
	}

}
