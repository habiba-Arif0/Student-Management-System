package com;

public class Student {

    private int id;
    private String firstname;
    private String lastname;
    private int age;

    public Student() {
    }

    public Student(int id, String firstname, String lastname, int age) { //parameters inside student constructor
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

// toString gives a string representation to the object. when used as default without override it gives object+ memory address
    //but when used with override it gives custom line. like proper words.
    @Override
    public String toString() {
        return this.id + "," + this.firstname + "," + this.lastname + "," + this.age;
    }
}
