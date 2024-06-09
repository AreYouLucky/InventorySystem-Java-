/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;
import java.sql.*;
/**
 *
 * @author John-Starbooks
 */
public class DB {
    public Connection con(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/inventory_system?user=root&password=");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.toString());
            return null;           
        }
    }
}
