package DAO;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import DTO.TodoDTO;
import DTO.TodoJoinDTO;
import common.JDBConnPool;

public class TodoDAO extends JDBConnPool {
	DateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	// 일반 할일보기 #화면 : 프로젝트 섹션
	
	public List<TodoJoinDTO> viewTodo() {
		List<TodoJoinDTO> li = new ArrayList();
		String sql = "select * from todo left join mem on todo.part_id=mem.id order by todo_id";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TodoJoinDTO dto = new TodoJoinDTO();
				dto.setSection_Id(rs.getInt("section_id"));
				dto.setTodo_Id(rs.getInt("todo_id"));
				dto.setTodo_Content(rs.getString("todo_content"));
				dto.setPart_Id(rs.getString("part_id"));
				dto.setTodo_Start_Date(sdformat.format(rs.getDate("start_date")));
				dto.setTodo_End_Date(sdformat.format(rs.getDate("end_date")));
				dto.setTodo_info(rs.getString("todo_info"));
				dto.setTodo_Status(rs.getString("todo_status"));
				dto.setTodo_Rank(rs.getString("todo_rank"));
				dto.setPart_Name(rs.getString("name"));
				System.out.println("할일 불러오던 id : " + rs.getInt("todo_id"));
				li.add(dto);
			}
			;

		} catch (Exception e) {
			System.out.println("할일 불러오던 중 에러");
			e.printStackTrace();
		}

		return li;
	}

	// 내 할일 #화면 : 내 할일
	public List<TodoDTO> viewTodo(int pro_id, String todo_id) {
		List<TodoDTO> li = new ArrayList();
		String sql = "select * from todo where pro_id=? and todo_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pro_id);
			psmt.setString(2, todo_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				TodoDTO dto = new TodoDTO();
				dto.setSection_Id(rs.getInt("section_id"));
				dto.setTodo_Id(rs.getInt("todo_id"));
				dto.setTodo_Content(rs.getString("todo_content"));
				dto.setPart_Id(rs.getString("part_id"));
				dto.setTodo_Start_Date(sdformat.format(rs.getDate("start_date")));
				dto.setTodo_End_Date(sdformat.format(rs.getDate("end_date")));
				dto.setTodo_info(rs.getString("todo_info"));
				dto.setTodo_Status(rs.getString("todo_status"));
				dto.setTodo_Rank(rs.getString("todo_rank"));

				li.add(dto);
			}
			;

		} catch (Exception e) {
			System.out.println("내 할일 불러오던 중 에러");
			e.printStackTrace();
		}

		return li;
	}

	// 내 할일 #화면 : 메인페이지
	public List<TodoDTO> viewTodo(int pro_id, String part_id,int todo_id, String status) {
		List<TodoDTO> li = new ArrayList();

		String sql = "select * from todo"
				+ " where pro_id=? and part_id=? and todo_id=? and status=?";
		try {
			TodoDTO dto = new TodoDTO();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pro_id);
			psmt.setString(2, part_id);			
			psmt.setInt(3, todo_id);
			psmt.setString(4, status);
			rs = psmt.executeQuery();
			while (rs.next()) {
				dto.setSection_Id(rs.getInt("section_id"));
				dto.setTodo_Content(rs.getString("todo_content"));
				dto.setTodo_Id(rs.getInt("todo_id"));
				dto.setTodo_End_Date(sdformat.format(rs.getDate("end_date")));
				li.add(dto);
			}
			;

		} catch (Exception e) {
			System.out.println("메인화면에서 할일 불러오던 중 에러");
			e.printStackTrace();
		}

		return li;
	}
	// 할일 상태 확인
	public List<String> statusTodo(int section_id){
		List<String> li = new ArrayList<>();
		String sql = "select todo_status from todo where section_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, section_id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				li.add(rs.getString("todo_status"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return li;
	}
	// 할일 추가
	public int addTodo(TodoDTO dto) {
		int result = 0;
		try {
			String sql = "Insert into todo(section_id,todo_id,todo_content,part_id,";
			if (!dto.getTodo_Start_Date().equals("")) {
				sql += "start_date,end_date,todo_status,todo_rank)"
						+ "values(?,todo_num.nextval,?,?,to_date(?),to_date(?),?,?)";
				System.out.println(1);
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, dto.getSection_Id());
				psmt.setString(2, dto.getTodo_Content());
				psmt.setString(3, dto.getPart_Id());
				psmt.setString(4, dto.getTodo_Start_Date());
				psmt.setString(5, dto.getTodo_End_Date());
				psmt.setString(6, dto.getTodo_Status());
				psmt.setString(7, dto.getTodo_Rank());
			} else {
				System.out.println(2);
				sql += "end_date,todo_status,todo_rank)" + "values(?,todo_num.nextval,?,?,to_date(?),?,?)";
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, dto.getSection_Id());
				psmt.setString(2, dto.getTodo_Content());
				psmt.setString(3, dto.getPart_Id());
				psmt.setString(4, dto.getTodo_End_Date());
				psmt.setString(5, dto.getTodo_Status());
				psmt.setString(6, dto.getTodo_Rank());
			}

			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("할일 추가 중 에러");
			e.printStackTrace();
		}

		return result;
	}
	// 할일 업데이트
	public int UpdateTodo(TodoDTO dto) {
		int result = 0;
		String sql = "update todo set part_id=? "
				+ ", todo_content=? "
				+ ", end_date=to_date(?) "
				+ ", section_id=? "
				+ ", todo_info=? "
				+ ", todo_status=? "
				+ "where todo_id=? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getPart_Id());
			psmt.setString(2, dto.getTodo_Content());
			psmt.setString(3, dto.getTodo_End_Date());
			psmt.setInt(4, dto.getSection_Id());
			psmt.setString(5, dto.getTodo_info());
			psmt.setString(6, dto.getTodo_Status());
			psmt.setInt(7, dto.getTodo_Id());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("할일 수정 중 오류");
			e.printStackTrace();
		}
		
		return result;
	}

	// 할일 삭제
	public int deleteTodo(int todo_id) {
		int result = 0;
		String sql = "delete from todo where todo_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, todo_id);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("세션 삭제 중 에러");
			e.printStackTrace();
		}
		return result;
	}
	//섹션에 해당하는 할일 전부 삭제
	public int deleteTodoAll(int section_id) {
		int result = 0;
		String sql = "delete from todo where section_id=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, section_id);
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("섹션에 해당하는 할 일 전부 삭제 중 에러");
			e.printStackTrace();
		}
		
		return result;
	}
}
