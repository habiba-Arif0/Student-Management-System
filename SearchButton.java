package com;

import javax.swing.*;

public class SearchButton extends Button {

    private Students students;

    public SearchButton(String text, Students students) {
        super(text);
        this.students = students;
    }

    public void searchStudent(Table table) {
        String input = JOptionPane.showInputDialog(null,
                "Enter Student ID to search:",
                "Search Student",
                JOptionPane.QUESTION_MESSAGE);

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            int targetId = Integer.parseInt(input.trim());

            // BINARY SEARCH LOGIC - Applied to ArrayList<Student>
            int result = binarySearchById(targetId);

            if (result != -1) {  // return index
                // Student found
                Student student = students.getStudents().get(result);

                // Find in table and highlight
                int tableRow = findStudentInTable(table, student.getId());
                if (tableRow != -1) {
                    table.getTable().setRowSelectionInterval(tableRow, tableRow);
                    table.getTable().scrollRectToVisible(table.getTable().getCellRect(tableRow, 0, true));
                }

                // Show details
                String message = "Student Found!\n\n" +
                        "ID: " + student.getId() + "\n" +
                        "Name: " + student.getfirstname() + " " + student.getlastname() + "\n" +
                        "Age: " + student.getAge();

                JOptionPane.showMessageDialog(null, message, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Student with ID " + targetId + " not found!",
                        "Not Found",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid numeric ID!",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // BINARY SEARCH LOGIC -  for ArrayList<Student>
    private int binarySearchById(int targetId) {
        int low = 0;
        int high = students.getStudents().size() - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            int midId = students.getStudents().get(mid).getId();

            if(midId == targetId)
                return mid;
            else if(targetId > midId)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    private int findStudentInTable(Table table, int studentId) {
        for (int row = 0; row < table.getTable().getRowCount(); row++) {
            String idInTable = (String) table.getTable().getValueAt(row, 0);
            if (Integer.parseInt(idInTable) == studentId) {
                return row;
            }
        }
        return -1;
    }
}