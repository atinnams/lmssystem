package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_CardBUS;
import org.hcmus.bus.JPOS_LogBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class VoidCard implements TransactionParticipant {
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

			/** get amount from message **/
			int amount = MessageHelper.getAmount(msg);
			
			/** get invoice number **/
			String invoiceId = MessageHelper.getInvoice(msg);
			
			
			/** get invoice Log **/
			String invoiceLog = MessageHelper.getInvoiceLog(msg);
			
			
			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);
			
			
			/** get MID **/
			String mid = MessageHelper.getMID(msg);

			/** get TID **/
			String tid = MessageHelper.getTID(msg);
			
			int voidCard = JPOS_CardBUS.voidCard(cardNumber, invoiceId, amount, con);
			
			
			if(voidCard == -1){
				ctx.put(Constant.RC, "17");
				return ABORTED | READONLY | NO_JOIN;
			}
			
			/** Add void log business **/
			JPOS_LogBUS.addVoidLog(cardNumber, 3, amount, invoiceLog, mid, tid, "01", con);
			
			String strPoint = "FF3E161111000000000000000000000000000000" 
				+ MessageHelper.format(Integer.toString(voidCard), 8) + "00" 
				+ "FF41168208000000000000000000000000000000" 
				+ MessageHelper.format(Integer.toString(amount), 8) + "00";

			ctx.put(Constant.POINT, strPoint);

			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}
	}
}
