<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<style>a{text-decoration:none;}</style>
</head>
<body>
    
    <h2>파일 첨부형 게시판 - 목록 보기(List)</h2>

    <!-- 검색 폼 -->
    <form method="get">  
    <table border="1" width="90%">
    <tr>
        <td align="center">
            <select name="searchField">
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="name">작성자</option>
            </select>
            <input type="text" name="searchWord" />
            <input type="submit" value="검색하기" />
        </td>
    </tr>
    </table>
    </form>

    <!-- 목록 테이블 -->
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="*">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
            <th width="8%">첨부</th>
        </tr>
<!-- 게시물이 없을 때 -->
        <tr>
            <td colspan="6" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
<!-- 출력할 게시물이 있을때 -->           
        <tr align="center">
            <td>100</td>
            <td align="left">
                제목
            </td> 
            <td>이름</td>
            <td>99</td>
            <td>작성일</td>
            <td>
                [Down]
            </td>
        </tr>
    </table>
   
    <table border="1" width="90%">
        <tr align="center">
            <td>
                페이지번호출력
            </td>
            <td width="100"><button type="button"
                onclick="location.href='../kyh/write.do';">글쓰기</button></td>
        </tr>
    </table>
</body>
</html>
