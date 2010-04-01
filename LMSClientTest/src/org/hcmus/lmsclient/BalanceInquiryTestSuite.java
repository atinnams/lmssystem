package org.hcmus.lmsclient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hcmus.lmsclient.balanceinquiry.BalanceInquiryTest;
import org.hcmus.lmsclient.balanceinquiry.MTITest;

public class BalanceInquiryTestSuite extends TestCase {

	public BalanceInquiryTestSuite(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite testSuite = new TestSuite();
		
		//Add balance inquiry test case
		testSuite.addTest(new BalanceInquiryTest("testBalanceInquiry"));
		
		//Add MTI test
		testSuite.addTest(new MTITest("testMTI"));
		
		return testSuite;
	}
}
