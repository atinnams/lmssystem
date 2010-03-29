package org.hcmus.Util;

import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.core.ReConfigurable;

public class LMSConfig implements ReConfigurable {

	Configuration cfg ;
	
	public LMSConfig(){
		super();
	}
	
	
	@Override
	public void setConfiguration(Configuration cfg)
			throws ConfigurationException {
		// TODO Auto-generated method stub
		this.cfg = cfg;
	}
	
	public String getTypeOfDatabase() {
		return cfg.get("dbtype");
	}
	
	public String getHost() {
		return cfg.get("host");
	}
	
	public String getPort() {
		return cfg.get("port");
	}
	
	public String getDBName() {
		return cfg.get("dbname");
	}
	
	public String getUserName() {
		return cfg.get("username");
	}
	
	public String getPassword() {
		return cfg.get("password");
	}

}
