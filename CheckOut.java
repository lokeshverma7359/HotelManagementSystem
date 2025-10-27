// ==================== FIXED FILE: CheckOut.java ====================
package hotel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class CheckOut extends JFrame {
    private JPanel contentPane;
    private JTextField t1;
    Choice c1;
    
    public CheckOut() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 294);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/sixth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(300, 0, 500, 225);
        add(l1);
        
        JLabel lblCheckOut = new JLabel("Check Out ");
        lblCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCheckOut.setBounds(70, 11, 140, 35);
        contentPane.add(lblCheckOut);
        
        JLabel lblName = new JLabel("Number :");
        lblName.setBounds(20, 85, 80, 14);
        contentPane.add(lblName);
        
        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
            rs.close();
            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(130, 82, 150, 20);
        contentPane.add(c1);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/tick.png"));
        Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JButton l2 = new JButton(i6);
        l2.setBounds(290, 82, 20, 20);
        add(l2);
        
        l2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                conn c = null;
                PreparedStatement pst = null;
                ResultSet rs = null;
                try {
                    c = new conn();
                    String number = c1.getSelectedItem();
                    
                    // FIXED: Use PreparedStatement
                    String query = "SELECT room_number FROM customer WHERE number = ?";
                    pst = c.c.prepareStatement(query);
                    pst.setString(1, number);
                    rs = pst.executeQuery();
                    
                    if (rs.next()) {
                        t1.setText(rs.getString("room_number"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pst != null) pst.close();
                        if (c != null) c.closeConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(20, 132, 86, 20);
        contentPane.add(lblRoomNumber);
        
        t1 = new JTextField();
        t1.setBounds(130, 132, 150, 20);
        contentPane.add(t1);
        
        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = c1.getSelectedItem();
                String roomNumber = t1.getText().trim();
                
                if (roomNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a customer first");
                    return;
                }
                
                conn c = null;
                PreparedStatement pst1 = null;
                PreparedStatement pst2 = null;
                
                try {
                    c = new conn();
                    
                    // FIXED: Use PreparedStatement for DELETE
                    String deleteSQL = "DELETE FROM customer WHERE number = ?";
                    pst1 = c.c.prepareStatement(deleteSQL);
                    pst1.setString(1, id);
                    pst1.executeUpdate();
                    
                    // FIXED: Use PreparedStatement for UPDATE
                    String updateSQL = "UPDATE room SET availability = 'Available' WHERE room_number = ?";
                    pst2 = c.c.prepareStatement(updateSQL);
                    pst2.setString(1, roomNumber);
                    pst2.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Check Out Successful");
                    new Reception().setVisible(true);
                    setVisible(false);
                    
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error during checkout: " + e1.getMessage());
                } finally {
                    try {
                        if (pst1 != null) pst1.close();
                        if (pst2 != null) pst2.close();
                        if (c != null) c.closeConnection();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnCheckOut.setBounds(50, 200, 100, 25);
        btnCheckOut.setBackground(Color.BLACK);
        btnCheckOut.setForeground(Color.WHITE);
        contentPane.add(btnCheckOut);
        
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(160, 200, 100, 25);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);
        
        getContentPane().setBackground(Color.WHITE);
    }
    
    public static void main(String[] args) {
        try {
            new CheckOut().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}