package Receptionist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DatabaseConnection.*;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import Main.*;

public class ManagePatient extends JInternalFrame implements ActionListener{

	Connect db = new Connect();
	User user;
	Random rand = new Random();
	
	
	JLabel titleLabel;
	
	//form
	JLabel idLabel, nameLabel, numberLabel, addressLabel;
	private JTextField idField;
	private JTextField nameField;
	private JTextField numberField;
	private JTextField addressField;
	
	JButton addBtn, deleteBtn, backBtn;
	
	
	//table
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	
	Vector<String> columnName = new Vector<>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	private JButton backbtn;
	
	public ManagePatient(User user) {
		// TODO Auto-generated constructor stub
		setFrame();
		this.user = user;
		
		
		titleLabel = new JLabel("Manage Patient");
		titleLabel.setForeground(Color.decode("#30E3CA"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(531, 39, 266, 58);
		getContentPane().add(titleLabel);
		
		idLabel = new JLabel("Patient ID :");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idLabel.setBounds(79, 163, 182, 37);
		idLabel.setForeground(Color.decode("#E4F9F5"));
		getContentPane().add(idLabel);
		
		nameLabel = new JLabel("Patient Name :");
		nameLabel.setForeground(new Color(228, 249, 245));
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(79, 221, 182, 37);
		getContentPane().add(nameLabel);
		
		numberLabel = new JLabel("Patient Number :");
		numberLabel.setForeground(new Color(228, 249, 245));
		numberLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		numberLabel.setBounds(79, 283, 182, 37);
		getContentPane().add(numberLabel);
		
		addressLabel = new JLabel("Patient Address :");
		addressLabel.setForeground(new Color(228, 249, 245));
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addressLabel.setBounds(79, 345, 182, 37);
		getContentPane().add(addressLabel);
		
		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idField.setBounds(258, 164, 235, 37);
		idField.setEditable(false);
		getContentPane().add(idField);
		idField.setColumns(10);
		generateID();
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameField.setColumns(10);
		nameField.setBounds(258, 221, 235, 37);
		getContentPane().add(nameField);
		
		numberField = new JTextField();
		numberField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		numberField.setColumns(10);
		numberField.setBounds(258, 283, 235, 37);
		getContentPane().add(numberField);
		
		addressField = new JTextField();
		addressField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addressField.setColumns(10);
		addressField.setBounds(258, 345, 235, 37);
		getContentPane().add(addressField);
		
		addBtn = new JButton("Add Patient");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(79, 434, 168, 37);
		addBtn.addActionListener(this);
		getContentPane().add(addBtn);
		
		deleteBtn = new JButton("Delete Patient");
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteBtn.setBounds(325, 434, 168, 37);
		deleteBtn.addActionListener(this);
		getContentPane().add(deleteBtn);
		
		backBtn = new JButton("Back to menu");
		backBtn.addActionListener(this);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backBtn.setBounds(196, 505, 168, 37);
		getContentPane().add(backBtn);
		
		
		//table
		
		columnName.add("Patient ID");
		columnName.add("Name");
		columnName.add("Number");
		columnName.add("Address");
		
		db.rs = db.getPatient();
		try {
			while(db.rs.next()) {
				Vector<Object> row = new Vector<>();
				String patientID = db.rs.getString("patientID");
				String patientName = db.rs.getString("patientName");
				String patientNumber = db.rs.getString("patientNumber");
				String patientAddress = db.rs.getString("patientAddress");
				
				row.add(patientID);
				row.add(patientName);
				row.add(patientNumber);
				row.add(patientAddress);
				data.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		dtm = new DefaultTableModel(data,columnName);
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		jsp.setBounds(555,163,583,412);
		getContentPane().add(jsp);
		
		
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
			add();
		}else if (e.getSource() == backBtn) {
			this.dispose();
			getParent().add(new ReceptionistHome(this.user));
		}else if (e.getSource() == deleteBtn) {
			delete();
		}
	}
	
	
	public void add() {

		
		String id = idField.getText();
		String name = nameField.getText();
		String number = numberField.getText();
		String address = addressField.getText();
		
		JOptionPane.showMessageDialog(this, "Patient added successfully!");
		
		db.rs = db.addPatient(id, name, number, address);
		
		String row[] = {id,name,number,address};
		dtm.addRow(row);
		
		clear();
	}
	
	public void delete() {
		int index = table.getSelectedRow();
		
		String id = String.valueOf(dtm.getValueAt(index, 0));
		
		JOptionPane.showMessageDialog(this, "Patient deleted!");
		
		db.rs = db.deletePatient(id);
		dtm.removeRow(index);
		
	}
	
	void generateID() {
		String id = "PA";
		id+= rand.nextInt(9-1+1)+1;
		id+= rand.nextInt(9-1+1)+1;
		id+= rand.nextInt(9-1+1)+1;
		
		idField.setText(id);
	}
	
	void clear() {
		idField.setText("");
		nameField.setText("");
		numberField.setText("");
		addressField.setText("");
		generateID();
	}
}
