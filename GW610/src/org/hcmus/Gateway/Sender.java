package org.hcmus.Gateway;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;

public class Sender extends QBeanSupport implements Runnable {

	static MUX mux;
	int sessions = 1;
	private static long TIMEOUT;

	public Sender() {

	}

	public void init() {
		log.info("init- sender");
		super.init();
	}

	public void start() {
		log.info("start - sender");
		super.start();
	}

	public void stop() {
		log.info("stop-sender");
		super.stop();
	}

	public void destroy() {
		log.info("destroy-sender");
		log = null;
	}

	protected void initService() throws ISOException {
		// Now do not do anything
	}

	protected void startService() {
		for (int i = 0; i < sessions; i++)
			new Thread(this).start();
	}

	public ISOMsg Request(ISOMsg m, int timeout) {
		try {
			ISOMsg respones = new ISOMsg();
			respones = mux.request(m, timeout);
			return respones;
		} catch (ISOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public ISOMsg Request(ISOMsg m) {
		try {
			if (mux != null) {
				ISOMsg respones = new ISOMsg();
				respones = mux.request(m, TIMEOUT);
				return respones;
			} else {
				log.info("mux == null. Please check config.!");
				return null;
			}
		} catch (ISOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public void run() {
		try {
			mux = (MUX) NameRegistrar.get("mux." + cfg.get("mux"));
			TIMEOUT = cfg.getLong("timeout");
			if (mux != null) {
				log.info("initalize mux");
			}
		} catch (NameRegistrar.NotFoundException e) {
			log.info(e.getMessage());
			log.info("initialize mux fail.");
		}
	}
	
	public static Sender getSender(String name){
		Sender mySender;
		try{
			mySender = (Sender)NameRegistrar.get("sender." + name);
		}catch(NameRegistrar.NotFoundException e){
			mySender = new Sender();
			NameRegistrar.register("senser." + name, mySender);
		}
		return mySender;
	}
}
