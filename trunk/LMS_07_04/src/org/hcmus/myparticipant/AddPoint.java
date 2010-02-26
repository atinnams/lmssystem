package org.hcmus.myparticipant;

import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CustomerBUS;
import org.hcmus.bus.JPOS_CustomerDTO;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class AddPoint implements TransactionParticipant {

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
			int point = MessageHelper.getPoint(msg);
			String cardNumber = MessageHelper.getCardId(msg);
			String mid = MessageHelper.getMID(msg);
			String tid = MessageHelper.getTID(msg);
			String poscc = MessageHelper.getPoSCC(msg);
			JPOS_CustomerDTO customer = new JPOS_CustomerDTO();
			customer.setJPOS_Barcode(cardNumber);
			int result = JPOS_CustomerBUS.addPoint(customer, 1, point, mid, tid, poscc);
			
			// TODO Change it after prepare procedure in DB
			/*if(result == 0) {
				ctx.put(Constant.RC, "14");
				return ABORTED | NO_JOIN;
			}else
			{
				result = JPOS_CardBUS.checkExpire(cardNumber);
				if(result == 0) {
					ctx.put(Constant.RC, "54");
					return ABORTED | NO_JOIN;
				}
			}*/
			return PREPARED;
			
		}else {
			ctx.put(Constant.RC, "12");
			return ABORTED | NO_JOIN;
		}
		
	}
	
}
