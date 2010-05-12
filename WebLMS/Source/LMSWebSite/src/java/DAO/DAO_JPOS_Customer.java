package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.*;
import DAO.iDAO.IJPOS_Customer;
import java.util.ArrayList;

/**
 * JPOS Customer Data Access Player
 * @author HUNGPT
 *
 */
public class DAO_JPOS_Customer implements IJPOS_Customer {

	@Override
	public DTO_JPOS_Customer getCustomer(int customerId,Connection con) {
		DTO_JPOS_Customer customer = new DTO_JPOS_Customer();
		String SQL = "select * from JPOS_Customer where JPOS_CustomerID = " +  customerId;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()) {
				customer.setJPOSID_customer(customerId);
				customer.setFirstName(rs.getString("JPOS_FirstName"));
				customer.setLastName(rs.getString("JPOS_LastName"));
				customer.setAddress(rs.getString("JPOS_Address"));
				customer.setEmail(rs.getString("JPOS_Email"));
				customer.setDateJoin(rs.getDate("JPOS_DateJoin"));
				customer.setBirthDay(rs.getDate("JPOS_BirthDay"));
				customer.setGender(rs.getBoolean("JPOS_Gender"));
				customer.setFavorite(rs.getNString("JPOS_Favorite"));
				customer.setJPOS_CurrentPoint(rs.getInt("JPOS_CurrentPoint"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public int subtractPoint(String cardNumber, int taskid, int point,
			String mid, String tid, String poscc,Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_Sub_Point(?,?,?,?,?,?,?)}");
				cstmt.setString("CardId", cardNumber);
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Point", point);
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.registerOutParameter("Result",java.sql.Types.INTEGER );
				cstmt.execute();
				
				result = cstmt.getInt("Result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		} 
		
		return result;
	}

	@Override
	public int addNormalPoint(String cardNumber, int taskid, int point,
			String mid, String tid, String poscc,Connection con) {
		
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_add_n_log_point(?,?,?,?,?,?,?)}");
				cstmt.setString("CardId",cardNumber);
				cstmt.setInt("TaskID", taskid);
				cstmt.setInt("Point", point);
				cstmt.setString("MID", mid);
				cstmt.setString("TID", tid);
				cstmt.setString("PoSCC", poscc);
				cstmt.registerOutParameter("Result",java.sql.Types.INTEGER );
				cstmt.execute();
				
				result = cstmt.getInt("Result");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	@Override
	public int getCurrentPoint(String cardId,Connection con){
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_balance_inquiry(?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, cardId);
				cstmt.execute();
				result = cstmt.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
	
	@Override
	public int checkRedemptionPoint(String cardNumber,int giftType,Connection con){
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_redemption_point(?,?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, cardNumber);
				cstmt.setInt(3, giftType);
				cstmt.execute();
				result = cstmt.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	@Override
	public int redemption(String cardNumber, int taskid, int giftType,
			String mid, String tid, String poscc, Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_redemption(?,?,?,?,?,?,?)}");
				cstmt.setString(1, cardNumber);
				cstmt.setInt(2, taskid);
				cstmt.setInt(3, giftType);
				cstmt.setString(4, mid);
				cstmt.setString(5, tid);
				cstmt.setString(6,poscc);
				cstmt.registerOutParameter(7,java.sql.Types.INTEGER );
				cstmt.execute();
				result = cstmt.getInt(7);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	@Override
	public int addEventPoint(String cardNumber,float money, int logId,Connection con) {
		
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_get_n_log_event_point(?,?,?,?)}");
				cstmt.setString("CardId", cardNumber);
				cstmt.setFloat("money", money);
				cstmt.setInt("LogId", logId);
				cstmt.registerOutParameter(4,java.sql.Types.INTEGER );
				cstmt.execute();
				result = cstmt.getInt(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}
        @Override
        public ArrayList<DTO_JPOS_Customer> Search_Customer(int iCustomerID,String strFirstName,String strLastName,String strAddress,String strEmail,String strDateJoin,String strBirthDay,boolean blGender, String strFavorite,int iCurrentPoint,Connection conn)
        {
            ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Search_Customer(?,?,?,?,?,?,?,?,?,?)}");

                stmt.setInt(1, iCustomerID);
                stmt.setString(2, strFirstName);
                stmt.setString(3, strLastName);
                stmt.setString(4, strAddress);
                stmt.setString(5, strEmail);
                stmt.setString(6, strDateJoin);
                stmt.setString(7, strBirthDay);
                stmt.setBoolean(8, blGender);
                stmt.setString(9, strFavorite);
                stmt.setInt(10, iCurrentPoint);
                
                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();            
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO_JPOS_Customer jposCustomer = new DTO_JPOS_Customer();
                        jposCustomer.setJPOS_CustomerID(rs.getInt("JPOS_CustomerID"));
                        jposCustomer.setFirstName(rs.getString("JPOS_FirstName"));
                        jposCustomer.setLastName(rs.getString("JPOS_LastName"));
                        jposCustomer.setAddress(rs.getString("JPOS_Address"));
                        jposCustomer.setEmail(rs.getString("JPOS_Email"));
                        try {                            
                            jposCustomer.setDateJoin(rs.getDate("JPOS_DateJoin"));
                        } catch (Exception e) {
                            jposCustomer.setDateJoin(null);
                        }
                        try {
                            jposCustomer.setBirthDay(rs.getDate("JPOS_BirthDay"));
                        } catch (Exception e) {
                            jposCustomer.setBirthDay(null);
                        }
                        jposCustomer.setGender(rs.getBoolean("JPOS_Gender"));
                        jposCustomer.setFavorite(rs.getString("JPOS_Favorite"));
                        jposCustomer.setJPOS_CurrentPoint(rs.getInt("JPOS_CurrentPoint"));                        
                        ArrayResult.add(jposCustomer);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                ArrayResult = null;
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                }

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                }

                return ArrayResult;
            }
        }
        
        @Override
        public ArrayList<DTO_Report> Transaction_Detail(int iCustomerID,Connection conn)
        {
            ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Transaction_Detail(?)}");

                stmt.setInt(1, iCustomerID);                

                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO_Report report = new DTO_Report();                                                

                        report.setCardID(rs.getString("JPOS_CardID"));
                        report.setCustomerID(rs.getInt("JPOS_CustomerID"));
                        report.setDate(rs.getDate("JPOS_Date"));
                        report.setGiftName(rs.getString("JPOS_GiftName"));
                        report.setLogID(rs.getInt("JPOS_LogID"));
                        report.setMerchantAddress(rs.getString("JPOS_Address"));
                        report.setMerchantName(rs.getString("JPOS_MerchantName"));
                        report.setPoSCCName(rs.getString("JPOS_PoSCC_Name"));
                        report.setPointGain(rs.getInt("JPOS_PointGain"));
                        report.setPointLoss(rs.getInt("JPOS_PointLoss"));
                        report.setTID(rs.getString("JPOS_TID"));
                        report.setTask(rs.getString("JPOS_TaskName"));
                        
                        ArrayResult.add(report);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                ArrayResult = null;
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                }

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                }

                return ArrayResult;
            }
        }
        @Override
        public DTO_JPOS_Customer GetCustomerInfor(int iCustomerID,Connection conn)
        {
            DTO_JPOS_Customer customer = null;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Customer_Infor(?)}");

                stmt.setInt(1, iCustomerID);

                boolean HasRow = stmt.execute();
                if (HasRow) {
                    customer = new DTO_JPOS_Customer();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {                        

                        customer.setAddress(rs.getString("JPOS_Address"));
                        customer.setBirthDay(rs.getDate("JPOS_BirthDay"));
                        customer.setDateJoin(rs.getDate("JPOS_DateJoin"));
                        customer.setEmail(rs.getString("JPOS_Email"));
                        customer.setFavorite(rs.getString("JPOS_Favorite"));
                        customer.setFirstName(rs.getString("JPOS_FirstName"));
                        customer.setGender(rs.getBoolean("JPOS_Gender"));
                        customer.setLastName(rs.getString("JPOS_LastName"));
                        customer.setJPOS_CustomerID(rs.getInt("JPOS_CustomerID"));
                        customer.setJPOS_CurrentPoint(rs.getInt("JPOS_CurrentPoint"));
                       
                    }
                }
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                customer = null;
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                }

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                }

                return customer;
            }
        }
        @Override
        public ArrayList<DTO_JPOS_Customer> GetCustomerList(Connection conn)
        {
            ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Customer_Report()}");
               

                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO_JPOS_Customer customer = new DTO_JPOS_Customer();

                        customer.setAddress(rs.getString("JPOS_Address"));
                        customer.setBirthDay(rs.getDate("JPOS_BirthDay"));
                        customer.setDateJoin(rs.getDate("JPOS_DateJoin"));
                        customer.setEmail(rs.getString("JPOS_Email"));
                        customer.setFavorite(rs.getString("JPOS_Favorite"));
                        customer.setFirstName(rs.getString("JPOS_FirstName"));
                        customer.setGender(rs.getBoolean("JPOS_Gender"));
                        customer.setLastName(rs.getString("JPOS_LastName"));
                        customer.setJPOS_CustomerID(rs.getInt("JPOS_CustomerID"));
                        customer.setJPOS_CurrentPoint(rs.getInt("JPOS_CurrentPoint"));

                        ArrayResult.add(customer);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                ArrayResult = null;
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                }

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                }

                return ArrayResult;
            }
        }
}
