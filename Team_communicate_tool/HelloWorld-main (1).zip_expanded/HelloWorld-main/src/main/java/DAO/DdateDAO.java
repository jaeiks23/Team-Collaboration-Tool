package DAO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import DTO.DdateDTO;
import common.JDBConnPool;

public class DdateDAO extends JDBConnPool {
 
  
public int updateDdate(int pro_id,String edate) {

	String sql = "update PROJECT set PRO_END_DATE= to_date(?,'yyyy-MM-dd') where PRO_ID = ?";
	int result = 0;
	System.out.println(pro_id);
	System.out.println(edate);
	    
	try {
		psmt = con.prepareStatement(sql); 
	    psmt.setString(1, edate);
	    psmt.setInt(2, pro_id);
	    	
	    result = psmt.executeUpdate();
	    
	    } catch (SQLException e) {
	    	System.out.println("업데이트하는 동중 오류");
	    	e.printStackTrace();
	    }
	    return result;
  }

  public DdateDTO getDdate(int pro_id) {
	DateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
    String sql = "select TO_CHAR(PRO_END_DATE, 'YYYY-MM-DD') as e_date from PROJECT where PRO_ID = ?";

    DdateDTO dto = new DdateDTO();

    try {
    	psmt = con.prepareStatement(sql); 
    	psmt.setInt(1, pro_id);
    	rs = psmt.executeQuery();
      rs.next();
      dto.setPro_e_date(rs.getString("e_date"));
      
      rs.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
   
    return dto;
  }
}