package beans;

public class Users {

	private int _id;
	private String name=" ";
	private String email=" ";
	private String message;
	
	
	


	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean validate () {
		message = "";
		if (name == null) {
			message = "Name property is not set.";
			return false;
		}
		if (email == null) {
			message = "Email property is not set.";
			return false;
		}
		if (name.length()<4) {
			message = "Name property must consist of atleast 5 characters. Current: "+name.length();
			return false;
		}
		if (!email.matches("\\w+@\\w+\\.\\w+")) {
			message += "Email must be fucking valid!";
			return false;
		}
		message = "valid";
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (_id != other._id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public Users(int _id, String name) {
		super();
		this._id = _id;
		this.name = name;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "data: "+get_id()+" name: "+getName();
	}
}
