package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;

import org.hcmus.Util.Constant;
import org.hcmus.dao.lms.DataProvider;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

public class Open implements TransactionParticipant {

	@Override
	public void abort(long id, Serializable serialize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commit(long id, Serializable serialize) {
		// TODO Auto-generated method stub

	}

	@Override
	public int prepare(long id, Serializable serialize) {
		// TODO Auto-generated method stub
		Context ctx = (Context) serialize;
		Connection con = DataProvider.getConnection();
		
		if(con == null)
			return ABORTED | NO_JOIN ;
		
		ctx.put(Constant.CONN, con, false);
		return PREPARED | NO_JOIN;
	}

}
