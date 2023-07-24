package DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import DTO.BoardDTO;
import common.JDBConnPool;

public class BoardDAO extends JDBConnPool {
	DateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	// 페이지별 게시물 읽어오기
	public List<BoardDTO> getListPage(Map<String, Object> map) {
		List<BoardDTO> bl = new Vector<>();
		String sql = "SELECT * FROM (" + "SELECT ROWNUM PNUM, S.* FROM("
				+ "SELECT B.* FROM BOARD B WHERE NOTICEFLAG = ?";
		if (map.get("searchStr") != null) {
			sql += " AND " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%'";
		}
		sql += " ORDER BY B_ID DESC" + ") s" + ")" + " WHERE PNUM BETWEEN ? AND ?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, map.get("noticeFlag").toString());
			psmt.setString(2, map.get("start").toString());
			psmt.setString(3, map.get("end").toString());
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setPnum(rs.getInt("pnum"));
				dto.setB_id(rs.getString("b_id"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setTitle(rs.getString("title"));
				dto.setNoticeFlag(rs.getString("noticeFlag"));
				dto.setContent(rs.getString("content"));
				dto.setBoardDate(sdformat.format(rs.getDate("BoardDate")));
				dto.setOfile(rs.getString("ofile"));
				dto.setNfile(rs.getString("nfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setVisitcount(rs.getInt("visitcount"));
				bl.add(dto);
			}

		} catch (Exception e) {
			System.out.println("게시물을 읽는 중 에러");
		}
		return bl;
	}
	//메인페이지 출력 게시물
	public List<BoardDTO> getListMain(int pro_id) {
		List<BoardDTO> bl = new ArrayList<>();
		String sql = "select rownum pnum, b.* from board b where pro_id =? and noticeflag=1 order by pnum";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pro_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setPnum(rs.getInt("pnum"));
				dto.setB_id(rs.getString("b_id"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setTitle(rs.getString("title"));
				dto.setNoticeFlag(rs.getString("noticeFlag"));
				dto.setContent(rs.getString("content"));
				dto.setBoardDate(sdformat.format(rs.getDate("BoardDate")));
				dto.setOfile(rs.getString("ofile"));
				dto.setNfile(rs.getString("nfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setVisitcount(rs.getInt("visitcount"));
				
				bl.add(dto);
			}

		} catch (Exception e) {
			System.out.println("게시물을 읽는 중 에러");
			e.printStackTrace();
		}
		return bl;
	}
	// 글 추가
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		String sql = "INSERT INTO BOARD(PRO_ID, B_ID, WRITER_ID, TITLE, NOTICEFLAG, CONTENT, BOARDDATE, OFILE, NFILE)"
				+ " VALUES(?, BOARD_NUM.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, ?)";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, dto.getPro_id());
			psmt.setString(2, dto.getWriter_id());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getNoticeFlag());
			psmt.setString(5, dto.getContent());
			psmt.setString(6, dto.getOfile());
			psmt.setString(7, dto.getNfile());
			result = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외");
			e.printStackTrace();
		}
		return result;
	}

	public void updateVisitCount(String b_id) {
		String sql = "UPDATE BOARD SET VISITCOUNT=VISITCOUNT+1" + " WHERE B_ID=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, b_id);
			psmt.executeQuery();
		} catch (Exception e) {
			System.out.println("조회수 증가 에러");
			e.printStackTrace();
		}
	}

	public BoardDTO getView(String b_id) {
		BoardDTO dto = new BoardDTO();
		String sql = "SELECT * FROM BOARD WHERE B_ID=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, b_id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto.setB_id(rs.getString("b_id"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setTitle(rs.getString("title"));
				dto.setNoticeFlag(rs.getString("noticeFlag"));
				dto.setContent(rs.getString("content"));
				dto.setBoardDate(sdformat.format(rs.getDate("BoardDate")));
				dto.setOfile(rs.getString("ofile"));
				dto.setNfile(rs.getString("nfile"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setDowncount(rs.getInt("downcount"));

			}
		} catch (Exception e) {
			System.out.println("상세보기 중 예외");
		}
		return dto;
	}

	// BOARD 테이블 검색 조건까지 고려하여 결과 전체 개수 세기
	public int countAll(Map<String, Object> map) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM BOARD";
		if (map.get("searchStr") != null) {
			sql += " WHERE " + map.get("searchType") + " LIKE '%" + map.get("searchStr") + "%'";
		}

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			totalCount = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("게시물 카운트 중 에러");
			e.printStackTrace();
		}
		return totalCount;
	}

	public int updatePost(BoardDTO dto) {
		int result = 0;
		try {
			String sql = "UPDATE BOARD SET WRITER_ID=?, TITLE=?, CONTENT=?, OFILE=?, NFILE=? WHERE B_ID=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getWriter_id());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			dto.setBoardDate(sdformat.format(rs.getDate("BoardDate")));
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getNfile());
			psmt.setInt(7, dto.getVisitcount());
//			psmt.setInt(8, dto.getDowncount());
			psmt.setString(6, dto.getB_id());
			result = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("게시물 수정 중 에러");
			e.printStackTrace();
		}
		return result;
	}

	public void updateDowncount(String b_id) {
		String sql = "UPDATE BOARD SET DOWNCOUNT=DOWNCOUNT+1 WHERE B_ID=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, b_id);
			psmt.executeQuery();
		} catch (Exception e) {
			System.out.println("다운로드수 증가 에러");
			e.printStackTrace();
		}
	}

	public int getDowncount(String b_id) {
		int dcount = 0;
		String sql = "SELECT DOWNCOUNT FROM BOARD WHERE B_ID=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, b_id);
			rs = psmt.executeQuery();
			rs.next();
			dcount = rs.getInt(1);

		} catch (Exception e) {
			System.out.println("다운로드 수 읽기 중 에러");
			e.printStackTrace();
		}
		return dcount;
	}

	public boolean confirmDelete(String b_id) {
		boolean isRight = false;
		try {
			String sql = "SELECT COUNT(*) FROM BOARD WHERE B_ID=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, b_id);
			rs = psmt.executeQuery();
			rs.next();

			if (rs.getInt(1) == 1) {
				isRight = true;
			}

		} catch (Exception e) {
			isRight = false;
			System.out.println("삭제 검증 중 에러");
			e.printStackTrace();
		}

		return isRight;
	}

	public int deletePost(String b_id) {
		int result = 0;
		try {
			String sql = "DELETE FROM BOARD WHERE B_ID=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, b_id);
			result = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("자유게시판 게시물 삭제 중 에러");
		}
		return result;
	}

	public int deleteNoticePost(String noticeFlag) {
		int result = 0;
		try {
			String sql = "DELETE FROM BOARD WHERE NOTICEFLAG=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, noticeFlag);
			result = psmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("공지게시판 게시물 삭제 중 에러");
		}
		return result;
	}
}
