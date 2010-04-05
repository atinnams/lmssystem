package org.hcmus.lmsclient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hcmus.lmsclient.subtractpoint.ForwardErrorFlowCase;
import org.hcmus.lmsclient.subtractpoint.SubtractPointCase;

public class SubtractPointTestSuite extends TestCase {

	public SubtractPointTestSuite(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite testSuite = new TestSuite();
		
		//add happy path
		testSuite.addTest(new SubtractPointCase("testSubtractHappyPath"));
		
		//add switching error case
		testSuite.addTestSuite(ForwardErrorFlowCase.class);
		
		return testSuite;
	}
}
