package org.hcmus.lmsclient;

import org.hcmus.lmsclient.addpoint.HappyPathCase;
import org.hcmus.lmsclient.addpoint.InvalidMerchantCase;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AddPointTestSuite extends TestCase {
	
	public AddPointTestSuite(String name){
		super(name);
	}
	
	static public Test suite(){
		TestSuite testSuite = new TestSuite();
		testSuite.addTest(new HappyPathCase("testHappyPath"));
		testSuite.addTest(new InvalidMerchantCase("testInvalidMerchant"));
		return testSuite;
	}
}
