package Doctor;

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

import Main.*;
import DatabaseConnection.*;
import javax.swing.JButton;

public class DoctorHome extends JInternalFrame implements ActionListener{
	Connect db = new Connect();
	User user;
	
	JLabel titleLabel;
	
	//table
		JTable table;
		DefaultTableModel dtm;
		JScrollPane jsp;
		Vector<String> columnName = new Vector<>();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		JButton prescriptionBtn;

	public DoctorHome(User user) {
		this.user = user;
		setFrame();
		
		titleLabel = new JLabel("Welcome Doctor " + user.getUserName());
		titleLabel.setForeground(Color.decode("#30E3CA"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(447,21,475,58);
		getContentPane().add(titleLabel);
		
		initTable();
		 
	}
	
	public void initTable() {
		columnName.add("Channel No");
		columnName.add("Doctor Name");
		columnName.add("Patient Name");
		columnName.add("Room No");
		columnName.add("Date");
		
		db.rs = db.doctorChannel(user.getUserName());
		try {
			while(db.rs.next()) {
				Vector<Object> row = new Vector<>();
				 row.add(db.rs.getString("channelNo"));
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
		table.setRowHeight(table.getRowHeight() + 20);
		jsp = new JScrollPane(table);
		jsp.setBounds(128,97,990,383);
		getContentPane().add(jsp);
		
		prescriptionBtn = new JButton("Make Prescription");
		prescriptionBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		prescriptionBtn.setBounds(484, 523, 280, 58);
		prescriptionBtn.addActionListener(this);
		getContentPane().add(prescriptionBtn);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == prescriptionBtn) {
			
			int index = table.getSelectedRow();
			if (index <0) {
				JOptionPane.showMessageDialog(this, "Please select row in table first!");
			}else {				
				this.dispose();
				String channelNo = String.valueOf(dtm.getValueAt(index, 0));
				getParent().add(new AddPrescription(user, channelNo));
			}
		}
	}
	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#222831")));
	}
}
