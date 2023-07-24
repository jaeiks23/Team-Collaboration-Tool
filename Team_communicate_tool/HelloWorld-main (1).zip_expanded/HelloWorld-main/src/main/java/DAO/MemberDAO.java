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
		System.out.println(uid);
		System.out.println(upw);
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
	//내정보 가져오기
	public MemberDTO getMyInfo(String uid){
		MemberDTO dto = new MemberDTO();
		String query="SELECT * FROM MEM WHERE MEM_ID=?";
		System.out.println(uid);
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("MEM_ID"));				
				dto.setName(rs.getString("MEM_name"));
				dto.setEmail(rs.getString("MEM_email"));
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
	
	public MemberDTO FindId(String name, String email) {
		MemberDTO dto = new MemberDTO();
		String sql = "SELECT mem_id FROM mem WHERE mem_name LIKE ? AND mem_email LIKE ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("MEM_ID"));
			}
			
		} catch(Exception e) {
			System.out.println("아이디 찾기 오류");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public List<String> CheckSameID() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT mem_id FROM mem";
		System.out.println(sql);
		try {
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				String str = rs.getString("mem_id");
				System.out.println(str);
				list.add(str);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public MemberDTO FindPass(String name, String id, String email) {
		MemberDTO dto = new MemberDTO();
		String sql = "select mem_pw from mem where mem_name like ? and mem_id like ? and mem_email like ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, id);
			psmt.setString(3, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setPw(rs.getString("MEM_PW"));
			}
			
		} catch(Exception e) {
			System.out.println("비번 찾기 오류");
			e.printStackTrace();
		}
		
		return dto;
	}
	public List<MemberDTO> getMemlist(){
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		String sql = "select * from mem";
		
		 try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				
				
				while(rs.next()) {
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString("MEM_ID"));
					dto.setName(rs.getString("MEM_NAME"));
					dto.setPw(rs.getString("MEM_PW"));
					dto.setEmail(rs.getString("MEM_EMAIL"));
					list.add(dto);
				
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("프로젝트 리스트 불러오는 중 오류");
			}
			return list;
		
	}
	public List<String> getidlist(){
		List<String> list = new ArrayList<String>();
		String sql = "select MEM_ID from mem";
		
		 try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				
				
				while(rs.next()) {
					
					list.add(rs.getString("MEM_ID"));
				
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("프로젝트 리스트 불러오는 중 오류");
			}
			return list;
		
	}
	public List<String> getMemlistexme(String id){
		List<String> list = new ArrayList<String>();
		String sql = "select MEM_ID from mem where mem_id != ?";
		
		 try {
			 psmt = con.prepareStatement(sql);
				psmt.setString(1, id);
				rs=psmt.executeQuery();
				
				while(rs.next()) {
					
					list.add(rs.getString("MEM_ID"));
				
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("프로젝트 리스트 불러오는 중 오류");
			}
			return list;
		
	}
	public int updatePassword(String id, String currentPassword, String newPassword) {
		int result = 0;
		String sql = "UPDATE MEM SET mem_PW = ? WHERE mem_ID = ? AND mem_PW = ?";
		try {
			psmt = con.prepareStatement(sql);
	        psmt.setString(1, newPassword);
	        psmt.setString(2, id);
	        psmt.setString(3, currentPassword);
	        result = psmt.executeUpdate();
			}catch(Exception e) {
				
				e.printStackTrace();
			}
		return result;
	}
}































