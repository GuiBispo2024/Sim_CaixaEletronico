/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADM
 */
public class Database {
    public static void main(String[] args){
        Connect();
        System.out.println("Connection established!!");
    }
    
    static String URL = "jdbc:postgresql://localhost:5432/postgres";
    static String user = "postgres";
    static String password = "password";
    
    public static Connection Connect(){
        try{
            return DriverManager.getConnection(URL,user,password);
        } 
        catch(SQLException e){
            throw new RuntimeException("Error to connect database!", e);
        }
    }
}
