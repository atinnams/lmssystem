package org.hcmus.dao.factory;
import org.hcmus.dao.idao.IJPOS_Card;
import org.hcmus.dao.idao.IJPOS_Customer;
import org.hcmus.dao.idao.IJPOS_Gift;
import org.hcmus.dao.idao.IJPOS_Log;
import org.hcmus.dao.idao.IJPOS_Log_Exchange;
import org.hcmus.dao.idao.IJPOS_Merchant;
import org.hcmus.dao.idao.IJPOS_PoSCC;
import org.hcmus.dao.idao.IJPOS_Task;

public abstract class LMSDAOFactory {
	public abstract IJPOS_Card getJPOS_Card();
	public abstract IJPOS_Merchant getJPOS_Merchant();
	public abstract IJPOS_PoSCC getJPOS_PoSCC();
	public abstract IJPOS_Customer getJPOS_Customer();
	public abstract IJPOS_Gift  getJPOS_Gift();
	public abstract IJPOS_Log_Exchange  getJPOS_Log_Exchange();
	public abstract IJPOS_Log getJPOS_Log();
	public abstract IJPOS_Task getJPOS_Task();
	
	public static LMSDAOFactory getInstances() {
		return new LMSSqlDAO();
	}
	
	
}
