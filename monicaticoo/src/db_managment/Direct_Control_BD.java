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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * Obtiene el valor del inventario segun el nombre del inventario La
     * consulta devuelve los siguientes atributos: idProducto,Nombre,Precio,
     * Cantidad,ValordeVenta
     *
     * @param nombreInv
     */
    public void valorInventario(String nombreInv) {// esta bien
        try {
            String valorInventario = this.readSql("../monicaticoo/"
                    + "src/sql_files/ValorInventario.sql");
            PreparedStatement stm = this.conection.prepareStatement(valorInventario);
            stm.setString(1, nombreInv);
            ResultSet resultset = stm.executeQuery();
            //Imprime el resultado obtenido del valor del inventario
            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getString(2) + "||" + resultset.getInt(3)
                        + "||" + resultset.getInt(4) + "||" + resultset.getInt(5));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el valor del inventario");
        }

    }

    /**
     * Obtiene todos los productos que estan agotados sin importar al inventario
     * que pertenecen La consulta devuelve los siguientes atributos:
     * idProducto,Nombre,Precio
     */
    public void verProductosAgotados() {//esta bien
        try {
            String verProductosAgotados = this.readSql("../monicaticoo/src/"
                    + "sql_files/"
                    + "verProductosAgotados.sql");
            ResultSet resultset = statement.executeQuery(verProductosAgotados);
            //Imprime el resultado obtenido de ver productos agotados
            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getString(2) + "||"
                        + resultset.getInt(3));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener los productos agotados");
        }

    }

    /**
     * Recibe un arreglo con todos los atributos necesarios para ingresar un
     * producto que pertenece a un inventario especifico La fecha que esta por
     * default es la del dia
     *
     * @param Producto
     */
    public void insertProductoPorCategoria(String[] Producto, int idInventario,
            int idCategoria) {//esta bien
        String idProducto = Producto[0];
        String Descripcion = Producto[1];
        int Cantidad = Integer.parseInt(Producto[2]);
        int Precio = Integer.parseInt(Producto[3]);
        int Costo = Integer.parseInt(Producto[4]);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        this.insertarProductoPorCategoria(idProducto, Descripcion, Precio, 
                Costo, idCategoria, fecha);
        this.insertarInventarioProducto(idInventario, idProducto, Cantidad);

    }

    public void consultarInventarioXSucursal(int idSucursal) {
        try {
            String valorInventario = this.readSql("../monicaticoo/src"
                    + "/sql_files/consultarInventarioxSucursal.sql");
            PreparedStatement stm = this.conection.prepareStatement(valorInventario);
            stm.setInt(1, idSucursal);
            ResultSet resultset = stm.executeQuery();
            //Imprime el resultado obtenido del valor del inventario
            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getString(2) + "||" + resultset.
                        getInt(3)
                        + "||" + resultset.getInt(4));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el valor del inventario");
        }

    }

    public void consultarProducto(String idProducto) {//esta bien
        try {
            String valorInventario = this.readSql("../monicaticoo/src/"
                    + "sql_files/consultarProducto.sql");
            PreparedStatement stm = this.conection.prepareStatement(valorInventario);
            stm.setString(1, idProducto);
            ResultSet resultset = stm.executeQuery();

            while (resultset.next()) {
                System.out.println(resultset.getString(1)
                        + "||" + resultset.getInt(2) + "||" + resultset.
                        getInt(3)
                        + "||" + resultset.getString(4));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener el producto");
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

    public void insertarInventarioProducto(int idInventarioProducto,
            String idProducto, int Cantidad) {//esta bien
        try {
            String InventarioProducto = this.readSql("../monicaticoo/src/"
                    + "sql_files/InsertarInventarioProducto.sql");
            PreparedStatement stm = this.conection.prepareStatement(InventarioProducto);
            stm.setInt(1, idInventarioProducto);
            stm.setString(2, idProducto);
            stm.setInt(3, Cantidad);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al crear inventarioProducto");
        }
    }

    public void insertarMovimientos(String Fecha, String Detalle, String Tipo) {
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

    public void insertarProductoPorCategoria(String idProducto, String nombre, int precio,
            int costo, int categoria, String fecha) {//esta bien

        try {
            String Producto = this.readSql("../monicaticoo/src/sql_files/"
                    + "InsertarProductoPorCategoria.sql");
            PreparedStatement stm = this.conection.prepareStatement(Producto);
            stm.setString(1, idProducto);
            stm.setString(2, nombre);
            stm.setInt(3, precio);
            stm.setInt(4, costo);
            stm.setInt(5, categoria);
            stm.setString(6, fecha);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(idProducto);
            System.out.println(nombre);
            System.out.println("Error al insertar producto");
        }
    }

    public void insertarProductoCantidadMov(String idProducto, int cantidad,
            int idMovimientos) { // esta bien
        try {
            String ProductoCantidadMov = this.readSql("../monicaticoo/src/sql"
                    + "_files/InsertarProductoCantidadMovimiento.sql");
            PreparedStatement stm = this.conection.prepareStatement(ProductoCantidadMov);
            stm.setString(1, idProducto);
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

    public void consultarSucursales() {//esta bien

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

    public void cantidadDeProductos() {//esta bien
        try {
            String CantidadProductos = this.readSql("../monicaticoo/"
                    + "src/sql_files/CantidadDeProductos.sql");

            ResultSet rs = statement.executeQuery(CantidadProductos);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Error al realizar la consulta de "
                    + "cantidadDeProducto");

        }
    }

    public void verListaDePrecioXInventario(String descripcionInventario) {//esta bien
        try {

            String listaDePrecios = this.readSql("../monicaticoo/src/sql_files/"
                    + "ListaDePreciosXInventario.sql");

            PreparedStatement stm = this.conection.prepareStatement(listaDePrecios);
            stm.setString(1, descripcionInventario);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getInt(3) + rs.getInt(4) + "||" + rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de precios");

        }

    }

    public void verProductosPorCategoriaDeUnInv(String categoria,
            String descripcionDeUnInv) {//esta bien
        try {

            String productosPorCategoria = this.readSql("../monicaticoo/"
                    + "src/sql_files/ProductosPorCategoriaDeUnInventario.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(productosPorCategoria);
            stm.setString(1, categoria);
            stm.setString(2, descripcionDeUnInv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los Productos por categoria");

        }

    }

    public void eliminarInventario(String nombre) {//esta bien
        try {
            String eliminarInv = this.readSql("../monicaticoo/src/sql_files/"
                    + "EliminarInventario.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(eliminarInv);
            stm.setString(1, nombre);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al eliminar inventario");
        }
    }

    public void verProductosSinCategoriaDeUnInv(String descripcionDeUnInv) {//esta bien
        try {

            String productosSinCategoria = this.readSql("../monicaticoo/"
                    + "src/sql_files/ProductosSinCategoria.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(productosSinCategoria);
            stm.setString(1, descripcionDeUnInv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los Productos sin categoria");

        }

    }

    public void modificarProducto(String idProducto, String nombre,
            int precio, int idCategoria) {//esta bien
        try {
            String ModificarProducto = this.readSql("../monicaticoo"
                    + "/src/sql_files/ModificarProducto.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(ModificarProducto);
            stm.setString(1, nombre);
            stm.setInt(2, precio);
            stm.setInt(3, idCategoria);
            stm.setString(4, idProducto);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al Modificar Producto");
        }
    }

    public void BuscarCategoriaPorDescripcion(String descripcionDeCategoria) {
        try {

            String BuscarCategoriaPorDescripcion = this.readSql("../monic"
                    + "aticoo/src/sql_files/BuscarCategoriaPorDescripcion.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(BuscarCategoriaPorDescripcion);
            stm.setString(1, descripcionDeCategoria);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener Categoria Por Descripcion");

        }

    }

    public void CambiarDescripcionDelInventario(int idInventario, String nombre) {//esta bien
        try {
            String cambiarDescripcionDelInventario = this.readSql("../"
                    + "monicaticoo/src/sql_files/CambiarDescripcion"
                    + "DelInventario.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(cambiarDescripcionDelInventario);
            stm.setString(1, nombre);
            stm.setInt(2, idInventario);

            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al cambiar Descripcion Del Inventario");
        }
    }

    public void CambiarSucursalDeUnInv(int idInventario, int idSucursal) 
    {//esta bien
        try {
            String cambiarSucursalDeUnInv = this.readSql("../monicaticoo"
                    + "/src/sql_files/CambiarSucursalDeUnInv.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(cambiarSucursalDeUnInv);
            stm.setInt(1, idSucursal);
            stm.setInt(2, idInventario);
            stm.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al Cambiar Sucursal De Un Inv");
        }

    }
}
