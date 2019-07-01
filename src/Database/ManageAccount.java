package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageAccount {
	private Connection conn;

	public ManageAccount(Connection conn) {
		super();
		this.conn = conn;
	}
	
	
	public boolean login(String email, String password) throws SQLException 
	{
		String sqlStatement = "select count(*) as count, first_name, last_name, email from users where _id in  (select user_id from accounts where username = ? and password = ?);";
		PreparedStatement state = conn.prepareStatement(sqlStatement);
		state.setString(1, email);
		state.setString(2, password);
		ResultSet rs =  state.executeQuery();
		int count = 0;
		if (rs.next()) {
			 count = rs.getInt("count");
			rs.close();
			if (count<1) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
		
	}
	
	
	public int newAccount (String first_name, String last_name, String email, String username, String password) throws SQLException {
		
		
		
		String sqlStatement = "insert into users (first_name, last_name, email) values (?,?,?) ";
		PreparedStatement st1 = conn.prepareStatement(sqlStatement);
		st1.setString(1, first_name);
		st1.setString(2, last_name);
		st1.setString(3, email);
		int rs1 =  st1.executeUpdate();
		if (rs1==1) {
			int _id = getID("users", "email", email);
			if (_id > Integer.MIN_VALUE) {
				String sqlStatement2 = "insert into accounts (username, password, user_id) values (?,?,?) ";
				PreparedStatement st2 = conn.prepareStatement(sqlStatement2);
				st2.setString(1, username);
				st2.setString(2, password);
				st2.setInt(3, _id);
				st2.executeUpdate();
			}
			
		}
		
		return Integer.MIN_VALUE;
	}
	
	
	public int getID(String table_name, String column_name, String value) throws SQLException {
		System.out.println(value);
		String sqlStatement = "select _id from "+table_name+" where "+column_name+" = ?";
		PreparedStatement st1 = conn.prepareStatement(sqlStatement);
		
		st1.setString(1, value);
		ResultSet set = st1.executeQuery();
		if(set.next()) {
			return set.getInt("_id");
		}
		
		return Integer.MIN_VALUE;
	}
	
	public boolean exists (String table_name, String column, String value) throws SQLException {
		
		String sqlStatement = "select count(*) as count from "+table_name+" where "+column+" = ?";
		PreparedStatement state = conn.prepareStatement(sqlStatement);
		
		state.setString(1, value);
		ResultSet rs = state.executeQuery();
		
		int count = 0;
		if (rs.next()) {
			count = rs.getInt("count");
			rs.close();
			if (count<1) {
				return false;
			}
		}
		
		
		return true;
	}
	
	
}
