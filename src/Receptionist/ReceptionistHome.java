package Receptionist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DatabaseConnection.*;
import Main.*;

public class ReceptionistHome extends JInternalFrame implements ActionListener{
	Connect db = new Connect();

	JLabel titleLabel;
	
	User user;
	
	
	//component
	JButton patientBtn, channelBtn;
	
	ManagePatient managePatient;
	ManageChannel manageChannel;
	
	public ReceptionistHome(User user) {
		// TODO Auto-generated constructor stub
		setFrame();
		this.user = user;

		titleLabel = new JLabel("Welcome Receptionist " + user.getUserName());
		titleLabel.setForeground(Color.decode("#40514E"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(403, 29, 594, 58);
		getContentPane().add(titleLabel);
		
		
		patientBtn = new JButton("Patient");
		patientBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		patientBtn.setBounds(152,276,334,106);
		patientBtn.setBackground(Color.decode("#30E3CA"));
		patientBtn.setForeground(Color.decode("#222831"));
		patientBtn.addActionListener(this);
		getContentPane().add(patientBtn);
		
		channelBtn = new JButton("Channel");
		channelBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		channelBtn.setBounds(780, 276, 334, 106);
		channelBtn.setBackground(Color.decode("#30E3CA"));
		channelBtn.setForeground(Color.decode("#222831"));
		channelBtn.addActionListener(this);
		getContentPane().add(channelBtn);
		
	}

	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#E4F9F5")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == patientBtn) {
			this.dispose();
			managePatient = new ManagePatient(user);
			getParent().add(managePatient);
		}else
			if (e.getSource() == channelBtn) {
			this.dispose();
			manageChannel = new ManageChannel(user);
			getParent().add(manageChannel);
		}
		
	}
}
