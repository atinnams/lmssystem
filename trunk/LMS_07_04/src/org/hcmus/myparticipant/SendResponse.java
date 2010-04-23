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
 * Send transaction information to client.
 * @author HUNGPT
 *
 */
public class SendResponse implements AbortParticipant {

	@Override
	public void abort(long id, Serializable context) { }
	
	@Override
	public int prepareForAbort(long id, Serializable context) {
		sendResponse(id, (Context)context);
		return ABORTED | READONLY | NO_JOIN;
	}
	
	@Override
	public void commit(long id, Serializable context) {
		sendResponse(id, (Context)context);
	}

	@Override
	public int prepare(long id, Serializable context) {
		
		//get context from space
		Context ctx = (Context) context;
		
		//get source or sender from context
		ISOSource source = (ISOSource) ctx.get(Constant.SOURCE);
		if (source == null || !source.isConnected()) {
			return ABORTED | READONLY | NO_JOIN;
		}
		
		
		return PREPARED | READONLY;
	}
	
	/**
	 * Send response to client.
	 * @param id Identify of transaction
	 * @param ctx Context of transaction
	 */
	private void sendResponse(long id,Context ctx) {
		
		ISOSource source = (ISOSource) ctx.get(Constant.SOURCE);
		if (source == null || !source.isConnected()) {
			return; // too late to send
		}

		try {
			ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
			String rc = (String)ctx.get(Constant.RC);
			String point = (String)ctx.get(Constant.POINT);
			String advertisement = (String)ctx.get(Constant.ADVERTISE);
			if (source != null && source.isConnected() && msg != null) {
				ISOMsg msgResponse = new ISOMsg();
				msgResponse.set(0,"0210");
				msgResponse.set(3,(String)msg.getValue(3));
				msgResponse.set(41,(String)msg.getValue(41));
				msgResponse.set(42,(String)msg.getValue(42));
				if(rc == null || "00".equals(rc)) {
					msgResponse.set(39, "00");
					msgResponse.set(48,ISOUtil.hex2byte(point));
					msgResponse.set(61,ISOUtil.hex2byte(advertisement));
				}
				else if(rc != null) {
					int error = Integer.parseInt(rc);
					String strError = "";
					switch(error){
					case 14 :
						msgResponse.set(39,"14");
						strError = MessageHelper.makeTLV("FF01",Constant.CARD_NOT_FOUND);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 54 :
						msgResponse.set(39,"54");
						strError = MessageHelper.makeTLV("FF01",Constant.EXPIRE_CARD);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 15:
						msgResponse.set(39,"15");
						strError = MessageHelper.makeTLV("FF01",Constant.INVALID_FIELD);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 3 :
						msgResponse.set(39,"03");
						strError = MessageHelper.makeTLV("FF01",Constant.MID_OR_TID_NOT_FOUND);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 58:
						msgResponse.set(39,"58");
						strError = MessageHelper.makeTLV("FF01",Constant.POSCC_NOT_FOUND);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 24:
						msgResponse.set(39,"24");
						strError = MessageHelper.makeTLV("FF01",Constant.FORWARD_FAIL);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					case 93 :
						msgResponse.set(39,"93");
						strError = MessageHelper.makeTLV("FF01",Constant.NO_ACTIVATED_CARD);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					default :
						msgResponse.set(39,"12");
						strError = MessageHelper.makeTLV("FF01",Constant.OTHER_ERROR);
						msgResponse.set(61,ISOUtil.hex2byte(strError));
						break;
					}
				}
				
				source.send(msgResponse);
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
