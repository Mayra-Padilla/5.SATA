/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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

    public void llenarTabla(JTable tablaCU) {
        String col[] = {"IdUsuario", "Nombre", "apPaterno", "apMaterno"};
        DefaultTableModel model = new DefaultTableModel(null, col) {
            //la tabla no es editable
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        String consulta = "SELECT u.idUsuario,u.nombre,"
                + "u.apPaterno,u.apMaterno\n"
                + "FROM usuario u ";
        Statement st;
        String[] dato = new String[4];
        try {
            st = cin.createStatement();
            ResultSet result = st.executeQuery(consulta);
            while (result.next()) {
                dato[0] = result.getString(1);
                dato[1] = result.getString(2);
                dato[2] = result.getString(3);
                dato[3] = result.getString(4);
                model.addRow(dato);
            }
            tablaCU.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }
}
