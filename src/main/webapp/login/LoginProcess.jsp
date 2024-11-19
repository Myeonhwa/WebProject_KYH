<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@page import="model2.kyh.KYHDTO"%>
<%@page import="model2.kyh.KYHDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String userId = request.getParameter("user_id");
	String userPwd = request.getParameter("user_pw");
	
	String oracleDriver = application.getInitParameter("OracleDriver");
	String oracleURL = application.getInitParameter("OracleURL");
	String oracleId = application.getInitParameter("OracleId");
	String oraclePwd = application.getInitParameter("OraclePwd");
	
	MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
	MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
	
	dao.close();
	/***********************/
	/* 여기서 로그인 실패가 뜨는데 모르겠음*/
	/***********************/
	if (memberDTO.getId() != null) {
		
		session.setAttribute("UserId", memberDTO.getId());
		session.setAttribute("UserName", memberDTO.getName());
		
		response.sendRedirect("index.jsp");
		
	}
	else {
		request.setAttribute("LoginErrMsg", "로그인 실패");
		request.getRequestDispatcher("Login.jsp").forward(request, response);
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