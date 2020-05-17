package jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.awt.event.ActionEvent;

public class Settings extends JFrame {
	
	public JFrame parent;
	private JPanel contentPane;
	JFileChooser bgfc;
	private final JLabel lblBackground = new JLabel("");
	private Image img = new ImageIcon(this.getClass().getResource("/login.jpg")).getImage();
	
	String bgDir;
	BufferedImage icon;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
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
	public Settings(JFrame parent, JLabel label) {
		this.parent = parent;
		setTitle("SpotyTunes settings");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnChangeBackgroundPic = new JButton("Change background pic");
		btnChangeBackgroundPic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				bgfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
				bgfc.setFileFilter(filter);
				bgfc.setAcceptAllFileFilterUsed(false);
				
				int result = bgfc.showSaveDialog(bgfc);
				
				
				if(result == bgfc.APPROVE_OPTION) {
					File bg = bgfc.getSelectedFile();
					String preBgDir = bg.getAbsolutePath();
					 bgDir = preBgDir.replace("\\","\\\\");
					 
					 JOptionPane.showMessageDialog(null, "File uploaded successfully", "Message", JOptionPane.INFORMATION_MESSAGE, null);
				}
				else {
					return;
				}
				
				
				
				
				
				
				
			}
		});
		btnChangeBackgroundPic.setBounds(67, 76, 200, 105);
		contentPane.add(btnChangeBackgroundPic);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File imgFile = new File(bgDir);
				 try {
					 icon = ImageIO.read(imgFile);
				 }catch(Exception e1){
					 e1.printStackTrace();
				 }
				label.setIcon(new ImageIcon(icon));
			}
		});
		btnSave.setBounds(67, 410, 89, 23);
		contentPane.add(btnSave);
		
		lblBackground.setBounds(0, 0, 494, 638);
		lblBackground.setIcon(new ImageIcon(img));
		contentPane.add(lblBackground);
		
	}
	
	public Settings() {
		this(null,null);
	}
	

}
