package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CustomerBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

/**
 * Subtract point to database server.
 * 
 * @author HUNGPT
 * 
 */
public class SubtractPoint implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) {
	}

	@Override
	public void commit(long arg0, Serializable arg1) {
	}

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

			/** Subtract point business **/
			int result = JPOS_CustomerBUS.subtractPoint(cardNumber, 2, point,
					mid, tid, "01", con);

			if (result == 0) {
				ctx.put(Constant.RC, "12");
				return ABORTED | READONLY | NO_JOIN;
			} else {
				int totalPoint = JPOS_CustomerBUS.getCurrentPoint(cardNumber,
						con);

				String strPoint = MessageHelper.makeTLV("FF51", MessageHelper
						.format(Integer.toString(point), 4))
						+ MessageHelper.makeTLV("FF52", MessageHelper.format(
								Integer.toString(totalPoint), 4));

				ctx.put(Constant.POINT, strPoint);

			}

			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | NO_JOIN;
		}
	}

}
