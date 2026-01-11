package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JPanel implements ActionListener {

    private int width;
    private int height;
    private int containerHeight;
    private Students students;
    private Table table;
    private JPanel container;
    private JLabel id;
    private JTextField idText;
    private JLabel firstname;
    private JTextField firstnameText;
    private JLabel lastname;
    private JTextField lastnameText;
    private JLabel age;
    private JTextField ageText;
    private AddButton addButton;
    private EditButton editButton;
    private SaveButton saveButton;
    private DeleteButton deleteButton;
    private SearchButton searchButton;  // NEW
    private SortButton sortButton;      // NEW

    public Form(int width, int height, Students students, Table table) {
        this.width = width / 3;
        this.height = height;
        this.containerHeight = 120;
        this.setPreferredSize(new Dimension(this.width, this.height));

        this.students = students;
        this.table = table;

        this.container = new JPanel();
        this.container.setLayout(new GridLayout(4, 2, 0, 10));
        this.container.setPreferredSize(new Dimension(this.width - 10, this.containerHeight));

        this.id = new JLabel("ID");
        this.idText = new JTextField();
        this.setUpIdTextField();

        this.firstname = new JLabel("Firstname");
        this.firstnameText = new JTextField();

        this.lastname = new JLabel("Lastname");
        this.lastnameText = new JTextField();

        this.age = new JLabel("Age");
        this.ageText = new JTextField();

        this.addButton = new AddButton("ADD", this.students);
        this.addButton.addActionListener(this);

        this.editButton = new EditButton("EDIT");
        this.editButton.addActionListener(this);

        this.saveButton = new SaveButton("SAVE");
        this.saveButton.addActionListener(this);

        this.deleteButton = new DeleteButton("DELETE");
        this.deleteButton.addActionListener(this);

        // NEW: Initialize Search and Sort buttons
        this.searchButton = new SearchButton("SEARCH", this.students);
        this.searchButton.addActionListener(this);

        this.sortButton = new SortButton("SORT BY AGE", this.students);
        this.sortButton.addActionListener(this);

        this.container.add(this.id);
        this.container.add(this.idText);
        this.container.add(this.firstname);
        this.container.add(this.firstnameText);
        this.container.add(this.lastname);
        this.container.add(this.lastnameText);
        this.container.add(this.age);
        this.container.add(this.ageText);
        this.add(this.container);
        this.add(this.addButton);
        this.add(this.editButton);
        this.add(this.saveButton);
        this.add(this.deleteButton);
        this.add(this.searchButton);   // NEW
        this.add(this.sortButton);     // NEW
    }

    private void setUpIdTextField() {
        // Since ArrayList is ALWAYS sorted by ID, last student has highest ID
        if (this.students.getStudents().size() > 0) {
            this.idText.setText(Integer.toString(
                    this.students.getStudents().get(this.students.getStudents().size() - 1).getId() + 1
            ));
        } else {
            this.idText.setText("1");
        }
        this.idText.setCaretPosition(this.idText.getText().length());
    }

    private boolean validateForm(String id, String firstname, String lastname, String age) {
        boolean isValid = true;

        if (id.trim().equals("") || id.trim().equals(" ") || !this.isNumber(id)) {
            isValid = false;
        }
        if (firstname.trim().equals("") || firstname.trim().equals(" ")) {
            isValid = false;
        }
        if (lastname.trim().equals("") || lastname.trim().equals(" ")) {
            isValid = false;
        }
        if (age.trim().equals("") || age.trim().equals(" ") || !this.isNumber(age)) {
            isValid = false;
        }

        return isValid;
    }

    private boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean checkIdExists(int id) {
        for (Student student : this.students.getStudents()) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void emptyForm() {
        this.idText.setText("");
        this.firstnameText.setText("");
        this.lastnameText.setText("");
        this.ageText.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.addButton) {
            String id = this.idText.getText();
            String firstname = this.firstnameText.getText();
            String lastname = this.lastnameText.getText();
            String age = this.ageText.getText();

            boolean formIsValid = this.validateForm(id, firstname, lastname, age);

            if (formIsValid) {
                boolean idExists = this.checkIdExists(Integer.parseInt(id));

                if (!idExists) {
                    this.addButton.addStudent(this.table, id, firstname, lastname, age);
                    this.emptyForm();
                    this.setUpIdTextField();
                    JOptionPane.showMessageDialog(null, "Student added successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "ID Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Form Invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == this.saveButton) {
            this.saveButton.saveData(this.students);
        }
        if (e.getSource() == this.editButton) {
            this.editButton.edit(this.table.getTable(), this.students, this.idText, this.firstnameText, this.lastnameText, this.ageText);
        }
        if (e.getSource() == this.deleteButton) {
            this.deleteButton.delete(this.table.getTable(), this.students);
            this.setUpIdTextField();
        }
        // NEW: Handle search button click
        if (e.getSource() == this.searchButton) {
            this.searchButton.searchStudent(this.table);
        }
        // NEW: Handle sort button click
        if (e.getSource() == this.sortButton) {
            this.sortButton.toggleSort(this.table);
        }
    }
}