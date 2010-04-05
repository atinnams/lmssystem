package org.hcmus.lmsclient;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hcmus.lmsclient.activation.ActivatedCard;
import org.hcmus.lmsclient.activation.HappyActivation;
import org.hcmus.lmsclient.activation.NoActivatedCard;

/**
 * Bundle of activation test case. 
 * @author HUNGPT
 *
 */
public class ActivationTestSuite extends TestCase {

	public ActivationTestSuite(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite testSuite = new TestSuite();
		
		//Add activated case
		testSuite.addTest(new ActivatedCard("testActivatedCard"));
		
		//Add happy activation
		testSuite.addTest(new HappyActivation("testHappyActivation"));
		
		//Add no activated case
		testSuite.addTest(new NoActivatedCard("testNoActivatedCase"));
		
		return testSuite;
	}

}
