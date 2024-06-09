/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author John-Starbooks
 */
public class product_class extends DB {
    private String name;
    private double price;
    private int quantity;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    public void showProducts(JTable productsTable){
        try{
            PreparedStatement stmt = con().prepareStatement("Select * from products;");
            ResultSet rs = stmt.executeQuery();
            String[] header = {"ID","Name","Quantity","Price"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            productsTable.setModel(dtm);
            while(rs.next()){
                Object[] products = {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)};
                dtm.addRow(products);
            }
            stmt.close();
            con().close();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            productsTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        }catch(Exception e){
            System.out.println(e+"show");
        } 
    }
    
    public void addProducts(){ 
        try{
            PreparedStatement stmt = con().prepareStatement("Insert into products(name,price,quantity) values (?,?,?)");
            stmt.setString(1, getName());
            stmt.setInt(2,getQuantity());
            stmt.setDouble(3, getPrice());
            stmt.execute();
            stmt.close();
            con().close();
        }
        catch(Exception e){
            System.out.println(e);
        } 
    }
    
    public void editProduct(int id){
        
        try {
            PreparedStatement stmt;
            stmt = con().prepareStatement("select * from products where id = ?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                setName(rs.getString(2));
                setQuantity(rs.getInt(3));
                setPrice(rs.getDouble(4));            
            }
            stmt.close();
            con().close();
            
        } catch (Exception e) {
            System.out.println(e+"findID");
        }
    }
    
    public void updateProduct(int id){
        try{
            PreparedStatement stmt = con().prepareStatement("Update products set name = ?, quantity = ?,"
                    + "price = ? where id = ?");
            stmt.setString(1, getName());
            stmt.setInt(2,getQuantity());
            stmt.setDouble(3, getPrice());
            stmt.setInt(4, id);
            stmt.execute();
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+"update");
        }
    }
    
    public void deleteProduct(int id){
        try{
            PreparedStatement stmt= con().prepareStatement("Delete from products where id = ?");
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
