package model2.commentboard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class COMMENTBoardDAO extends DBConnPool{
	public COMMENTBoardDAO() {
		super();
	}
	
	 public List<COMMENTBoardDTO> getCommentsByPostIdx(String postIdx) {
	        List<COMMENTBoardDTO> commentList = new ArrayList<>();
	        String query = "SELECT cmx, idx, id, content, postdate FROM commentboard WHERE idx = ?"; // idx를 기준으로 조회

	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, postIdx);
	            rs = psmt.executeQuery();

	            while (rs.next()) {
	                COMMENTBoardDTO comment = new COMMENTBoardDTO();
	                comment.setCmx(rs.getString(1));
	                comment.setIdx(rs.getString(2));
	                comment.setId(rs.getString(3));
	                comment.setContent(rs.getString(4));
	                comment.setPostdate(rs.getDate(5));
	                commentList.add(comment);
	            }
	        } catch (SQLException e) {
	            System.out.println("댓글 조회 중 예외 발생: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            DBClose(); // 자원 해제
	        }
	        return commentList;
	    }
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM commentboard";
		if (map.get("searchWord") != null ) {
			query += "WHERE" + map.get("searchField") + " "
			+ " LIKE '%" + map.get("searchWord") +"%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	public List<COMMENTBoardDTO> selectList(Map<String,Object> map) {
		List<COMMENTBoardDTO> board = new Vector<COMMENTBoardDTO>();
		String query = "SELECT * FROM commentboard";
		if (map.get("searchWord") != null) {
			query += "WHERE" + map.get("searchField")
			+ " LIKE '%" + map.get("searchWord") + "%' ";
		} 
		query += " ORDER BY idx DESC";
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				COMMENTBoardDTO dto = new COMMENTBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setPostdate(rs.getDate(4));
				
				board.add(dto);
			}
		}
		catch (Exception e ) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	public int insertWrite(COMMENTBoardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO commentboard( "
					+ " cmx, idx, id, content)"
					+ " VALUES ( "
					+ " seq_board_num.NEXTVAL, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getIdx()); // 부모 테이블의 IDX
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getContent());
			System.out.println(query);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public COMMENTBoardDTO selectView(String cmx) {
		COMMENTBoardDTO dto = new COMMENTBoardDTO();	//DTO 객체 생성
		String query = "SELECT Bo.*, Me.name FROM commentboard Bo "
				+ " INNER JOIN member Me On Bo.id=Me.id"
				+ " WHERE cmx=?";	//쿼리문 템플릿 준비
		try {
			psmt = con.prepareStatement(query);	//쿼리문 준비
			psmt.setString(1, cmx);
//			System.out.println(cmx);
//			System.out.println(query);
			rs = psmt.executeQuery();	//쿼리문 실행
			
			if (rs.next()) {
				
				dto.setCmx(rs.getString(1));
				dto.setIdx(rs.getString(2));
				dto.setId(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto; //결과 반환
	}
	
	public void updateVisitCount(String idx) {
		String query = "UPDATE commentboard SET "
				+	" visitcount=visitcount+1 "
				+	" WHERE cmx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외ㅣ 발생");
			e.printStackTrace();
		}
	}
	
	public void downCountPlus(String idx) {
		String sql = "UPDATE commentboard SET "
				+ " downcount=downcount+1 "
				+ " WHERE idx=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		}
		catch (Exception e) {}
	}
	
	public int deletePost(String idx) {
		int result = 0;
		try {
			String query = "DELETE FROM commentboard WHERE idx=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	public int updatePost(COMMENTBoardDTO dto) {
		int result = 0;
		try {
//			쿼리문 템플릿 준비
			String query = "UPDATE commentboard"
					+ " SET content=? "
					+ " WHERE id=?";
			
//			쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getContent());
			psmt.setString(2, dto.getId());
			
//			쿼리문 실행
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<COMMENTBoardDTO> selectListPage(Map<String, Object> map) {
		List<COMMENTBoardDTO> board = new Vector<COMMENTBoardDTO>();
		String query = 
				"SELECT * FROM (" 
						+ " SELECT Tb.*, ROWNUM rNum FROM ( "
						+ "		SELECT * FROM commentboard ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
					+ "		LIKE '%" + map.get("searchWord") + "%'";
		}
		query += "		ORDER BY idx DESC	"
				+ "		) Tb "
				+ "	)"
				+ "WHERE rNum BETWEEN ? AND ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				COMMENTBoardDTO dto = new COMMENTBoardDTO();
				
				dto.setCmx(rs.getString(1));
				dto.setIdx(rs.getString(2));
				dto.setId(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회중 예외 발생");
			e.printStackTrace();
		}
		return board;		
	}
	
	private void DBClose() {
        try {
            if (rs != null) rs.close();
            if (psmt != null) psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
