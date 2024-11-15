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
	
	KYHDAO dao = new KYHDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
	KYHDTO kyhDTO = dao.getKYHDTO(userId, userPwd);
	
	dao.close();
	
	if (kyhDTO.getId() != null) {
		
		session.setAttribute("UserId", kyhDTO.getId());
		session.setAttribute("UserName", kyhDTO.getName());
		
		response.sendRedirect("index.jsp");
		request.setAttribute("LoginErrMsg", "로그인성공")
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