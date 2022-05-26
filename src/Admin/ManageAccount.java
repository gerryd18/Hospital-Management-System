package Admin;

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
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ManageAccount extends JInternalFrame implements ActionListener, MouseListener{
	Connect db = new Connect();
	User user;

	JLabel titleLabel;

	// table
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Vector<String> columnName;
	Vector<Vector<Object>> data;
	
	//form
	JLabel nameLabel, emailLabel, roleLabel;
	JComboBox<String> roleField;
	private JTextField nameField;
	private JTextField emailField;
	Vector<String> vrole = new Vector<>();
	private JButton deleteBtn;
	private JButton updateBtn;
	private JButton backBtn;

	public ManageAccount(User user) {
		setFrame();
		this.user = user;

		titleLabel = new JLabel("Manage Account");
		titleLabel.setForeground(Color.decode("#40514E"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(514, 21, 279, 58);
		getContentPane().add(titleLabel);
		
		initTable();
		
		
		
		nameLabel = new JLabel("Username : ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(146, 407, 185, 29);
		getContentPane().add(nameLabel);
		
		emailLabel = new JLabel("Email :");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailLabel.setBounds(146, 447, 185, 29);
		getContentPane().add(emailLabel);
		
		roleLabel = new JLabel("Role :");
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roleLabel.setBounds(146, 486, 185, 29);
		getContentPane().add(roleLabel);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameField.setBounds(341, 407, 185, 29);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailField.setColumns(10);
		emailField.setBounds(341, 447, 185, 29);
		getContentPane().add(emailField);
		
		
		vrole.add("Receptionist");
		vrole.add("Doctor");
		vrole.add("Pharmacist");
		vrole.add("Admin");
		
		roleField = new JComboBox(vrole);
		roleField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		roleField.setBounds(341, 486, 185, 29);
		getContentPane().add(roleField);
		
		deleteBtn = new JButton("Delete Account");
		deleteBtn.setBackground(new Color(255, 51, 102));
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteBtn.setBounds(728, 384, 219, 36);
		deleteBtn.addActionListener(this);
		getContentPane().add(deleteBtn);
		
		updateBtn = new JButton("Update Account");
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateBtn.setBounds(728, 439, 219, 36);
		updateBtn.addActionListener(this);
		getContentPane().add(updateBtn);
		
		backBtn = new JButton("Back to Menu");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backBtn.setBounds(728, 490, 219, 36);
		backBtn.addActionListener(this);
		getContentPane().add(backBtn);
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
		if (e.getSource() == deleteBtn) {
			deleteAccount();
		}else if (e.getSource() == updateBtn) {
			updateAccount();
		}else if (e.getSource() == backBtn) {
			this.dispose();
			getParent().add(new AdminHome(user));
		}
	}
	
	private void deleteAccount() {
		 int index = table.getSelectedRow();
		 String name = String.valueOf(table.getValueAt(index, 1));
		 String email = String.valueOf(table.getValueAt(index, 2));
		 String role = String.valueOf(table.getValueAt(index, 3));
		 
		db.rs = db.deleteUser(name, email, role);
		JOptionPane.showMessageDialog(this, "Account Deleted Successfully!");
		dtm.removeRow(index);
	}
	
	private void updateAccount() {
		 int index = table.getSelectedRow();
		String id = String.valueOf(table.getValueAt(index, 0));
		String name = nameField.getText();
		String email = emailField.getText();
		String role = String.valueOf(roleField.getSelectedItem());
		
		db.rs = db.updateUser(name, email, role, id);
		
		JOptionPane.showMessageDialog(this, "Account Updated Successfull!");
		dtm.setValueAt(id, index, 0);
		dtm.setValueAt(name, index,1);
		dtm.setValueAt(email, index, 2);
		dtm.setValueAt(role, index, 3);
		
	}
	

	public void initTable() {
		columnName = new Vector<>();
		data = new Vector<Vector<Object>>();
		
		columnName.add("ID");
		columnName.add("Name");
		columnName.add("Email");
		columnName.add("Role");
		
		db.rs = db.getUser();
		try {
			while(db.rs.next()) {
				Vector<Object>row = new Vector<>();
				row.add(db.rs.getString("userID"));
				row.add(db.rs.getString("username"));
				row.add(db.rs.getString("email"));
				row.add(db.rs.getString("role"));
				data.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		dtm = new DefaultTableModel(data, columnName);
		table = new JTable(dtm);
		table.addMouseListener(this);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(table.getRowHeight() + 20);
		
		jsp = new JScrollPane(table);

		
		jsp.setBounds(146,104,993,222);
		getContentPane().add(jsp);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == table) {
			int index = table.getSelectedRow();
			nameField.setText(String.valueOf(table.getValueAt(index, 1)));
			emailField.setText(String.valueOf(table.getValueAt(index, 2)));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
