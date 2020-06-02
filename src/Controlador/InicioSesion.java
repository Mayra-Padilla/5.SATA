/* 5.Patriots
    Proyecto: SATA
    Version: 1.1
    01/Junio/2020
    Caso de uso: iniciarSesion
Descripcion: Este controlador se encarga del proceso 
                    para la verificacion de las cuentas de los usuarios para el login
    El caso de uso utiliza para el funcionamiento:
        -Número de  requerimientos: RF_01, FR_19, RN_12, RN_17, RN_21, RN_24
        -Clase: Login, InicioSesion
        -Metodos: verficarSesion, cerrarSesion
 */
package Controlador;

import Vista.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Vista.Login;

public class InicioSesion {

    //La variable cin realizara la interaccion con la BD
    Connection conn = Controlador.conexion.getConexion();
    //ps sera la variable que utilizaremos para ejecutar consultas
    PreparedStatement ps;
    
    //Este metodo obtiene de los resultados de nuestras cajas de texto para verificar la existencia de id y constraseña
    //Número de  requerimientos: RF_01, FR_03, RN_17
    public void verificarSesion(JTextField txtContraseña,
            JTextField txtNumeroControl, Login t) {
        try {
            String usuario = txtNumeroControl.getText();
            int convertirUsuario = Integer.parseInt(usuario);
            String contrasenia = txtContraseña.getText(); 
//las siguiente variables obtienen los valores de las cajas de texto  colocarlas en la consulta
            //declaramos la consulta para checar si existe el usuario y el pass
            String consulta = "SELECT usuario, contraseña FROM inicioSesion WHERE usuario = " + convertirUsuario
                    + " AND contraseña = '" + contrasenia + "'";
            //declaramos las variables para ejecutar las consultas
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            String sesion = "UPDATE inicioSesion SET estado = 1 WHERE usuario = " + convertirUsuario;
            //y es en este proceso donde verifica la informacion y cambia los estados para indicar el login del usuario
            ps = conn.prepareStatement(sesion);
            //el ciclo recorrera los renglones y los if checaran que estos no esten vacios
            rs.next();
            if (rs.getString("usuario") != null) {
                if (rs.getString("contraseña") != null) {
                    //ejecutamos la actualizacion para activar sesion
                    ps.executeUpdate();
                    //cerramos la ventana actual y abrimos la ventana prueba
                    Menu obj = new Menu();
                    obj.setVisible(true);
                    t.dispose();
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña "
                    + "son incorrectos o no existen",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //limpiamos las cajas de texto

        txtNumeroControl.setText(null);
        txtContraseña.setText("");
    }
    //En este metodo es donde cambiamos el estado actual del usuario que ingreso y esta en la cuenta 
    //a que termino de usar sus sesion
    //Número de  requerimientos: RF_01, FR_03, RN_12, RN_17
    public void cerrarSesion() {
        try {
            //aqui declaramos el estado 0 para decir que ya salio de su sesion
            String sesion = "UPDATE inicioSesion SET estado = 0 WHERE estado = '1'";

            ps = conn.prepareStatement(sesion);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
