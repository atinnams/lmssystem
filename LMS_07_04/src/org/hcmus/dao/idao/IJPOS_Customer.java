package org.hcmus.dao.idao;

import java.sql.Connection;

import org.hcmus.bus.JPOS_CustomerDTO;

public interface IJPOS_Customer {
	public JPOS_CustomerDTO getCustomer(int customerId,Connection con);
	public int addPoint(JPOS_CustomerDTO customer,int taskid, int point,String mid,String tid,String poscc,Connection con);
	public int subtractPoint(JPOS_CustomerDTO customer,int taskid, int point,String mid,String tid,String poscc,Connection con);
}
