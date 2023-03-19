package itemsconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import itemspublisher.ItemInterface;

public class Activator implements BundleActivator {

	private ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Item Consumer");
		serviceReference = context.getServiceReference(ItemInterface.class.getName());
		@SuppressWarnings("unchecked")
		ItemInterface itemInterface = (ItemInterface)context.getService(serviceReference);
		displayItemMenu(itemInterface);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Consumer stopped");
		context.ungetService(serviceReference);
	}
	
	public void displayItemMenu(ItemInterface itemInterface) {
		
		int option;
		String loopThrough = "y";
		
		Scanner itemFunctions = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("---------- Point of Sale System - Item Manager ----------");
		System.out.println("1  - Create Items");
		System.out.println("2  - Get all Items");
		System.out.println("3  - Search Item using Item ID");
		System.out.println("4  - Search Item using Item name");
		System.out.println("5  - Delete Item from the System");
		System.out.print("Select an option : ");
		
		option = Integer.parseInt(itemFunctions.nextLine().trim());
		
		switch(option) {
			case 1:
				itemInterface.createItem();
				
				while(loopThrough.equals("y")) {
					System.out.println("\n\nDo you want to Add Another item (y/n) ? ");
					loopThrough = itemFunctions.nextLine().trim();
		
					if(loopThrough.equals("y")) {
						itemInterface.createItem();
					}
				}
				displayItemMenu(itemInterface);
				break;
			case 2:
				itemInterface.displayAllItems();
				displayItemMenu(itemInterface);
				break;
			case 3:
				itemInterface.getItemDetailsById();
				displayItemMenu(itemInterface);
				break;
			case 4:
				itemInterface.getItemDetailsByName();
				displayItemMenu(itemInterface);
				break;
			case 5:
				itemInterface.deleteItem();
				displayItemMenu(itemInterface);
				break;
			default:
				System.out.println("Incorrect Input. Please Enter Valid Number...");
				displayItemMenu(itemInterface);
		}
		
		
	}

}
