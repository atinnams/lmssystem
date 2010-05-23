/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BUS;
import java.sql.Connection;
import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Status;
import DTO.DTO_JPOS_Status;
import java.util.ArrayList;
/**
 *
 * @author NKLapTop
 */
public class BUS_JPOS_Status {
     public static ArrayList<DTO_JPOS_Status> GetStatus(String strTableName,Connection conn){
            	LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Status myStatus = factory.getJPOS_Status();
		return myStatus.GetListStatus(strTableName, conn);
        }

}
