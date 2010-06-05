package org.hcmus.Util;

import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;

public class NormalRule extends QBeanSupport implements INormalRule {
	
	public NormalRule(){
		super();
	}
	
	public String getAmount() {
		return cfg.get("amount");
	}
	
	public String getPoint() {
		return cfg.get("point");
	}
	
	public void startService(){
		log.info("Normal Rule  start");
		this.setName("normal.rule");
	}
	
	public void setName(String name){
		super.setName(name);
		NameRegistrar.register(name, this);
	}

}
