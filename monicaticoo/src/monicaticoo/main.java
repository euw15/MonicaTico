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
       AdministradorBD.VentasProductoPorCategoriaFecha(1,"2012-02-02","2014-02-06");
       
    }

}
