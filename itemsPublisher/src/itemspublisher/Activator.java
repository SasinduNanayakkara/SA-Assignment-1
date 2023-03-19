package itemspublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Item Publisher up and running");
		
		ItemInterface itemInterface = new ItemServiceImplementation();
		serviceRegistration = context.registerService(ItemInterface.class.getName(), itemInterface, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Item Publisher shut down");
		serviceRegistration.unregister();
	}

}
