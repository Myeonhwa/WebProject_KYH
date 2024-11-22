package model2.commentboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/commentboard/commentview.do")
public class CommentViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		게시물 불러오기
		COMMENTBoardDAO dao = new COMMENTBoardDAO();
		String idx = req.getParameter("idx");
		dao.updateVisitCount(idx);  //조회수 1 증가
		COMMENTBoardDTO dto = dao.selectView(idx);
		dao.close();
		
//		줄바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		
//		게시물(dto) 저장 후 뷰로 포워드
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/board/QnaView.jsp").forward(req, resp);
	}

}
