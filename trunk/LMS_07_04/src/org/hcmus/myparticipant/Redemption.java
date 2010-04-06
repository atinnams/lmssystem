package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CustomerBUS;
import org.hcmus.bus.JPOS_CustomerDTO;
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
			int giftType = MessageHelper.getGiftType(msg);

			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);

			/** get MID **/
			String mid = MessageHelper.getMID(msg);

			/** get TID **/
			String tid = MessageHelper.getTID(msg);

			/** Get PoSCC **/
			String poscc = MessageHelper.getPoSCC(msg);

			/** Add point business **/
			int result = JPOS_CustomerBUS.redemption(cardNumber, 3, giftType,
					mid, tid, poscc, con);

			int currentPoint = JPOS_CustomerBUS
					.getCurrentPoint(cardNumber, con);
			int giftPoint = JPOS_GiftBUS.getGiftPoint(giftType, con);
			String point = MessageHelper.format(Integer.toString(giftPoint))
					+ MessageHelper.format(Integer.toString(currentPoint));
			ctx.put(Constant.POINT, point);

			// TODO Change it after prepare procedure in DB
			/*
			 * if(result == 0) { ctx.put(Constant.RC, "14"); return ABORTED |
			 * NO_JOIN; }else { result = JPOS_CardBUS.checkExpire(cardNumber);
			 * if(result == 0) { ctx.put(Constant.RC, "54"); return ABORTED |
			 * NO_JOIN; } }
			 */
			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | NO_JOIN;
		}
	}
}
