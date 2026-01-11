package com;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Students {
// to store multiple students, we use arraylist which is dynamic means
// that it can adjust the size itself.
    private ArrayList<Student> students;

    public Students() {
        this.students = new ArrayList<>();
        this.readData();
    }
//overloading
    public void addStudent(Student student) {
        this.students.add(student);
    }

//this shows method overloading. which is polymorphism
    public void addStudent(int index, Student student) {
        this.students.add(index, student);
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public Object[][] getData() {
                                        //rows            col
        Object[][] data = new Object[this.students.size()][4];

        int index = 0;
        //for loop use hua hai
        for (Student student : students) {

            data[index][0] = String.valueOf(student.getId());
            data[index][1] = student.getfirstname();
            data[index][2] = student.getlastname();
            //
            data[index][3] = String.valueOf(student.getAge());

            index++;
        }
        return data;
    }

    private void readData() {
        try {
            File file = new File("students.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty())
                    continue;
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String firstname = parts[1].trim();
                    String lastname = parts[2].trim();
                    int age = Integer.parseInt(parts[3].trim());
                    Student student = new Student(id, firstname, lastname, age);
                    this.students.add(student);
                }
            }
            sc.close();
        } catch (Exception e) {

        }
    }

}
