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
public class login_class extends DB {
    public boolean logAuth(String username, char[] password){
        boolean auth = false;
        try{
            String query = "Select * from users where username = ? and password = ?";
            PreparedStatement stmt;
            stmt = con().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, new String(password));
            ResultSet rs = stmt.executeQuery();
            auth = rs.next();
            stmt.close();
            
        }catch(Exception e){
            System.out.println(e+"login");
        }
        return auth;
    }
}
