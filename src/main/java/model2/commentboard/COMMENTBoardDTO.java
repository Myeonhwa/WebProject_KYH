package model2.commentboard;

public class COMMENTBoardDTO {
//	멤버 변수 선언
	
	private String cmx;
	private String idx;
	private String id;
	private String content;
	private java.sql.Date postdate;
	private String name;
	
//	게터/세터
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public String getCmx() {
		return cmx;
	}
	public void setCmx(String cmx) {
		this.cmx = cmx;
	}
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	

	
	

	
}
