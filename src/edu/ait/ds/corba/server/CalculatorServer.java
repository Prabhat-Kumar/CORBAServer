package edu.ait.ds.corba.server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import edu.ait.ds.corba.Calculator;
import edu.ait.ds.corba.CalculatorHelper;

/**
 * @author prabh
 *
 *Start ordb server
 *orbd -ORBInitialPort 1050 -ORBInitialHost localhost
 *
 *start ClaculatorServer
 *java edu.ait.ds.corba.server.CalculatorServer -ORBInitialPort 1050 -ORBInitialHost localhost
 */
public class CalculatorServer {

	public static void main(String args[]) {
	    try{
	      // create and initialize the ORB
	      ORB orb = ORB.init(args, null);

	      // get reference to rootpoa & activate the POAManager
	      POA rootpoa = 
	        (POA)orb.resolve_initial_references("RootPOA");
	      rootpoa.the_POAManager().activate();

	      // create servant and register it with the ORB
	      CalculatorImpl helloImpl = new CalculatorImpl();
	      helloImpl.setORB(orb); 

	      // get object reference from the servant
	      org.omg.CORBA.Object ref = 
	        rootpoa.servant_to_reference(helloImpl);
	      // and cast the reference to a CORBA reference
	      Calculator href = CalculatorHelper.narrow(ref);
		  
	      // get the root naming context
	      // NameService invokes the transient name service
	      org.omg.CORBA.Object objRef =
	          orb.resolve_initial_references("NameService");
	      // Use NamingContextExt, which is part of the
	      // Interoperable Naming Service (INS) specification.
	      NamingContextExt ncRef = 
	        NamingContextExtHelper.narrow(objRef);

	      // bind the Object Reference in Naming
	      NameComponent path[] = ncRef.to_name("Calc");
	      ncRef.rebind(path, href);

	      System.out.println
	        ("HelloServer ready and waiting ...");

	      // wait for invocations from clients
	      orb.run();
	    } 
		
	      catch (Exception e) {
	        System.err.println("ERROR: " + e);
	        e.printStackTrace(System.out);
	      }
		  
	      System.out.println("HelloServer Exiting ...");
		
	  } //end main
}
