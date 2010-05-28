package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CardBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class Reversal implements TransactionParticipant {

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
			
			/** get invoice number **/
			String invoiceLog = MessageHelper.getInvoiceLog(msg);

			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);

			/** get MID **/
			String mid = MessageHelper.getMID(msg);

			/** get TID **/
			String tid = MessageHelper.getTID(msg);
			
			String task = MessageHelper.getTransactionName(msg);
			
			if(task.equals(Constant.REDEEM_PROCESS)){
				JPOS_CardBUS.reversalRedeem(cardNumber,5,invoiceLog, mid, tid,"01", con);
			}else if(task.equals(Constant.RELOAD_PROCESS)){
				JPOS_CardBUS.reversalReload(cardNumber,5,invoiceLog, mid, tid,"01", con);
			}else if(task.equals(Constant.VOID_REDEEM_PROCESS)){
				JPOS_CardBUS.reversalVoidRedeem(cardNumber,5,invoiceLog, mid, tid,"01", con);
			}else if(task.equals(Constant.VOID_RELOAD_PROCESS)){
				JPOS_CardBUS.reversalVoidReload(cardNumber,5,invoiceLog, mid, tid,"01", con);
			}
			
			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}

	}

}
