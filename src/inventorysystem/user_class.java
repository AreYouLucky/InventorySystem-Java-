/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;
import javax.swing.table.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author John-Starbooks
 */
import java.sql.*;
public class user_class extends DB {
    
    private String fname;
    private String lname;
    private String username;
    private String password;
    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void showUsers(JTable usersTable){
        try{
            PreparedStatement stmt = con().prepareStatement("Select id, fname,lname,password,username from users");
            ResultSet rs = stmt.executeQuery();
            String[] header = {"ID","First Name","Last Name","username"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            usersTable.setModel(dtm);
            while(rs.next()){
                Object[] products = {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                dtm.addRow(products);
            }
            stmt.close();
            con().close();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            usersTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        }catch(Exception e){
            System.out.println(e+"show");
        } 
    }
    
    public void addUser(){ 
        try{
            PreparedStatement stmt = con().prepareStatement("Insert into users(fname,lname,password,username) values (?,?,?,?)");
            stmt.setString(1, getFname());
            stmt.setString(2,getLname());
            stmt.setString(3, getUsername());
            stmt.setString(4, getPassword());
            stmt.execute();
            stmt.close();
            con().close();
        }
        catch(Exception e){
            System.out.println(e);
        } 
    }
    
    public void editUser(int id){
        
        try {
            PreparedStatement stmt;
            stmt = con().prepareStatement("select * from users where id = ?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                setFname(rs.getString(2));
                setLname(rs.getString(3));
                setUsername(rs.getString(4));
                setPassword(rs.getString(5));             
            }
            stmt.close();
            con().close();
            
        } catch (Exception e) {
            System.out.println(e+"findID");
        }
    }
    
    public void updateUser(int id){
        try{
            PreparedStatement stmt = con().prepareStatement("Update users set fname = ?, lname = ?,"
                    + "username = ?, password = ? where id = ?");
            stmt.setString(1, getFname());
            stmt.setString(2,getLname());
            stmt.setString(3, getUsername());
            stmt.setString(4, getPassword());
            stmt.setInt(5, id);
            stmt.execute();
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+"update");
        }
    }
    
    public void deleteUser(int id){
        try{
            PreparedStatement stmt= con().prepareStatement("Delete from users where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
            con().close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    

    
}
