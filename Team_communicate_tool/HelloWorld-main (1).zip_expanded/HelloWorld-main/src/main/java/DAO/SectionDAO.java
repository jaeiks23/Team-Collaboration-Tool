package DAO;

import java.util.ArrayList;
import java.util.List;

import DTO.SectionDTO;
import common.JDBConnPool;

public class SectionDAO extends JDBConnPool{
	//섹션 출력
	public List<SectionDTO> getSection(int project_Id) {
		List<SectionDTO> sec_list = new ArrayList();
		String sql = "select * from section where pro_Id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, project_Id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				SectionDTO dto = new SectionDTO();
				dto.setProject_id(rs.getInt("pro_id"));
				dto.setSection_id(rs.getInt("section_id"));
				dto.setSection_name(rs.getString("section_name"));
				dto.setWrite_Id(rs.getString("write_id"));
				dto.setSection_keep(rs.getString("secton_keep"));
				sec_list.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("세션 불러오는 중 에러");
			e.printStackTrace();
		}
		
		return sec_list;
	}
	//섹션 추가
	public int addSection(SectionDTO dto) {
		int result = 0;
		String sql = "Insert into section(pro_id,section_id,section_name,write_id)"
				+ " values(?,section_num.nextval,?,?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1,dto.getProject_id());
			psmt.setString(2, dto.getSection_name());
			psmt.setString(3, dto.getWrite_Id());
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("세션 추가 중 에러");
			e.printStackTrace();
		}
		
		return result;
	}
	//섹션 보관
	public int keepSection(int keep) {
		int result = 0;
		String sql = "update section set section_keep=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1,keep);
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("세션 보관 중 에러");
			e.printStackTrace();
		}
		return result;
	}
	//섹션 삭제
	public int deleteSection(int project_id,int section_id) {
		int result= 0;
		String sql = "delete from section where pro_id=? and section_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1,project_id);
			psmt.setInt(2,section_id);
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("세션 삭제 중 에러");
			e.printStackTrace();
		}
		return result;
	}
	
}
