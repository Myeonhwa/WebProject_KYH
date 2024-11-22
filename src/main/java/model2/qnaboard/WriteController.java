package model2.qnaboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		로그인 확인
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")==null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요. ", "../login/Login.jsp");
			return;
		}
		req.getRequestDispatcher("/board/Write.jsp").forward(req, resp);
}
	
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	로그인 확인
	HttpSession session = req.getSession();
	if(session.getAttribute("UserId")==null) {
		JSFunction.alertLocation(resp, "로그인 후 이용해주세요. ", "../login/Login.jsp");
		return;
	}

//		1. 파일 업로드 처리 ======================================
//		업로드 디렉토리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		System.out.println("물리적경로="+saveDirectory);
//		파일 업로드
		String originalFileName = "";

		try {
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		}
		catch (Exception e) {
			JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "../qnaboard/write.do");
			return;
		}
	
	
//	2. 파일 업로드 외 처리 ========================================
//	폼값을 DTO에 저장
	QNABoardDTO dto = new QNABoardDTO();
	dto.setId(session.getAttribute("UserId").toString());
	dto.setTitle(req.getParameter("title"));
	dto.setContent(req.getParameter("content"));
	
//	원본 파일명과 저장된 파일 이름 설정
	if (originalFileName != "") {
//		파일명 변경
		String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
		
		dto.setOfile(originalFileName);
		dto.setSfile(savedFileName);
	}
	
//	DAO를 통해 DB에 게시 내용 저장
	QNABoardDAO dao = new QNABoardDAO();
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
		resp.sendRedirect("../qnaboard/qnaListPage.do");
	}
	else { // 글쓰기 실패
		JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "../qnaboard/qnawrite.do");
	}
}
}	





















