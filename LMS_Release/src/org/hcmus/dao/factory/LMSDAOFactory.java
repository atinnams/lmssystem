package org.hcmus.dao.factory;
import org.hcmus.Util.Constant;
import org.hcmus.Util.LMSConfig;
import org.hcmus.dao.idao.IJPOS_Card;
import org.hcmus.dao.idao.IJPOS_Customer;
import org.hcmus.dao.idao.IJPOS_Gift;
import org.hcmus.dao.idao.IJPOS_Log;
import org.hcmus.dao.idao.IJPOS_Log_Exchange;
import org.hcmus.dao.idao.IJPOS_Merchant;
import org.hcmus.dao.idao.IJPOS_PoSCC;
import org.hcmus.dao.idao.IJPOS_Task;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

/**
 * 
 * @author HUNGPT
 * General class for all database.
 */
public abstract class LMSDAOFactory {
	
	//Get Card interface stub.
	public abstract IJPOS_Card getJPOS_Card();
	
	//Get Merchant interface stub.
	public abstract IJPOS_Merchant getJPOS_Merchant();
	
	// Get PoSCC interface stub.
	public abstract IJPOS_PoSCC getJPOS_PoSCC();
	
	// Get Customer interface stub.
	public abstract IJPOS_Customer getJPOS_Customer();
	
	//Get Gift interface stub.
	public abstract IJPOS_Gift  getJPOS_Gift();
	
	//Get Log_exchange interface stub.
	public abstract IJPOS_Log_Exchange  getJPOS_Log_Exchange();
	
	// Get Log interface stub.
	public abstract IJPOS_Log getJPOS_Log();
	
	//Get Task interface stub.
	public abstract IJPOS_Task getJPOS_Task();
	
	
	/**
	 * Get instance of class indicate for specific database..
	 * @return instance of class indicate for specific database.
	 */
	public static LMSDAOFactory getInstances() {
		LMSConfig cfg;
		try {
			cfg = (LMSConfig)NameRegistrar.get("MyConfigDB");
			if(cfg.getTypeOfDatabase().equals(Constant.SQL_DB)){
				return new LMSSqlDAO();
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		
		//default SQL server
		return new LMSSqlDAO();
	}
}
