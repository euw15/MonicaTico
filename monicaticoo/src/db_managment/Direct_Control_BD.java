/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db_managment;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    /**
     * Obtiene el valor del inventario segun el nombre del inventario
     * @param nombreInv 
     */
    public void valorInventario(String nombreInv){
        try{
            String valorInventario=this.readSql("../monicaticoo/src/sql_files/"
                    + "ValorInventario.sql");
            PreparedStatement stm = this.conection.prepareStatement
                    (valorInventario);
            stm.setString(1,nombreInv);
            ResultSet resultset =stm.executeQuery();
            //Imprime el resultado obtenido del valor del inventario
            while (resultset.next()) {
                System.out.println(resultset.getString(1)+
                        "||" +resultset.getString(2)+ "||" +resultset.getInt(3)
                        + "||" +resultset.getInt(4));                
            }
            
        }
        catch (Exception e) {
            System.out.println("Error al obtener el valor del inventario");
        }
            
    

    
}
     private String readSql(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        return stringBuilder.toString();
    }
}
