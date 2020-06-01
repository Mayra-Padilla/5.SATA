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

/**
 *
 * @author Adrian
 */
public class ModificarUsuario {

    //La variable cin realizara la interaccion con la BD
    Connection conn = Controlador.conexion.getConexion();
    //ps sera la variable que utilizaremos para ejecutar consultas
    PreparedStatement ps;

    //SELECT p.idProfesor,u.nombre,
//u.apPaterno,u.apMaterno,
//p.departamento,u.correo,
//p.horario,p.descripcion
//FROM profesor p
//INNER JOIN usuario	u
//on u.idUsuario = p.idProfesor where idProfesor = 11123
    public int VerificarId(String id) {
        try {
            String consultaP = "SELECT idProfesor FROM profesor "
                    + "WHERE idProfesor =" + id;

            System.out.println(id);
            Statement st;
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(consultaP);
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}
