package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DTO.ProjectDTO;
import common.JDBConnPool;

public class ProjectDAO extends JDBConnPool{

	
	public ProjectDAO() {
		super();
	}
	
	public  int CreatePro(String name,String host_id,String Mem_id,String PRO_END_DATE) {
		 int num = 0;
		 String mems;
		 
		
		try {
			if(Mem_id.contains(",")) {
				
			
			String[] Mem_ids = Mem_id.split(",");
			ArrayList<String> mlist = new ArrayList<>(Arrays.asList(Mem_ids));
			
			for(int i =0;i< mlist.size();i++) {
				String sql = "insert into PROJECT(PRO_ID,PRO_NAME,PRO_HOST,MEM_ID,PRO_E_DATE) values(1111,?,?,?,TO_DATE(?,'YYYY-MM-DD'))";
				mems  = mlist.get(i).toString();
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, host_id);
				psmt.setString(3, mems);
				psmt.setString(4, PRO_END_DATE);
				
				num =  psmt.executeUpdate();
				
				}
			}
			else {
				String sql = "insert into PROJECT(PRO_ID,PRO_NAME,PRO_HOST,MEM_ID,PRO_E_DATE) values(1111,?,?,?,TO_DATE(?,'YYYY-MM-DD'))";
	
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, host_id);
				psmt.setString(3, Mem_id);
				psmt.setString(4, PRO_END_DATE);
				
				num =  psmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("프로젝트 생성 중 오류");
		}
		return num;
	}
	public int DeletePro(String proId) {
		String sql = "DROP TABLE PROJECT WHERE PRO_ID = ?";
		 int num = 0;
		 try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, proId);
		
				
				num =  psmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("프로젝트 생성 중 오류");
			}
			return num;
		 
	}
	public List<ProjectDTO> ProList(String id){
		List<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "select PRO_ID, PRO_NAME, PRO_HOST, MEM_ID, PRO_END,TO_CHAR(PRO_S_DATE, 'YYYY/MM/DD') as PRO_S, TO_CHAR(PRO_E_DATE, 'YYYY/MM/DD')as PRO_E from Project  where mem_id = ? OR PRO_HOST = ?";
		
		 try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, id);
				
				rs=psmt.executeQuery();
				
				while(rs.next()) {
					ProjectDTO dto = new ProjectDTO();
					dto.setPro_id(rs.getInt("PRO_ID"));
					dto.setPro_name(rs.getString("PRO_NAME"));
					dto.setPro_host(rs.getString("PRO_HOST"));
					dto.setPro_mem_id(rs.getString("MEM_ID"));
					dto.setPro_end_date(rs.getString("PRO_END"));
					dto.setPro_start_date(rs.getString("PRO_S"));
					dto.setPro_end_date(rs.getString("PRO_E"));
					list.add(dto);
					System.out.println(dto.getPro_start_date());
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("프로젝트 리스트 불러오는 중 오류");
			}
			return list;
		
	}
	public int UpdatePro(String PRO_ID,String Pro_name,String host_id) {
		 int num = 0;
			String sql = "update  PROJECT set PRO_NAME = ? where PRO_ID = ? and PRO_HOST = ?";
			
			try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, Pro_name);
				psmt.setString(2, PRO_ID);
				psmt.setString(3, host_id);
				
				num =  psmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("프로젝트 업데이트 중 오류");
			}
			return num;
	}
	public List<ProjectDTO> getMember(int project_id) {
		List<ProjectDTO> li = new ArrayList();
		String sql = "select mem_id, name from project p inner join mem m on p.mem_id=m.id and pro_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, project_id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPro_mem_id(rs.getString("mem_id"));
				dto.setPro_member(rs.getString("name"));
				li.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("프로젝트 멤버 불러오던 중 오류");
		}
		return li;
	}

}
