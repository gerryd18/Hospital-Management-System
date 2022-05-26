package Pharmacist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Main.*;
import DatabaseConnection.*;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Inventory extends JFrame implements ActionListener{
	User user;
	Connect db = new Connect();
	
	JLabel titleLabel;
	
	//table
	JTable table;
	JScrollPane jsp;
	DefaultTableModel dtm;
	Vector<Object> columnName = new Vector<>();
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	
	
	//form
	JLabel idLabel, drugCodeLabel, drugNameLabel, qtyLabel;
	JSpinner qtyField;
	private JTextField idField;
	private JComboBox drugCodeField;
	private JTextField drugNameField;
	JButton addBtn;
	Vector<String> vDrugCode = new Vector<String>();
	
	public Inventory(String prescriptionID, User user) {
		setFrame();
		this.user = user;
		
		titleLabel = new JLabel("Sales Inventory");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titleLabel.setBounds(465, 10, 146, 38);
		getContentPane().add(titleLabel);
		
		initTable();
		
		
		idLabel = new JLabel("Prescription ID :");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idLabel.setBounds(44, 107, 146, 25);
		getContentPane().add(idLabel);
		
		drugCodeLabel = new JLabel("Drug Code :");
		drugCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		drugCodeLabel.setBounds(44, 147, 146, 25);
		getContentPane().add(drugCodeLabel);
		
		drugNameLabel = new JLabel("Drug Name :");
		drugNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		drugNameLabel.setBounds(44, 187, 146, 25);
		getContentPane().add(drugNameLabel);
		
		qtyLabel = new JLabel("Quantity :");
		qtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		qtyLabel.setBounds(44, 227, 146, 25);
		getContentPane().add(qtyLabel);
		
		idField = new JTextField();
		idField.setText(prescriptionID);
		idField.setEditable(false);
		idField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idField.setBounds(212, 107, 212, 25);
		getContentPane().add(idField);
		idField.setColumns(10);
		
	
		
		qtyField = new JSpinner();
		qtyField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		qtyField.setBounds(212, 227, 212, 27);
		getContentPane().add(qtyField);
		
		addBtn = new JButton("Add");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(125, 292, 176, 38);
		getContentPane().add(addBtn);
		addBtn.addActionListener(this);
		
		db.rs = db.getDrug();
		try {
			while(db.rs.next()) {
				vDrugCode.add(db.rs.getString("drugID"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		drugCodeField = new JComboBox(vDrugCode);
		drugCodeField.addActionListener(this);
		drugCodeField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		drugCodeField.setBounds(212, 149, 212, 25);
		getContentPane().add(drugCodeField);
		
		drugNameField = new JTextField();
		drugNameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		drugNameField.setColumns(10);
		drugNameField.setEditable(false);
		drugNameField.setBounds(212, 187, 212, 26);
		getContentPane().add(drugNameField);
		
		drugCodeField.addItemListener(new ItemListener() {
			
			String drugName ="";
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				db.rs =db.getDrugByID(String.valueOf(drugCodeField.getSelectedItem()));
//				System.out.println(String.valueOf(drugCodeField.getSelectedItem()));
				try {
					while(db.rs.next()) {
						drugName = db.rs.getString("drugname");
						drugNameField.setText(drugName);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		
		
		
	}
	
	void initTable() {
		columnName.add("prescriptionID");
		columnName.add("Drug ID");
		columnName.add("Drug Name");
		columnName.add("Qty");
		columnName.add("Price");
		columnName.add("Total Price");
		
		db.rs = db.getSalesInventory();
		try {
			while(db.rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(db.rs.getString("prescriptionID"));
				row.add(db.rs.getString("drugCode"));
				row.add(db.rs.getString("drugName"));
				row.add(db.rs.getInt("qty"));
				row.add(db.rs.getInt("price"));
				row.add(db.rs.getInt("totalPrice"));
				data.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		dtm = new DefaultTableModel(data, columnName);
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		jsp.setBounds(520,58,531,410);
		getContentPane().add(jsp);
	
	}
	
	void setFrame() {
		this.setVisible(true);
		this.setSize(1080,520);
		this.setLocationRelativeTo(null);
		this.setTitle("Inventory");
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#40514E")));
		getContentPane().setLayout(null);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addBtn) {
			String prescriptionID = idField.getText();
			String drugCode = String.valueOf(drugCodeField.getSelectedItem());
			String drugName = drugNameField.getText();
			int qty = (int) qtyField.getValue();
			int price = 0;
			int totalPrice=0;
			
			db.rs = db.getDrugByID(drugCode);
			try {
				while(db.rs.next()) {
					price = db.rs.getInt("price");
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			totalPrice = price*qty;
			
			db.addSalesInventory(prescriptionID, drugCode, drugName, qty, price, totalPrice);
			
			db.rs = db.getDrugByID(drugCode);
			int qtyBefore=0, qtyAfter=0;
			
			try {
				while(db.rs.next()) {
					qtyBefore = db.rs.getInt("qty");
					qtyAfter = qtyBefore - qty;
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		
			
			db.updateDrugStock(qtyAfter, drugCode);
			JOptionPane.showMessageDialog(this, "sales Inventory updated successfully");
			
			Object[]row = {prescriptionID, drugCode, drugName, qty, price, totalPrice};
			dtm.addRow(row);
		}
	
	}
}
