package org.hcmus.dao.idao;

import org.hcmus.bus.JPOS_CustomerDTO;

public interface IJPOS_Customer {
	public JPOS_CustomerDTO getCustomer(int customerId);
	public int addPoint(JPOS_CustomerDTO customer,int taskid, int point,String mid,String tid,String poscc);
	public int subtractPoint(JPOS_CustomerDTO customer,int taskid, int point,String mid,String tid,String poscc);
}
