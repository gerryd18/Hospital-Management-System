package Receptionist;

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

import DatabaseConnection.*;
import Main.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ManageChannel extends JInternalFrame implements ActionListener{
	
	Connect db = new Connect();
	User user;
	
	JLabel titleLabel;
	
	Vector<String> vdoctor = new Vector<>();
	Vector<String> vpatient = new Vector<>();
	private JTextField noField;
	private JTextField roomField;
	private JTextField dateField;
	
	
	//form
	JLabel noLabel, doctorNameLabel, patientNameLabel, roomLabel, dateLabel;
	JComboBox<String> doctorField, patientField;
	
	JButton addBtn, deleteBtn, backBtn;
	
	
	//table
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Vector<String> columnName = new Vector<>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();

	public ManageChannel(User user) {
		setFrame();
		this.user = user;
		
		
		titleLabel = new JLabel("Manage Channel");
		titleLabel.setForeground(Color.decode("#30E3CA"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(531, 39, 266, 58);
		getContentPane().add(titleLabel);
		
		noLabel = new JLabel("Channel No :");
		noLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noLabel.setBounds(72, 130, 147, 25);
		noLabel.setForeground(Color.decode("#E4F9F5"));
		getContentPane().add(noLabel);
		
		doctorNameLabel = new JLabel("Doctor name :");
		doctorNameLabel.setForeground(new Color(228, 249, 245));
		doctorNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		doctorNameLabel.setBounds(72, 176, 147, 25);
		getContentPane().add(doctorNameLabel);
		
		patientNameLabel = new JLabel("Patient Name :");
		patientNameLabel.setForeground(new Color(228, 249, 245));
		patientNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		patientNameLabel.setBounds(72, 225, 147, 25);
		getContentPane().add(patientNameLabel);
		
		roomLabel = new JLabel("Room No :");
		roomLabel.setForeground(new Color(228, 249, 245));
		roomLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roomLabel.setBounds(72, 273, 147, 25);
		getContentPane().add(roomLabel);
		
		dateLabel = new JLabel("Channel Date :");
		dateLabel.setForeground(new Color(228, 249, 245));
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateLabel.setBounds(72, 317, 147, 25);
		getContentPane().add(dateLabel);
		
		noField = new JTextField();
		noField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		noField.setEditable(false);
		noField.setBounds(229, 131, 221, 31);
		getContentPane().add(noField);
		noField.setColumns(10);
		
		int channelNo = 1;
		db.rs = db.getChannel();
		
		try {
			while(db.rs.next()) {
				channelNo++;
				noField.setText(String.valueOf(channelNo));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		doctorField = new JComboBox(vdoctor);
		doctorField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		doctorField.setBounds(229, 177, 221, 31);
		getContentPane().add(doctorField);
		
		patientField = new JComboBox(vpatient);
		patientField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		patientField.setBounds(229, 225, 221, 31);
		getContentPane().add(patientField);
		
		roomField = new JTextField();
		roomField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roomField.setColumns(10);
		roomField.setBounds(229, 270, 221, 31);
		getContentPane().add(roomField);
		
		dateField = new JTextField();
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateField.setColumns(10);
		dateField.setBounds(229, 314, 221, 31);
		getContentPane().add(dateField);
		
		addBtn = new JButton("Create Channel");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(72, 391, 378, 38);
		addBtn.addActionListener(this);
		getContentPane().add(addBtn);
		
		deleteBtn = new JButton("Delete Channel");
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteBtn.setBounds(72, 440, 378, 38);
		deleteBtn.addActionListener(this);
		getContentPane().add(deleteBtn);
		
		backBtn = new JButton("Back to Menu\r\n");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backBtn.setBounds(72, 519, 378, 38);
		backBtn.addActionListener(this);
		getContentPane().add(backBtn);
		
		getDoctor();
		getPatient();
		
		
		initTable();
		
		
	}
	
	public void initTable() {
		columnName.add("Channel No");
		columnName.add("Doctor");
		columnName.add("Patient");
		columnName.add("Room No");
		columnName.add("Date");
		
		
		db.rs = db.getChannel();
		try {
			while(db.rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(db.rs.getString("ChannelNo"));
				row.add(db.rs.getString("doctorName"));
				row.add(db.rs.getString("patientName"));
				row.add(db.rs.getString("roomNo"));
				row.add(db.rs.getString("date"));
				data.add(row);
			}
		} catch (Exception e) {
			
		}
		
		dtm = new DefaultTableModel(data, columnName);
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		jsp.setBounds(497,130,713,348);
		getContentPane().add(jsp);
		
	}
	
	public void getDoctor() {
		db.rs = db.getDoctor();
		try {
			while(db.rs.next()) {
				vdoctor.add(db.rs.getString("username"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getPatient() {
		db.rs = db.getPatient();
		
		try {
			while(db.rs.next()) {
				vpatient.add(db.rs.getString("patientName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setLocation(0,0);
		this.getContentPane().setBackground(Color.decode(("#393E46")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addBtn) {
			addChannel();
		}else
			if (e.getSource() == deleteBtn) {
			deleteChannel();
		}else
			if (e.getSource() == backBtn) {
			this.dispose();
			getParent().add(new ReceptionistHome(this.user));
		}
		
	}
	
	
	private void addChannel() {
		
		int channelno = Integer.parseInt(noField.getText());
		String doctorName = String.valueOf(doctorField.getSelectedItem());
		String patientName = String.valueOf(patientField.getSelectedItem());
		String roomNo = String.valueOf(roomField.getText());
		String date = String.valueOf(dateField.getText());
		
		db.rs = db.addChannel(channelno, doctorName, patientName, roomNo, date);
		Object []newRow = {channelno, doctorName, patientName,roomNo,date};
		
		JOptionPane.showMessageDialog(this, "Channel successfully added!");
		dtm.addRow(newRow);
	}
	
	private void deleteChannel() {
		int index = table.getSelectedRow();
		int channelno = Integer.parseInt((String) table.getValueAt(index, 0));
		System.out.println(channelno);
		db.rs = db.deleteChannel(channelno);
		JOptionPane.showMessageDialog(this, "Channel successfully deleted!");
		dtm.removeRow(index);
	}
}
