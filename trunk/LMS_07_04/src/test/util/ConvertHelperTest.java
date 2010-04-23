package test.util;
import junit.framework.TestCase;

import org.hcmus.Util.ConvertHelper;


public class ConvertHelperTest extends TestCase {

	public ConvertHelperTest(String name) {
		super(name);
	}
	
	public void testAsciiToHex(){
		String asciiText = "10 BAN NHAN AO THUN ";
		String hexText = ConvertHelper.asciiToHex(asciiText);
		System.out.println(hexText);
		assertEquals("31302042414e204e48414e20414f205448554e20", hexText);
	}
	
	public void testDecToHex(){
		String hexValue = ConvertHelper.decToHex(4);
		System.out.println(hexValue);
		assertEquals("04",hexValue);
		
		hexValue = ConvertHelper.decToHex(16);
		System.out.println(hexValue);
		assertEquals("10",hexValue);
		
		hexValue = ConvertHelper.decToHex(20);
		System.out.println(hexValue);
		assertEquals("14",hexValue);
		
		hexValue = ConvertHelper.decToHex(1234);
		System.out.println(hexValue);
		assertEquals("4d2",hexValue);
	}

}
