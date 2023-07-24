package DAO;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import DTO.BoardDTO;
import DTO.CommentDTO;
import common.JDBConnPool;

public class CommentDAO extends JDBConnPool {

	
	public int insertComment(CommentDTO dto) {
		int result = 0;
		String sql = "INSERT INTO BCOMMENT (COM_ID, B_ID, WRITER_ID, COM_CONTENT) VALUES (SEQ_COMMENT_NUM.NEXTVAL, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getB_id());
			psmt.setString(2, dto.getWriter_id());
			psmt.setString(3, dto.getCom_content());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 등록 중 에러");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<CommentDTO> getComment(Map<String, Object> map) {
		List<CommentDTO> cl = new Vector<>(); 
		String sql = "SELECT * FROM (" + "SELECT ROWNUM PNUM, S.* FROM("
				+ "SELECT B.* FROM BCOMMENT B WHERE B_ID = ?";
		sql += " ORDER BY COM_ID DESC" + ") s" + ")" + " WHERE PNUM BETWEEN ? AND ?";
		System.out.println(sql);
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, map.get("b_id").toString());
			psmt.setString(2, map.get("start").toString());
			psmt.setString(3, map.get("end").toString());
			rs = psmt.executeQuery();
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCom_id(rs.getString("com_id"));
				dto.setB_id(rs.getString("b_id"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setCom_content(rs.getString("com_content"));
				dto.setCom_date(rs.getDate("com_date"));
				cl.add(dto);
			}
		} catch (Exception e) {
			System.out.println("댓글 읽는 중 에러");
			e.printStackTrace();
		}
		return cl;
	}
	
	public int deleteComment(String com_id) {
		int result = 0;
		String sql = "DELETE FROM BCOMMENT WHERE COM_ID = ? ";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, com_id);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("댓글 삭제 중 에러");
			e.printStackTrace();
		}
		return result;
	}
	
	public int countAll(String b_id) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM BCOMMENT WHERE B_ID = ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, b_id);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("댓글 카운트 중 에러");
			e.printStackTrace();
		}
		return totalCount;
	}
}
