package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Content;

public class ManageLibrary {
	private Connection conn;

	public ManageLibrary(Connection conn) {
		super();
		this.conn = conn;
	}
	
	
	public ArrayList<Content> retrieveMaster(String content_type, int previous_level, int previous_level_group_id, int next_level, int next_level_group_id) throws SQLException 
	{	
		
		ArrayList<Content> content = new ArrayList<Content>();
		String sqlStatement = "select * from content where content_type = ? ";
		PreparedStatement state = conn.prepareStatement(sqlStatement);
		state.setString(1, content_type);
		ResultSet rs =  state.executeQuery();
		
		
		while (rs.next()) {
			Content c = new Content();
			c.set_id(rs.getInt("_id"));
			c.setPrevious_level_group_id(rs.getInt("previous_level_group_id"));
			c.setPrevious_level(rs.getInt("previous_level"));
			c.setPrevious_level_group_id(rs.getInt("previous_level_group_id"));
			c.setNext_level(rs.getInt("next_level"));
			c.setNext_level_group_id(rs.getInt("next_level_group_id"));
			c.setContent(rs.getString("content"));
			c.setTitle(rs.getString("title"));
			c.setDefinition(rs.getString("definition"));
			c.setContent_type(rs.getString("content_type"));
			c.setDate(rs.getString("date"));
			content.add(c);
			
		} rs.close();
		
		
		return content;
		
	}
	
	public ArrayList<Content> retrieveBranchContent(String previous_level, int previous_level_group_id, int next_level, int next_level_group_id) throws SQLException 
	{	
		
		ArrayList<Content> content = new ArrayList<Content>();
		String sqlStatement = "select * from content where previous_level = ? ";
		PreparedStatement state = conn.prepareStatement(sqlStatement);
		state.setString(1, previous_level);

		ResultSet rs =  state.executeQuery();
		
		
		while (rs.next()) {
			Content c = new Content();
			c.set_id(rs.getInt("_id"));
			c.setPrevious_level_group_id(rs.getInt("previous_level_group_id"));
			c.setPrevious_level(rs.getInt("previous_level"));
			c.setPrevious_level_group_id(rs.getInt("previous_level_group_id"));
			c.setNext_level(rs.getInt("next_level"));
			c.setNext_level_group_id(rs.getInt("next_level_group_id"));
			c.setContent(rs.getString("content"));
			c.setTitle(rs.getString("title"));
			c.setDefinition(rs.getString("definition"));
			c.setContent_type(rs.getString("content_type"));
			c.setDate(rs.getString("date"));
			content.add(c);
			
		} rs.close();
		
		
		return content;
		
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
