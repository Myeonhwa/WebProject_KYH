<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실 게시판</title>
</head>
<body>
	<h2>자료실 게시판 - 상세 보기(View)</h2>

	<table border="1" width="90%">
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="*" />
		</colgroup>
		<tr>
			<td>번호</td>
			<td>${ dto.idx }</td>
			<td>작성자</td>
			<td>${ dto.name }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${ dto.postdate }</td>
			<td>조회수</td>
			<td>${ dto.visitcount }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3">${ dto.title }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3" height="100">${ dto.content }</td>
		</tr>
		<tr>
		<!-- mimeType이 안넘어오는거 같음 -->
			<td>파일</td>
			<td colspan="3" height="100">
			 <c:if test="${ not empty dto.ofile }">
				<br />
				<c:choose>
					<c:when test="${ mimeType eq 'img' }">
						<img src="../Uploads/${ dto.sfile }" style="max-width: 600px;" />
					</c:when>
					<c:when test="${ mimeType eq 'audio' }">
						<audio controls="controls">
							<source src="../Uploads/${ dto.sfile }" type="audio/mp3" />
						</audio>
					</c:when>
					<c:when test="${ mimeType eq 'video' }">
						<video controls>
							<source src="../Uploads/${ dto.sfile }" type="video/mp4" />
						</video>
					</c:when>
				</c:choose>
			</c:if>
			</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><c:if test="${ not empty dto.ofile }">
					<a
						href="../databoard/downloadData.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&idx=${ dto.idx }">
						[다운로드] </a>
				</c:if></td>
			<td>다운로드수</td>
			<td>${ dto.downcount }</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<button type="button"
					onclick="location.href='../databoard/editData.do?idx=${ param.idx }';">
					수정하기</button>
				<button type="button"
					onclick="location.href='../databoard/deleteData.do?idx=${ param.idx }';">
					삭제하기</button>
				<button type="button"
					onclick="location.href='../databoard/dataListPage.do';">
					목록 바로가기</button>
			</td>
		</tr>
	</table>

</body>





























</html>
