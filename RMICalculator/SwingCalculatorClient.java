import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class SwingCalculatorClient extends JFrame implements ActionListener{

	JTextField X, Y,Z;
	JButton Add,Sub,Mul,Div;
	JPanel Row1, Row2, Row3;

	CalculatorInterface calc;


	public SwingCalculatorClient (String hostname) {

		try {
			Registry registry = LocateRegistry.getRegistry(hostname);
			calc = (CalculatorInterface) registry.lookup("Calculator");

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}

		// Now build the GUI
		java.awt.Container cp = getContentPane();
		cp.setLayout (new BorderLayout (3,4));

		Add = new JButton("+");
		Sub = new JButton("-");
		Mul = new JButton("x");
		Div = new JButton("/");

		Add.addActionListener(this);
		Sub.addActionListener(this);
		Mul.addActionListener(this);
		Div.addActionListener(this);

		X = new JTextField("Input 1",12);
		Y = new JTextField("Input 2",12);
		Z = new JTextField("Result",15);

		Row1 = new JPanel();
		Row2 = new JPanel();
		Row3 = new JPanel();

		Row1.setLayout(new FlowLayout(FlowLayout.CENTER));
		Row2.setLayout(new FlowLayout(FlowLayout.CENTER));
		Row3.setLayout(new FlowLayout(FlowLayout.CENTER));

		cp.add("North",Row1);
		cp.add("Center",Row2);
		cp.add("South",Row3);

		Row1.add(X);
		Row1.add(Y);
		Row2.add(Add);
		Row2.add(Sub);
		Row2.add(Mul);
		Row2.add(Div);
		Row3.add(Z);


		// Stop java if window closed by window close icon
		addWindowListener (new java.awt.event.WindowAdapter () {
			public void windowClosing (java.awt.event.WindowEvent evt) {
				System.exit(0);;
			}
		} );
	}

	// Handle interface events here
	public void actionPerformed(ActionEvent evt) {
		double x,y,z;
		String sx,sy,sz;

		java.lang.Object source = evt.getSource();

		z = 0;
		sx = X.getText();
		sy = Y.getText();
		sz = "";

		try {
			x = (new Double(sx)).doubleValue();
			y = (new Double(sy)).doubleValue();

			if ( source == Add) {
				z = calc.add(x,y);
			} else if ( source == Sub) {
				z = calc.sub(x,y);
			} else if ( source == Mul) {
				z = calc.mult(x,y);
			} else if ( source == Div) {
				z = calc.div(x,y);
			}
			sz = String.valueOf(z);

		} catch (NumberFormatException e) {
			sz = "Not A Number Error";

		} catch (Exception e){
			sz = "Other Error";
			System.out.println("Error:");
			e.printStackTrace();

		} finally {
			Z.setText(sz);
		}
	}

	public static void main(String args[]) {
		String remoteMachine;

		if (args.length == 0)
			remoteMachine = "localhost";
		else
			remoteMachine = args[0];

		SwingCalculatorClient MyCalc = new SwingCalculatorClient(remoteMachine);

		MyCalc.setSize(300,200);
		MyCalc.setTitle("Java RMI Calculator");
		MyCalc.setVisible(true);
	}
}
