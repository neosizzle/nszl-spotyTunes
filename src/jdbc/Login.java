package jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jdbc.User;
import jdbc.SpotyTunesDAO;




public class Login extends JFrame {

	Settings settings;
	private JPanel contentPane;
	private JTextField tfUsername;
	private final JButton btnLogin = new JButton("Login");
	private final JLabel lblBackground = new JLabel("");
	private Image img = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage();
	private final JLabel SpotyTunes = new JLabel("SpotyTunes");
	private SpotyTunesDAO SpotyTunesDAO;
	private JTextField passwordField;
	public User user;
	
	//creating the DAO

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("SpotyTunes login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			SpotyTunesDAO = new SpotyTunesDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(69, 187, 84, 22);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setForeground(Color.BLACK);
		contentPane.add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(192, 191, 156, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(192, 279, 156, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblPassowrd = new JLabel("Password:");
		lblPassowrd.setBounds(69, 275, 80, 22);
		lblPassowrd.setForeground(Color.BLACK);
		lblPassowrd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblPassowrd);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameEntered = tfUsername.getText();
				String passwordEntered = passwordField.getText();		
				
				
				try {
					boolean loginStatus = SpotyTunesDAO.login(usernameEntered, passwordEntered);
					
					if(loginStatus) {
						User loggedIn = SpotyTunesDAO.selectUser(usernameEntered);
						Home home = new Home("D:\\default_home.jpg", loggedIn);
						closeFrame();
						home.setUser(loggedIn);
						home.setVisible(true);
						
						
						
					}
					else {
						JOptionPane.showMessageDialog(null, "login failed", "you die",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnLogin.setBounds(69, 342, 84, 23);
		contentPane.add(btnLogin);
		
		JButton btnSignUp = new JButton("SignUp");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				closeFrame();
				
				Signup signup = new Signup();
				signup.setVisible(true);
			}
		});
		btnSignUp.setBounds(299, 342, 85, 23);
		contentPane.add(btnSignUp);
		SpotyTunes.setForeground(new Color(0, 153, 255));
		
		SpotyTunes.setBounds(192, 56, 135, 31);
		contentPane.add(SpotyTunes);
		SpotyTunes.setFont(new Font("Yu Gothic UI", SpotyTunes.getFont().getStyle() | Font.BOLD, SpotyTunes.getFont().getSize() + 13));
		
		lblBackground.setBounds(0, 0, 494, 638);
		lblBackground.setIcon(new ImageIcon(img));
		contentPane.add(lblBackground);
		
		
		
	}
	

	
	public void closeFrame(){
		this.dispose();
	}
	
	
}

