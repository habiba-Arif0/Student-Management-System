package com;

public class AddButton extends Button {

    private Students students;

    public AddButton(String text, Students students) {
        super(text);
        this.students = students;
    }

    public void addStudent(Table table, String id, String firstname, String lastname, String age) {
        //  add a student.

        Student student = new Student(Integer.parseInt(id), firstname, lastname, Integer.parseInt(age));
// logic for adding
        int index = this.students.getStudents().size();
        for (int i=this.students.getStudents().size() - 1; i>=0; i--) {
            if (Integer.parseInt(id) > students.getStudents().get(i).getId()) {
                break;
            }
            index--;
        }
        table.insertRow(index, id, firstname, lastname, age);
        this.students.addStudent(index, student);
    }
}
