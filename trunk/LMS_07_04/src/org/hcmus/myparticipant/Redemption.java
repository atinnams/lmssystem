package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CustomerBUS;
import org.hcmus.bus.JPOS_GiftBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class Redemption implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) {
	}

	@Override
	public void commit(long arg0, Serializable arg1) {
	}

	@Override
	public int prepare(long arg0, Serializable serializable) {
		Context ctx = (Context) serializable;
		ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
		Connection con = (Connection) ctx.get(Constant.CONN);
		if (con == null) {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		if (msg != null) {

			/** get gift type from the message **/
			int giftPoint = MessageHelper.getGiftPoint(msg);

			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);

			/** get MID **/
			String mid = MessageHelper.getMID(msg);

			/** get TID **/
			String tid = MessageHelper.getTID(msg);

			/** Add point business **/
			int result = JPOS_CustomerBUS.redemption(cardNumber, 3, giftPoint,
					mid, tid, "01", con);

			if (result == 0) {
				ctx.put(Constant.RC, "12");
				return ABORTED | READONLY | NO_JOIN;
			} 
			
			int reservePoint = JPOS_CustomerBUS.getCurrentPoint(cardNumber, con);
			
			/** convert point to response string message **/
			String strPoint = MessageHelper.makeTLV("FF51", MessageHelper
					.format(Integer.toString(reservePoint), 4));
			
			ctx.put(Constant.POINT, strPoint);
			
			String giftInfo = JPOS_GiftBUS.getGiftName(giftPoint, con);
			
			if(giftInfo.isEmpty()){
				ctx.put(Constant.RC, "12");
				return ABORTED | READONLY | NO_JOIN;
			}
			
			giftInfo = MessageHelper.makeTLV("FF51", giftInfo);
			ctx.put(Constant.ADVERTISE, giftInfo);
			
			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | NO_JOIN;
		}
	}
}
