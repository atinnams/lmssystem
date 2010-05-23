package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.iDAO.IJPOS_Card;
import java.util.*;
import DTO.*;
import java.sql.ResultSet;


/**
 * 
 * @author HUNGPT
 * Card Data access player.
 */
public class DAO_JPOS_Card implements IJPOS_Card {

	@Override
	public int checkCard(String cardId,Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_card(?)}");
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
	public int checkExpire(String cardId,Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_card_expire(?)}");
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
	public int checkActivatedCard(String cardId, Connection con){
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_activated(?)}");
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
	public void activateCard(String cardId,Connection con){
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{call dbo.sp_card_activate(?)}");
				cstmt.setString("cardid", cardId);
				cstmt.execute();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
	}
        @Override
        public ArrayList<DTO_JPOS_Card> getListCard(Connection conn)
        {
            ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Card_Report()}");


                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO_JPOS_Card card = new DTO_JPOS_Card();

                        card.setJPOS_CardId(rs.getString("JPOS_CardId"));
                        card.setJPOS_ExpireDay(rs.getDate("JPOS_ExpireDay"));
                        card.setStatus(rs.getString("JPOS_StatusName"));
                        card.setCustomerOwnerID(rs.getInt("JPOS_CustomerID"));
                        card.setActiveCode(rs.getString("JPOS_ActivateCode"));

                        ArrayResult.add(card);
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
        public boolean NewCard(DTO_JPOS_Card card,Connection conn)
        {            
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_New_Card(?,?,?)}");
                stmt.setString(1, card.getJPOS_CardId());
                stmt.setDate(2, card.getJPOS_ExpireDay());
                stmt.setString(3, card.getActiveCode());

                stmt.execute();
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                return false;
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
                
            }
            return true;
        }
        @Override
        public boolean DeleteCard(String strCardID,Connection conn)
        {
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Delete_Card(?)}");
                stmt.setString(1, strCardID);

                stmt.execute();
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                return false;
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

            }
            return true;
        }
        @Override
        public DTO_JPOS_Card GetCard(String strCardID,Connection conn)
        {
            CallableStatement stmt = null;
            DTO_JPOS_Card card = null ;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Get_Card(?)}");
                stmt.setString(1, strCardID);

                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {                        
                        card = new DTO_JPOS_Card();
                        card.setJPOS_CardId(rs.getString("JPOS_CardId"));
                        card.setJPOS_ExpireDay(rs.getDate("JPOS_ExpireDay"));
                        card.setStatus(rs.getString("JPOS_StatusName"));
                        card.setCustomerOwnerID(rs.getInt("JPOS_CustomerID"));
                        card.setActiveCode(rs.getString("JPOS_ActivateCode"));
                        card.setStatusCode(rs.getInt("JPOS_Status"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                card = null;
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

                return card;
            }
        }
        @Override
        public boolean UpdateCard(DTO_JPOS_Card card,Connection conn)
        {
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Update_Card(?,?,?,?)}");
                stmt.setString(1, card.getJPOS_CardId());
                stmt.setDate(2, card.getJPOS_ExpireDay());
                stmt.setString(3, card.getActiveCode());
                stmt.setInt(4, card.getStatusCode());

                stmt.execute();
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                return false;
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

            }
            return true;
        }
        @Override
        public boolean AssignCard(String CardID,int CustomerID,Connection conn)
        {
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Assign_Card(?,?)}");
                stmt.setString(1, CardID);
                stmt.setInt(2, CustomerID);                
                stmt.execute();
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                return false;
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

            }
            return true;
        }
        @Override
        public boolean StopAssignCard(String CardID,Connection conn)
        {
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Stop_Assign_Card(?)}");
                stmt.setString(1, CardID);                
                stmt.execute();
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                return false;
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

            }
            return true;
        }
}
