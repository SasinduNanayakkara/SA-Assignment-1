package wholesalepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Wholesaler Publisher service started");
		WholesaleInterface wholesalerInterface = new WholesaleImpl();
		serviceRegistration = context.registerService(WholesaleInterface.class.getName(), wholesalerInterface, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Wholesaler Publisher service stopped");
		serviceRegistration.unregister();
	}

}
