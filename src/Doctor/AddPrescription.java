package Doctor;

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
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AddPrescription extends JInternalFrame implements ActionListener, MouseListener{
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
	private JTextField channelNoField;
	private JTextField diseaseTypeField;
	private JButton updateBtn;
	private JButton backBtn, addBtn;
	JTextArea descriptionField;
	JLabel channelNoLabel, diseaseTypeLabel, descriptionLabel;
	String channelNo;
	
	public AddPrescription(User user, String channelNo) {
		this.user = user;
		this.channelNo = channelNo;
		setFrame();
		
		titleLabel = new JLabel("Prescription");
		titleLabel.setForeground(Color.decode("#30E3CA"));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(530,21,475,58);
		getContentPane().add(titleLabel);
		
		initTable();
		
		channelNoLabel = new JLabel("Channel No :");
		channelNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		channelNoLabel.setBounds(161, 394, 153, 27);
		channelNoLabel.setForeground(Color.decode("#EEEEEE"));
		getContentPane().add(channelNoLabel);
		
		diseaseTypeLabel = new JLabel("Disease Type :");
		diseaseTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		diseaseTypeLabel.setBounds(161, 444, 153, 27);
		diseaseTypeLabel.setForeground(Color.decode("#EEEEEE"));
		getContentPane().add(diseaseTypeLabel);
		
		descriptionLabel = new JLabel("Description :");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		descriptionLabel.setBounds(161, 494, 153, 27);
		descriptionLabel.setForeground(Color.decode("#EEEEEE"));
		getContentPane().add(descriptionLabel);
		
		channelNoField = new JTextField();
		channelNoField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		channelNoField.setBounds(335, 394, 200, 27);
		channelNoField.setEditable(false);
		channelNoField.setText(channelNo);
		getContentPane().add(channelNoField);
		channelNoField.setColumns(10);
		
		diseaseTypeField = new JTextField();
		diseaseTypeField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		diseaseTypeField.setColumns(10);
		diseaseTypeField.setBounds(335, 444, 200, 27);
		getContentPane().add(diseaseTypeField);
		
		descriptionField = new JTextArea();
		descriptionField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		descriptionField.setBounds(335, 499, 200, 58);
		getContentPane().add(descriptionField);
		
		addBtn = new JButton("Add Prescription");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setBounds(822, 387, 231, 40);
		addBtn.addActionListener(this);
		getContentPane().add(addBtn);
		
		updateBtn = new JButton("Update Prescription");
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateBtn.setBounds(822, 451, 231, 40);
		updateBtn.addActionListener(this);
		getContentPane().add(updateBtn);
		
		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backBtn.setBounds(822, 517, 231, 40);
		backBtn.addActionListener(this);
		getContentPane().add(backBtn);
		
	}
	
	public void setFrame() {
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,720);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.decode(("#222831")));
	}
	
	private void initTable() {
		columnName = new Vector<>();
		columnName.add("Prescription ID");
		columnName.add("Channel ID");
		columnName.add("Desease Type");
		columnName.add("Description");
		
		db.rs = db.getChannelPrescription(channelNo);
		
		data = new Vector<Vector<Object>>();
		try {
			while(db.rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(db.rs.getString("prescriptionID"));
				row.add(db.rs.getString("channelID"));
				row.add(db.rs.getString("deseaseType"));
				row.add(db.rs.getString("description"));
				data.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		dtm = new DefaultTableModel(data, columnName);
		table = new JTable(dtm);
		table.addMouseListener(this);
		table.setRowHeight(table.getRowHeight() + 10);
		jsp = new JScrollPane(table);
		jsp.setBounds(146,89,928,249);
		getContentPane().add(jsp);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			this.dispose();
			getParent().add(new DoctorHome(user));
		}else if (e.getSource() == addBtn) {
			add();
		}else if (e.getSource() == updateBtn) {
			update();
		}
	}
	
	private void add() {
		String channelNo = channelNoField.getText();
		String diseaseType = diseaseTypeField.getText();
		String desc = descriptionField.getText();
		
		db.addPrescription(channelNo, diseaseType, desc);
		JOptionPane.showMessageDialog(this, "Prescription added successfully!");
		
		table.removeAll();
		table.repaint();
		table.revalidate();
		initTable();
	}
	
	private void update() {
		String channelNo = channelNoField.getText();
		String diseaseType = diseaseTypeField.getText();
		String desc = descriptionField.getText();
		int index = table.getSelectedRow();
		String prescriptionID = String.valueOf(table.getValueAt(index, 0));
		
		
		db.rs = db.updatePrescription(channelNo, diseaseType, desc, prescriptionID);
		JOptionPane.showMessageDialog(this, "Prescripton updated successfully!");
		
		dtm.setValueAt(diseaseType, index, 2);
		dtm.setValueAt(desc, index, 3);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == table) {
			int index = table.getSelectedRow();
			diseaseTypeField.setText(String.valueOf(dtm.getValueAt(index, 2)));
			descriptionField.setText(String.valueOf(dtm.getValueAt(index, 3)));
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
