package Pharmacist;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import DatabaseConnection.*;
import Main.*;
import javax.swing.JButton;

public class CheckPrescription extends JFrame implements ActionListener{
	User user;
	Connect db = new Connect();
	
	JLabel titleLabel;
	
	//table
	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;
	Vector<Object> columnName;
	Vector<Vector<Object>> data;
	private JButton inventoryBtn;
	private JButton backBtn;

	public CheckPrescription(User user) {
		// TODO Auto-generated constructor stub
		this.user = user;
		setFrame();
		
		titleLabel = new JLabel("Prescriptions");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleLabel.setBounds(465, 10, 146, 38);
		getContentPane().add(titleLabel);
		
		initTable();
		
		inventoryBtn = new JButton("Inventory");
		inventoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inventoryBtn.setBounds(123, 368, 299, 50);
		inventoryBtn.addActionListener(this);
		getContentPane().add(inventoryBtn);
		
		backBtn = new JButton("Back To Menu");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backBtn.setBounds(633, 368, 299, 50);
		backBtn.addActionListener(this);
		getContentPane().add(backBtn);
	}
	
	private void initTable() {
		columnName = new Vector<>();
		columnName.add("Prescription ID");
		columnName.add("Channel No");
		columnName.add("Desease Type");
		columnName.add("Description");
		
		data = new Vector<Vector<Object>>();
		
		db.rs = db.getPrescription();
		try {
			while(db.rs.next()) {
				Vector<Object> row = new Vector<Object>();
				row.add(db.rs.getString("prescriptionID"));
				row.add(db.rs.getString("channelID"));
				row.add(db.rs.getString("deseaseType"));
				row.add(db.rs.getString("description"));
				data.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dtm = new DefaultTableModel(data, columnName);
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		jsp.setBounds(123,58,809,274);
		getContentPane().add(jsp);
	
	}
	
	void setFrame() {
		this.setVisible(true);
		this.setSize(1080,520);
		this.setLocationRelativeTo(null);
		this.setTitle("Prescriptions");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#40514E")));
		getContentPane().setLayout(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
		}else if (e.getSource() == inventoryBtn) {
			String prescriptionID = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
			this.dispose();
			new Inventory(prescriptionID, user);
		}
		
	}

}
