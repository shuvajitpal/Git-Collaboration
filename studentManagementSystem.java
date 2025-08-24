package com.Broskieshub;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
class student {
   private int id;
   private String name;
   private String grade;
   public student(int id, String name, String grade) {
      this.id = id;
      this.name = name;
      this.grade = grade;
   }
   public int getId() {
      return id;
   }
   @Override
   public String toString() {
      return "Student ID: " + id + ", Name: " + name + ", Grade: " + grade;
   }
}
public class studentManagementSystem {
   private static ArrayList<student> students = new ArrayList<>();
   private static Scanner sc = new Scanner(System.in);
   public static void addStudent() {
      int id;
      String name;
      String grade;
      while (true) {
         System.out.print("Enter Student ID: ");
         if (sc.hasNextInt()) {
            id = sc.nextInt();
            sc.nextLine();
            if (id > 0) break;
            else System.out.println("❌ ID must be greater than 0. Try again.");
         } else {
            System.out.println("❌ Invalid input! Please enter an integer number.");
            sc.nextLine(); // clear wrong input
         }
      }
      System.out.print("Enter Student Name: ");
      name = sc.nextLine();
      while (true) {
         System.out.print("Enter Student Grade: ");
         grade = sc.nextLine().toUpperCase();
         if (grade.matches("A\\+\\+|A|B|C|D|E|F")) break;
         else System.out.println("❌ Invalid grade! Must be one of: A++, A, B, C, D, E, F.");
      }
      students.add(new student(id, name, grade));
      System.out.println("✅ Student added successfully.\n" + students.get(students.size() - 1));
   }
   public static void removeStudent() {
      System.out.println("Current Students:");
      for (student s : students) System.out.println(s);
      int id;
      try {
         System.out.print("Enter Student ID to remove: ");
         if (!sc.hasNextInt()) throw new NumberFormatException("❌ ID must be an integer.");
         id = sc.nextInt();
         sc.nextLine();
         if (id < 1) throw new NumberFormatException("❌ ID must be an integer greater than 0.");
      } catch (NumberFormatException e) {
         System.out.println(e.getMessage());
         sc.nextLine(); return;
      } catch (Exception e) {
         System.out.println("❌ Invalid input. Please enter a valid Student ID.");
         sc.nextLine();
         return;
      }
      boolean removed = false;
      Iterator<student> itr = students.iterator();
      while (itr.hasNext()) {
         student s = itr.next();
         if (s.getId() == id) {
            itr.remove();
            removed = true;
            break;
         }
      }
      if (!removed) System.out.println("⚠ No student found with ID " + id);
      else {
         System.out.println("✅ Student removed successfully.");
         System.out.println("Current Students:");
         for (student s : students) System.out.println(s);
      }
   }
   public static void displayStudents() {
      if (students.isEmpty()) System.out.println("No students to display.");
      else {
         System.out.println("📋 Student List:");
         for (student s : students) {
            System.out.println(s);
         }
      }
   }
   public static void main(String[] args) {
      while (true){
         System.out.println("----- Welcome to the Student Management Menu -----\n1. Add Student\n2. Remove Student\n3. View Students\n4. Exit");
         System.out.print("Please select an option: ");
         if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice >= 1 && choice <= 4)
               switch (choice) {
                  case 1: addStudent(); break;
                  case 2: removeStudent(); break;
                  case 3: displayStudents(); break;
                  case 4:
                     System.out.println("Exiting the system. Goodbye!");
                     return;
               }
            else System.out.println("❌ Invalid choice! Please enter a number between 1 and 4.");
         } else {
            System.out.println("❌ Invalid input! Please enter a number between 1 and 4.");
            sc.nextLine();
         }
      }
   }
}