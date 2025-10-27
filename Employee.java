// ==================== FILE 12: Employee.java ====================
package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import java.sql.*;
import java.awt.event.*;

public class Employee extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    
    public Employee() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        table = new JTable();
        table.setBounds(0, 34, 1000, 450);
        contentPane.add(table);
        
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String displayCustomersql = "SELECT * FROM employee";
                    ResultSet rs = c.s.executeQuery(displayCustomersql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnLoadData.setBounds(350, 500, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);
        
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(510, 500, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);
        
        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(41, 11, 46, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lblJob = new JLabel("Age");
        lblJob.setBounds(159, 11, 46, 14);
        contentPane.add(lblJob);
        
        JLabel lblName = new JLabel("Gender");
        lblName.setBounds(273, 11, 46, 14);
        contentPane.add(lblName);
        
        JLabel lblDepartment = new JLabel("Job");
        lblDepartment.setBounds(416, 11, 86, 14);
        contentPane.add(lblDepartment);
        
        JLabel l1 = new JLabel("Salary");
        l1.setBounds(536, 11, 86, 14);
        contentPane.add(l1);
        
        JLabel l2 = new JLabel("Phone");
        l2.setBounds(656, 11, 86, 14);
        contentPane.add(l2);
        
        JLabel l3 = new JLabel("Aadhar");
        l3.setBounds(786, 11, 86, 14);
        contentPane.add(l3);
        
        JLabel l4 = new JLabel("Gmail");
        l4.setBounds(896, 11, 86, 14);
        contentPane.add(l4);
        
        getContentPane().setBackground(Color.WHITE);
    }
    
    public static void main(String[] args) {
        try {
            new Employee().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}