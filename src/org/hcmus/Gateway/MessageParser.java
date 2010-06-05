package org.hcmus.Gateway;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

public class MessageParser {

	public MessageParser(){ }

	static ISOMsg getMessage(String filename, ISOPackager packager)
			throws IOException, ISOException {
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		try {
			byte[] b = new byte[fis.available()];
			fis.read(b);
			ISOMsg m = new ISOMsg();
			m.setPackager(packager);
			m.unpack(b);
			return m;
		} finally {
			fis.close();
		}
	}

	static ISOMsg getMessage(byte[] b, ISOPackager packager) {
		try {
			ISOMsg m = new ISOMsg();
			m.setPackager(packager);
			m.unpack(b);
			return m;
		} catch (ISOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
