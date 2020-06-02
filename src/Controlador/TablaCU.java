/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/* 5.Patriots
    Proyecto: SATA
    Version: 1.1
    01/Junio/2020
    Caso de uso: obtenerInformacion
    El caso de uso utiliza para el funcionamiento:
        -Número de  requerimientos: RF_03, RN_21, RN-22
        -Clase: Login, InicioSesion, MisDatos, PerfilTutor
        -Metodos: TablaCU, llenarTabla
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaCU {

    Connection cin = conexion.getConexion();
    PreparedStatement ps;
    DefaultTableModel tabla;
    
    //Este metodo se basa en que obtendremos la informacion de los usuario que actualmente estan registrados
    public void llenarTabla(JTable tablaCU) {
        String col[] = {"IdUsuario", "Nombre", "apPaterno", "apMaterno", "Departamento",
        "Carrera", "Semestre", "Dirección", "Tel.Emergencia"};
        DefaultTableModel model = new DefaultTableModel(null, col) {
            //la tabla no es editable
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        String consulta = "SELECT u.*, p.departamento, a.carrera, a.semestre, "
                + "a.direccion, a.telEmergencia FROM usuario u "
                + "FULL JOIN profesor p ON u.idUsuario = p.idProfesor "
                + "FULL JOIN alumno a ON u.idUsuario = a.idAlumno";
        Statement st;
        String[] dato = new String[9];
        try {
            st = cin.createStatement();
            ResultSet result = st.executeQuery(consulta);
            while (result.next()) {
                dato[0] = result.getString(1);
                dato[1] = result.getString(2);
                dato[2] = result.getString(3);
                dato[3] = result.getString(4);
                dato[4] = result.getString(5);
                dato[5] = result.getString(6);
                dato[6] = result.getString(7);
                dato[7] = result.getString(8);
                dato[8] = result.getString(9);
                model.addRow(dato);
            }

            tablaCU.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }
}
