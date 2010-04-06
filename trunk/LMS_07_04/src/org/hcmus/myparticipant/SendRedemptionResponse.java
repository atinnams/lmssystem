package org.hcmus.myparticipant;

import java.io.IOException;
import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOFilter.VetoException;
import org.jpos.transaction.AbortParticipant;
import org.jpos.transaction.Context;

public class SendRedemptionResponse implements AbortParticipant {

	@Override
	public void abort(long arg0, Serializable arg1) {
	}

	@Override
	public void commit(long id, Serializable ctx) {
		sendResponse(id, (Context)ctx);
	}

	@Override
	public int prepare(long arg0, Serializable arg1) {
		return PREPARED;
	}

	@Override
	public int prepareForAbort(long id, Serializable ctx) {
		sendResponse(id, (Context)ctx);
		return ABORTED | READONLY | NO_JOIN;
	}

	/**
	 * Send response to client.
	 * 
	 * @param id
	 *            Identify of transaction
	 * @param ctx
	 *            Context of transaction
	 */
	private void sendResponse(long id, Context ctx) {

		ISOSource source = (ISOSource) ctx.get(Constant.SOURCE);
		if (source == null || !source.isConnected()) {
			return; // too late to send
		}

		try {
			ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
			String rc = (String) ctx.get(Constant.RC);
			String point = (String)ctx.get(Constant.POINT);
			if (source != null && source.isConnected() && msg != null) {
				msg.set(0, "0210");
				if (rc == null || "00".equals(rc)) {
					msg.set(39, "00");
					msg.set(62, Constant.SUCCESFULL);
					if(point != null){
						msg.set(63,point);
					}
				} else if (rc != null) {
					int error = Integer.parseInt(rc);
					switch (error) {
					case 14:
						msg.set(39, "14");
						msg.set(62, Constant.CARD_NOT_FOUND);
						break;
					case 54:
						msg.set(39, "54");
						msg.set(62, Constant.EXPIRE_CARD);
						break;
					case 15:
						msg.set(39, "15");
						msg.set(62, Constant.INVALID_FIELD);
						break;
					case 3:
						msg.set(39, "03");
						msg.set(62, Constant.MID_OR_TID_NOT_FOUND);
						break;
					case 58:
						msg.set(39, "58");
						msg.set(62, Constant.POSCC_NOT_FOUND);
						break;
					case 24:
						msg.set(39, "24");
						msg.set(62, Constant.FORWARD_FAIL);
						break;
					case 93:
						msg.set(39, "93");
						msg.set(62, Constant.NO_ACTIVATED_CARD);
						break;
					case 95:
						msg.set(39,"95");
						msg.set(62,Constant.NOT_ENOUGH_POINT);
						break;
					default:
						msg.set(39, "12");
						msg.set(62, Constant.OTHER_ERROR);
						break;
					}
				}
				source.send(msg);
			}
		} catch (VetoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ISOException e) {
			e.printStackTrace();
		}
	}
}
