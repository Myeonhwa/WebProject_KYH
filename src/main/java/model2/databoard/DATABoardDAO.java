package model2.databoard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class DATABoardDAO extends DBConnPool{
	public DATABoardDAO() {
		super();
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM databoard";
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
	
	public List<DATABoardDTO> selectList(Map<String,Object> map) {
		List<DATABoardDTO> board = new Vector<DATABoardDTO>();
		String query = "SELECT * FROM databoard";
		if (map.get("searchWord") != null) {
			query += "WHERE" + map.get("searchField")
			+ " LIKE '%" + map.get("searchWord") + "%' ";
		} 
		query += " ORDER BY idx DESC";
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				DATABoardDTO dto = new DATABoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setVisitcount(rs.getInt(9));
				
				board.add(dto);
			}
		}
		catch (Exception e ) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	public int insertWrite(DATABoardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO databoard( "
					+ " idx, id, title, content, ofile, sfile)"
					+ " VALUES ( "
					+ " seq_board_num.NEXTVAL, ?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2,  dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public DATABoardDTO selectView(String idx) {
		DATABoardDTO dto = new DATABoardDTO();	//DTO 객체 생성
		String query = "SELECT Bo.*, Me.name FROM databoard Bo "
				+ " INNER JOIN member Me On Bo.id=Me.id"
				+ " WHERE idx=?";	//쿼리문 템플릿 준비
		try {
			psmt = con.prepareStatement(query);	//쿼리문 준비
			psmt.setString(1, idx);	// 인파라미터 설정
			rs = psmt.executeQuery();	//쿼리문 실행
			
			if (rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setVisitcount(rs.getInt(9));
				dto.setName(rs.getString(10));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		return dto; //결과 반환
	}
	
	public void updateVisitCount(String idx) {
		String query = "UPDATE databoard SET "
				+	" visitcount=visitcount+1 "
				+	" WHERE idx=?";
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
		String sql = "UPDATE databoard SET "
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
			String query = "DELETE FROM databoard WHERE idx=?";
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
	public int updatePost(DATABoardDTO dto) {
		int result = 0;
		try {
//			쿼리문 템플릿 준비
			String query = "UPDATE databoard"
					+ " SET title=?, content=?, ofile=?, sfile=? "
					+ " WHERE idx=? and id=?";
			
//			쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getOfile());
			psmt.setString(4, dto.getSfile());
			psmt.setString(5, dto.getIdx());
			psmt.setString(6, dto.getId());
			
//			쿼리문 실행
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<DATABoardDTO> selectListPage(Map<String, Object> map) {
		List<DATABoardDTO> board = new Vector<DATABoardDTO>();
		String query = 
				"SELECT * FROM (" 
						+ " SELECT Tb.*, ROWNUM rNum FROM ( "
						+ "		SELECT * FROM databoard ";
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
				DATABoardDTO dto = new DATABoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setVisitcount(rs.getInt(9));
				
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회중 예외 발생");
			e.printStackTrace();
		}
		return board;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
