package org.hcmus.Util;

import org.jpos.util.LogEvent;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.NameRegistrar;

public class LMSLogSource implements LogSource{
	protected Logger logger;
    protected String realm;
	
	public LMSLogSource(){
		super();
		logger = Logger.getLogger("LMS");
		logger.addListener(new LMSLogListener("log/lms.log"));		
		realm = "LMS.system";
	}
	
	public LMSLogSource(Logger logger,String realm){
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
	
	public static LMSLogSource getLogSource(String name){
		LMSLogSource logSource;
		try{
			logSource = (LMSLogSource)NameRegistrar.get("logsource." + name);
		}catch(NameRegistrar.NotFoundException e){
			logSource = new LMSLogSource();
			NameRegistrar.register("logsource." + name, logSource);
		}
		return logSource;
	}
	
}
