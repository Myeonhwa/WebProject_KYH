package model2.databoard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/databoard/dataview.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		게시물 불러오기
		DATABoardDAO dao = new DATABoardDAO();
		String idx = req.getParameter("idx");
		dao.updateVisitCount(idx);  //조회수 1 증가
		DATABoardDTO dto = dao.selectView(idx);
		dao.close();
		
//		줄바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		
//		게시물(dto) 저장 후 뷰로 포워드
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/board/DataView.jsp").forward(req, resp);

		
//		첨부파일 확장자 추출 및 이미지 타입 확인
		String ext = null;
		String fileName = dto.getSfile();
		System.out.println("파일 경로: " + fileName);
		String mimeType = null;
		
		if(fileName!=null) {
			ext = fileName.substring(fileName.lastIndexOf(".")+1);
			System.out.println("첨부파일이 있습니다.");
		} else {
		    System.out.println("첨부파일이 없습니다.");
		    req.setAttribute("mimeType", null);
		    return;
		}
		
		String[] extArray1 = {"png","jpg","gif","pcx","bmp"};
		String[] extArray2 = {"mp3","wav"};
		String[] extArray3 = {"mp4","avi","wmv"};
		
		if(mimeContains(extArray1, ext)) {
			mimeType = "img";
		}
		else if(mimeContains(extArray2, ext)) {
			mimeType = "audio";
		}
		else if(mimeContains(extArray3, ext)) {
			mimeType = "video";
		}
		System.out.println("MIME타입="+mimeType);
		req.setAttribute("mimeType", mimeType);
	}
	
	public boolean mimeContains(String[] strArr, String ext) {
		boolean retValue = false;
		for (String s : strArr) {
			if(s.equalsIgnoreCase(ext))
				retValue = true;
		}
		
		return retValue;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
