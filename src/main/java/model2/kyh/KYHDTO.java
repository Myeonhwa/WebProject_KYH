package model2.kyh;

public class KYHDTO {
//	멤버변수 선언
	private String idx;
	private String id;
	private String password;
	private String sex;
	private String email;
	private String address;
	private String phone;
	private String cate;
	private java.sql.Date join_date;
	
	
//	게터세터
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public java.sql.Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(java.sql.Date join_date) {
		this.join_date = join_date;
	}
	
	
}
