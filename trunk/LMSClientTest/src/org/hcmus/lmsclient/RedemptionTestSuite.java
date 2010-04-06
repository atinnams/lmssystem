package org.hcmus.lmsclient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hcmus.lmsclient.redemption.NotEnoughPointCase;
import org.hcmus.lmsclient.redemption.RedemptionHappyPath;

public class RedemptionTestSuite extends TestCase {

	public RedemptionTestSuite(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite testSuite = new TestSuite();
		
		//Add Not Enough Point Case
		testSuite.addTest(new NotEnoughPointCase("testNotEnoughPoint"));
		
		//Add Redemption happy path case
		testSuite.addTest(new RedemptionHappyPath("testRedemptionHappyPath"));
		
		return testSuite;
	}

}
