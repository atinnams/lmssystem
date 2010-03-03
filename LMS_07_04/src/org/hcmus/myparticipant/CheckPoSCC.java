package org.hcmus.myparticipant;

import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_PoSCCBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class CheckPoSCC implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializeable) {
		
	}

	@Override
	public void commit(long id, Serializable serializeable) {
		
	}

	@Override
	public int prepare(long id, Serializable serializeable) {
		Context ctx = (Context)serializeable;
		ISOMsg msg = (ISOMsg)ctx.get(Constant.REQUEST);
		if(msg != null) {
			String posccid = MessageHelper.getPoSCC(msg);
			if(!posccid.isEmpty()) {
				int result = JPOS_PoSCCBUS.checkPoSCC(posccid);
				if(result == 0) {
					ctx.put(Constant.RC, "58");
					return ABORTED | READONLY |  NO_JOIN;
				}else {
					return PREPARED;
				}
			}else {
				ctx.put(Constant.RC, "58");
				return ABORTED | READONLY | NO_JOIN;
			}
		}else {
			ctx.put(Constant.RC, 12);
			return ABORTED | READONLY | NO_JOIN;
		}
	}

}
