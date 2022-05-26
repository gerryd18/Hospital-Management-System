package Admin;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Main.*;
import DatabaseConnection.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHome extends JInternalFrame implements ActionListener{
	
	Connect db = new Connect();
	User user;
	
	JLabel titleLabel;
	
	//buttons
	JButton addBtn, manageAccountBtn, checkPasswordBtn;
	
	//right panel
	JPanel rightPanel;
	JLabel rightTitleLabel, idLabel, nameLabel, emailLabel, roleLabel;

	public AdminHome(User user) {
		// TODO Auto-generated constructor stub
		setFrame();
		this.user = user;
		
		
		titleLabel = new JLabel("Welcome Admin " + user.getUserName());
		titleLabel.setForeground(Color.decode("#40514E"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(464,31,475,58);
		getContentPane().add(titleLabel);
		
		addBtn = new JButton("Add Account");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addBtn.addActionListener(this);
		addBtn.setBounds(119, 241, 338, 64);
		getContentPane().add(addBtn);
		
		manageAccountBtn = new JButton("Manage Account");
		manageAccountBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		manageAccountBtn.setBounds(119, 391, 338, 63);
		manageAccountBtn.addActionListener(this);
		getContentPane().add(manageAccountBtn);
		
		
		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBackground(Color.decode("#393E46"));
		rightPanel.setBounds(586,139,458,385);
		getContentPane().add(rightPanel);
		
		//information in right panel
		rightTitleLabel = new JLabel("Your Information");
		rightTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rightTitleLabel.setBounds(165,22,170,57);
		rightTitleLabel.setForeground(Color.decode("#30E3CA"));
		rightPanel.add(rightTitleLabel);
		
		nameLabel = new JLabel("Username : " + user.getUserName());
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setForeground(Color.decode("#00ADB5"));
		nameLabel.setBounds(74,108,334,30);
		rightPanel.add(nameLabel);
		
		emailLabel = new JLabel("Email : " + user.getEmail());
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailLabel.setBounds(74,181,334,30);
		emailLabel.setForeground(Color.decode("#00ADB5"));
		rightPanel.add(emailLabel);
		
		roleLabel = new JLabel("Role : " + user.getRole());
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		roleLabel.setBounds(74,255,334,30);
		roleLabel.setForeground(Color.decode("#00ADB5"));
		rightPanel.add(roleLabel);
		
		checkPasswordBtn = new JButton("Check Password");
		checkPasswordBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkPasswordBtn.setBounds(139, 316, 176, 36);
		checkPasswordBtn.addActionListener(this);
		rightPanel.add(checkPasswordBtn);
		
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
		// TODO Auto-generated method stub
		if (e.getSource() == checkPasswordBtn) {
			JOptionPane.showMessageDialog(this, "Your Password = " + this.user.getPassword());
			
		}else if (e.getSource() == addBtn) {
			this.dispose();
			getParent().add(new AddAccount(user));
			
		}else if (e.getSource() == manageAccountBtn) {
			this.dispose();
			getParent().add(new ManageAccount(user));
		}
	}
}
