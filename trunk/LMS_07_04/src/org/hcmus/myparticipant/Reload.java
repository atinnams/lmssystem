package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.Util.ReversalObject;
import org.hcmus.bus.JPOS_CardBUS;
import org.hcmus.bus.JPOS_LogBUS;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class Reload implements TransactionParticipant {

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
			int amount = MessageHelper.getReloadAmount(msg);
			
			/** get invoice number **/
			String invoiceLog = MessageHelper.getInvoiceLog(msg);

			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);

			/** get MID **/
			String mid = MessageHelper.getMID(msg);

			/** get TID **/
			String tid = MessageHelper.getTID(msg);
			
			if(cardNumber.isEmpty() || amount == -1 || invoiceLog == null){
				ctx.put(Constant.RC, "14");
				return ABORTED | READONLY | NO_JOIN;
			}
			
			if(ReversalObject.contains(cardNumber, invoiceLog)){
				ReversalObject.remove(cardNumber, invoiceLog);
				ctx.put(Constant.RC, "98");
				return ABORTED | READONLY | NO_JOIN;
			}
			
			int reload = JPOS_CardBUS.reloadCard(cardNumber, amount, con);
			
			if(reload == -1){
				ctx.put(Constant.RC, "18");
				return ABORTED | READONLY | NO_JOIN;
			}
			
			/** Add reload Log business **/
			JPOS_LogBUS.addReloadLog(cardNumber,2, amount, invoiceLog, mid, tid, "01", con);
			
			String strPoint = "FF3E161111000000000000000000000000000000" 
				+ MessageHelper.format(Integer.toString(reload), 8) + "00" 
				+ "FF41168707000000000000000000000000000000" 
				+ MessageHelper.format(Integer.toString(amount), 8) + "00";

			ctx.put(Constant.POINT, strPoint);

			return PREPARED | READONLY | NO_JOIN;

		} else {
			ctx.put(Constant.RC, "12");
			return ABORTED | READONLY | NO_JOIN;
		}

	}
}
