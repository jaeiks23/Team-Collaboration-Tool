package DAO;

import java.util.*;
import common.JDBConnPool;
import DTO.MemberDTO;

public class MemberDAO extends JDBConnPool {
	public MemberDAO() {
		super();
	}
	public MemberDTO getMember(String uid, String upw){
		MemberDTO dto = new MemberDTO();
		String query="SELECT * FROM MEM WHERE MEM_ID=? AND MEM_PW=?";
			
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upw);
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("MEM_ID"));
				dto.setPw(rs.getString("MEM_PW"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int createMember(MemberDTO dto) {
		int result = 0;
		String sql = "INSERT INTO MEM(MEM_NAME, MEM_ID, MEM_PW, MEM_EMAIL) VALUES(?,?,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getPw());
			psmt.setString(4, dto.getEmail());
			result = psmt.executeUpdate();
		} catch(Exception e){
			System.out.println("회원가입 오류");
		}
		
		return result;
	}
}































