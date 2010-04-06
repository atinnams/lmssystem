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

/**
 * Send activation information to client.
 * @author HUNGPT
 *
 */
public class SendActivationResponse implements AbortParticipant {

	@Override
	public int prepareForAbort(long id, Serializable serializable) {
		sendResponse(id, (Context)serializable);
		return ABORTED | READONLY | NO_JOIN;
	}

	@Override
	public void abort(long id, Serializable serializable) {
	}

	@Override
	public void commit(long id, Serializable serializable) {
		sendResponse(id, (Context)serializable);
	}

	@Override
	public int prepare(long id, Serializable serializable) {

		// get context from space
		Context ctx = (Context) serializable;

		// get source or sender from context
		ISOSource source = (ISOSource) ctx.get(Constant.SOURCE);
		if (source == null || !source.isConnected()) {
			return ABORTED | READONLY | NO_JOIN;
		}

		return PREPARED | READONLY;
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
			if (source != null && source.isConnected() && msg != null) {
				msg.set(0, "0210");
				if (rc == null || "00".equals(rc)) {
					msg.set(39, "00");
					msg.set(62, Constant.SUCCESFULL);
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
					case 91:
						msg.set(39,"91");
						msg.set(62,Constant.ACTIVATED_CARD);
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