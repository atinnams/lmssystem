package DAO.FACTORY;
import UTIL.Constant;
import UTIL.LMSConfig;
import DAO.iDAO.IJPOS_Admin;
import DAO.iDAO.IJPOS_Card;
import DAO.iDAO.IJPOS_Customer;
import DAO.iDAO.IJPOS_Gift;
import DAO.iDAO.IJPOS_Log;
import DAO.iDAO.IJPOS_Log_Exchange;
import DAO.iDAO.IJPOS_Merchant;
import DAO.iDAO.IJPOS_PoSCC;
import DAO.iDAO.IJPOS_Task;



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

        public abstract IJPOS_Admin getJPOS_Admin();
	
	/**
	 * Get instance of class indicate for specific database..
	 * @return instance of class indicate for specific database.
	 */
	public static LMSDAOFactory getInstances() {						
		//default SQL server
		return new LMSSqlDAO();
	}
}
