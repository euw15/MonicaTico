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
import java.sql.ResultSetMetaData;
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

    public Direct_Control_BD(Connection conection, Statement statement) {
        this.conection = conection;
        this.statement = statement;

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

    public void consultarInventarioXSucursal(int idSucursal) {
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

    public void consultarProducto(int idProducto) {//esta bien
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

    public void insertarInventario(String Descripcion, int idSucursal) {
        //esta bien
        try {
            String Inventario = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarInventario.sql");
            PreparedStatement stm = this.conection.prepareStatement(Inventario);
            stm.setString(1, Descripcion);
            stm.setInt(2, idSucursal);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al crear inventario");
        }
    }

    public void insertarInventarioProducto(int idInventarioProducto, int idProducto, int Cantidad) {
        try {
            String InventarioProducto = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarInventarioProducto.sql");
            PreparedStatement stm = this.conection.prepareStatement(InventarioProducto);
            stm.setInt(1, idInventarioProducto);
            stm.setInt(2, idProducto);
            stm.setInt(3, Cantidad);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al crear inventarioProducto");
        }
    }

    public void insertarMovimientos(String Fecha, String Detalle, String Tipo)
    {//esta bien
        try {
            String movimientos = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarMovimientos.sql");
            PreparedStatement stm = this.conection.prepareStatement(movimientos);
            stm.setString(1, Fecha);
            stm.setString(2, Detalle);
            stm.setString(3, Tipo);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar movimiento");
        }
    }

    public void insertarProducto(int idProducto, String nombre, int precio,
            int costo, String categoria) {//esta bien
        try {
            String Producto = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarProducto.sql");
            PreparedStatement stm = this.conection.prepareStatement(Producto);
            stm.setInt(1, idProducto);
            stm.setString(2, nombre);
            stm.setInt(3, precio);
            stm.setInt(4, costo);
            stm.setString(5, categoria);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar producto");
        }
    }

    public void insertarProductoCantidadMov(int idProducto, int cantidad, int idMovimientos) {
        try {
            String ProductoCantidadMov = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarProductoCantidadMovimiento.sql");
            PreparedStatement stm = this.conection.prepareStatement(ProductoCantidadMov);
            stm.setInt(1, idProducto);
            stm.setInt(2, cantidad);
            stm.setInt(3, idMovimientos);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar ProductoCantidadMov");
        }
    }

    public void insertarSucursal(String nombre, String direccion, int idTienda) {//esta bien
        try {
            String valorSucursal = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarSucursal.sql");

            PreparedStatement stm = this.conection.prepareStatement(valorSucursal);
            stm.setString(1, nombre);
            stm.setString(2, direccion);
            stm.setInt(3, idTienda);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar Sucursal");
        }
    }

    public void insertarTienda(String nombre, String telefono, String direccion) {//esta bien
        try {
            String valorTienda = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarTienda.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(valorTienda);
            stm.setString(1, nombre);
            stm.setString(2, telefono);
            stm.setString(3, direccion);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar Tienda");
        }
    }

    public void consultarTienda(int idTienda) {//esta bien

        try {

            String InfoTienda = this.readSql("../monicaticoo/src/sql_files/"
                    + "ConsultarTienda.sql");

            PreparedStatement stm = this.conection.prepareStatement(InfoTienda);
            stm.setInt(1, idTienda);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("Error al realizar la consulta");

        }

    }

    public void consultarSucursal() {//esta bien

        try {
            String InfoSucursales = this.readSql("../monicaticoo/src/sql_files/"
                    + "ConsultarSucursal.sql");

            ResultSet rs = statement.executeQuery(InfoSucursales);

            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("Error al realizar la consulta");

        }

    }
}
