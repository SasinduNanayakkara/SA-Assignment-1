package billpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Bill Publisher service started");
		BillInterface billerInterface = new BillServiceImpl();
		serviceRegistration = context.registerService(BillInterface.class.getName(), billerInterface, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Bill Publisher service stopped");
		serviceRegistration.unregister();
	}

}
