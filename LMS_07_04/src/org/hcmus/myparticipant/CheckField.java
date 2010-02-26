package org.hcmus.myparticipant;

import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

/**
 * 
 * @author HungPT
 * 
 */
public class CheckField implements TransactionParticipant,Configurable {
	
	Configuration cfg = null;
	@Override
	public void abort(long id, Serializable context) {
		// TODO Bussiness rules for abort phas will be implemented here

	}

	@Override
	public void commit(long id, Serializable context) {
		// TODO Bussiness rules for commit phase will be implemented here

	}

	@Override
	public int prepare(long id, Serializable context) {
		// TODO Bussiness rules for prepare phase will be implemented here
		Context ctx = (Context) context;
		ISOMsg m = (ISOMsg) ctx.get(Constant.REQUEST);
		String fields = cfg.get("fields");
		String[] campos = fields.split(",");
		int c = 0;
		boolean validate = true;
		while ((c < campos.length) && (validate)) {
			validate = (m.hasField(Integer.parseInt(campos[c])));
			//System.out.println(campos[c]);
			c++;
		}
		if (validate) {
			return PREPARED | READONLY | NO_JOIN;
		} else {
			ctx.put(Constant.RC, "14");
			return ABORTED | NO_JOIN;
		}
	}

	@Override
	public void setConfiguration(Configuration cfg)
			throws ConfigurationException {
		// TODO Auto-generated method stub
		this.cfg = cfg;
	}
}
