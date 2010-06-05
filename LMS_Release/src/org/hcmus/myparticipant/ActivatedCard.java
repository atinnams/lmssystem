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
 * Activate card to ability usage.
 * @author HUNGPT
 *
 */
public class ActivatedCard implements TransactionParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) { }

	@Override
	public void commit(long arg0, Serializable arg1) { }

	@Override
	public int prepare(long id, Serializable serializable) {
		
		/** Get context of this transaction **/
		Context ctx = (Context)serializable;
		
		/** Get connection **/
		Connection con = (Connection) ctx.get(Constant.CONN);
		if(con == null){
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
		
		/** Get ISO 8583 message **/
		ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
		if (msg != null) {
			
			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);

			/** Activate business **/
			JPOS_CardBUS.activateCard(cardNumber, con);
			
			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | NO_JOIN;
		}
	}

}
