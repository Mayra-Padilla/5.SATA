/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public void  borrarUser(String id) {
        try {

            String consulta = "DELETE FROM inicioSesion WHERE usuario = " + id;
            Statement st;
            st = conn.createStatement();
            ps = conn.prepareStatement(consulta);

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
