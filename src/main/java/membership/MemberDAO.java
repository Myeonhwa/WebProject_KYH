package membership;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

/*
DAO(Data Access Object)
: 실제 데이터베이스에 접근하여 기본적인 CRUD 작업을 하기위한 객체이다. 
DB접속 및 select와 같은 쿼리문을 실행한 후 결과를 반환받아 처리하는
기능을 수행한다. 
 */

//JDBC를 위한 클래스를 상속하여 DB에 연결한다. 
public class MemberDAO extends JDBConnect {
	
	//생성자1 : 드라이버, 커넥션URL 등 4개의 매개변수로 정의
    public MemberDAO(String drv, String url, String id, String pw) {
        //super()를 통해 부모클래스의 생성자를 호출한다. 
    	super(drv, url, id, pw);
    }
    
    
    /*
    사용자가 입력한 아이디, 패스워드를 통해 회원테이블을 select한 후
    존재하는 회원정보인 경우 DTO객체에 레코드를 저장한 후 반환한다. 
     */
    public MemberDTO getMemberDTO(String uid, String upass) {
        //회원인증을 위한 쿼리문 실행 후 회원정보를 저장하기 위한 인스턴스 생성
    	MemberDTO dto = new MemberDTO();
    	/*로그인 폼에서 입력한 아이디, 패스워드를 통해 인파라미터를 
    	설정할 수 있도록 쿼리문을 작성 */
    	String query = "SELECT * FROM member WHERE id=? AND "
    			+ " pass=?";
    	
        try {
        	//쿼리문 실행을 위한 prepared 인스턴스 생성 
            psmt = con.prepareStatement(query);
            //쿼리문의 인파라미터 설정(아이디와 비번)
            psmt.setString(1, uid);     
            psmt.setString(2, upass);  
            //쿼리문 실행 및 결과는 ResultSet으로 반환받는다. 
            rs = psmt.executeQuery();
            //반환된 ResultSet객체에 정보가 저장되어 있는지 확인한다. 
            if (rs.next()) {
            	//회원정보가 있다면 DTO 객체에 저장한다. 
                dto.setId(rs.getString("id"));
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString(3));
                dto.setRegidate(rs.getDate(4));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //회원정보를 저장한 DTO객체를 반환한다. 
        return dto;
    }
    
    public int insertMember(MemberDTO member) {
        int result = 0;
        String query = "INSERT INTO member "
        +"(id, pass, name, regidate, sex, phonenumber, category, email) "
        		+"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try  {
        	psmt = con.prepareStatement(query);
        	
            psmt.setString(1, member.getId());
            psmt.setString(2, member.getPass());
            psmt.setString(3, member.getName());
            psmt.setDate(4, member.getRegidate());
            psmt.setString(5, member.getSex());
            psmt.setString(6, member.getPhonenumber());
            psmt.setString(7, member.getCategory());
            psmt.setString(8, member.getEmail());
            
            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int updateMember(MemberDTO member) {
        int result = 0;
        String query = "UPDATE member SET pass = ?, name = ?, sex = ?, phonenumber = ?, category = ?, email = ? WHERE id = ?";

        try  {
        	psmt = con.prepareStatement(query);
        	
            psmt.setString(1, member.getPass());
            psmt.setString(2, member.getName());
            psmt.setString(3, member.getSex());
            psmt.setString(4, member.getPhonenumber());
            psmt.setString(5, member.getCategory());
            psmt.setString(6, member.getEmail());
            psmt.setString(7, member.getId()); // WHERE 절에 ID 사용

            result = psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // 실제 서비스에서는 로깅 시스템 사용
        }
        return result;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      
}