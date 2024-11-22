package model2.commentboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

public class CommentWriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		로그인 확인
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")==null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요. ", "../login/Login.jsp");
			return;
		}
		req.getRequestDispatcher("/board/QnaView.jsp").forward(req, resp);
}
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	로그인 확인
	HttpSession session = req.getSession();
	if(session.getAttribute("UserId")==null) {
		JSFunction.alertLocation(resp, "로그인 후 이용해주세요. ", "../login/Login.jsp");
		return;
	}

	
	// 부모 게시글의 IDX 값을 가져옴 (숨겨진 필드나 URL 매개변수에서)
	// "idx"는 HTML 폼에서 전달되는 부모 IDX
//	int parentIdx = Integer.parseInt(req.getParameter("idx")); 
	
//	2. 파일 업로드 외 처리 ========================================
//	폼값을 DTO에 저장
	COMMENTBoardDTO dto = new COMMENTBoardDTO();
	// 부모 테이블의 IDX 값
	dto.setIdx(req.getParameter("idx").toString()); 
	dto.setId(session.getAttribute("UserId").toString());
	dto.setContent(req.getParameter("user_com"));
	String idx = req.getParameter("idx");
	

//	DAO를 통해 DB에 게시 내용 저장
	COMMENTBoardDAO dao = new COMMENTBoardDAO();
	int result = dao.insertWrite(dto);
	
	/*********************************************/
//	더미데이터 100개 입력하기
//	for(int i=1;  i<=100 ; i++) {
//		dto.setTitle(req.getParameter("title")+"-"+i);
//		dao.insertWrite(dto);
//	}
	/*********************************************/
	
	dao.close();
	
//	성공 or 실패?
	if (result ==1) {
		resp.sendRedirect("../qnaboard/qnaListPage.do?idx="+idx);
	}
	else { // 글쓰기 실패
		JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "../qnaboard/qnaListPage.do?idx="+idx);
	}
}
}	





















