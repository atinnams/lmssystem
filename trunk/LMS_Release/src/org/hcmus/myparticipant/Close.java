package org.hcmus.myparticipant;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.hcmus.Util.Constant;
import org.jpos.transaction.AbortParticipant;
import org.jpos.transaction.Context;

/** 
 * Closed connection after a while the transaction had finished. 
 * @author HUNGPT
 *
 */
public class Close implements AbortParticipant {

	@Override
	public void abort(long id, Serializable serializable) { }

	@Override
	public void commit(long id, Serializable serializable) {
		closeConnection(id, serializable);
	}

	@Override
	public int prepare(long arg0, Serializable arg1) {
		return PREPARED;
	}

	@Override
	public int prepareForAbort(long id, Serializable serializable) {
		closeConnection(id, serializable);
		return ABORTED | READONLY | NO_JOIN;
	}
	
	public void closeConnection(long id,Serializable serializable){
		
		/** get context from space **/
		Context ctx = (Context)serializable;
		
		/** get connection from context **/
		Connection con = (Connection)ctx.get(Constant.CONN);
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
