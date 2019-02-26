import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {

	public static void main(String args[]) {
		String hostname;

		if (args.length == 0)
			hostname = "localhost";
		else
			hostname = args[0];

		try {
            Registry registry = LocateRegistry.getRegistry(hostname);
            CalculatorInterface calc = (CalculatorInterface) registry.lookup("Calculator");
            
            double result1 = calc.add(10.5, 4.5);
			double result2 = calc.sub(15.1, 4.6);
			System.out.println("\n result 1 = " + result1 + 
					" and result 2 = " + result2);
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}
}
