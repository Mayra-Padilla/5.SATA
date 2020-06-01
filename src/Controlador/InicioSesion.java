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

    public void verificarSesion(JTextField txtContraseña,
            JTextField txtNumeroControl, Login t) {
        try {
            String usuario = txtNumeroControl.getText();
            int convertirUsuario = Integer.parseInt(usuario);
            String contrasenia = txtContraseña.getText();
            //declaramos la consulta para checar si existe el usuario y el pass
            String consulta = "SELECT usuario, contraseña FROM inicioSesion WHERE usuario = " + convertirUsuario
                    + " AND contraseña = '" + contrasenia + "'";
            //declaramos las variables para ejecutar las consultas
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            String sesion = "UPDATE inicioSesion SET estado = 1 WHERE usuario = " + convertirUsuario;
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
}
