package com.Broskieshub;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class Employee {
    private String name;
    private int age;
    private double salary;
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "Employee{name='" + name + "', Age=" + age + ", Salary=" + salary + '}';
    }
}
public class EmployeeSorter {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Abir", 30, 53000));
        employees.add(new Employee("Baibhab", 25, 6700));
        employees.add(new Employee("Chandan", 28, 15000));
        employees.add(new Employee("Debangshu", 35, 70000));
        employees.add(new Employee("Einstine", 32, 40000));
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e2.getSalary(), e1.getSalary());
            }
        });
        System.out.println("Employees sorted by Salary (Descending):");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return e1.getName().compareToIgnoreCase(e2.getName());
            }
        });
        System.out.println("\nSorted by Name (Ascending):");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
