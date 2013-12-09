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
     *
     * @param nombreInv
     */
    public void valorInventario(String nombreInv) {
        try {
            String valorInventario = this.readSql("../monicaticoo/src/sql_files/"
                    + "ValorInventario.sql");
            PreparedStatement stm = this.conection.prepareStatement(valorInventario);
            stm.setString(1, nombreInv);
            ResultSet resultset = stm.executeQuery();
            //Imprime el resultado obtenido del valor del inventario
            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getString(2) + "||" + resultset.getInt(3)
                        + "||" + resultset.getInt(4));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el valor del inventario");
        }




    }
    /**
     * Obtiene todos los productos que estan agotados sin importar al inventario
     * que pertenecen
     */

    public void verProductosAgotados() {
        try {
            String valorInventario = this.readSql("../monicaticoo/src/sql_files/"
                    + "verProductosAgotados.sql");
            ResultSet resultset = statement.executeQuery(valorInventario);
            //Imprime el resultado obtenido de ver productos agotados
            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getString(2) + "||" + resultset.getInt(3));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener los productos agotados");
        }




    }
    
    public void consultarInventarioXSucursal(int idSucursal)
    {
        try {
            String valorInventario = this.readSql("../monicaticoo/src/sql_files/"
                    + "consultarInventarioxSucursal.sql");
            PreparedStatement stm = this.conection.prepareStatement(valorInventario);
            stm.setInt(1, idSucursal);
            ResultSet resultset = stm.executeQuery();
            //Imprime el resultado obtenido del valor del inventario
            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getString(2) + "||" + resultset.getInt(3)
                        + "||" + resultset.getInt(4));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el valor del inventario");
        }


    }

    public void consultarProducto(int idProducto)
    {
        try {
            String valorInventario = this.readSql("../monicaticoo/src/sql_files/"
                    + "consultarProducto.sql");
            PreparedStatement stm = this.conection.prepareStatement(valorInventario);
            stm.setInt(1, idProducto);
            ResultSet resultset = stm.executeQuery();
            //Imprime el resultado obtenido del valor del inventario
            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getInt(2) + "||" + resultset.getInt(3)
                        + "||" + resultset.getString(4));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el valor del producto");
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
