package edu.ait.ds.corba.server;

import org.omg.CORBA.ORB;

import edu.ait.ds.corba.CalculatorPOA;

public class CalculatorImpl extends CalculatorPOA{

	private ORB orb;
	public void setORB(ORB orb_val) {
		orb = orb_val;
	}
	
	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public void shutdown() {
		orb.shutdown(false);
	}

	@Override
	public int subtract(int a, int b) {
		return a-b;
	}

	@Override
	public int multiply(int a, int b) {
		return a*b;
	}

	@Override
	public int divide(int a, int b) {
		return a/b;
	}

}
