/* 5.Patriots
    Proyecto: SATA
    Version: 1.1
    01/Junio/2020
    Caso de uso: iniciarSesion 
    El caso de uso utiliza para el funcionamiento:
        -Número de  requerimientos: RF_01, FR_19, RN_21
        -Clase: Login, InicioSesion
        -Metodos: getConexion, Consulta
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
        
public class conexion {
    static Connection contacto = null;
    
    public static Connection getConexion(){
//        DESKTOP-JH0QBD0
        String url = "jdbc:sqlserver://DESKTOP-JH0QBD0:1433;databaseName=TutoriasITL;";
        try{               //com.microsoft.sqlserver.jdbc
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, REVISAR DRIVER " + e.getMessage(), 
                "Error de conexión", JOptionPane.ERROR_MESSAGE);
            }
        try{
            contacto = DriverManager.getConnection(url,"sa","root");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, REVISAR DRIVER " + e.getMessage(), 
                "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return contacto;
    }
    
   //MANDAR A PEDIR UNA CONSULTA DIRECTAMENTE A BD
    public static ResultSet Consulta(String consulta){
        Connection con = getConexion();
        Statement declara;
        try{
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion, REVISAR DRIVER " + e.getMessage(), 
                "Error de conexión", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
