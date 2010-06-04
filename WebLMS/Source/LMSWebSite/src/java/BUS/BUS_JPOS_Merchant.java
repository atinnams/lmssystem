package BUS;

import java.sql.Connection;

import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Merchant;
import java.util.ArrayList;

/**
 * 
 * @author HUNGPT
 * Merchant BUS service for Merchant DAO.
 */
public class BUS_JPOS_Merchant {
	
	/**
	 * Check exist merchant.
	 * @param mid Merchant identify.
	 * @param tid Terminal identify.
	 * @param con Connection
	 * @return result of check : 1 pass and 0 fail 
	 */
	public static int checkMerchant(String mid,String tid,Connection con) {
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Merchant merchant = factory.getJPOS_Merchant();
		return merchant.checkMerchant(mid, tid,con);
	}
        public static DTO.DTO_JPOS_Merchant getMerchant(String strMID,Connection conn){
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Merchant merchant = factory.getJPOS_Merchant();
            return merchant.getMerchant(strMID, conn);
        }
        public static boolean addMerchant(DTO.DTO_JPOS_Merchant NewMerchant,Connection conn){
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Merchant merchant = factory.getJPOS_Merchant();
            return merchant.addMerchant(NewMerchant, conn);
        }
        public static boolean deleteMerchant(String strMID,Connection conn){
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Merchant merchant = factory.getJPOS_Merchant();
            return merchant.deleteMerchant(strMID, conn);
        }
        public static boolean updateMerchant(DTO.DTO_JPOS_Merchant NewMerchant,Connection conn){
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Merchant merchant = factory.getJPOS_Merchant();
            return merchant.updateMerchant(NewMerchant, conn);
        }
        public static ArrayList<DTO.DTO_JPOS_Merchant> listMerchant(Connection conn){
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Merchant merchant = factory.getJPOS_Merchant();
            return merchant.listMerchant(conn);
        }
        public static boolean checkMerchantExist(String mid,Connection con){
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Merchant merchant = factory.getJPOS_Merchant();
            return merchant.checkMerchantExist(mid, con);
        }
        public static ArrayList<DTO.DTO_JPOS_Merchant> searchMerchant(String strKey,Connection conn){
            LMSDAOFactory factory = LMSDAOFactory.getInstances();
            IJPOS_Merchant merchant = factory.getJPOS_Merchant();
            return merchant.searchMerchant(strKey, conn);
        }

}
