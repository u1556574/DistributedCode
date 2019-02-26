import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


class CalculatorServer implements CalculatorInterface {

	public CalculatorServer() throws RemoteException{
		super();
	}

	public double add(double a, double b)  throws RemoteException{
		return a + b;
	}

	public double sub(double a, double b)  throws RemoteException{
		return a - b;
	}

	public double mult(double a, double b)  throws RemoteException{
		return a * b;
	}

	public double div(double a, double b)  throws RemoteException{
		if (b == 0)
			return 0;
		else
			return a / b;
	}


	public static void main(String args[]) throws Exception{
		try {
			CalculatorServer calc = new CalculatorServer();
			CalculatorInterface stub = (CalculatorInterface) UnicastRemoteObject.exportObject(calc, 0);

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("Calculator", stub);

			System.out.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
