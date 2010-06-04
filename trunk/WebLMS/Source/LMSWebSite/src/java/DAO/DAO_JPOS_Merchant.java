package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import DAO.iDAO.IJPOS_Merchant;
import DTO.DTO_JPOS_Merchant;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO_JPOS_Merchant implements IJPOS_Merchant {
	
	@Override
	public int checkMerchant(String mid, String tid,Connection con) {
		int result = -1;
		try {
			if(con != null) {
				CallableStatement cstmt = null;
				cstmt = (CallableStatement) con
						.prepareCall("{ ? = call dbo.fn_check_merchant_n_terminal(?,?)}");
				cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
				cstmt.setString(2, mid);
				cstmt.setString(3, tid);
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
        public boolean addMerchant(DTO.DTO_JPOS_Merchant merchant,Connection conn)
        {
             CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_New_Merchant(?,?,?)}");
                stmt.setString(1, merchant.getjPOS_MID());
                stmt.setString(2, merchant.getJPOS_MerchantName());
                stmt.setString(3, merchant.getAddress());

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
        public boolean deleteMerchant(String strMID,Connection conn)
        {
           CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Delete_Merchant(?)}");
                stmt.setString(1, strMID);

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
        public boolean updateMerchant(DTO.DTO_JPOS_Merchant merchant,Connection conn)
        {
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Update_Merchant(?,?,?,?)}");
                stmt.setString(1, merchant.getjPOS_MID());
                stmt.setString(2, merchant.getJPOS_MerchantName());
                stmt.setString(3, merchant.getAddress());
                stmt.setInt(4, merchant.getStatusCode());
                
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
        public ArrayList<DTO.DTO_JPOS_Merchant> listMerchant(Connection conn)
        {
            ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Merchant_List()}");


                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO.DTO_JPOS_Merchant merchant = new DTO_JPOS_Merchant();

                        merchant.setJPOS_MerchantName(rs.getString("JPOS_MerchantName"));
                        merchant.setjPOS_MID(rs.getString("JPOS_MID"));
                        merchant.setAddress(rs.getString("JPOS_Address"));
                        merchant.setIssuerCode(rs.getInt("JPOS_IssuerID"));
                        merchant.setIssuerName(rs.getString("JPOS_IssuerName"));
                        merchant.setStatus(rs.getString("JPOS_StatusName"));
                        merchant.setStatusCode(rs.getInt("JPOS_StatusID"));

                        ArrayResult.add(merchant);
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
        public DTO.DTO_JPOS_Merchant getMerchant(String strMID,Connection conn)
        {
            CallableStatement stmt = null;
            DTO_JPOS_Merchant merchant = null ;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Get_Merchant(?)}");
                stmt.setString(1, strMID);

                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        merchant = new DTO_JPOS_Merchant();

                        merchant.setJPOS_MerchantName(rs.getString("JPOS_MerchantName"));
                        merchant.setjPOS_MID(rs.getString("JPOS_MID"));
                        merchant.setAddress(rs.getString("JPOS_Address"));
                        merchant.setIssuerCode(rs.getInt("JPOS_IssuerID"));
                        merchant.setIssuerName(rs.getString("JPOS_IssuerName"));
                        merchant.setStatus(rs.getString("JPOS_StatusName"));
                        merchant.setStatusCode(rs.getInt("JPOS_StatusID"));

                    }
                }
            } catch (Exception e) {
                System.out.println("Error!!!!!!" + e);
                merchant = null;
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

                return merchant;
            }
        }

        @Override
        public boolean checkMerchantExist(String mid,Connection con)
        {
            boolean result = false;
            try {
                if(con != null) {
                        CallableStatement cstmt = null;
                        cstmt = (CallableStatement) con
                                        .prepareCall("{ ? = call dbo.fn_Check_Merchant_exist(?)}");

                        cstmt.registerOutParameter(1,java.sql.Types.INTEGER );
                        cstmt.setString(2, mid);                        
                        cstmt.execute();
                        int iOut = cstmt.getInt(1);
                        if (iOut != 0)
                            result = true;
                }
            } catch (SQLException e) {
                    e.printStackTrace();
                    result = false;
            }

            return result;
        }
        public ArrayList<DTO.DTO_JPOS_Merchant> searchMerchant(String strKey,Connection conn)
        {
            ArrayList ArrayResult = null ;
            CallableStatement stmt = null;
            try {
                stmt = conn.prepareCall("{call dbo.sp_Merchant_Search(?)}");
                stmt.setString(1, strKey);

                boolean HasRow = stmt.execute();
                if (HasRow) {
                    ArrayResult = new ArrayList();
                    ResultSet rs = stmt.getResultSet();
                    while (rs.next()) {
                        DTO.DTO_JPOS_Merchant merchant = new DTO_JPOS_Merchant();

                        merchant.setJPOS_MerchantName(rs.getString("JPOS_MerchantName"));
                        merchant.setjPOS_MID(rs.getString("JPOS_MID"));
                        merchant.setAddress(rs.getString("JPOS_Address"));
                        merchant.setIssuerCode(rs.getInt("JPOS_IssuerID"));
                        merchant.setIssuerName(rs.getString("JPOS_IssuerName"));
                        merchant.setStatus(rs.getString("JPOS_StatusName"));
                        merchant.setStatusCode(rs.getInt("JPOS_StatusID"));

                        ArrayResult.add(merchant);
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
