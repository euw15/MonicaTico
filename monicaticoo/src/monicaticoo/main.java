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
        
        Direct_Control_BD AdministradorBD;
        Setting_Up_BD setting= new Setting_Up_BD();
     
        //Pruebas de Edward 
        AdministradorBD= new Direct_Control_BD(setting.getConection(),setting.getStatement());
       // AdministradorBD.insertarSucursal("lucia", "cuatrofilosdecuchillomocho", 1);//.consultarTienda(1);
       // AdministradorBD.consultarInventarioXSucursal(1);
     //  AdministradorBD.consultarProducto(2);
       // AdministradorBD.insertarTienda("francini", "2256545", "nigeria") ;
        //AdministradorBD.insertarProducto(1,"carrito", 5000, 2500, "infantil");
        AdministradorBD.insertarInventario("primerInventario", 1);
        //**********************
    }
    
}
