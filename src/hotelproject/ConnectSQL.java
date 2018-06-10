/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelproject;

/**
 *
 * @author Cute
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Cute
 */
public class ConnectSQL {
    public static Connection connectDB(){
      try{
          Connection conn=null;
          Class.forName("com.mysql.jdbc.Driver");
          conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","system");
          //JOptionPane.showMessageDialog(null,"Connected to Database");
          return conn;
      }  catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
          return null;
      }
    }
}