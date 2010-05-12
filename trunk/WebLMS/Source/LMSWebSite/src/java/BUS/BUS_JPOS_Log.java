/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BUS;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.FACTORY.LMSDAOFactory;
import DAO.iDAO.IJPOS_Log;
import DTO.DTO_Report;
/**
 *
 * @author NKLapTop
 */
public class BUS_JPOS_Log {
    public static ArrayList<DTO_Report> getReportLog(Connection con){
		LMSDAOFactory factory = LMSDAOFactory.getInstances();
		IJPOS_Log log = factory.getJPOS_Log();
		return log.getReportLog(con);
	}

}
