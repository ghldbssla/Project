package dto;

public class UserDTO {
	private String userid;
	private String userpw;
	private String username;
	private String email;
	private String userphone;
	private String useraddr;
	private String bday;
	private int coupon;
	private int money;

	public UserDTO() {
	}

	public UserDTO(String userid, String userpw) {
		this.userid = userid;
		this.userpw = userpw;
	}

	
	
	public UserDTO(String userid, String userpw, String username, String email, String userphone, String useraddr,
			String bday) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.email = email;
		this.userphone = userphone;
		this.useraddr = useraddr;
		this.bday = bday;
		this.coupon=0;
		this.money=0;
	}

	

	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UserDTO) {
			UserDTO target = (UserDTO) obj;
			if (this.userid.equals(target.userid)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return userid;
	}

	public String getUserid() {
		// TODO Auto-generated method stub
		return null;
	}

}
