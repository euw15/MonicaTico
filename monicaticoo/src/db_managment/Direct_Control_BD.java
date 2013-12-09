/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db_managment;


import java.sql.Connection;
import java.sql.Statement;


/**
 *
 * @author Monicaticooo
 */
public class Direct_Control_BD {
    private Connection conection;
    private Statement statement;
    private String[] columnNames;
    private Object[][] data;
    
    public Direct_Control_BD(Connection conection, Statement statement){
        this.conection=conection;
        this.statement=statement;
        
    }
    
    

    
}
