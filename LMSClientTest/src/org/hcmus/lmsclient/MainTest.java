package org.hcmus.lmsclient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author HUNGPT
 * Main Test case invokes all test suite.
 */
public class MainTest extends TestCase {

	public MainTest(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite mainTest = new TestSuite();
		
		//add add point test suite
		mainTest.addTest(AddPointTestSuite.suite());
		
		//add subtract point test suite
		mainTest.addTest(SubtractPointTestSuite.suite());
		
		//add balance inquiry suite
		mainTest.addTest(BalanceInquiryTestSuite.suite());
		
		//add activation suite
		mainTest.addTest(ActivationTestSuite.suite());
		
		// TODO Add other test suite here
		
		return mainTest;
	}
}
