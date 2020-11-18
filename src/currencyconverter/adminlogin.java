package currencyconverter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class adminlogin extends JFrame {

	private JPanel contentPane;
	private String username = "Admin";
	private String password;
	private JPasswordField passwordtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlogin frame = new adminlogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminlogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 50, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setBounds(97, 10, 219, 49);
		lblAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 40));
		contentPane.add(lblAdminLogin);
		
		JLabel label = new JLabel("Username");
		label.setFont(new Font("Tahoma", Font.PLAIN, 21));
		label.setBounds(59, 89, 94, 26);
		contentPane.add(label);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblPassword.setBounds(59, 143, 94, 26);
		contentPane.add(lblPassword);
		
		JTextArea usernametxt = new JTextArea();
		usernametxt.setText("Admin");
		usernametxt.setBounds(230, 94, 132, 26);
		contentPane.add(usernametxt);

		passwordtxt = new JPasswordField();
		passwordtxt.setBounds(230, 151, 132, 26);
		contentPane.add(passwordtxt);
		
		JLabel errormessage = new JLabel("Invalid username or password");
		errormessage.setForeground(Color.RED);
		errormessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errormessage.setBounds(10, 193, 210, 60);
		errormessage.setVisible(false);
		contentPane.add(errormessage);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = usernametxt.getText();
				password = passwordtxt.getText();
				if(ValidateCredentials(username , password)) {
					errormessage.setVisible(false);
					dispose();
					new adminpanel().setVisible(true);
				}else {
					errormessage.setVisible(true);
					System.out.println("Invalid User");
				}
				
				
			}
		});
		btnLogin.setBounds(230, 205, 132, 33);
		contentPane.add(btnLogin);
		
		
		
	}
	
	public boolean ValidateCredentials (String username , String password) {
		  String query = "SELECT COUNT(*) AS TOTAL FROM `users` WHERE `username` = '"+username.trim()+"' AND password = '"+password.trim()+"'";
		  connectiondatabase connection = new connectiondatabase();
	        try (Statement stmt = connection.getconnection().createStatement()) {

	            ResultSet rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                String total = rs.getString("TOTAL");
	                int i_total = Integer.parseInt(total);
	                
	                if(i_total>0){
	                	System.out.println("Validated User");
	                    return true;
	                    
	                }else {
	                	System.out.println(query);
	                	System.out.println(i_total);
	                	return false;
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println(""+e.getMessage().toString());
	            //JDBCTutorialUtilities.printSQLException(e);
	            
	        }
	        return false;
	}
}
