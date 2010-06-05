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
 * Check All Information about card include existing card, expire card and activated card
 * @author HUNGPT
 *
 */
public class CheckCard implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializeable) {
		
	}

	@Override
	public void commit(long id, Serializable serialieable) {
		
	}

	@Override
	public int prepare(long id, Serializable serializeable) {
		
		/** get context from space **/
		Context ctx = (Context)serializeable;
		
		/** get message from context **/
		ISOMsg msg = (ISOMsg)ctx.get(Constant.REQUEST);
		
		/** Get connection from context **/
		Connection con = (Connection)ctx.get(Constant.CONN);
		if(con == null){
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		
		if(msg != null) {
			String cardNumber = MessageHelper.getCardId(msg);
			int result = JPOS_CardBUS.checkCard(cardNumber,con);
			if(result == 0) {
				ctx.put(Constant.RC, "14");
				return ABORTED | READONLY | NO_JOIN;
			}else
			{
				result = JPOS_CardBUS.checkExpire(cardNumber,con);
				if(result == 1) {
					ctx.put(Constant.RC, "54");
					return ABORTED | READONLY | NO_JOIN;
				}
				result = JPOS_CardBUS.checkActivatedCard(cardNumber, con);
				if(result == 0){
					ctx.put(Constant.RC, "93");
					return ABORTED | READONLY | NO_JOIN;
				}
			}
		}else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		return PREPARED | READONLY | NO_JOIN;
	}

}
