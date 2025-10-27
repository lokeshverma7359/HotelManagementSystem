// ==================== FIXED FILE: AddEmployee.java ====================
package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddEmployee extends JFrame {
    JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6;
    JComboBox c1;
    JRadioButton NewRadioButton, Female;
    
    public AddEmployee() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD EMPLOYEE DETAILS");
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778, 486);
        getContentPane().setLayout(null);
        
        JLabel Passportno = new JLabel("NAME");
        Passportno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Passportno.setBounds(60, 30, 150, 27);
        add(Passportno);
        
        textField = new JTextField();
        textField.setBounds(200, 30, 150, 27);
        add(textField);
        
        JButton Next = new JButton("SAVE");
        Next.setBounds(200, 420, 150, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        add(Next);
        
        JLabel Pnrno = new JLabel("AGE");
        Pnrno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Pnrno.setBounds(60, 80, 150, 27);
        add(Pnrno);
        
        textField_1 = new JTextField();
        textField_1.setBounds(200, 80, 150, 27);
        add(textField_1);
        
        JLabel Gender = new JLabel("GENDER");
        Gender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Gender.setBounds(60, 120, 150, 27);
        add(Gender);
        
        NewRadioButton = new JRadioButton("MALE");
        NewRadioButton.setBackground(Color.WHITE);
        NewRadioButton.setBounds(200, 120, 70, 27);
        add(NewRadioButton);
        
        Female = new JRadioButton("FEMALE");
        Female.setBackground(Color.WHITE);
        Female.setBounds(280, 120, 70, 27);
        add(Female);
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(NewRadioButton);
        genderGroup.add(Female);
        
        JLabel Address = new JLabel("JOB");
        Address.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Address.setBounds(60, 170, 150, 27);
        add(Address);
        
        String course[] = {"Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Waiter/Waitress", "Manager", "Accountant", "Chef"};
        c1 = new JComboBox(course);
        c1.setBackground(Color.WHITE);
        c1.setBounds(200, 170, 150, 30);
        add(c1);
        
        JLabel Nationality = new JLabel("SALARY");
        Nationality.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Nationality.setBounds(60, 220, 150, 27);
        add(Nationality);
        
        textField_3 = new JTextField();
        textField_3.setBounds(200, 220, 150, 27);
        add(textField_3);
        
        JLabel Name = new JLabel("PHONE");
        Name.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Name.setBounds(60, 270, 150, 27);
        add(Name);
        
        textField_4 = new JTextField();
        textField_4.setBounds(200, 270, 150, 27);
        add(textField_4);
        
        JLabel Phno = new JLabel("AADHAR");
        Phno.setFont(new Font("Tahoma", Font.PLAIN, 17));
        Phno.setBounds(60, 320, 150, 27);
        add(Phno);
        
        textField_5 = new JTextField();
        textField_5.setBounds(200, 320, 150, 27);
        add(textField_5);
        
        JLabel email = new JLabel("EMAIL");
        email.setFont(new Font("Tahoma", Font.PLAIN, 17));
        email.setBounds(60, 370, 150, 27);
        add(email);
        
        textField_6 = new JTextField();
        textField_6.setBounds(200, 370, 150, 27);
        add(textField_6);
        
        setVisible(true);
        
        JLabel AddPassengers = new JLabel("ADD EMPLOYEE DETAILS");
        AddPassengers.setForeground(Color.BLUE);
        AddPassengers.setFont(new Font("Tahoma", Font.PLAIN, 31));
        AddPassengers.setBounds(450, 24, 442, 35);
        add(AddPassengers);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("hotel/management/system/icons/tenth.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel image = new JLabel(i2);
        image.setBounds(410, 80, 480, 410);
        add(image);
        
        Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                conn c = null;
                PreparedStatement pst = null;
                try {
                    String name = textField.getText().trim();
                    String age = textField_1.getText().trim();
                    String salary = textField_3.getText().trim();
                    String phone = textField_4.getText().trim();
                    String aadhar = textField_5.getText().trim();
                    String email = textField_6.getText().trim();
                    
                    // Input validation
                    if (name.isEmpty() || age.isEmpty() || salary.isEmpty() || 
                        phone.isEmpty() || aadhar.isEmpty() || email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }
                    
                    // Validate age and salary are numbers
                    try {
                        Integer.parseInt(age);
                        Double.parseDouble(salary);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Age and Salary must be valid numbers");
                        return;
                    }
                    
                    String gender = null;
                    if (NewRadioButton.isSelected()) {
                        gender = "male";
                    } else if (Female.isSelected()) {
                        gender = "female";
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select gender");
                        return;
                    }
                    
                    String job = (String) c1.getSelectedItem();
                    
                    c = new conn();
                    // FIXED: Use PreparedStatement
                    String str = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                    pst = c.c.prepareStatement(str);
                    pst.setString(1, name);
                    pst.setString(2, age);
                    pst.setString(3, gender);
                    pst.setString(4, job);
                    pst.setString(5, salary);
                    pst.setString(6, phone);
                    pst.setString(7, aadhar);
                    pst.setString(8, email);
                    
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Employee Added Successfully");
                    setVisible(false);
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
                } finally {
                    try {
                        if (pst != null) pst.close();
                        if (c != null) c.closeConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        setSize(900, 600);
        setVisible(true);
        setLocation(530, 200);
    }
    
    public static void main(String[] args) {
        new AddEmployee();
    }
}