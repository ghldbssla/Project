package dto;

public class UserDTO {
	//아이디	비밀번호	이름	나이	핸드폰번호	주소	추천수	거래수
	
	//alt shift a 후 / shift 누른채 방향키로 커서 늘리고 쓰기!
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private String userphone;
	private String useraddr;
	private String userbday;
	private int usermoney;
	
	//alt shift s > r : getter, setter 만들기
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseraddr() {
		return useraddr;
	}

	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}

	public String getUserbday() {
		return userbday;
	}

	public void setUserbday(String userbday) {
		this.userbday = userbday;
	}

	public int getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(int usermoney) {
		this.usermoney = usermoney;
	}

	public UserDTO() {;}

	public UserDTO(String[] datas) {
		this.userid = datas[0];
		this.userpw = datas[1];
		this.username = datas[2];
		this.useremail = datas[3];
		this.userphone = datas[4];
		this.useraddr = datas[5];
		this.userbday = datas[6];
		this.usermoney=Integer.parseInt(datas[7]);
	}
	public UserDTO(String userid, String userpw, String username, String useremail, String userphone, String useraddr,
			String userbday, int usermoney) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.useremail = useremail;
		this.userphone = userphone;
		this.useraddr = useraddr;
		this.userbday = userbday;
		this.usermoney = usermoney;
	}

	public UserDTO(String userid, String userpw, String username, String useremail, String userphone, String useraddr, String userbday) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.useremail = useremail;
		this.userphone = userphone;
		this.useraddr = useraddr;
		this.userbday = userbday;
		this.usermoney=0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserDTO) {
			UserDTO target = (UserDTO)obj;
			if(this.userid.equals(target.userid)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return userid+"\t"+userpw+"\t"+username+"\t"+useremail+"\t"+userphone+"\t"
				+useraddr+"\t"+userbday+"\t"+usermoney;
	}
	
}
