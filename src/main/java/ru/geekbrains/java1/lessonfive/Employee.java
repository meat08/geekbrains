package ru.geekbrains.java1.lessonfive;

public class Employee {
    private String fio;
    private String post;
    private String email;
    private String tel;
    private int salary;
    private int age;

    public Employee(String fio, String post, String email, String tel, int salary, int age) {
        this.fio = fio;
        this.post = post;
        this.email = email;
        this.tel = tel;
        this.salary = salary;
        this.age = age;
    }

//    public String getFio() {return fio;}
//    public String getPost() {return post;}
//    public String getEmail() {return email;}
//    public String getTel() {return tel;}
//    public double getSalary() {return salary;}
    public int getAge() {return age;}

    public void employeeShow() {
        System.out.format("%22s%3s%10s%3s%15s%3s%5s%3s%10d%3s%5d\n", fio, "|", post, "|", email, "|", tel, "|", salary, "|", age);
    }

}
