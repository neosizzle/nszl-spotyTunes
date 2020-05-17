package jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
import javax.swing.SwingConstants;

import jdbc.MusicPlayer;

public class Home extends JFrame {

	public Settings setting = new Settings(this ,lblBackground);
	private JPanel contentPane;
	public Home frame;
	private String bgDir;
	public File imgFile;//file dir here
	private BufferedImage bgImage = null;
	public static  ImageIcon customBackgroundImg = null;
	private static JLabel lblBackground = new JLabel("");
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static User user;
	private static String musicDir;
	private Thread musicPlayer;
	
	public Action closeFrame = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	        closeFrame();
	        musicPlayer.stop();
	    }
	};
	public Action openSettings = new AbstractAction() {
	    public void actionPerformed(ActionEvent e) {
	    			
	        setting.setVisible(true);
	    }
	};
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					Home frame = new Home();
					
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			musicDir = user.getMusicDir().replace("\\", "\\\\");
			File directory = new File(musicDir);
			File files[] = directory.listFiles();
			for(File f : files) {
				play(f);
			}
		}catch(Exception c) {
			c.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Home(String bgDir,User user){
		super();
		this.bgDir = bgDir;
		this.user = user;
		
		imgFile = new File(bgDir);
		readBufferedImage(imgFile);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(screenSize);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EXIT");
		contentPane.getActionMap().put("EXIT", closeFrame);
		contentPane.getInputMap().put(KeyStroke.getKeyStroke("S"), "openSettings");
		contentPane.getActionMap().put("openSettings", openSettings);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPressEscTo = new JLabel("Press Esc to close");
		lblPressEscTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPressEscTo.setForeground(new Color(0, 153, 255));
		lblPressEscTo.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblPressEscTo.setBounds(1066, 668, 290, 41);
		contentPane.add(lblPressEscTo);
		
		JLabel lblPressSTo= new JLabel("Press S to open settings");
		lblPressSTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPressSTo.setForeground(new Color(0, 153, 255));
		lblPressSTo.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblPressSTo.setBounds(1127, 699, 229, 41);
		contentPane.add(lblPressSTo);
		
		JLabel lblLoggedInAs = new JLabel("Logged In As : " +" "+ this.user.getUser());
		lblLoggedInAs.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoggedInAs.setForeground(new Color(0, 153, 255));
		lblLoggedInAs.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblLoggedInAs.setBounds(10, 11, 444, 30);
		contentPane.add(lblLoggedInAs);
		
		lblBackground.setBounds(169, 31, 1111, 627);
		lblBackground.setIcon(new ImageIcon(bgImage));
		contentPane.add(lblBackground);
		
		musicPlayer = new Thread() {
			public void run() {
				try {
					musicDir = user.getMusicDir().replace("\\", "\\\\");
					File directory = new File(musicDir);
					File files[] = directory.listFiles();
					for(File f : files) {
						play(f);
					}
				}catch(Exception e) {
					
					e.printStackTrace();
				}
				
				
				
			}
		};
		
		musicPlayer.start();
		setVisible(true);
		
		/**try {//player start
			musicDir = user.getMusicDir().replace("\\", "\\\\");
			File directory = new File(musicDir);
			File files[] = directory.listFiles();
			for(File f : files) {
				play(f);
			}
		}catch(Exception c) {
			c.printStackTrace();
		}**/
		

			
	}
	
	public Home() {
		this("D:\\default_home.jpg", null);
	}
	
	

	
	public void closeFrame() {
		this.dispose();
	}

	public void readBufferedImage(File imgFile) {
		 try {
			 bgImage = ImageIO.read(imgFile);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	public void changeBg() {
		System.out.println("hi");
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public static void play(File f) {//play function
		try {
			 FileInputStream fis = new FileInputStream(f.getAbsolutePath());
			    Player playMP3 = new Player(fis);
			    playMP3.play();
			
		}catch(Exception c) {
			c.printStackTrace();
		}
	}
	
	
}
