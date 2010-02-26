package org.hcmus.myparticipant;

import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_MerchantBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class CheckMerchant implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializeable) {
		// DONT DO ANYTHING
		
	}

	@Override
	public void commit(long id, Serializable serializeable) {
		// TODO DONT DO ANYTHING
		
	}

	@Override
	public int prepare(long id, Serializable serializeable) {
		// TODO Auto-generated method stub
		Context ctx = (Context)serializeable;
		ISOMsg msg = (ISOMsg)ctx.get(Constant.REQUEST);
		if(msg != null) {
			String mid = MessageHelper.getMID(msg);
			String tid = MessageHelper.getTID(msg);
			if(!mid.isEmpty() && !tid.isEmpty()) {
				int result = JPOS_MerchantBUS.checkMerchant(mid, tid);
				if(result == 0) {
					ctx.put(Constant.RC, "03");
					return ABORTED | READONLY | NO_JOIN;
				}else {
					return PREPARED;
				}
			}else {
				ctx.put(Constant.RC, "03");
				return ABORTED | READONLY | NO_JOIN;
			}
		}else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
	}
	
}
