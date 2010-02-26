package org.hcmus.myparticipant;

import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CardBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class CheckCard implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializeable) {
		// DONT DO ANY THING
		
	}

	@Override
	public void commit(long id, Serializable serialieable) {
		// DONT DO ANY THING
		
	}

	@Override
	public int prepare(long id, Serializable serializeable) {
		// TODO Auto-generated method stub
		Context ctx = (Context)serializeable;
		ISOMsg msg = (ISOMsg)ctx.get(Constant.REQUEST);
		if(msg != null) {
			String cardNumber = MessageHelper.getCardId(msg);
			int result = JPOS_CardBUS.checkCard(cardNumber);
			if(result == 0) {
				ctx.put(Constant.RC, "14");
				return ABORTED | NO_JOIN;
			}else
			{
				result = JPOS_CardBUS.checkExpire(cardNumber);
				if(result == 0) {
					ctx.put(Constant.RC, "54");
					return ABORTED | NO_JOIN;
				}
			}
		}else {
			ctx.put(Constant.RC, "12");
			return ABORTED | NO_JOIN;
		}
		return PREPARED | READONLY | NO_JOIN;
	}

}
