package test.connection;

import test.bus.JPOS_CardBUS_TEST;
import test.bus.JPOS_CustomerBUS_TEST;
import test.bus.JPOS_MerchantBUS_TEST;
import test.bus.JPOS_PoSCCBUS_TEST;
import test.util.MessageHelper_TEST;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainTest extends TestCase {

	public MainTest(String name) {
		super(name);
	}
	
	public static Test suite(){
		TestSuite mainTest = new TestSuite();
		
		//Add all test method of JPOS_CardBUS_Test
		mainTest.addTestSuite(JPOS_CardBUS_TEST.class);
		
		//Add all test method of JPOS_CustomerBUS_Test
		mainTest.addTestSuite(JPOS_CustomerBUS_TEST.class);
		
		//Add all test method of JPOS_MerchantBUS_Test
		mainTest.addTestSuite(JPOS_MerchantBUS_TEST.class);
		
		//Add all test method of JPOS_PoSCCBUS_Test
		mainTest.addTestSuite(JPOS_PoSCCBUS_TEST.class);
		
		//Add all test method of MessageHelper_Test
		mainTest.addTestSuite(MessageHelper_TEST.class);
		return mainTest;
	}

}
