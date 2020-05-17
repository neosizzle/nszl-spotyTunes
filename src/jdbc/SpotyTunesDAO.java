package jdbc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.User;


public class SpotyTunesDAO {
	String tempUser,tempPass,tempMusicDir;
	
	//credentials
		static String user = "root";
		static String SQLpassword = "password";
		static String dburl = "jdbc:mysql://localhost/main?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		private Connection conn;
	
	public SpotyTunesDAO() throws Exception{
		try {
			//connecting to DB
			conn = DriverManager.getConnection(dburl, user, SQLpassword);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void signUp(User user)throws Exception {
		//preparing statement
		PreparedStatement stmt = null;
		stmt = conn.prepareStatement("INSERT INTO users (username, pass,musicdir) VALUES  (?,?,?) ");
		
		//setting params
		stmt.setString(1,user.getUser());
		stmt.setString(2,user.getPassword());
		stmt.setString(3, user.getMusicDir());
		
		//executing stmt
		stmt.executeUpdate();
		
		//closiing stmt
		stmt.close();
		
	}
	
	public boolean login(String theUsername, String password)throws Exception{
		try {
		//preparing statement
		PreparedStatement stmt = null;
		stmt = conn.prepareStatement("SELECT * FROM users where username = ? AND pass = ?");
		
		//setting params
		stmt.setString(1, theUsername);
		stmt.setString(2, password);
		
		//processing query
		ResultSet rs = stmt.executeQuery();
		System.out.println("query sent");
		
		String tempUsername= "";
		String tempPassword="";
		
		if(rs.next()) {
			rs.close();
			stmt.close();
			return true;
		}
	
		return false;
		
		}catch(Exception e) {
			System.out.println("Username not found");
		}
		
		return false;
		
	
	
	
}
	
	public User selectUser(String username) throws Exception{
		
			
		try {
			//preparing statement
			PreparedStatement stmt = null;
			stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			stmt.setString(1, username);
			
			//executing query
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				 tempUser = rs.getString("username");
				 tempPass = rs.getString("pass");
				 tempMusicDir = rs.getString("musicdir");
			}
	
			
			
		}
		catch(Exception c) {
			c.printStackTrace();
		}
		
		return new User(tempUser,tempPass,tempMusicDir);
	}
	
	
	

}
