package model2.kyh;

import common.JDBConnect;

public class KYHDAO extends JDBConnect {

	public KYHDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	public KYHDTO getKYHDTO (String uid, String upass) {
		KYHDTO dto = new KYHDTO();
		String query = "SELECT * FROM userinfo WHERE id=? AND "
				+ " pass=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("pass"));
				dto.setSex(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setPhone(rs.getString(7));
				dto.setCate(rs.getString(8));
				dto.setJoindate(rs.getDate(9));
				dto.setName(rs.getString(10));
			}
			System.out.print("로그인 성공");
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("로그인 실패");
		}
		return dto;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
