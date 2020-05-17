package jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import jdbc.User;
import jdbc.SpotyTunesDAO;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	private JPanel contentPane;
	private Image bgImg = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage();
	private JTextField tfNewUsername;
	private JTextField tfNewPassword;
	private JTextField tfConfirmPassword;
	private final JLabel lblBackground = new JLabel("");
	private  SpotyTunesDAO  SpotyTunesDAO;
	
	public String musicDir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setTitle("SpotyTunes sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500,667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//creating the DAO
		try {
			SpotyTunesDAO = new SpotyTunesDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		JLabel lblSpotytunes = new JLabel("SpotyTunes");
		lblSpotytunes.setForeground(new Color(51, 153, 255));
		lblSpotytunes.setFont(new Font("Yu Gothic UI", lblSpotytunes.getFont().getStyle() | Font.BOLD, lblSpotytunes.getFont().getSize() + 24));
		lblSpotytunes.setBounds(191, 34, 238, 82);
		contentPane.add(lblSpotytunes);
		
		JLabel lblNewUsername = new JLabel("New Username:");
		lblNewUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewUsername.setBounds(56, 163, 124, 28);
		contentPane.add(lblNewUsername);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewPassword.setBounds(56, 243, 124, 28);
		contentPane.add(lblNewPassword);
		
		tfNewUsername = new JTextField();
		tfNewUsername.setBounds(191, 170, 184, 20);
		contentPane.add(tfNewUsername);
		tfNewUsername.setColumns(10);
		
		tfNewPassword = new JTextField();
		tfNewPassword.setBounds(191, 250, 184, 20);
		contentPane.add(tfNewPassword);
		tfNewPassword.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmPassword.setBounds(56, 326, 163, 20);
		contentPane.add(lblConfirmPassword);
		
		tfConfirmPassword = new JTextField();
		tfConfirmPassword.setBounds(216, 329, 159, 20);
		contentPane.add(tfConfirmPassword);
		tfConfirmPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if(tfNewPassword.getText().equals(tfConfirmPassword.getText()) == false) {
				JOptionPane.showMessageDialog(null, "Passwords do not match", "Message",JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			User tempUser = new User(tfNewUsername.getText(), tfNewPassword.getText(),musicDir);
			try {
				SpotyTunesDAO.signUp(tempUser);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
			
			
			JOptionPane.showMessageDialog(null, "Signed up successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
			closeFrame();
			
			Login login = new Login();
			login.setVisible(true);
			
				
			}
		});
		btnNewButton.setBounds(56, 581, 89, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				closeFrame();
				
				Login login = new Login();
				login.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(247, 581, 109, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnMusicDir = new JButton("Select music folder");
		btnMusicDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				 JFileChooser musicFc = new JFileChooser();
			        musicFc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
			        musicFc.showSaveDialog(null);
			        musicFc.setAcceptAllFileFilterUsed(false);

			        musicDir = musicFc.getSelectedFile().toString();
			        JOptionPane.showMessageDialog(null, "Folder updated successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
			        
				}
				catch(Exception c) {
					JOptionPane.showMessageDialog(null, "Please select a folder", "Message",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
		btnMusicDir.setBounds(81, 407, 275, 23);
		contentPane.add(btnMusicDir);
		
		lblBackground.setBounds(0, 0, 494, 638);
		lblBackground.setIcon(new ImageIcon(bgImg));
		contentPane.add(lblBackground);
		
		
		
	
	}
	
public void closeFrame() {
	this.dispose();
}




}
