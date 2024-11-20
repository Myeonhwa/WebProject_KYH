<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String userId = request.getParameter("user_id");
	String userPwd = request.getParameter("user_pw");
	String userPwd2 = request.getParameter("user_pw2");
	String userSex = request.getParameter("user_sex");
	String userPn = request.getParameter("user_ph");
	String userCtg = request.getParameter("user_cate");
	String userEml = request.getParameter("user_em");
	String userAdd = request.getParameter("user_ad");
	
	String oracleDriver = application.getInitParameter("OracleDriver");
	String oracleURL = application.getInitParameter("OracleURL");
	String oracleId = application.getInitParameter("OracleId");
	String oraclePwd = application.getInitParameter("OraclePwd");
	
	MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
	MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd, userSex, userPn, userCtg, userEml, userAdd);
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