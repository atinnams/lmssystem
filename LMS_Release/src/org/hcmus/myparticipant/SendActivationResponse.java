package org.hcmus.myparticipant;

import java.io.IOException;
import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.hcmus.Util.MessageHelper;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOUtil;
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
			//Get message
			ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
			
			//Get response code
			String rc = (String) ctx.get(Constant.RC);
			
			if (source != null && source.isConnected() && msg != null) {
				ISOMsg msgResponse = new ISOMsg();
				
				//Set MTI
				msgResponse.set(0,"0210");
				
				msgResponse.set(3,(String)msg.getValue(3));
				msgResponse.set(41,(String)msg.getValue(41));
				msgResponse.set(42,(String)msg.getValue(42));
				
				if (rc == null || "00".equals(rc)) {
					msg.set(39, "00");
					String strResult = MessageHelper.makeTLV("FF51",Constant.SUCCESFULL);
					msg.set(48, strResult);
				} else if (rc != null) {
					int error = Integer.parseInt(rc);
					String strError = "";
					switch (error) {
					case 14:
						msgResponse.set(39,"14");
						strError = MessageHelper.makeTLV("FF01",Constant.CARD_NOT_FOUND);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 54:
						msgResponse.set(39,"54");
						strError = MessageHelper.makeTLV("FF01",Constant.EXPIRE_CARD);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 15:
						msgResponse.set(39,"15");
						strError = MessageHelper.makeTLV("FF01",Constant.INVALID_FIELD);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 3:
						msgResponse.set(39,"03");
						strError = MessageHelper.makeTLV("FF01",Constant.MID_OR_TID_NOT_FOUND);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 58:
						msgResponse.set(39,"58");
						strError = MessageHelper.makeTLV("FF01",Constant.POSCC_NOT_FOUND);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 91:
						msgResponse.set(91,"58");
						strError = MessageHelper.makeTLV("FF01",Constant.ACTIVATED_CARD);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
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
