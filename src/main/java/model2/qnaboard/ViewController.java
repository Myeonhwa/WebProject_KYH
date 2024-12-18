package model2.qnaboard;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.commentboard.COMMENTBoardDAO;
import model2.commentboard.COMMENTBoardDTO;

@WebServlet("/qnaboard/qnaview.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		게시물 불러오기
		QNABoardDAO dao = new QNABoardDAO();
		String idx = req.getParameter("idx");
		dao.updateVisitCount(idx);  //조회수 1 증가
		QNABoardDTO dto = dao.selectView(idx);
		dao.close();
	
		// 댓글 목록 가져오기
        COMMENTBoardDAO commentDao = new COMMENTBoardDAO();
        List<COMMENTBoardDTO> commentList = commentDao.getCommentsByPostIdx(idx); // 중요: postIdx를 idx로 변경
        commentDao.close();
		
		
//		줄바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		
		
//		게시물(dto) 저장 후 뷰로 포워드
		req.setAttribute("dto", dto);
		req.setAttribute("commentList", commentList); // 댓글 목록 추가
		req.getRequestDispatcher("/board/QnaView.jsp").forward(req, resp);
	}

}
