package com.julie.employee.model;

public class Employee {
    private int Id;
    private String name;
    private int salary;
    private int age;

    public Employee(){

    }

    public Employee(int id, String name, int salary, int age) {
        this.Id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
