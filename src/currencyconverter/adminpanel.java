package currencyconverter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.Color;

public class adminpanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String[] columnNames = {"Currency_name", "Indian_Value"};
	String from;
	private JTable table_1;
	private JTable table_2;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminpanel frame = new adminpanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public adminpanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 50, 450, 300);
		setSize(500,900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCurrencyName = new JLabel("Currency Name");
		lblCurrencyName.setBounds(39, 85, 143, 29);
		lblCurrencyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblCurrencyName);
		
		JLabel lblNewLabel = new JLabel("Currencies");
		lblNewLabel.setBounds(148, 10, 190, 54);
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 49));
		contentPane.add(lblNewLabel);
		
		JLabel lblIndianValue = new JLabel("Indian Rupee value");
		lblIndianValue.setBounds(205, 85, 157, 29);
		lblIndianValue.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblIndianValue);
		
		JTextArea currencyname = new JTextArea();
		currencyname.setBounds(39, 124, 121, 27);
		contentPane.add(currencyname);
		
		JTextArea currencyvalue = new JTextArea();
		currencyvalue.setBounds(215, 124, 108, 27);
		contentPane.add(currencyvalue);
		
		
		
		 
		 JButton btnAdd = new JButton("ADD");
		 btnAdd.setBounds(380, 122, 85, 29);
		 btnAdd.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		      String currency_name = currencyname.getText();
		      String currency_value = currencyvalue.getText();
		 	if(currency_name != null && currency_name.isEmpty() && currency_value != null && currency_value.isEmpty()) {
		 		JOptionPane.showMessageDialog(null,"Please fill all the fields", "Error" ,JOptionPane.ERROR_MESSAGE);
		 	}else {
		 		float value =Float.parseFloat(currency_value);
		 		AddCurrency(currency_name , value);
		 		currencyname.setText("");
		 		currencyvalue.setText("");
		 		
		 	}
		 	}
		 });
		      contentPane.add(btnAdd);
	          //Show Records
		      ShowTable();       	 
		
	}
	
	public void ShowTable() {
		  String[] columnNames = {"Currency Name", "Indian Value"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
   // Initializing the JTable 	
		 try {
		    	Connection con = new connectiondatabase().getconnection();
		    	String currency_title;			    	
		    	String indian_value;
		        java.sql.PreparedStatement pst = con.prepareStatement("SELECT name , indian_value FROM `currencies`");
	            ResultSet rs = pst.executeQuery();
	            int i = 0;
	            while(rs.next()) {
	            currency_title = rs.getString("name");
	            indian_value= rs.getFloat("indian_value")+"";
	            String[] data = {currency_title, indian_value} ;
             tableModel.addRow(data);
	             i++;
	            }
	        } catch (Exception ex) {

	            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

	        }		 		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 466, 372);
		contentPane.add(scrollPane);
		table =  new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JButton btnConverter = new JButton("Converter");
		btnConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new converter().setVisible(true);
			}
		});
		btnConverter.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnConverter.setBounds(336, 631, 115, 39);
		contentPane.add(btnConverter);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Indian rupee value is that value which corrosponds to one unit of your selected currency like if you are adding Us dollar then 1 us dollar corrosponds to 74.17 rupees.");
			}
		});
		btnHelp.setBounds(225, 161, 85, 27);
		contentPane.add(btnHelp);
	}
	
	
	public void AddCurrency(String name , float value ) {
		Connection con = new connectiondatabase().getconnection();		
	    try {
	    	java.sql.PreparedStatement ps =con.prepareStatement("insert into currencies (name,indian_value) values(?,?);");
			ps.setString(1,name);
			 ps.setFloat(2,value);
			    int cc = ps.executeUpdate();
			    if(cc>0) {
			    		
			    	JOptionPane.showMessageDialog(null,"Currency added successfully");
			    	dispose();
			    	new adminpanel().setVisible(true);
			    	
			    }else {
			    	JOptionPane.showMessageDialog(null,"Database error");
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Internal error");
		}
	   
	}
}
