package employeemanagerpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Employee Management Publisher service up and running");
		//Register Service
		EmployeeInterface employeeInterface = new EmployeeImpl();
		serviceRegistration = context.registerService(EmployeeInterface.class.getName(), employeeInterface, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Employee Management Publisher shut down");
		serviceRegistration.unregister();
	}

}
