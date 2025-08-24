package com.Broskieshub.CSvReadAndWrite;
import java.io.*;
public class csvReadWrite {
   public static void main(String[] args) {
      String inputFile = "C:\\Users\\Shuvajit\\IdeaProjects\\MyJava Project\\src\\com\\Broskieshub\\products.csv";
      String outputFile = "expensive_products.csv";
      try (
              BufferedReader reader = new BufferedReader(new FileReader(inputFile));
              BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
      ) {
         String line;
         line = reader.readLine();
         if (line != null) writer.write(line + "\n");
         while ((line = reader.readLine()) != null) {
            String[] parts = line.split(","); // Split by comma
            String Name = parts[0];
            double Price = Double.parseDouble(parts[1]);
            if (Price > 10000) {
               writer.write(Name + "," + Price + "\n");
            }
         }
         System.out.println("✅ Products filtered successfully! Check '" + outputFile + "'.");
      } catch (IOException e) {
         System.out.println("❌ Error: " + e.getMessage());
      }
   }
}
