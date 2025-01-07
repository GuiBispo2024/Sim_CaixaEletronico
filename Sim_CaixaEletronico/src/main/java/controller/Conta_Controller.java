/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conta_DAO;
import java.sql.SQLException;
import model.Conta_Model;

/**
 *
 * @author ADM
 */
public class Conta_Controller {
    private Conta_DAO dao = new Conta_DAO();
    
    public void criarConta(String numero, String titular){
        try{
            Conta_Model conta = new Conta_Model(numero, titular);
            dao.criarConta(conta);
            System.out.println("Conta criada!!");
        }catch(SQLException e){
            System.out.println("Erro ao criar a conta!! " + e.getMessage());
        }
    }
    
    public void consultarSaldo(String numero){
        try{
            Conta_Model conta = dao.buscarConta(numero);
            if(conta != null){
                System.out.println("Saldo: R$ " + conta.getSaldo());
            }else{
                System.out.println("Conta não encontrada");
            }
        }catch(SQLException e){
            System.out.println("Erro ao consultar saldo: " + e.getMessage());
        }
    }
    
    public void depositar(String numero, Double valor){
        try{
            Conta_Model conta = dao.buscarConta(numero);
            if(conta != null){
                dao.atualizarSaldo(numero, valor);
                System.out.println("Deposito realizado com sucesso!!");
            }else{
                System.out.println("Conta não encontrada.");
            }
        }catch(SQLException e){
            System.out.println("Erro ao depositar " + e.getMessage());
        }
    }
    
    public String saque(String numero, Double valor){
         try{
            Conta_Model conta = dao.buscarConta(numero);
            if(conta != null){
                if(conta.getSaldo() >= valor){
                    dao.atualizarSaldo(numero, -valor);
                    return "Saque realizado com sucesso!";
                }else{
                    return "Saldo insuficiente para realizar o saque.";
                }
            }
        }catch(SQLException e){
            return "Erro ao sacar " + e.getMessage();
        }
        return null;
    }
}
