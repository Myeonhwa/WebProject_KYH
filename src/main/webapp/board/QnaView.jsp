<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="description" content="Videograph Template">
<meta name="keywords" content="Videograph, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Videograph | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Play:wght@400;700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="../css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="../css/magnific-popup.css" type="text/css">
<link rel="stylesheet" href="../css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="../css/style.css" type="text/css">

<title>Q&A 게시판 상세보기</title>
</head>
<body>
	<h2>Q&A 게시판 - 상세 보기(View)</h2>

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
			<td colspan="3" height="100">${ dto.content } <c:if
					test="${ not empty dto.ofile and isImage eq true }">
					<br>
					<img src="../Uploads/${ dto.sfile }" style="max-width: 100%;" />
				</c:if>
			</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><c:if test="${ not empty dto.ofile }">
					<a
						href="../qnaboard/qnadownload.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&idx=${ dto.idx }">
						[다운로드] </a>
				</c:if></td>
			<td>다운로드수</td>
			<td>${ dto.downcount }</td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<button type="button"
					onclick="location.href='../qnaboard/qnaedit.do?idx=${ param.idx }';">
					수정하기</button>
				<button type="button"
					onclick="location.href='../qnaboard/qnadelete.do?idx=${ param.idx }';">
					삭제하기</button>
				<button type="button"
					onclick="location.href='../qnaboard/qnaListPage.do';">목록
					바로가기</button>
			</td>
		</tr>
		<tr>
		<td>
			<h3>댓글</h3>
		</td>
			<c:if test="${not empty commentList}">
				<ul>
					<c:forEach items="${commentList}" var="comment">
						<li><strong>${comment.id}</strong> (${comment.postdate})<br>
							${comment.content}</li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty commentList}">
				<p>등록된 댓글이 없습니다.</p>
			</c:if>
		</tr>
			<!-- table border="1" width="90%">
			<tr align="center">
				<td>
					${ map.pagingImg }
				</td>
				<td width="100"><button type="button"
						onclick="location.href='../qnaboard/qnawrite.do';">글쓰기</button></td>
			</tr-->
	</table>
	<!-- Blog Details Section Begin -->
	<div class="blog-details spad">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-lg-8">
					<div class="blog__details__content">
						<div class="blog__details__comment">
							<h4>Comment</h4>
							<form name="writeCmt" method="post"
								enctype="application/x-www-form-urlencoded"
								action="../commentboard/commentwrite.do"
								onsubmit="return validateForm(this);">
								<input type="hidden" name="idx" value="${dto2.idx}">
								<textarea name="user_com" placeholder="내용입력"></textarea>
								<button type="submit" class="site-btn">Send Comment</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
