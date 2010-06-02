package DAO.iDAO;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * 
 * @author HUNGPT
 * Merchant interface stub.
 */
public interface IJPOS_Merchant {
	
	/**
	 * Check merchant.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param con Connection.
	 * @return result.
	 */
	public int checkMerchant(String mid,String tid,Connection con);
        public boolean addMerchant(DTO.DTO_JPOS_Merchant merchant,Connection con);
        public boolean deleteMerchant(String MID,Connection con);
        public boolean updateMerchant(DTO.DTO_JPOS_Merchant merchant,Connection con);
        public ArrayList<DTO.DTO_JPOS_Merchant> listMerchant(Connection con);
        public DTO.DTO_JPOS_Merchant getMerchant(String MID,Connection con);
        public boolean checkMerchantExist(String mid,Connection con);
}
