<%@page import="membership.MemberDAO"%>
<%@page import="membership.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    
	String userId = request.getParameter("user_id");
	String userPwd = request.getParameter("user_pw");
	String userName = request.getParameter("user_na");
	String userSex = request.getParameter("user_sex");
	String userPn = request.getParameter("user_ph");
	String userCtg = request.getParameter("user_cate");
	String userEmail = request.getParameter("user_em");
	
	
	String oracleDriver = application.getInitParameter("OracleDriver");
	String oracleURL = application.getInitParameter("OracleURL");
	String oracleId = application.getInitParameter("OracleId");
	String oraclePwd = application.getInitParameter("OraclePwd");
	
	MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
	MemberDTO member = new MemberDTO();
	member.setId(userId);
	member.setPass(userPwd);
	member.setName(userName);
	member.setSex(userSex);
	member.setPhonenumber(userPn);
	member.setCategory(userCtg);
	member.setEmail(userEmail);
	
	
	try {
	    int result = dao.updateMember(member); // 수정된 updateMember 메서드 호출
	    if (result > 0) {
	        out.println("회원 정보 수정 성공!");
	        response.sendRedirect("../index.jsp"); // 수정 후 마이페이지로 이동 (적절한 페이지로 변경)
	    } else {
	        out.println("회원 정보 수정 실패!");
	        response.sendRedirect("UpdateInfo.jsp"); // 에러 메시지와 함께 수정 페이지로 돌아감
	    }
	} catch (Exception e) {
	    out.println("오류: " + e.getMessage());
	    e.printStackTrace(); // 실제 서비스에서는 로그 파일로 처리
	    	
	} finally {
	    dao.close();
	}

    
%>    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>