package model2.kyh;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;



public class KYHDAO extends DBConnPool {
	public KYHDAO() {
		super();
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM board";
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
	
	public List<KYHDTO> selectList(Map<String,Object> map) {
		List<KYHDTO> board = new Vector<KYHDTO>();
		String query = "SELECT * FROM board";
		if (map.get("searchWord") != null) {
			query += "WHERE" + map.get("searchField")
			+ " LIKE '%" + map.get("searchWord") + "%' ";
		} 
		query += " ORDER BY idx DESC";
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				KYHDTO dto = new KYHDTO();
				
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
	
	public int insertWrite(KYHDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO board ( "
					+ " idx, id, title, content, ofile, sfile)"
					+ " VALUES ("
					+ " seq_board_num.NEXT_VAL,?,?,?,?,?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 등록 중 에러가 발생했습니다.");
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
