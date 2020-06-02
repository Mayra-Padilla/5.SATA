/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /* 5.Patriots
    Proyecto: SATA
    Version: 1.1
    01/Junio/2020
    Caso de uso: EliminarUsuario
Descripcion: El caso de eliminarUsuario es la clase en donde se eliminan los datos de un usuario por su id
    El caso de uso utiliza para el funcionamiento:
        -Número de  requerimientos: RF_01, RN_03,  RN_17,  RN_20
        -Clase: EliminarUsuario
        -Metodos: borrarUser
 */

package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mayra
 */

public class EliminarUsuario {

    Connection conn = conexion.getConexion();
    PreparedStatement ps;
    //Este metodo es para buscar el id del usuario para eliminar sus datos de la BD
    //Número de  requerimiento: RF_03,  RN_17
    public void  borrarUser(String id) {
        try {

            String consulta = "DELETE FROM inicioSesion WHERE usuario = " + id;
            Statement st; //Primero definimos la instancia para eliminar de SQL al encontrar el Id del usuario
            st = conn.createStatement();
            ps = conn.prepareStatement(consulta);
            //luego se ejecuta esta consulta  y se hace la actualizacion de la BD
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
