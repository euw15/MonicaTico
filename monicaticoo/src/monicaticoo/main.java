/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monicaticoo;

import db_managment.Direct_Control_BD;
import db_managment.Setting_Up_BD;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PruebaOscar pruebaOscar;
        Direct_Control_BD AdministradorBD;
        Setting_Up_BD setting = new Setting_Up_BD();

       
        AdministradorBD = new Direct_Control_BD(setting.getConection(),
                setting.getStatement());
        
       // pruebaOscar = new PruebaOscar(AdministradorBD);
//       AdministradorBD.VentasProductoPorCategoriaFecha(1,"2012-02-02","2014-02-06");
 //      AdministradorBD.crearFactura(10, "Tarjeta",1, 4, "Act", "AlgunaNota",10000);
        
   //AdministradorBD.insertarDevoluciones("2013-01-01", "Contado", 24000, 1, 4, "la nota");

  // AdministradorBD.insertarproductocantidaddev("1020", 2, 1, 1, 1000);
   
  // AdministradorBD.insertarmovimiento("2013-01-01", "detalle", 1, 3, 6, "1020", 1);
  // AdministradorBD.crearCierreDeCaja("2013-01-01", "2018-01-01");
  // AdministradorBD.consultarVentasXProducto("1020");
  //AdministradorBD.consultarVentasXVendedor(4);
  // AdministradorBD.consultarVentasXProducto("1020");
  // AdministradorBD.consultarVentasXCliente(1);
  // AdministradorBD.verProductosAgotados("Bodega");
      //  AdministradorBD.consultarVentasXTipoPago("Contado");
       // AdministradorBD.consultarVentasXVendedorYCliente(4);
      //  AdministradorBD.consultarMovimientosProductos("1020");
      //  AdministradorBD.cierreDeVentasXFecha("2000-01-01", "2020-01-01");
        //AdministradorBD.crearCierreDeCaja("2010-01-01", "2020-01-01");
    }

}
