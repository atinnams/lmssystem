package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.dao.lms.DataProvider;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

/**
 * Open Connection before start transaction flow.
 * @author HUNGPT
 *
 */
public class Open implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serialize) { }

	@Override
	public void commit(long id, Serializable serialize) { }

	@Override
	public int prepare(long id, Serializable serialize) {
		Context ctx = (Context) serialize;
		Connection con = DataProvider.getConnection();
		
		if(con == null)
			return ABORTED | NO_JOIN ;
		
		ctx.put(Constant.CONN, con, false);
		return PREPARED | NO_JOIN;
	}

}
