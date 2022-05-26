package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import DatabaseConnection.*;
public class Register extends JFrame implements ActionListener{
	
	Connect db = new Connect();
	
	JLabel titleLabel;
	
	//form
	JLabel nameLabel, emailLabel, passwordLabel, roleLabel;
	JTextField nameField, emailField;
	JPasswordField passwordField;
	JComboBox<String> roleField;
	Vector<String> vrole = new Vector<>();
	JButton registerBtn, loginBtn;
	
	

	public Register() {
		setFrame();
		
		titleLabel = new JLabel("Register");
		titleLabel.setForeground(Color.decode("#00ADB5"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(582,82,128,58);
		getContentPane().add(titleLabel);
		
		
		//name
		nameLabel = new JLabel("Username :");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(404,212,150,30);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setBounds(588,216,238,30);
		getContentPane().add(nameField);
		
		
		//email
		emailLabel = new JLabel("Email :");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailLabel.setBounds(404,272,128,58);
		getContentPane().add(emailLabel);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailField.setBounds(588,290,238,30);
		getContentPane().add(emailField);
		
		//password
		passwordLabel = new JLabel("Password :");
		passwordLabel.setBounds(404,359,150,30);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(588,359,238, 30);
		getContentPane().add(passwordField);
		
		//role
		vrole.add("Receptionist");
		vrole.add("Doctor");
		vrole.add("Pharmacist");
		vrole.add("Admin");
		
		roleLabel = new JLabel("Role :");
		roleLabel.setBounds(404,432,150,30);
		roleLabel.setForeground(Color.WHITE);
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(roleLabel);
		
		roleField = new JComboBox<String>(vrole);
		roleField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roleField.setBounds(588,432,238, 30);
		getContentPane().add(roleField);
		
		
		//buttons
		registerBtn = new JButton("Register");
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		registerBtn.setBounds(526, 513, 244, 44);
		registerBtn.setBackground(Color.decode("#00ADB5"));
		registerBtn.setForeground(Color.white);
		registerBtn.addActionListener(this);
		getContentPane().add(registerBtn);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		loginBtn.setBounds(561,599,168,48);
		loginBtn.setBackground(Color.decode("#00ADB5"));
		loginBtn.setForeground(Color.white);
		loginBtn.addActionListener(this);
		getContentPane().add(loginBtn);
		
	}
	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setLocationRelativeTo(null);
		this.setTitle("Hospital Management System - Register");
		this.getContentPane().setBackground(Color.decode(("#393E46")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == loginBtn) {
			this.dispose();
			new Login();
		}else 
			if (e.getSource() == registerBtn) {
			register();
		}
	}
	
	public void register() {
		String username = nameField.getText();
		String email = emailField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String role =  String.valueOf(roleField.getSelectedItem());
		
		if (emptyValidation(username, email, password, role)) {
			if (emailValidation(email)) {
				db.addUser(username, email, password, role);
				JOptionPane.showMessageDialog(this, "New " +  role + " Created!");				
				clear();
				
				this.dispose();
				new Login();
			}
		}
	}
	
	public boolean emailValidation(String email) {
		
		if (email.contains("@")) {
			if (email.contains(".")) {
				if (email.indexOf("@") - email.indexOf(".") < 1) {
					if (!(email.endsWith("@") || email.endsWith("."))) {
						return true;
					}else {
						JOptionPane.showMessageDialog(this, "email must not ends with '@' or '.' ");
					}
				}else {
					JOptionPane.showMessageDialog(this, "'@' must before '.'");
				}
			}else {
				JOptionPane.showMessageDialog(this, "email must contains one '.'");
			}
		}else {
			JOptionPane.showMessageDialog(this, "email must contains one '@'");
		}
		
		return false;
	}
	
	
	public boolean emptyValidation(String username, String email, String password, String role) {
		
		if (!username.equals("")) {
			if (!email.equals("")) {
				if (!password.equals("")) {
					return true;
				}else {
					JOptionPane.showMessageDialog(this, "please input password!", "Alert", JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "please input email!", "Alert", JOptionPane.INFORMATION_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(this, "please input username!", "Alert", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return false;
	}
	
	void clear() {
		nameField.setText("");
		passwordField.setText("");
		emailField.setText("");
	}
	
}
