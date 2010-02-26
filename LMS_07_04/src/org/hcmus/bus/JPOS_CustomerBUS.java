package org.hcmus.bus;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Customer;

public class JPOS_CustomerBUS {
	public static int addPoint(JPOS_CustomerDTO customer,int taskid, int point,
			String mid,String tid,String poscc) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.addPoint(customer, taskid, point, mid,tid,poscc);
	}
	public static int subtractPoint(JPOS_CustomerDTO customer,int taskid, int point,
			String mid,String tid,String poscc) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.subtractPoint(customer, taskid, point, mid,tid,poscc);
	}
	
	public static JPOS_CustomerDTO getCustomer(int customerId) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCustomer(customerId);
	}
}
