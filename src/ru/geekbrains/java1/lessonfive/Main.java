package ru.geekbrains.java1.lessonfive;

public class Main {

    public static void main(String[] args) {
        Employee[] employeeArr = new Employee[5];
        employeeArr[0] = new Employee("Господин Уэф", "чатланин", "uef@pluk.ku", "111", 50, 60);
        employeeArr[1] = new Employee("Би", "пацак", "bi@pluk.ku", "222", 10, 40);
        employeeArr[2] = new Employee("Дядя Вова", "пацак", "dv@earth.kc", "333", 4, 42);
        employeeArr[3] = new Employee("Гедеван Александрович", "пацак", "ga@earth.kc", "444", 2, 26);
        employeeArr[4] = new Employee("Господин ПЖ", "чатланин", "pj@pluk.ku", "000", 200000, 50);

        System.out.format("%22s%3s%10s%3s%15s%3s%5s%3s%10s%3s%5s\n", "ФИО", "|", "Должность", "|", "email", "|", "Тел.", "|", "ЗП", "|", "Возраст");
        for(Employee employee : employeeArr) {
            if(employee.getAge() > 40) employee.employeeShow();
        }
    }
}
