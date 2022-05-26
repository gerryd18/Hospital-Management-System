package Pharmacist;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class CreateItem extends JInternalFrame implements ActionListener{
	Connect db = new Connect();
	User user;
	
	JLabel titleLabel;
	
	//table
	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;
	Vector<String> columnName;
	Vector<Vector<Object>> data;
	
	//form
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel qtyLabel;
	JSpinner priceSpinner, qtySpinner;
	private JTextField nameField;
	
	JButton addBtn, updateBtn, deleteBtn, backBtn;
	
	public CreateItem(User user) {
		this.user = user;
		setFrame();
		
		titleLabel = new JLabel("Create Item");
		titleLabel.setForeground(Color.decode("#30E3CA"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(567,10,230,58);
		getContentPane().add(titleLabel);
		
		initTable();
		
		nameLabel = new JLabel("Name : ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(111, 145, 133, 25);
		nameLabel.setForeground(Color.decode("#E4F9F5"));
		getContentPane().add(nameLabel);
		
		priceLabel = new JLabel("Price :");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		priceLabel.setBounds(111, 207, 133, 25);
		priceLabel.setForeground(Color.decode("#E4F9F5"));
		getContentPane().add(priceLabel);
		
		qtyLabel = new JLabel("Quantity :");
		qtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		qtyLabel.setBounds(111, 272, 133, 25);
		qtyLabel.setForeground(Color.decode("#E4F9F5"));
		getContentPane().add(qtyLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameField.setBounds(254, 145, 192, 25);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		priceSpinner = new JSpinner();
		priceSpinner.setModel(new SpinnerNumberModel(new Integer(1000), null, null, new Integer(1000)));
		priceSpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		priceSpinner.setBounds(254, 207, 192, 25);
		getContentPane().add(priceSpinner);
		
		qtySpinner = new JSpinner();
		qtySpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		qtySpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		qtySpinner.setBounds(254, 272, 192, 25);
		getContentPane().add(qtySpinner);
		
		addBtn = new JButton("Add Item");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(111, 348, 318, 48);
		addBtn.addActionListener(this);
		getContentPane().add(addBtn);
		
		updateBtn = new JButton("Update Item");
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateBtn.setBounds(111, 414, 318, 48);
		updateBtn.addActionListener(this);
		getContentPane().add(updateBtn);
		
		deleteBtn = new JButton("Delete Item");
		deleteBtn.setBackground(new Color(255, 51, 102));
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteBtn.setBounds(111, 481, 318, 48);
		deleteBtn.addActionListener(this);
		getContentPane().add(deleteBtn);
		
		backBtn = new JButton("Back to menu");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backBtn.setBounds(143, 553, 258, 51);
		backBtn.addActionListener(this);
		getContentPane().add(backBtn);
		
	}
	
	private void initTable() {
		columnName = new Vector<>();
		columnName.add("Drug ID");
		columnName.add("Drug Name");
		columnName.add("Drug Price");
		columnName.add("Drug Qty");
		
		data = new Vector<Vector<Object>>();
		
		db.rs = db.getDrug();
		
		try {
			while(db.rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(db.rs.getString("drugID"));
				row.add(db.rs.getString("drugName"));
				row.add(db.rs.getInt("price"));
				row.add(db.rs.getInt("qty"));
				data.add(row);
				
			}
		} catch (Exception e) {
			
		}
		
		
		dtm = new DefaultTableModel(data, columnName);
		table = new JTable(dtm);
		table.setRowHeight(table.getRowHeight() + 10);
		jsp = new JScrollPane(table);
		jsp.setBounds(675,104,567,503);
		getContentPane().add(jsp);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				nameField.setText(String.valueOf(table.getValueAt(index, 1)));
				priceSpinner.setValue((int)table.getValueAt(index, 2));
				qtySpinner.setValue((int)table.getValueAt(index, 3));
				System.out.println(index);
			}
		});
		
	}

	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#222831")));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == backBtn) {
			this.dispose();
			getParent().add(new PharmacistHome(user));
		}else if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(this, "Please select row first!");
		}else {			
			if (e.getSource() == addBtn) {
				addItem();
			}else if (e.getSource() == deleteBtn) {
				deleteItem();
			}else if (e.getSource() == updateBtn) {
				updateItem();
			}
		}
		
		
	}
	
	public void addItem() {
		String name = nameField.getText();
		int price = (int) priceSpinner.getValue();
		int qty = (int) qtySpinner.getValue();
		
		db.addDrug(name, price, qty);
		
		JOptionPane.showMessageDialog(this, "Item successfully added!");
		refreshTable();
	
	}
	
	public void deleteItem() {
		String drugID = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
		db.rs = db.deleteDrug(drugID);
		
		dtm.removeRow(table.getSelectedRow());
	}
	
	public void updateItem() {
		int index = table.getSelectedRow();
		String drugID = String.valueOf(table.getValueAt(index, 0));
		String name = nameField.getText();
		int price = (int) priceSpinner.getValue();
		int qty = (int) qtySpinner.getValue();
		db.rs = db.updateDrug(name, price, qty, drugID);
		
		dtm.setValueAt(name, index, 1);
		dtm.setValueAt(price, index, 2);
		dtm.setValueAt(qty, index, 3);
	}
	
	void refreshTable() {
		table.removeAll();
		table.repaint();
		table.revalidate();
		initTable();
		
	}
}
