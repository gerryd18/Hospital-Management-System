package Pharmacist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Main.*;
import DatabaseConnection.*;
import javax.swing.JButton;

public class PharmacistHome extends JInternalFrame implements ActionListener{
	User user;
	Connect db = new Connect();
	
	JLabel titleLabel;
	
	
	//right panel
	JPanel rightPanel;
	JLabel infoLabel, nameLabel, emailLabel, roleLabel;
	JButton checkPasswordBtn;
	private JButton checkPrescriptionBtn;
	private JButton createItemBtn;
	

	public PharmacistHome(User user) {
		this.user = user;
		setFrame();
		
		titleLabel = new JLabel("Welcome Pharmacist " + user.getUserName());
		titleLabel.setForeground(Color.decode("#30E3CA"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(382,21,643,58);
		getContentPane().add(titleLabel);
		
		rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setBounds(765,143,365,357);
		rightPanel.setBackground(Color.decode("#393E46"));
		getContentPane().add(rightPanel);
		
		infoLabel = new JLabel("Your Information");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		infoLabel.setBounds(111, 10, 162, 47);
		infoLabel.setForeground(Color.decode("#E4F9F5"));
		rightPanel.add(infoLabel);
		
		nameLabel = new JLabel("Name : " + user.getUserName());
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(35, 97, 304, 26);
		nameLabel.setForeground(Color.decode("#EEEEEE"));
		rightPanel.add(nameLabel);
		
		emailLabel = new JLabel("Email : " + user.getEmail());
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailLabel.setForeground(Color.decode("#EEEEEE"));
		emailLabel.setBounds(35, 147, 304, 26);
		rightPanel.add(emailLabel);
		
		roleLabel = new JLabel("Role : " + user.getRole());
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roleLabel.setForeground(Color.decode("#EEEEEE"));
		roleLabel.setBounds(35, 193, 304, 26);
		rightPanel.add(roleLabel);
		
		checkPasswordBtn = new JButton("Check Password");
		checkPasswordBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		checkPasswordBtn.setBounds(65, 270, 225, 38);
		checkPasswordBtn.addActionListener(this);
		rightPanel.add(checkPasswordBtn);
		
		checkPrescriptionBtn = new JButton("Check Prescription");
		checkPrescriptionBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		checkPrescriptionBtn.setBounds(199, 223, 275, 45);
		checkPrescriptionBtn.addActionListener(this);
		getContentPane().add(checkPrescriptionBtn);
		
		createItemBtn = new JButton("Create Item");
		createItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		createItemBtn.setBounds(199, 362, 275, 45);
		createItemBtn.addActionListener(this);
		getContentPane().add(createItemBtn);
		
	}
	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#222831")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkPasswordBtn) {
			JOptionPane.showMessageDialog(this, "Your Password = " + user.getPassword());
		}else
			if (e.getSource() == checkPrescriptionBtn) {
				new CheckPrescription(user);
		}else
			if (e.getSource() == createItemBtn) {
				this.dispose();
				getParent().add(new CreateItem(user));
		}
	}
}
