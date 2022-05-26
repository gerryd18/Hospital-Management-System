package Main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import DatabaseConnection.*;

public class Login extends JFrame implements ActionListener{
	
	
	Connect db = new Connect();
	
	JLabel titleLabel;
	
	//loginForm
	JLabel nameLabel, passwordLabel;
	JTextField nameField;
	JPasswordField passwordField;
	JButton loginBtn, registerBtn;

	public Login() {
		setFrame();
		
		titleLabel = new JLabel("Login");
		titleLabel.setForeground(Color.decode("#00ADB5"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(582,82,128,58);
		getContentPane().add(titleLabel);
		
		
		//form
		nameLabel = new JLabel("Username :");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(422,276,150,30);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameField.setBounds(615,280,238,30);
		getContentPane().add(nameField);
		
		passwordLabel = new JLabel("Password :");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(422,354,150,30);
		getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(615,358,238,30);
		getContentPane().add(passwordField);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginBtn.setBounds(422,489,150,52);
		loginBtn.setBackground(Color.decode("#00ADB5"));
		loginBtn.setForeground(Color.white);
		loginBtn.addActionListener(this);
		getContentPane().add(loginBtn);
		
		registerBtn = new JButton("Register");
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		registerBtn.setBounds(703, 489, 150, 52);
		registerBtn.setBackground(Color.decode("#00ADB5"));
		registerBtn.setForeground(Color.white);
		registerBtn.addActionListener(this);
		getContentPane().add(registerBtn);
		
	}
	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Hospital Management System - Login");
		this.getContentPane().setBackground(Color.decode(("#393E46")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == loginBtn) {
			login();
		}else 
			if(e.getSource() == registerBtn){
				
			this.dispose();
			new Register();
		}
		
	}
	
	public void login() {
		String username = nameField.getText();
		String password = String.valueOf(passwordField.getPassword());
		
		if (emptyValidation(username, password)) {
			
			String id = null;
			String email ="";
			String role = "";
			boolean validUser = false;
			User user=null;
			
			try {
				db.rs = db.userLogin(username, password);
				while(db.rs.next()) {
					id = db.rs.getString("userId");
					email= db.rs.getString("email");
					role = db.rs.getString("role");
					
					user = new User(username, email,password, role);
					validUser = true;
					clear();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if (validUser == true) {
				JOptionPane.showMessageDialog(this, "Hello " + username + " Your id is :" + id);
				this.dispose();
				new Home(user);
			}else {
				JOptionPane.showMessageDialog(this, "user not found!");
			}
		}
	}
	
	public boolean emptyValidation(String username, String password) {
		if (!username.equals("")) {
			if (!password.equals("")) {
				return true;
			}else {
				JOptionPane.showMessageDialog(this, "Please insert password!", "Alert", JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "Please insert username!", "Alert", JOptionPane.INFORMATION_MESSAGE);
		}
		return false;
	}
	
	void clear(){
		nameField.setText("");
		passwordField.setText("");
	}
	
	
	
}
