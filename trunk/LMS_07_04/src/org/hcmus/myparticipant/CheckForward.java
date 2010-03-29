package org.hcmus.myparticipant;

import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class CheckForward implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) {
	}

	@Override
	public void commit(long arg0, Serializable arg1) {
	}

	@Override
	public int prepare(long id, Serializable serializable) {
		
		//get context from space
		Context ctx = (Context)serializable;
		
		//get message from context
		ISOMsg msg = (ISOMsg)ctx.get(Constant.REQUEST);
		
		ctx.put(Constant.RC, "15");
		return ABORTED |READONLY | NO_JOIN; 
		
	}
}
