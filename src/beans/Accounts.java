package beans;

public class Accounts {

	private int _id;
	private String username=" ";
	private String password=" ";
	private String email=" ";
	private String first_name = "";
	private String last_name = "";
	private String message= "";
	private int number = 0;
	

	public int get_id() {
		return _id;
	}


	public void set_id(int _id) {
		this._id = _id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public Accounts() {
		// TODO Auto-generated constructor stub
	}
	
	public int getNumber() {
		
		return (int) (Math.random()*90+10);
	}
	
	public boolean validate_login () {
		
		if (username == null) {
			message = "invalid username";
			return false;
		}
		if (username.length()<4) {
			message = "invalid username";
			return false;
		}
		if (password == null) {
			message = "invalid password";
			return false;
		}
		if (password.length()<3) {
			message = "invalid password";
			return false;
		}
		
		
		return true;
	}
	
	public boolean validate_create () {
		
		if (username == null) {
			message = "invalid username";
			return false;
		}
		if (username.length()<4) {
			message = "invalid username";
			return false;
		}
		if (password == null) {
			message = "invalid password";
			return false;
		}
		if (password.length()<3) {
			message = "invalid password";
			return false;
		}
		if (first_name == null) {
			message = "invalid firstname";
			return false;
		}
		if (last_name == null) {
			message = "invalid lastname";
			return false;
		}
		if (email == null) {
			message = "invalid email";
			return false;
		}
		if (email.length()<3) {
			message = "invalid email";
			return false;
		}
		
		return true;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	

	
	


	
}
