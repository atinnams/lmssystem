package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.hcmus.bus.JPOS_GiftBUS;
import org.hcmus.bus.JPOS_GiftDTO;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class MakeAdvertisementMessage implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serializable) { }

	@Override
	public void commit(long id, Serializable serializable) { }

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
			
			/** get card number **/
			String cardNumber = MessageHelper.getCardId(msg);
			ArrayList<JPOS_GiftDTO> gifts = JPOS_GiftBUS.getGifts(cardNumber, con);
			String tag = "";
			String value = "";
			String message = "";
			
			if(gifts.size() == 0){
				tag = "FF01";
				value =Constant.NO_GIFT;
				message = MessageHelper.makeTLV(tag, value);
			}else
			{
				int size = gifts.size();
				for(int i=0;i<size;i++){
					tag = "FF0" + Integer.toString(i);
					JPOS_GiftDTO gift = gifts.get(i);
					value = Integer.toString(gift.getPointForGift()) + " " + gift.getGiftName();
					message += MessageHelper.makeTLV(tag, value); 
				}
			}
			
			ctx.put(Constant.ADVERTISE, message);
		}
		
		return PREPARED | READONLY | NO_JOIN;
	}

}
