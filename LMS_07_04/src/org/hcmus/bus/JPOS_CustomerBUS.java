package org.hcmus.bus;

import java.sql.Connection;

import org.hcmus.dao.factory.LMSDAOFactory;
import org.hcmus.dao.idao.IJPOS_Customer;

public class JPOS_CustomerBUS {
	public static int addPoint(JPOS_CustomerDTO customer,int taskid, int point,
			String mid,String tid,String poscc,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.addPoint(customer, taskid, point, mid,tid,poscc,con);
	}
	public static int subtractPoint(JPOS_CustomerDTO customer,int taskid, int point,
			String mid,String tid,String poscc,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.subtractPoint(customer, taskid, point, mid,tid,poscc,con);
	}
	
	public static JPOS_CustomerDTO getCustomer(int customerId,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Customer myCustomer = factory.getJPOS_Customer();
		return myCustomer.getCustomer(customerId,con);
	}
}
