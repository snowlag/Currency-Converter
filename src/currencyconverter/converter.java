package currencyconverter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class converter extends JFrame {

	  private javax.swing.ButtonGroup buttonGroup1;
	    private javax.swing.JColorChooser jColorChooser1;
	    private javax.swing.JInternalFrame jInternalFrame1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel2;
	    private javax.swing.JButton jbtnConvert;
	    private javax.swing.JButton jbtnExit;
	    private javax.swing.JButton jbtnReset;
	    private javax.swing.JComboBox<String> jcmbCurrency;
	    private javax.swing.JTextField value_to_convert;
	    
	    private JTextField ResultField;
	    private JButton btnAdmin;
	    private JComboBox<String> comboBox;
	    
	    
	    
	    public converter() {
	    	initComponents();
	  
	    }
	    
	    
	   private String[] GetCurrenciesName() {
		   ArrayList<String> Currencies = new ArrayList<String>();
		   ResultSet rs;
		   Currencies.add("Select Currency");
		   try {
			 java.sql.Connection con = new connectiondatabase().getconnection();
			 java.sql.PreparedStatement pst = con.prepareStatement("SELECT name FROM `currencies`");  
			  rs = pst.executeQuery();
			 
			  while(rs.next()) {
				  Currencies.add(rs.getString("name"));	        	   
	          }			
		    } catch (SQLException e) {
			   // TODO Auto-generated catch block
			    e.printStackTrace();			
		    }	
		   
		   String CurrencyArray[]= Currencies.toArray(new String[Currencies.size()]);
		   
		   
		 return CurrencyArray;
	   }
	    
	 private float ConvertCurrency(String GivenCurrency , String CurrencyToConvert , float GivenCurrencyvalue) {
		 java.sql.Connection con = new connectiondatabase().getconnection();
		 ResultSet rs1;
		 ResultSet rs2;
		 float IndianValueOfGivenCurrency = 0;
		 float IndianValueOfResultCurrency = 0;
		 float result = 0;
		 try {
			 java.sql.Connection con1 = new connectiondatabase().getconnection();
			 java.sql.PreparedStatement pst1 = con1.prepareStatement("SELECT indian_value FROM `currencies` where `name` = '" + GivenCurrency + "'");  
			 java.sql.PreparedStatement pst2 = con1.prepareStatement("SELECT indian_value FROM `currencies` where `name` = '" + CurrencyToConvert + "'");
			
			 rs1 = pst1.executeQuery();
			 rs2 = pst2.executeQuery();	  
			 while(rs1.next()) {
				IndianValueOfGivenCurrency = rs1.getFloat("indian_value");
			 }
			 while(rs2.next()) {
				IndianValueOfResultCurrency = rs2.getFloat("indian_value");
			 }
		     result = (GivenCurrencyvalue *  IndianValueOfGivenCurrency) / IndianValueOfResultCurrency;		
		     System.out.println(GivenCurrencyvalue);
		     System.out.println(IndianValueOfGivenCurrency);
		     System.out.println(IndianValueOfGivenCurrency);
		     System.out.println(result);
		    } catch (SQLException e) {
			   // TODO Auto-generated catch block
			    e.printStackTrace();			
		    }	
		return result;
		 
	 }
	    
	
	  private void initComponents() {
		  jPanel1 = new javax.swing.JPanel();
	        jInternalFrame1 = new javax.swing.JInternalFrame();
	        jPanel2 = new javax.swing.JPanel();
	        buttonGroup1 = new javax.swing.ButtonGroup();
	        jColorChooser1 = new javax.swing.JColorChooser();
	        jLabel1 = new javax.swing.JLabel();
	        jcmbCurrency = new javax.swing.JComboBox<>();
	        jbtnConvert = new javax.swing.JButton();
	        jbtnReset = new javax.swing.JButton();
	        jbtnExit = new javax.swing.JButton();
	        value_to_convert = new javax.swing.JTextField();
	        
	        
	        
	        
	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );

	        jInternalFrame1.setVisible(true);

	        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
	        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
	        jInternalFrame1Layout.setHorizontalGroup(
	            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );
	        jInternalFrame1Layout.setVerticalGroup(
	            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );

	        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	        jPanel2.setLayout(jPanel2Layout);
	        jPanel2Layout.setHorizontalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );
	        jPanel2Layout.setVerticalGroup(
	            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 100, Short.MAX_VALUE)
	        );

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        setTitle("cConverter");
	        setAutoRequestFocus(false);
	        setBackground(new java.awt.Color(99, 155, 174));
	        setBounds(new java.awt.Rectangle(50, 50, 55, 70));

	        jLabel1.setBackground(new java.awt.Color(160, 72, 72));
	        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
	        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
	        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel1.setText("Currency Converter");
	        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));
	        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

	        jcmbCurrency.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
	        jcmbCurrency.setModel(new javax.swing.DefaultComboBoxModel<>(GetCurrenciesName()));
	        jcmbCurrency.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                
	            }
	        });
	        
	        comboBox = new JComboBox<String>();
	        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 24));
	        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(GetCurrenciesName()));
	        comboBox.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                
	            }
	        });

	        jbtnConvert.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
	        jbtnConvert.setText("Convert");
	        jbtnConvert.setActionCommand("CONVERT");
	        jbtnConvert.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jbtnConvertActionPerformed(evt);
	            }
	        });

	        jbtnReset.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
	        jbtnReset.setText("Reset");
	        jbtnReset.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jbtnResetActionPerformed(evt);
	            }
	        });

	        jbtnExit.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
	        jbtnExit.setText("Exit");
	        jbtnExit.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jbtnExitActionPerformed(evt);
	            }
	        });

	        value_to_convert.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
	        value_to_convert.setForeground(new java.awt.Color(0, 51, 153));
	        value_to_convert.setHorizontalAlignment(javax.swing.JTextField.CENTER);
	        value_to_convert.setCaretColor(new java.awt.Color(255, 255, 255));
	        
	      
	        
	        ResultField = new JTextField();
	        ResultField.setHorizontalAlignment(SwingConstants.CENTER);
	        ResultField.setForeground(new Color(0, 51, 153));
	        ResultField.setFont(new Font("Dialog", Font.PLAIN, 18));
	        ResultField.setCaretColor(Color.WHITE);
	        
	        btnAdmin = new JButton("ADMIN");
	        btnAdmin.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		dispose();
	        		new adminlogin().setVisible(true);
	        	
	        };
	        });

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.TRAILING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(jbtnReset, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
	        			.addGap(131)
	        			.addComponent(jbtnConvert, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
	        			.addComponent(jbtnExit, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap())
	        		.addGroup(layout.createSequentialGroup()
	        			.addGap(34)
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(jcmbCurrency, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))
	        			.addGap(37)
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(ResultField, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(value_to_convert, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
	        			.addContainerGap(86, Short.MAX_VALUE))
	        		.addGroup(layout.createSequentialGroup()
	        			.addGap(99)
	        			.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
	        			.addPreferredGap(ComponentPlacement.RELATED)
	        			.addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
	        			.addGap(27))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addGap(23)
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(btnAdmin))
	        			.addGap(18)
	        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(jcmbCurrency, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(value_to_convert, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
	        			.addGap(25)
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
	        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(ResultField, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
	        			.addGap(93)
	        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
	        				.addComponent(jbtnReset, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(jbtnExit, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(jbtnConvert, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
	        			.addContainerGap(41, Short.MAX_VALUE))
	        );
	        getContentPane().setLayout(layout);

	        pack();
	  }
	  private void jbtnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExitActionPerformed
	        // TODO add your handling code here:
	        
	        System.exit(0);
	    }//GEN-LAST:event_jbtnExitActionPerformed

	    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetActionPerformed
	      dispose();
	      new converter().setVisible(true);
	    }//GEN-LAST:event_jbtnResetActionPerformed

	    private void jbtnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnConvertActionPerformed
	    	String GivenCurrency = jcmbCurrency.getSelectedItem() + "";
	    	String CurrencyToConvert = comboBox.getSelectedItem() + "";
	    	float value = 0;
	    	System.out.println(GivenCurrency);
	    	System.out.println(GivenCurrency.matches("Select Currency"));
	    	if(!GivenCurrency.matches("Select Currency")  && !value_to_convert.getText().isEmpty() && !CurrencyToConvert.matches("Select Currency")){
	    		value = Float.parseFloat(value_to_convert.getText());
	    		float result = ConvertCurrency(GivenCurrency , CurrencyToConvert ,value );
	    		ResultField.setText(result+"");
	    		
	    	}else {
	    		JOptionPane.showMessageDialog(null,"Please configure all fields" , "Error" , JOptionPane.ERROR_MESSAGE);
	    	}
	        
	    }//GEN-LAST:event_jbtnConvertActionPerformed


}
