package org.hcmus.Gateway.util;

import org.jpos.util.LogEvent;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.NameRegistrar;

public class GWLogSource implements LogSource{
	protected Logger logger;
    protected String realm;
	
	public GWLogSource(){
		super();
		logger = Logger.getLogger("GW610");
		logger.addListener(new GWLogListener("log/gw610.log"));		
		realm = "GW610.system";
	}
	
	public GWLogSource(Logger logger,String realm){
		setLogger(logger, realm);
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public String getRealm() {
		return realm;
	}

	@Override
	public void setLogger(Logger logger, String realm) {
		this.logger = logger;
		this.realm = realm;
	}
	
	public void printHexValue(String taskName,String strPack){
		LogEvent evt = new LogEvent(this,taskName);
		evt.addMessage(strPack);
		Logger.log(evt);
	}
	
	public static GWLogSource getLogSource(String name){
		GWLogSource logSource;
		try{
			logSource = (GWLogSource)NameRegistrar.get("logsource." + name);
		}catch(NameRegistrar.NotFoundException e){
			logSource = new GWLogSource();
			NameRegistrar.register("logsource." + name, logSource);
		}
		return logSource;
	}
	
}
