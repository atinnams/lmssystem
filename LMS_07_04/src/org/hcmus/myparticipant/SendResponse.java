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
import org.jpos.transaction.TransactionParticipant;

public class SendResponse implements AbortParticipant {

	@Override
	public void abort(long id, Serializable context) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int prepareForAbort(long id, Serializable context) {
		// TODO Auto-generated method stub
		sendResponse(id, (Context)context);
		return ABORTED | READONLY | NO_JOIN;
	}
	
	@Override
	public void commit(long id, Serializable context) {
		// TODO send response here
		sendResponse(id, (Context)context);
	}

	@Override
	public int prepare(long id, Serializable context) {
		// TODO Auto-generated method stub
		Context ctx = (Context) context;
		ISOSource source = (ISOSource) ctx.get(Constant.SOURCE);
		if (source == null || !source.isConnected()) {
			return ABORTED | READONLY | NO_JOIN;
		}

		return PREPARED | READONLY;
	}

	private void sendResponse(long id,Context ctx) {
		ISOSource source = (ISOSource) ctx.get(Constant.SOURCE);
		if (source == null || !source.isConnected()) {
			return; // too late to send
		}

		try {
			ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);
			String rc = (String)ctx.get(Constant.RC);
			if (source != null && source.isConnected() && msg != null) {
				if(rc == null || "00".equals(rc)) {
					msg.set(39, "00");
				}
				else if(rc != null) {
					msg.set(39,rc);
				}
				source.send(msg);
			}
		} catch (VetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
