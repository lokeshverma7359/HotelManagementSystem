
// ==================== FILE 11: Department.java ====================
package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import java.sql.*;
import java.awt.event.*;

public class Department extends JFrame {
    Connection conn = null;
    private JPanel contentPane;
    private JTable table;
    private JLabel lblNewLabel, lblNewLabel_1;
    
    public Department() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 700, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        table = new JTable();
        table.setBounds(0, 40, 700, 350);
        contentPane.add(table);
        
        JButton btnNewButton = new JButton("Load Data");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String displayCustomersql = "SELECT * FROM department";
                    ResultSet rs = c.s.executeQuery(displayCustomersql);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(170, 410, 120, 30);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnNewButton_1.setBounds(400, 410, 120, 30);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.setForeground(Color.WHITE);
        contentPane.add(btnNewButton_1);
        
        lblNewLabel = new JLabel("Department");
        lblNewLabel.setBounds(145, 11, 105, 14);
        contentPane.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Budget");
        lblNewLabel_1.setBounds(431, 11, 75, 14);
        contentPane.add(lblNewLabel_1);
        
        getContentPane().setBackground(Color.WHITE);
    }
    
    public static void main(String[] args) {
        try {
            new Department().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}