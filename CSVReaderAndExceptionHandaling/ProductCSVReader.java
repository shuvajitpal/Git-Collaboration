package com.Broskieshub.CSVReaderAndExceptionHandaling;
import java.io.*;

class InvalidProductDataException extends Exception {
   public InvalidProductDataException(String message) {
      super(message);
   }
}
public class ProductCSVReader {
   public static void main(String[] args) {
      BufferedReader reader = null;
      try {
         reader = new BufferedReader(new FileReader("C:\\Users\\Shuvajit\\IdeaProjects\\MyJava Project\\src\\com\\Broskieshub\\Product.csv"));
         String line;
         while ((line = reader.readLine()) != null) {
            try {
               String[] parts = line.split(",");
               if (parts.length < 3 || parts[1].isEmpty()) throw new InvalidProductDataException("Invalid row: " + line);
               int id = Integer.parseInt(parts[0]);
               String name = parts[1];
               double price = Double.parseDouble(parts[2]);
               System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
            }
            catch (NumberFormatException e) {
               System.err.println("Error parsing number in row: " + line);
            }
            catch (InvalidProductDataException e) {
               System.err.println("Custom Exception: " + e.getMessage());
            }
         }
      }
      catch (FileNotFoundException e) {
         System.err.println("File not found: product.csv");
      }
      catch (IOException e) {
         System.err.println("Error reading file: " + e.getMessage());
      }
      finally {
         try {
            if (reader != null) reader.close();
         } catch (IOException e) {
            System.err.println("Error closing reader: " + e.getMessage());
         }
      }
   }
}

