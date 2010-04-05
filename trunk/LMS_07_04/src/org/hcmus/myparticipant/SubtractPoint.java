package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CustomerBUS;
import org.hcmus.bus.JPOS_CustomerDTO;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

/**
 * Subtract point to database server.
 * @author HUNGPT
 *
 */
public class SubtractPoint implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) { }

	@Override
	public void commit(long arg0, Serializable arg1) { }

	@Override
	public int prepare(long id, Serializable serializable) {
		
		Context ctx = (Context) serializable;
		ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
		Connection con = (Connection) ctx.get(Constant.CONN);
		if (con == null) {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		if (msg != null) {

			/** get point from the message **/
			int point = MessageHelper.getPoint(msg);

			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);

			/** get MID **/
			String mid = MessageHelper.getMID(msg);

			/** get TID **/
			String tid = MessageHelper.getTID(msg);

			/** Get PoSCC **/
			String poscc = MessageHelper.getPoSCC(msg);

			/** Construct a customer **/
			JPOS_CustomerDTO customer = new JPOS_CustomerDTO();

			/** Set bar code for customer **/
			customer.setJPOS_Barcode(cardNumber);

			/** Subtract point business **/
			int result = JPOS_CustomerBUS.subtractPoint(customer, 2 , point, mid,
					tid, poscc, con);

			/** convert point to response string message **/
			String strPoint = MessageHelper.pointToStringField63(0, 0, point,
					0, 0);

			/** put it to context for response participant **/
			ctx.put(Constant.POINT, strPoint);

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
