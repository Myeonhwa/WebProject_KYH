<%@page import="membership.MemberDAO"%>
<%@page import="membership.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    
    MemberDTO member = new MemberDTO();
    member.setId(request.getParameter("id"));
    member.setPass(request.getParameter("pass"));
    member.setName(request.getParameter("name"));
    member.setSex(request.getParameter("sex"));
    member.setPhonenumber(request.getParameter("phonenumber"));
    member.setCategory(request.getParameter("category"));
    member.setEmail(request.getParameter("email"));

    String oracleDriver = application.getInitParameter("OracleDriver");
	String oracleURL = application.getInitParameter("OracleURL");
	String oracleId = application.getInitParameter("OracleId");
	String oraclePwd = application.getInitParameter("OraclePwd");
	
	MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
    MemberDTO newMember = new MemberDTO();
    
    int result = dao.updateMember(member);
    if (result > 0) {
        out.println("회원 정보 수정 성공!");
        // 성공 시 처리 (예: 메인 페이지로 리다이렉트)
    } else {
        out.println("회원 정보 수정 실패!");
        // 실패 시 처리 (예: 에러 메시지 표시)
    }

    // ... (기타 코드) ...
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