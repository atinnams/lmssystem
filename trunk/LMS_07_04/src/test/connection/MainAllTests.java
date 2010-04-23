package test.connection;

import test.dao.JPOS_CardDAO_TEST;
import test.dao.JPOS_CustomerDAO_TEST;
import test.dao.JPOS_MerchantDAO_TEST;
import test.dao.JPOS_PoSCCBUS_TEST;
import test.util.MessageHelper_TEST;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainAllTests extends TestCase {

	public MainAllTests(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite mainTest = new TestSuite();
		
		//Add all test method of JPOS_CardBUS_Test
		mainTest.addTestSuite(JPOS_CardDAO_TEST.class);
		
		//Add all test method of JPOS_CustomerBUS_Test
		mainTest.addTestSuite(JPOS_CustomerDAO_TEST.class);
		
		//Add all test method of JPOS_MerchantBUS_Test
		mainTest.addTestSuite(JPOS_MerchantDAO_TEST.class);
		
		//Add all test method of JPOS_PoSCCBUS_Test
		mainTest.addTestSuite(JPOS_PoSCCBUS_TEST.class);
		
		//Add all test method of MessageHelper_Test
		mainTest.addTestSuite(MessageHelper_TEST.class);
		return mainTest;
	}

}
