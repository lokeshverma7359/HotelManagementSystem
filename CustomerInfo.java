
// ==================== FILE 13: CustomerInfo.java ====================
package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import javax.swing.JTable;
import java.awt.event.*;

public class CustomerInfo extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    
    public CustomerInfo() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 900, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(450, 510, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);
        
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    conn c = new conn();
                    String displayCustomersql = "SELECT * FROM customer";
                    ResultSet rs = c.s.executeQuery(displayCustomersql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnLoadData.setBounds(300, 510, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);
        
        JLabel lblId = new JLabel("ID");
        lblId.setBounds(31, 11, 46, 14);
        contentPane.add(lblId);
        
        JLabel l1 = new JLabel("Number");
        l1.setBounds(150, 11, 46, 14);
        contentPane.add(l1);
        
        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(270, 11, 65, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(360, 11, 46, 14);
        contentPane.add(lblGender);
        
        table = new JTable();
        table.setBounds(0, 40, 900, 450);
        contentPane.add(table);
        
        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(480, 11, 46, 14);
        contentPane.add(lblCountry);
        
        JLabel lblRoom = new JLabel("Room");
        lblRoom.setBounds(600, 11, 46, 14);
        contentPane.add(lblRoom);
        
        JLabel lblStatus = new JLabel("Check-in Status");
        lblStatus.setBounds(680, 11, 100, 14);
        contentPane.add(lblStatus);
        
        JLabel lblNewLabel_1 = new JLabel("Deposit");
        lblNewLabel_1.setBounds(800, 11, 100, 14);
        contentPane.add(lblNewLabel_1);
        
        getContentPane().setBackground(Color.WHITE);
    }
    
    public static void main(String[] args) {
        try {
            new CustomerInfo().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}