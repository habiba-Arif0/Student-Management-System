package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    private JTextField textFieldName;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public Login() {
        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null); // Center on screen
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Aesthetic pastel background color
        getContentPane().setBackground(new Color(219, 236, 245));

        // Username Label & TextField
        JLabel labelName = new JLabel("Username");
        labelName.setBounds(40, 30, 100, 20);
        labelName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelName.setForeground(new Color(44, 62, 80)); // Navy-blue text
        add(labelName);

        textFieldName = new JTextField();
        textFieldName.setBounds(150, 30, 180, 25);
        textFieldName.setBackground(new Color(245, 248, 250)); // Very light blue/gray
        textFieldName.setBorder(BorderFactory.createLineBorder(new Color(180, 208, 218))); // Subtle border
        textFieldName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(textFieldName);

        // Password Label & PasswordField
        JLabel labelPass = new JLabel("Password");
        labelPass.setBounds(40, 80, 100, 20);
        labelPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelPass.setForeground(new Color(44, 62, 80));
        add(labelPass);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 80, 180, 25);
        passwordField.setBackground(new Color(245, 248, 250));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 208, 218)));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 140, 120, 30);
        loginButton.setBackground(new Color(70, 130, 180)); // Muted steel blue
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(180, 208, 218))); // Optional border to match fields
        loginButton.addActionListener(this);
        add(loginButton);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Login button clicked
        if (e.getSource() == loginButton) {
            String username = textFieldName.getText().trim();
            String password = new String(passwordField.getPassword());

            // Simple validation: check if username/password are not empty
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Simple hardcoded login check (replace with your logic)
            if (username.equals("habiba") && password.equals("12345")) {
                JOptionPane.showMessageDialog(this, "Login successful!");

                // Close login window
                this.dispose();

                // Open main student management GUI:
                openMainGUI();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    private void openMainGUI() {
        // Create the students data model
        Students students = new Students();

        // Create main frame
        MyFrame frame = new MyFrame();

        int width = frame.getWidth();
        int height = frame.getHeight();

        // Create Table and Form with data model
        Table table = new Table(width, height, students);
        Form form = new Form(width, height, students, table);

        // Prepare panel with layout to add Form and Table side by side
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(form, BorderLayout.WEST);
        panel.add(table, BorderLayout.EAST);

        // Add panel to frame and show
        frame.add(panel);
        frame.setVisible(true);
    }


}
