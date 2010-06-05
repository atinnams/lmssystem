package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CardBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

/**
 * Check a card whether activated or not.
 * @author HUNGPT
 *
 */
public class CheckActivatedCard implements TransactionParticipant{

	@Override
	public void abort(long arg0, Serializable arg1) { }

	@Override
	public void commit(long arg0, Serializable arg1) { }

	@Override
	public int prepare(long id, Serializable serializable) {
		/** get context from space **/
		Context ctx = (Context) serializable;

		/** get message from context **/
		ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);

		/** Get connection from context **/
		Connection con = (Connection) ctx.get(Constant.CONN);
		if (con == null) {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}

		if (msg != null) {
			String cardId = MessageHelper.getCardId(msg);
			if (!cardId.isEmpty()) {
				int result = JPOS_CardBUS.checkActivatedCard(cardId, con);
				if (result == 1) {
					ctx.put(Constant.RC, "91");
					return ABORTED | READONLY | NO_JOIN;
				} else {
					return PREPARED | READONLY | NO_JOIN;
				}
			} else {
				ctx.put(Constant.RC, "12");
				return ABORTED | READONLY | NO_JOIN;
			}
		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
	}

}
