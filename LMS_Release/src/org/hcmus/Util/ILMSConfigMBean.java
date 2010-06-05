package org.hcmus.Util;

import org.jpos.q2.QBeanSupportMBean;

public interface ILMSConfigMBean extends QBeanSupportMBean {
	
	public String getTypeOfDatabase();
	
	public String getHost();
	
	public String getPort() ;
	
	public String getDBName();
	
	public String getUserName();
	
	public String getPassword();
}
