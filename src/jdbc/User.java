package jdbc;

public class User {
	
	private String username;
	private String password;
	private String musicDir;
	private int id;
	
	
	public User(String username, String password, String musicDir) {
		this(0, username,password,musicDir);
	}
	
	
	public User(int id, String username, String password, String musicDir) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.musicDir = musicDir;
	}
	
	public void setUser(String user){
		this.username = user;
	}
	
	public String getUser() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setMusicDir(String path) {
		this.musicDir = path;
	}
	
	public String getMusicDir() {
		return musicDir;
	}
}
