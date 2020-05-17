package jdbc;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicPlayer {
	String musicDir;
	public MusicPlayer(String dir) {
		this.musicDir = dir;
		
		try {//player start
			File directory = new File(musicDir);
			File files[] = directory.listFiles();
			for(File f : files) {
				play(f);
			}
		}catch(Exception c) {
			c.printStackTrace();
		}
	}
	
	public static void play(File f) {
		try {
			 FileInputStream fis = new FileInputStream(f.getAbsolutePath());
			    Player playMP3 = new Player(fis);
			    playMP3.play();
			
		}catch(Exception c) {
			c.printStackTrace();
		}
	}
}
