package com.Broskieshub.JDBCProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class insertUser {
   public static void main(String[] args) {
      Connection con = null;
      PreparedStatement ps = null;
      Statement st = null;
      ResultSet rs = null;
      try {
         String url = "jdbc:mysql://localhost:3306/testdb";
         String user = "root";
         String password = "Micra135@";

         con = DriverManager.getConnection(url, user, password);
         System.out.println("Connected to database!");

         String sql = "INSERT INTO user (name, email) VALUES (?, ?)";

         ps = con.prepareStatement(sql);

         ps.setString(1, "Shuvajit Pal");
         ps.setString(2, "shuvajitpal103@gmail.com");
         ps.executeUpdate();

         ps.setString(1, "Debasish Behera");
         ps.setString(2, "debasish@tech69.com");
         ps.executeUpdate();

         ps.setString(1, "Farhat Akram");
         ps.setString(2, "farhatakram7077@gmail.com");
         ps.executeUpdate();

         int rowsInserted = ps.executeUpdate();
         if (rowsInserted > 0) System.out.println("A new user was inserted successfully!");

         st = con.createStatement();
         rs = st.executeQuery("SELECT * FROM user");

         System.out.println("\nUser Table Data:");
         while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");

            System.out.println(id + " | " + name + " | " + email);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (ps != null) ps.close();
            if (con != null) con.close();
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
   }
}
