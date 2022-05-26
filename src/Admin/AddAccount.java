package Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DatabaseConnection.*;
import Main.*;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class AddAccount extends JInternalFrame implements ActionListener{
	User user;
	Connect db = new Connect();
	
	JLabel titleLabel;
	
	//table
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Vector<String> columnName;
	Vector<Vector<Object>> data;
	
	Vector<String> vrole = new Vector<>();
	
	
	//form
	JLabel nameLabel, emailLabel, passwordLabel, roleLabel;
	private JTextField nameField;
	private JTextField emailField;
	JComboBox<String> roleField;
	private JButton backBtn, addBtn;
	private JPasswordField passwordField;
	
	public AddAccount(User user) {
		setFrame();
		this.user = user;
		
		titleLabel = new JLabel("Add New Account");
		titleLabel.setForeground(Color.decode("#40514E"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(514,41,279,58);
		getContentPane().add(titleLabel);
		
//		initTable();
		
		nameLabel = new JLabel("Username :");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setBounds(412, 186, 211, 32);
		getContentPane().add(nameLabel);
		
		emailLabel = new JLabel("Email :");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailLabel.setBounds(412, 241, 211, 32);
		getContentPane().add(emailLabel);
		
		passwordLabel = new JLabel("Password :");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordLabel.setBounds(412, 293, 211, 32);
		getContentPane().add(passwordLabel);
		
		roleLabel = new JLabel("Role :");
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		roleLabel.setBounds(412, 348, 211, 32);
		getContentPane().add(roleLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameField.setBounds(633, 186, 225, 32);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailField.setColumns(10);
		emailField.setBounds(633, 241, 225, 32);
		getContentPane().add(emailField);
		
		vrole.add("Receptionist");
		vrole.add("Doctor");
		vrole.add("Pharmacist");
		vrole.add("Admin");
		
		roleField = new JComboBox(vrole);
		roleField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		roleField.setBounds(633, 348, 225, 32);
		getContentPane().add(roleField);
		
		addBtn = new JButton("Add Account");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addBtn.addActionListener(this);
		addBtn.setBounds(473, 449, 287, 45);
		getContentPane().add(addBtn);
		
		backBtn = new JButton("Back to Menu");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		backBtn.setBounds(501, 519, 233, 45);
		backBtn.addActionListener(this);
		getContentPane().add(backBtn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(633, 293, 225, 32);
		getContentPane().add(passwordField);
		
	}
	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#E4F9F5")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			addAccount();
		}else if (e.getSource() == backBtn) {
			this.dispose();
			getParent().add(new AdminHome(user));
		}
		
	}
	
	private void addAccount() {
		//get element
		String name = nameField.getText();
		String email = emailField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String role = String.valueOf(roleField.getSelectedItem());
		
		db.rs = db.addUser(name, email, password, role);
		
		JOptionPane.showMessageDialog(this, "Account Successfully Added!");
		
		nameField.setText("");
		emailField.setText("");
		passwordField.setText("");
	}
	
}
