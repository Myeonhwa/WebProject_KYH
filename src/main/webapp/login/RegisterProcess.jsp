<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String userId = request.getParameter("user_id");
	String userPwd = request.getParameter("user_pw");
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	String userSex = request.getParameter("user_sex");
	String userPn = request.getParameter("user_ph");
	String userCtg = request.getParameter("user_cate");
	String userEml = request.getParameter("user_em");
	String userNam = request.getParameter("user_na");
	
	String oracleDriver = application.getInitParameter("OracleDriver");
	String oracleURL = application.getInitParameter("OracleURL");
	String oracleId = application.getInitParameter("OracleId");
	String oraclePwd = application.getInitParameter("OraclePwd");
	
	MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
    MemberDTO newMember = new MemberDTO();
    newMember.setId(userId);
    newMember.setPass(userPwd);
    newMember.setName(userNam);
    newMember.setRegidate(sqlDate);
    newMember.setSex(userSex);
    newMember.setPhonenumber(userPn);
    newMember.setCategory(userCtg);
    newMember.setEmail(userEml);

    try {
        int result = dao.insertMember(newMember);
        if (result > 0) {
            out.println("회원가입 성공!");  // 성공 메시지 출력 후 리다이렉트하는 것이 좋습니다.
            response.sendRedirect("Login.jsp"); // 회원가입 후 로그인 페이지로 리다이렉트
        } else {
            out.println("회원가입 실패!"); // 실패 메시지 출력 후 다시 회원가입 페이지로 돌아가는 것이 좋습니다.
            // 실패 원인을 파악하여 에러 메시지를 더 구체적으로 표시해야 합니다.
            response.sendRedirect("register.jsp"); // 실패시 다시 회원가입 페이지로 돌아갑니다.

        }
    } catch (Exception e) {
        out.println("오류: " + e.getMessage());
        e.printStackTrace(); // 에러 로그 출력 (실제 서비스에서는 로그 파일로 처리)
        // 예외 발생시 에러 메시지를 표시하고, Register.jsp 로 이동하도록 수정
        response.sendRedirect("register.jsp");
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