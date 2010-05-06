package org.hcmus.dao.factory;

import java.sql.Connection;
import org.hcmus.bus.JPOS_AdminDTO;
import org.hcmus.dao.idao.IJPOS_Admin;
import org.hcmus.dao.idao.IJPOS_Card;
import org.hcmus.dao.idao.IJPOS_Customer;
import org.hcmus.dao.idao.IJPOS_Gift;
import org.hcmus.dao.idao.IJPOS_Log;
import org.hcmus.dao.idao.IJPOS_Log_Exchange;
import org.hcmus.dao.idao.IJPOS_Merchant;
import org.hcmus.dao.idao.IJPOS_PoSCC;
import org.hcmus.dao.idao.IJPOS_Task;
import org.hcmus.dao.lms.JPOS_CardDAO;
import org.hcmus.dao.lms.JPOS_CustomerDAO;
import org.hcmus.dao.lms.JPOS_GiftDAO;
import org.hcmus.dao.lms.JPOS_LogDAO;
import org.hcmus.dao.lms.JPOS_Log_ExchangeDAO;
import org.hcmus.dao.lms.JPOS_MerchantDAO;
import org.hcmus.dao.lms.JPOS_PoSCCDAO;
import org.hcmus.dao.lms.JPOS_TaskDAO;
import org.hcmus.dao.lms.JPOS_AdminDAO;
/**
 * Concrete SQL database object of LMS DAO Factory.
 * @author HUNGPT
 * @see LMSDAOFactory
 */
public class LMSSqlDAO extends LMSDAOFactory {

	@Override
	public IJPOS_Customer getJPOS_Customer() {
		return new JPOS_CustomerDAO();
	}

	@Override
	public IJPOS_Gift getJPOS_Gift() {
		return new JPOS_GiftDAO();
	}

	@Override
	public IJPOS_Log getJPOS_Log() {
		return new JPOS_LogDAO();
	}

	@Override
	public IJPOS_Log_Exchange getJPOS_Log_Exchange() {
		return new JPOS_Log_ExchangeDAO();
	}

	@Override
	public IJPOS_Task getJPOS_Task() {
		return new JPOS_TaskDAO();
	}

	@Override
	public IJPOS_Card getJPOS_Card() {		
		return new JPOS_CardDAO();
	}

	@Override
	public IJPOS_Merchant getJPOS_Merchant() {
		return new JPOS_MerchantDAO();
	}

	@Override
	public IJPOS_PoSCC getJPOS_PoSCC() {
		return new JPOS_PoSCCDAO();
	}

        @Override
        public IJPOS_Admin getJPOS_Admin() {
            return new JPOS_AdminDAO();

        }
}
