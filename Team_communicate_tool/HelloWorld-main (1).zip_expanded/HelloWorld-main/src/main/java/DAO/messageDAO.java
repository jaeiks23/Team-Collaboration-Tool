package DAO;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import common.JDBConnPool;
import DTO.messageDTO;

public class messageDAO extends JDBConnPool {
	
	public int sendCountAll(Map<String, Object> map, String id) {
		
		int totalCount=0;
		
		String sql = "select count(*) from message where sendid=? and senddelete='N'";
		if(map.get("searchStr")!=null) {
			sql+=" and "+map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
		}
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			rs.next();
			totalCount=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("쪽지 카운트 중 에러");
			e.printStackTrace();
		}
		
		return totalCount;
		
	}
	
	
	public List<DTO.messageDTO> sendMessageList(DTO.messageDTO dto, Map<String,Object> map) {
		
		List<DTO.messageDTO> bl = new Vector<>();
		
		try {
			String sql = "select * from (select rownum pnum ,a.* from (select m.mem_name, e.mnum, e.receiveId, e.MASSAGEDATE, e.title, e.content "
					+ "from mem m, message e "
					+ "where m.mem_id=e.receiveId "
					+ "and e.sendid=? "
					+ "and e.senddelete='N' ";
			if(map.get("searchStr")!=null) {
				sql += " and "+map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
			}
			sql+=" order by mnum desc)a)b where pnum between ? and ?";
			psmt=con.prepareStatement(sql);
			psmt.setString(1, dto.getMsg_sendId());
			psmt.setString(2, map.get("start").toString());
			psmt.setString(3, map.get("end").toString());
			rs=psmt.executeQuery();
			while(rs.next()) {
				DTO.messageDTO dto1 = new DTO.messageDTO();
				dto1.setMsg_mnum(rs.getInt("mnum"));
				dto1.setMsg_receiveId(rs.getString("receiveId"));
				dto1.setMsg_title(rs.getString("title"));
				dto1.setMsg_content(rs.getString("content"));
				dto1.setMsg_messagedate(rs.getString("MASSAGEDATE"));
				dto1.setMsg_name(rs.getString("mem_name"));
				bl.add(dto1);
			}
		} catch (Exception e) {
			System.out.println("보낸 쪽지함 읽어오는 중 에러");
			e.printStackTrace();
		}
		
		return bl;
	}

	public int SendMessage(DTO.messageDTO dto) {
		int res = 0;
		
		try {
			String sql = "insert into message(mnum,title,content,sendId,receiveId) values(message_num.nextval,?,?,?,?)";
			String[] receivesId = dto.getMsg_receiveId().split(",");
			for(String receiveId : receivesId) {
				psmt=con.prepareStatement(sql);
				psmt.setString(1, dto.getMsg_title());
				psmt.setString(2, dto.getMsg_content());			
				psmt.setString(3, dto.getMsg_sendId());			
				psmt.setString(4, receiveId.trim());			
				res=psmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("쪽지 송신 중 예외 발생");
			e.printStackTrace();
		}
		
		return res;
	}

	public List<DTO.messageDTO> receiveList(DTO.messageDTO dto,Map<String, Object> map) {
		List<DTO.messageDTO> ml = new Vector<>();
		try {
			String sql = "select * from (select rownum pnum ,a.* from (select m.mem_name, e.mnum, e.sendId, e.MASSAGEDATE, e.title, e.content "
					+ "from mem m, message e "
					+ "where m.mem_id=e.sendId "
					+ "and e.receiveid=? "
					+ "and keep='N' "
					+ "and receivedelete='N' ";
			if(map.get("searchStr")!=null) {
				sql += " and "+map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
			}
			sql+=" order by mnum desc)a)b where pnum between ? and ?";
			psmt=con.prepareStatement(sql);
			psmt.setString(1, dto.getMsg_receiveId());
			psmt.setString(2, map.get("start").toString());
			psmt.setString(3, map.get("end").toString());
			rs=psmt.executeQuery();
			while(rs.next()) {
				DTO.messageDTO dto1 = new DTO.messageDTO();
				dto1.setMsg_mnum(rs.getInt("mnum"));
				dto1.setMsg_name(rs.getString("mem_name"));
				dto1.setMsg_sendId(rs.getString("sendId"));
				dto1.setMsg_messagedate(rs.getString("MASSAGEDATE"));
				dto1.setMsg_title(rs.getString("title"));
				dto1.setMsg_content(rs.getString("content"));
				ml.add(dto1);
			}
		} catch (Exception e) {
			System.out.println("받은 편지함 읽어오는 중 예외 발생");
			e.printStackTrace();
		}
		
		return ml;
	}

	public DTO.messageDTO receiveview(DTO.messageDTO dto,String mnum) {
		DTO.messageDTO dto1 = new DTO.messageDTO(); 
		try {
			String sql = "select m.name, e.* "
					+ "from mem m, message e "
					+ "where m.mem_id=e.sendId "
					+ "and e.mnum=? ";
			psmt=con.prepareStatement(sql);
			psmt.setString(1,mnum);
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto1.setMsg_mnum(rs.getInt("mnum"));
				dto1.setMsg_title(rs.getString("title"));
				dto1.setMsg_content(rs.getString("content"));
				dto1.setMsg_sendId(rs.getString("sendId"));
				dto1.setMsg_receiveId(rs.getString("receiveId"));
				dto1.setMsg_messagedate(rs.getString("MASSAGEDATE"));
				dto1.setMsg_name(rs.getString("mem_name"));
			}
		} catch (Exception e) {
			System.out.println("쪽지 뷰를 불러오는 중 생긴 예외");
			e.printStackTrace();
		}
		return dto1;
	}
	
	public DTO.messageDTO sendview(DTO.messageDTO dto,String mnum) {
		DTO.messageDTO dto1 = new DTO.messageDTO(); 
		try {
			String sql = "select m.mem_name, e.* "
					+ "from mem m, message e "
					+ "where m.mem_id=e.receiveId "
					+ "and e.mnum=? ";
			psmt=con.prepareStatement(sql);
			psmt.setString(1,mnum);
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto1.setMsg_mnum(rs.getInt("mnum"));
				dto1.setMsg_title(rs.getString("title"));
				dto1.setMsg_content(rs.getString("content"));
				dto1.setMsg_sendId(rs.getString("sendId"));
				dto1.setMsg_receiveId(rs.getString("receiveId"));
				dto1.setMsg_messagedate(rs.getString("MASSAGEDATE"));
				dto1.setMsg_name(rs.getString("mem_name"));
			}
		} catch (Exception e) {
			System.out.println("쪽지 뷰를 불러오는 중 생긴 예외");
			e.printStackTrace();
		}
		return dto1;
	}

	public int senddeleteMessage(String mnumres) {
		
		int res = 0;
		
		String sql = "update message set senddelete='y' where mnum=?";
		
		try {
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, mnumres);
			res=psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int receivedeleteMessage(String mnumres) {
		
		int res = 0;
		
		String sql = "update message set receivedelete='y' where mnum=?";
		
		try {
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, mnumres);
			res=psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}


	public int receiveCountAll(Map<String, Object> map, String id) {
		int totalCount=0;

		String sql = "select count(*) from message where receiveId=? and keep='N' and receivedelete='N'";
		if(map.get("searchStr")!=null) {
			sql+=" and "+map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
		}
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			rs.next();
			totalCount=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("쪽지 카운트 중 에러");
			e.printStackTrace();
		}
		
		return totalCount;
	}


	public int sendKeepList(String mnum) {
		int res = 0;
		String sql ="update message set keep='y' where mnum=?";
		String[] mnums = mnum.split(",");
		try {
			psmt=con.prepareStatement(sql);
			for(String mnumres : mnums) {
				psmt.setString(1, mnumres);
				res=psmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("쪽지 보관 중 예외 발생");
			e.printStackTrace();
		}
		return res;
	}


	public List<messageDTO> keepListMessage(String id,Map<String, Object> map) {
		List<messageDTO> kl = new Vector<>();
		String sql = "select * from (select rownum pnum ,a.* from (select m.mem_name, e.* "
				+ " from mem m, message e "
				+ "where m.mem_id=e.sendId "
				+ "and e.receiveid=? "
				+ "and keep='y' "
				+ "and receivedelete='N'";
		if(map.get("searchStr")!=null) {
			sql += " and "+map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
		}
		sql+=" order by mnum desc)a)b where pnum between ? and ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2,map.get("start").toString());
			psmt.setString(3,map.get("end").toString());
			rs=psmt.executeQuery();
			while(rs.next()) {
				messageDTO dto = new messageDTO();
				dto.setMsg_mnum(rs.getInt("mnum"));
				dto.setMsg_title(rs.getString("title"));
				dto.setMsg_content(rs.getString("content"));
				dto.setMsg_messagedate(rs.getString("MASSAGEDATE"));
				dto.setMsg_receiveId(rs.getString("receiveId"));
				dto.setMsg_sendId(rs.getString("sendId"));
				dto.setMsg_name(rs.getString("mem_name"));
				kl.add(dto);
			}
		} catch (Exception e) {
			System.out.println("쪽지 보관함 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return kl;
	}


	public int keepCountAll(Map<String, Object> map, String id) {
		
		int totalCount=0;

		String sql = "select count(*) from message where receiveId=? and keep='y' and receivedelete='N'";
		System.out.println(map.get("searchType"));
		System.out.println(map.get("searchStr"));
		
		if(map.get("searchStr")!=null) {
			sql+=" and "+map.get("searchType")+" like '%"+map.get("searchStr")+"%'";
		}

		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, id);
			rs=psmt.executeQuery();
			rs.next();
			totalCount=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("쪽지 카운트 중 에러");
			e.printStackTrace();
		}
		System.out.println(totalCount);
		return totalCount;
	}


	public int cancel(String mnum) {
		int res =0;
		String sql = "update message set keep='N' where mnum=?";
		String[] mnums = mnum.split(",");
		try {
			psmt=con.prepareStatement(sql);
			for(String mnumres:mnums) {
				psmt.setString(1, mnumres);
				res=psmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("보관해제 중 예외발생");
			e.printStackTrace();
		}
		return res;
	}


	

}
