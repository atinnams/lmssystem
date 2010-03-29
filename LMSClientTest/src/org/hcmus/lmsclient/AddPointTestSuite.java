package org.hcmus.lmsclient;

import org.hcmus.lmsclient.addpoint.ExpireCardCase;
import org.hcmus.lmsclient.addpoint.HappyPathCase;
import org.hcmus.lmsclient.addpoint.InvalidCardCase;
import org.hcmus.lmsclient.addpoint.InvalidMerchantCase;
import org.hcmus.lmsclient.addpoint.InvalidPoSCCCase;
import org.hcmus.lmsclient.addpoint.LackFieldCase;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author HUNGPT
 * Purpose of this test case is used to check expire card.
 */
public class AddPointTestSuite extends TestCase {
	
	public AddPointTestSuite(String name){
		super(name);
	}
	
	static public Test suite(){
		TestSuite testSuite = new TestSuite();
		
		//add happy path case
		testSuite.addTest(new HappyPathCase("testHappyPath"));
		
		//add Invalid Merchant Case
		testSuite.addTest(new InvalidMerchantCase("testInvalidMerchant"));
		
		//add Invalid Card Case
		testSuite.addTest(new InvalidCardCase("testInvalidCard"));
		
		//add Expire Card Case
		testSuite.addTest(new ExpireCardCase("testExpireCard"));
		
		//Add Invalid PoSCC
		testSuite.addTest(new InvalidPoSCCCase("testInvalidPoSCC"));
		
		//add Lack Field Case
		testSuite.addTest(new LackFieldCase("testLackField"));
		
		return testSuite;
	}
}
