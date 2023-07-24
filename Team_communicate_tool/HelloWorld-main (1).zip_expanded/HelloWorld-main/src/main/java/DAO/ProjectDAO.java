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
import java.util.Vector;

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
			String str = "SELECT PRO_NUM.NEXTVAL FROM DUAL";
			stmt = con.createStatement();
			stmt.executeUpdate(str);	
			
			
			String[] mem_ids = Mem_id.split(",");
			
			for(String mem:mem_ids) {
				String sql = "insert into PROJECT(PRO_ID,PRO_NAME,PRO_HOST,MEM_ID,PRO_END_DATE) values(PRO_NUM.CURRVAL,?,?,?,TO_DATE(?))";
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, host_id);
				psmt.setString(3, mem);
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
		String sql = "delete from PROJECT WHERE PRO_ID = ?";
		 int num = 0;
		 try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, proId);
		
				
				num =  psmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("프로젝트 삭제 중 오류");
			}
			return num;
		 
	}
	public List<ProjectDTO> ProList(String id){
		List<ProjectDTO> list = new ArrayList<ProjectDTO>();
		String sql = "select distinct(p.pro_id) as PS ,p.pro_name from project p where pro_host = ? or mem_id = ?";
		
		 try {
				psmt = con.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, id);
				
				rs=psmt.executeQuery();
				
				while(rs.next()) {
					ProjectDTO dto = new ProjectDTO();
					dto.setPro_id(rs.getInt("PS"));
					dto.setPro_name(rs.getString("pro_name"));
					
					list.add(dto);
					
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
		String sql = "select p.mem_id, m.mem_name from project p inner join mem m on p.mem_id=m.mem_id and pro_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, project_id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setPro_mem_id(rs.getString("mem_id"));
				dto.setPro_member(rs.getString("mem_name"));
				li.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("프로젝트 멤버 불러오던 중 오류");
		}
		return li;
	}
	
	public int deleteMember(String memId,String projectId) {
		int res = 0;
		String sql = "delete from project where mem_id=? and pro_id=?";
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, memId);
			psmt.setString(2, projectId);
			
			res=psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("프로젝트 멤버 제외 중 에러");
			e.printStackTrace();
		}
		return res;
	}

	public List<ProjectDTO> memdelList(int projectId) {
		List<ProjectDTO> memdelList = new Vector<>();
		String sql = "select * from project p, mem m where p.mem_id=m.mem_id and pro_id=?";
		try {
			System.out.println("check");
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, projectId);
			rs=psmt.executeQuery();
			System.out.println("check");
			while(rs.next()) {
				System.out.println("check");
				ProjectDTO dto = new ProjectDTO();
				dto.setPro_id(rs.getInt("pro_id"));
				dto.setPro_name(rs.getString("pro_name"));
				dto.setPro_host(rs.getString("pro_host"));
				dto.setPro_mem_id(rs.getString("mem_id"));
				dto.setMem_Name(rs.getString("mem_name"));
				dto.setPro_end(rs.getString("pro_end"));
				dto.setPro_start_date(rs.getString("pro_start_date"));
				dto.setPro_end_date(rs.getString("pro_end_date"));
				System.out.println(dto.getPro_id());
				System.out.println(dto.getPro_name());
				System.out.println(dto.getPro_host());
				System.out.println(dto.getPro_mem_id());
				System.out.println(dto.getMem_Name());
				System.out.println(dto.getPro_end());
				System.out.println(dto.getPro_start_date());
				System.out.println(dto.getPro_end_date());
				
				memdelList.add(dto);
			}
		} catch (Exception e) {
			System.out.println("프로젝트 멤버 제외 리스트 불러오는 중 에러");
			e.printStackTrace();
		}
		return memdelList;
	}


}
