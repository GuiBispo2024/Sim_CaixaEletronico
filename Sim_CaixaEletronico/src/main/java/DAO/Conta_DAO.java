/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Conta_Model;

/**
 *
 * @author ADM
 */
public class Conta_DAO {
    
    public void criarConta(Conta_Model conta) throws SQLException{
        String sql = "insert into conta(numero, titular, saldo) values(?,?,?)";
        try(Connection con = Database.Connect(); PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, conta.getNumero());
            pst.setString(2, conta.getTitular());
            pst.setDouble(3, conta.getSaldo());
            pst.executeUpdate();
        }
    }
    
    public Conta_Model buscarConta(String numero) throws SQLException{
        String sql = "select * from conta where numero = ?";
        try(Connection con  = Database.Connect(); PreparedStatement pst = con.prepareStatement(sql)){
            pst.setString(1, numero);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
              return new Conta_Model(rs.getString("numero"),rs.getString("titular"),rs.getDouble("saldo"));
           }
        }
        return null;
    }
    
    public void atualizarSaldo(String numero, Double saldo) throws SQLException{
        String sql = "update conta set saldo = saldo + ? where numero = ?";
        try(Connection con = Database.Connect(); PreparedStatement pst = con.prepareStatement(sql)){
            pst.setDouble(1, saldo);
            pst.setString(2, numero);
            pst.executeUpdate();
        }
    }
    
    public void diminuirSaldo(String numero, Double saldo) throws SQLException{
        String sql = "update conta set saldo = saldo - ? where numero  = ?";
        try(Connection con = Database.Connect(); PreparedStatement pst = con.prepareStatement(sql)){
            pst.setDouble(1, saldo);
            pst.setString(2, numero);
            pst.executeUpdate();
        }
    }
}
