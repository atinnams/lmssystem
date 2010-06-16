package DAO.FACTORY;

import java.sql.Connection;
import DTO.DTO_JPOS_Admin;
import DAO.iDAO.IJPOS_Admin;
import DAO.iDAO.IJPOS_Card;
import DAO.iDAO.IJPOS_Customer;
import DAO.iDAO.IJPOS_Gift;
import DAO.iDAO.IJPOS_Log;
import DAO.iDAO.IJPOS_Log_Exchange;
import DAO.iDAO.IJPOS_Merchant;
import DAO.iDAO.IJPOS_PoSCC;
import DAO.iDAO.IJPOS_Task;
import DAO.iDAO.IJPOS_Terminal;
import DAO.iDAO.IJPOS_Status;
import DAO.iDAO.IJPOS_Issuer;

import DAO.DAO_JPOS_Card;
import DAO.DAO_JPOS_Customer;
import DAO.DAO_JPOS_Gift;
import DAO.DAO_JPOS_Log;
import DAO.DAO_JPOS_Log_Exchange;
import DAO.DAO_JPOS_Merchant;
import DAO.DAO_JPOS_PoSCC;
import DAO.DAO_JPOS_Task;
import DAO.DAO_JPOS_Admin;
import DAO.DAO_JPOS_Terminal;
import DAO.DAO_JPOS_Status;
import DAO.DAO_JPOS_Issuer;
/**
 * Concrete SQL database object of LMS DAO Factory.
 * @author HUNGPT
 * @see LMSDAOFactory
 */
public class LMSSqlDAO extends LMSDAOFactory {

	@Override
	public IJPOS_Customer getJPOS_Customer() {
		return new DAO_JPOS_Customer();
	}

	@Override
	public IJPOS_Gift getJPOS_Gift() {
		return new DAO_JPOS_Gift();
	}

	@Override
	public IJPOS_Log getJPOS_Log() {
		return new DAO_JPOS_Log();
	}

	@Override
	public IJPOS_Log_Exchange getJPOS_Log_Exchange() {
		return new DAO_JPOS_Log_Exchange();
	}

	@Override
	public IJPOS_Task getJPOS_Task() {
		return new DAO_JPOS_Task();
	}

	@Override
	public IJPOS_Card getJPOS_Card() {		
		return new DAO_JPOS_Card();
	}

	@Override
	public IJPOS_Merchant getJPOS_Merchant() {
		return new DAO_JPOS_Merchant();
	}

	@Override
	public IJPOS_PoSCC getJPOS_PoSCC() {
		return new DAO_JPOS_PoSCC();
	}

        @Override
        public IJPOS_Admin getJPOS_Admin() {
            return new DAO_JPOS_Admin();

        }
        @Override
        public IJPOS_Terminal getJPOS_Terminal() {
            return new DAO_JPOS_Terminal();

        }
        @Override
        public IJPOS_Status getJPOS_Status() {
            return new DAO_JPOS_Status();

        }
        @Override
        public IJPOS_Issuer getJPOS_Issuer() {
            return new DAO_JPOS_Issuer();

        }
}
