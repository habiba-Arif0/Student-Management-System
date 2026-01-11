package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditButton extends Button {

    public EditButton(String text) {
        super(text);
    }

    public void edit(JTable table, Students students, JTextField id, JTextField firstname, JTextField lastname, JTextField age) {
        int row = table.getSelectedRow();
        if (row > -1) {
            Object[] data = students.getData()[row];
            students.getStudents().remove(row);
            ((DefaultTableModel)table.getModel()).removeRow(row);

            id.setText("");
            firstname.setText("");
            lastname.setText("");
            age.setText("");

            id.setText((String)data[0]);
            firstname.setText((String)data[1]);
            lastname.setText((String)data[2]);
            age.setText((String)data[3]);
        }
    }
}
