package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SortButton extends Button {

    private Students students;
    private boolean isSortedByAge = false;

    public SortButton(String text, Students students) {
        super(text);
        this.students = students;
    }

    public void toggleSort(Table table) {
        if (isSortedByAge) {
            // Restore to ID order
            restoreIdOrder(table);
            isSortedByAge = false;
            JOptionPane.showMessageDialog(null,
                    "Restored to ID order",
                    "Sort",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Sort by age
            sortByAgeView(table);
            isSortedByAge = true;
            JOptionPane.showMessageDialog(null,
                    "Sorted by Age (Ascending)\n\nNote: Internal data remains ID-sorted.\n Search will work correctly!",
                    "Sort",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void sortByAgeView(Table table) {
        // Create a copy of the student list
        ArrayList<Student> tempList = new ArrayList<>();
        for (Student student : students.getStudents()) {
            tempList.add(student);
        }

        // YOUR EXACT INSERTION SORT LOGIC - Applied to ArrayList<Student>
        insertionSortByAge(tempList);

        // Display sorted list in table
        displayInTable(table, tempList);
    }

    //  INSERTION SORT LOGIC
    private void insertionSortByAge(ArrayList<Student> tempList) {
        for(int i = 1; i < tempList.size(); i++) {
            Student current = tempList.get(i);
            int j = i - 1;

            while(j >= 0 && tempList.get(j).getAge() > current.getAge()) {
                // Keep swapping
                tempList.set(j+1, tempList.get(j));
                j--;
            }
            tempList.set(j+1, current);
        }
    }

    private void restoreIdOrder(Table table) {
        // Display original list (still ID-sorted)
        displayInTable(table, students.getStudents());
    }

    private void displayInTable(Table table, ArrayList<Student> studentList) {
        DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
        model.setRowCount(0);

        for (Student student : studentList) {
            Object[] data = {
                    String.valueOf(student.getId()),
                    student.getfirstname(),
                    student.getlastname(),
                    String.valueOf(student.getAge())
            };
            model.addRow(data);
        }
    }

    public boolean isSortedByAge() {
        return isSortedByAge;
    }
}