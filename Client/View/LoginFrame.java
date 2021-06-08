package  Client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;


public class LoginFrame extends JFrame {
	

	private JPanel contentPane;
	
	private JTextField tfUsername;
	
	private JButton btnLogin;
	
	private JButton btnExit;
	
	private JPasswordField passwordField;
	
	
	/**
	 * Create the login frame.
	 */
	public LoginFrame() {
		setTitle("Login System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Login");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBackground(new Color(240, 240, 240));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(175, 13, 57, 30);
		contentPane.add(lblName);
		//text field for username is made and added to the frame
		tfUsername = new JTextField();
		tfUsername.setHorizontalAlignment(SwingConstants.LEFT);
		tfUsername.setText("Username");
		tfUsername.setBounds(150, 94, 213, 22);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		//UserName label made and added to the frame
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(56, 96, 107, 19);
		contentPane.add(lblUsername);
		
		//Password label made and added to frame
		JLabel studentID = new JLabel("Password:");
		studentID.setForeground(Color.WHITE);
		studentID.setBounds(56, 138, 82, 16);
		contentPane.add(studentID);
		
		//this is where the password is entered
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 135, 213, 22);
		contentPane.add(passwordField);
		
		//login button is initialized and location set
		btnLogin = new JButton("Login");
		btnLogin.setBounds(94, 204, 97, 25);
		contentPane.add(btnLogin);
		
		//exit button initialized
		btnExit = new JButton("Exit");
		btnExit.setBounds(241, 204, 97, 25);
		contentPane.add(btnExit);
		
		
		setVisible(true);
	}
	
	/**
	 * basic getters for class LoginFrame.java
	 */
	public JPanel getContentPane() {
		return contentPane;
	}
	public JTextField getpasswordField() {
		return passwordField;
	}
	public JTextField getTfUsername() {
		return tfUsername;
	}
	public JButton getBtnLogin() {
		return btnLogin;
	}
	public JButton getBtnExit() {
		return btnExit;
	}
}
