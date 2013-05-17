package businessLogic;

import java.rmi.Naming;

import configuration.Config;

public class RemoteServer {

	public static void main(String[] args) {
		Config c=Config.getInstance();

		System.setProperty("java.security.policy", c.getJavaPolicyPath());

		//System.setSecurityManager(new RMISecurityManager());

		try {
			java.rmi.registry.LocateRegistry.createRegistry(Integer.parseInt(c.getPortRMI()));
			// Create RMIREGISTRY
		} catch (Exception e) {
			System.out.println(e.toString() + "RMIregistry already running.");
		}

		try {
			System.setProperty("java.security.policy", c.getJavaPolicyPath());

			ApplicationFacadeInterface server = new FacadeImplementation();

			String service= "//"+c.getServerRMI() +":"+ c.getPortRMI()+"/"+c.getServiceRMI();

			//Register the remote server
			Naming.rebind(service, server);
			System.out.println("Running service at:\n\t" + service);
			//This operation removes the actual database and initializes it with some predefined values

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
}
