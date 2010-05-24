package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CardBUS;
import org.hcmus.bus.JPOS_CustomerBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

/**
 * Update point to database server.
 * 
 * @author HUNGPT
 * 
 */
public class AddPoint implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializeable) { }

	@Override
	public void commit(long id, Serializable serializeable) { }

	@Override
	public int prepare(long id, Serializable serializeable) {
		Context ctx = (Context) serializeable;
		ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
		Connection con = (Connection) ctx.get(Constant.CONN);
		if (con == null) {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		if (msg != null) {

			/** get point from the message **/
			int point = MessageHelper.getPoint(msg);

			/** get amount from message **/
			int amount = MessageHelper.getAmount(msg);

			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);

			/** get MID **/
			String mid = MessageHelper.getMID(msg);

			/** get TID **/
			String tid = MessageHelper.getTID(msg);
			
			if(point == -1){
				ctx.put(Constant.RC, "14");
				return ABORTED | READONLY | NO_JOIN;
			}
			
			int redeem = JPOS_CardBUS.redeem(cardNumber, amount, con);
			if(redeem == -1){
				ctx.put(Constant.RC, "16");
				return ABORTED | READONLY | NO_JOIN;
			}
			
			/** Add point business **/
			int logId = JPOS_CustomerBUS.addNormalPoint(cardNumber, 1, point,
					mid, tid, "01", con);

			/** Declare event point **/
			int eventPoint = 0;

			if (logId <= 0) {
				ctx.put(Constant.RC, "14");
				return ABORTED | READONLY | NO_JOIN;
			} else {
				/** write into event log and return event point **/
				eventPoint = JPOS_CustomerBUS.addEventPoint(cardNumber,amount, logId, con);
			}

			/** put normal + event point to context for response participant **/
			point += eventPoint;

			int totalPoint = JPOS_CustomerBUS.getCurrentPoint(cardNumber, con);
			int balance_amount = JPOS_CardBUS.getAmountCard(cardNumber, con);
			
			String strPoint = "FF3E161111000000000000000000000000000000" 
				+ MessageHelper.format(Integer.toString(balance_amount), 8) + "00" 
				+ "FF41161111000000000000000000000000000000" 
				+ MessageHelper.format(Integer.toString(amount), 8) + "00"
				+ "FF5805" + MessageHelper.format(Integer.toString(totalPoint), 8) + "00"; 

			ctx.put(Constant.POINT, strPoint);

			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}

	}

}
