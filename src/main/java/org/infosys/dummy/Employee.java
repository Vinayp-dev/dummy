package org.infosys.dummy;
//This is entity class for Employee table in database
public class Employee {
    private int id;
    private String name;
    private String dept;
    private double salary;
    private String email;

    // Constructors
    public Employee() {}

    public Employee(int id, String name, String dept, double salary, String email) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.email = email;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
