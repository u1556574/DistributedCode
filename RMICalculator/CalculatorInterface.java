import java.rmi.*;


public interface CalculatorInterface extends Remote{

	public double add(double a, double b)  throws RemoteException;

	public double sub(double a, double b)  throws RemoteException;

	public double mult(double a, double b)  throws RemoteException;

	public double div(double a, double b)  throws RemoteException;

}
