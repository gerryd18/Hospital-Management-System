package Main;

import java.awt.Color;

import Doctor.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DatabaseConnection.*;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import Admin.*;
import Receptionist.*;
import Pharmacist.*;

public class Home extends JFrame {
	
	Connect db = new Connect();
	User user;
	
	JLabel titleLabel;
	
	JMenu accountMenu;
	JMenuBar menubar;
	JMenuItem logoutMenuItem;
	
//	internalFrame
	JInternalFrame adminHome,receptionistHome, pharmacistHome, doctorHome;

	public Home(User user) {
		this.user = user;
		
		setFrame();
		
		if (user.getRole().equalsIgnoreCase("admin")) {
			adminHome = new AdminHome(user);
			adminHome.setVisible(true);
			this.add(adminHome);
		}else 
			if(user.getRole().equalsIgnoreCase("doctor")){
				doctorHome = new DoctorHome(user);
				this.add(doctorHome);
			
		}else
			if (user.getRole().equalsIgnoreCase("receptionist")) {
				receptionistHome = new ReceptionistHome(user);
				receptionistHome.setVisible(true);
				this.add(receptionistHome);
		}else
			if (user.getRole().equalsIgnoreCase("pharmacist")) {
				pharmacistHome = new PharmacistHome(user);
				this.add(pharmacistHome);
		}
		
		
	}

	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Hospital Management System - Home");
		this.getContentPane().setBackground(Color.decode(("#393E46")));
		setMenu();
	}
	
	void setMenu() {
		menubar = new JMenuBar();
		menubar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		accountMenu = new JMenu("Account");
		accountMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		logoutMenuItem = new JMenuItem("Log Out");
		logoutMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		logoutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new Login();
			}
		});
		
		accountMenu.add(logoutMenuItem);
		menubar.add(accountMenu);
		
		this.setJMenuBar(menubar);
	}


}
