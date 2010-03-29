package org.hcmus.lmsclient;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.jpos.iso.*;
import org.jpos.util.*;
import org.jpos.iso.channel.*;
import org.jpos.iso.packager.*;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try 
		{
			 PrintStream myLog = new PrintStream(new FileOutputStream("client.log"));
			 ISOPackager p = new GenericPackager("cfg/iso87binary.xml");
			 ISOMsg m = new ISOMsg ();
			 m.setMTI ("0200");
			 m.set ("3", "407000");
			 m.set("4","000001500000");
			 m.set("11","123456");
			 m.set("12","114109");
			 m.set("13","0122");
			 m.set("25","00");
			 m.set("35","1234567812345678=10041011765710800000");
			 m.set ("41", "00000001");
			 m.set("42","000000000000001");
			 //m.set("63","000000000000000000000000000150000000000000000000000000000150");
			 m.setPackager(p);      
			 
			 Logger logger = new Logger();
			 logger.addListener (new SimpleLogListener (myLog));
			 ISOChannel channel = new ASCIIChannel("localhost",9800,p);
			 ((LogSource)channel).setLogger (logger, "test-channel");
			 
			 for(int i=5;i>=0;i--) {
				 channel.connect ();
				 channel.send (m);
				 ISOMsg r = channel.receive ();
				 Thread.sleep(2000);
				 channel.disconnect();
			 }
			 System.out.println("Finish");
		}
		catch(Exception ex) 
		{
			System.out.println("Cant connect.! Please try Again.");
		}
	}
}


