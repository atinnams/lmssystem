package org.hcmus.lms;

import java.io.Serializable;

import org.hcmus.Util.Constant;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;

/**
 * 
 * @author HUNGPT Switch message to right business by field no3 of message.
 */
public class Switch implements GroupSelector, Configurable {

	Configuration cfg = null;

	@Override
	public String select(long arg0, Serializable context) {

		try {
			// Get context from space
			Context ctx = (Context) context;

			// Get message from context
			ISOMsg msg = (ISOMsg) ctx.get(Constant.REQUEST);

			String groups = "";
			// Get groups from fields No3 of message.
			if (!msg.hasField(3)) {
				return cfg.get(Constant.ERROR_FLOW);
			}

			try {
				String field3 = (String) msg.getValue(3);
				if (!Constant.ADD_POINT_PROCESS.equals(field3)
						&& !Constant.SUBTRACT_POINT_PROCESS.equals(field3)
						&& !Constant.BALANCE_INQUIRY_PROCESS.equals(field3)
						&& !Constant.REDEMPTION_PROCESS.equals(field3)) {
					return cfg.get(Constant.ERROR_FLOW);
				}
			} catch (ISOException e) {
				e.printStackTrace();
			}

			groups = cfg.get((String) msg.getValue(3));

			return groups;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void abort(long arg0, Serializable arg1) {
	}

	@Override
	public void commit(long arg0, Serializable arg1) {
	}

	@Override
	public int prepare(long arg0, Serializable arg1) {
		return PREPARED | READONLY | NO_JOIN;
	}

	@Override
	public void setConfiguration(Configuration cfg)
			throws ConfigurationException {
		this.cfg = cfg;
	}

}
