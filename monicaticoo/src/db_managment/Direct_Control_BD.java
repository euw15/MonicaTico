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
                        + "||" + resultset.getString(2) + "||"
                        + resultset.getInt(3)
                        + "||" + resultset.getInt(4) + "||"
                        + resultset.getInt(5));
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
    public void insertProductoPorCategoria(String[] Producto,
            int idUbicacionProducto, int idCategoria) {
        String idProducto = Producto[0];
        String nombre = Producto[1];
        int Cantidad = Integer.parseInt(Producto[2]);
        int Precio = Integer.parseInt(Producto[3]);
        int Costo = Integer.parseInt(Producto[4]);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String fecha = dateFormat.format(date);

        //crear el producto
        crearProducto(idProducto, nombre, Precio, Costo, fecha, "Act",
                "AlgunaDescripcion", idCategoria);
        //insertar los productos creados al inventario
        insertarEnInventario(idProducto, idUbicacionProducto, Cantidad);
    }

    /**
     * Crea un nuevo Producto
     *
     * @param idProducto
     * @param nombre
     * @param precio
     * @param costo
     * @param fechaCreacion
     * @param estado
     * @param Descripcion
     * @param idCategoria
     */
    public void crearProducto(String idProducto, String nombre, int precio,
            int costo, String fechaCreacion, String estado, String Descripcion,
            int idCategoria) {//revisado +
        try {
            String CrearProducto = this.readSql("../monicaticoo/src/"
                    + "sql_files/CrearProducto.sql");
            PreparedStatement stm = conection.prepareStatement(CrearProducto);
            stm.setString(1, idProducto);
            stm.setString(2, nombre);
            stm.setInt(3, precio);
            stm.setInt(4, costo);
            stm.setString(5, fechaCreacion);
            stm.setString(6, estado);
            stm.setString(7, Descripcion);
            stm.setInt(8, idCategoria);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al Error al crear producto");

        }
    }

    /**
     * Insertar productos en el inventario
     *
     * @param idProducto
     * @param idUbicacionProducto
     * @param cantidad
     */
    public void insertarEnInventario(String idProducto, int idUbicacionProducto,
            int cantidad) {// revisado+
        try {
            String insertarProductoEnInventario = this.readSql("../monicaticoo/src/"
                    + "sql_files/InsertarProductoEnInventario.sql");
            PreparedStatement stm = conection.prepareStatement(insertarProductoEnInventario);
            stm.setString(1, idProducto);
            stm.setInt(2, idUbicacionProducto);
            stm.setInt(3, cantidad);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar producto al inventario");

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
    /**
     * Esta consulta retorna la lista de precios del inventario seleccionado
     * (Bodega o General) Ordenados por Categoria
     * @param ubicaciondelInv 
     */

    public void verListaDePrecioDelInventario(int ubicaciondelInv) {
//esta bien
        try {

            String listaDePrecios = this.readSql("../monicaticoo/src/sql_files/"
                    + "ListaDePreciosDelInventario.sql");

            PreparedStatement stm = this.conection.prepareStatement(listaDePrecios);
            stm.setInt(1, ubicaciondelInv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getInt(3)
                        + rs.getInt(4) + "||" + rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de precios");

        }

    }
    /**
     * Esta consulta permite ver los productos de una categoria en especifico y 
     * de una ubicacion del inventario(Bodega o General)
     * @param categoria
     * @param descripcionDeUnInv 
     */

    public void verProductosPorCategoriaDeUnInv(String categoria,
            String LugarInventario) {//esta bien
        try {

            String productosPorCategoria = this.readSql("../monicaticoo/"
                    + "src/sql_files/ProductosPorCategoriaDeUnInventario.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(productosPorCategoria);
            stm.setString(1, categoria);
            stm.setString(2, LugarInventario);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los Productos por categoria");

        }

    }
    /**
     * Esta consulta obtiene todos los productos del inventario seleccionado
     * (Bodega o General) que no pertenecen a ninguna categoria.
     * @param descripcionDeUnInv 
     */

    public void verProductosSinCategoriaDeUnInv(String lugarDeUnInv) {//estabien
        try {

            String productosSinCategoria = this.readSql("../monicaticoo/"
                    + "src/sql_files/ProductosSinCategoria.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(productosSinCategoria);
            stm.setString(1, lugarDeUnInv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getInt(3));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los Productos sin categoria");

        }

    }
    /**
     * Esta Consulta Obtiene el movimiento de un producto por el termino de
     * facturacion. Se le debe proporcionar el lugar del inventario
     * (Bodega o General)
     * y la fecha de rango de busqueda.
     * @param lugarDeUnInv
     * @param fechaInicio
     * @param fechaFinal 
     */
    public void verMovProductosFacturados(String lugarDeUnInv,String fechaInicio
            ,String fechaFinal) {//esta bien
        try {

            String verMovProductosFacturados = this.readSql("../monicaticoo/"
                    + "src/sql_files/MovProductoFacturado.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(verMovProductosFacturados);
            stm.setString(1, lugarDeUnInv);
            stm.setString(2, fechaInicio);
            stm.setString(3,  fechaFinal);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getInt(3)+ "||"
                        + rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los movimientos de los"
                    + " productos facturados");

        }

    }
    /**
     * Esta consulta muestra todos los movimientos de los productos ordenados 
     * por fecha producto y el concepto del movimiento
     * @param lugarDeUnInv
     * @param fechaInicio
     * @param fechaFinal 
     */
    public void verMovProductosOrdenadosPorTipo(String lugarDeUnInv,String fechaInicio
            ,String fechaFinal) {//esta bien
        try {

            String verMovProductosFacturadosPorTipo = this.readSql("../monicaticoo/"
                    + "src/sql_files/MovProductosPorTipo.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(verMovProductosFacturadosPorTipo);
            stm.setString(1, lugarDeUnInv);
            stm.setString(2, fechaInicio);
            stm.setString(3,  fechaFinal);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getString(3)+ "||"
                        + rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los movimientos de los"
                    + " productos");

        }

    }
    /**
     * Esta consulta devuelve el movimiento de un producto en especifico
     * @param idProducto
     * @param lugarDeUnInv
     * @param fechaInicio
     * @param fechaFinal 
     */
    public void verMovProductoOrdenadoPorTipo(String idProducto,
            String lugarDeUnInv,String fechaInicio
            ,String fechaFinal) {//esta bien
        try {

            String verMovProductoOrdenadoPorTipo = this.readSql("../monicaticoo/"
                    + "src/sql_files/MovProductoPorTipo.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(verMovProductoOrdenadoPorTipo);
            stm.setString(1, idProducto);
            stm.setString(2, lugarDeUnInv);
            stm.setString(3, fechaInicio);
            stm.setString(4,  fechaFinal);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1)
                        + "||" + rs.getString(2) + "||" + rs.getString(3)+ "||"
                        + rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el movimiento");

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
                    = this.conection.prepareStatement
        (BuscarCategoriaPorDescripcion);
            stm.setString(1, descripcionDeCategoria);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener Categoria Por Descripcion");

        }

    }


    /**
     * Dado un idVendedor muestra los detalles de las ventas realizadas por este
     * en rango de fechas
     */
    public void ventasPorVendedor(int idVendedor) {//falta hacerla por fecha
        try {

            String ventasPorVend = this.readSql("../monic"
                    + "aticoo/src/sql_files/VentasPorVendedor.sql");
            PreparedStatement stm
                    = this.conection.prepareStatement(ventasPorVend);
            stm.setInt(1, idVendedor);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "||" + rs.getString(2) + "||"
                        + rs.getString(3) + "||" + rs.getInt(4) + "||"
                        + rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener ventas por vendedor");

        }

    }

}
