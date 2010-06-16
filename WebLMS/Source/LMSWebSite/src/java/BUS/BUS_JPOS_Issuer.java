/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BUS;
import DTO.*;
import DAO.iDAO.*;
import DAO.FACTORY.*;
import java.sql.Connection;


/**
 *
 * @author NKLapTop
 */
public class BUS_JPOS_Issuer {
    public static DTO_JPOS_Issuer getIssuer(Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Issuer myIssuer = factory.getJPOS_Issuer() ;
        return myIssuer.getIssuer(conn);
    }
    public static boolean UpdateIssuer(DTO_JPOS_Issuer issuer, Connection conn)
    {
        LMSDAOFactory factory = LMSDAOFactory.getInstances();
	IJPOS_Issuer myIssuer = factory.getJPOS_Issuer() ;
        return myIssuer.UpdateIssuer(issuer, conn);
    }

}
