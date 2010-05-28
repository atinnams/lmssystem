package org.hcmus.myparticipant;

import java.io.IOException;
import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.hcmus.Util.LMSLogSource;
import org.hcmus.Util.MessageHelper;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.ISOFilter.VetoException;
import org.jpos.transaction.AbortParticipant;
import org.jpos.transaction.Context;

public class ReversalSendResponse implements AbortParticipant {

	@Override
	public void abort(long id, Serializable context) {
	}

	@Override
	public int prepareForAbort(long id, Serializable context) {
		sendResponse(id, (Context) context);
		return ABORTED | READONLY | NO_JOIN;
	}

	@Override
	public void commit(long id, Serializable context) {
		sendResponse(id, (Context) context);
	}

	@Override
	public int prepare(long id, Serializable context) {

		// get context from space
		Context ctx = (Context) context;

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
	private void sendResponse(long id,Context ctx) {
		
		ISOSource source = (ISOSource) ctx.get(Constant.SOURCE);
		if (source == null || !source.isConnected()) {
			return; // too late to send
		}

		try {
			ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
			
			//Get response code 
			String rc = (String)ctx.get(Constant.RC);
			
			if (source != null && source.isConnected() && msg != null) {
				
				//Create new message response
				ISOMsg msgResponse = new ISOMsg();
				msgResponse.set(0,"0410");
				msgResponse.set(3,(String)msg.getValue(3));
				msgResponse.set(41,(String)msg.getValue(41));
				msgResponse.set(42,(String)msg.getValue(42));
				String field48Value = ISOUtil.hexString(msg.getComponent(48).getBytes());
				msgResponse.set(48,ISOUtil.hex2byte(field48Value));
				if(rc == null || "00".equals(rc)) {
					msgResponse.set(39, "00");
				}
				else if(rc != null) {
					msgResponse.set(39,"12");
					String strError = MessageHelper.makeTLV("FF39",Constant.OTHER_ERROR);
					msgResponse.set(61,ISOUtil.hex2byte(strError));
				}
				
				source.send(msgResponse);
				
				LMSLogSource logSource = LMSLogSource.getLogSource("LMS");
				logSource.printHexValue("Reversal_Receive", ISOUtil.hexString(msg.pack()));
				logSource.printHexValue("Reversal_Response", ISOUtil.hexString(msgResponse.pack()));
				
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
