/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AgregarUser {
    //La variable cin realizara la interaccion con la BD

    Connection conn = Controlador.conexion.getConexion();
    //ps sera la variable que utilizaremos para ejecutar consultas
    PreparedStatement ps;

    public void agregarProfesor(JTextField txtNoEmpleado, JTextField txtNombre,
            JTextField txtApPaterno, JTextField txtApMaterno, JTextField txtDepartamento,
            JTextField txtCorreo, String txtHorario, JTextArea txtDescripcion) {
        try {
            String noEmpleado = txtNoEmpleado.getText();
            String nombre = txtNombre.getText();
            String paterno = txtApPaterno.getText();
            String materno = txtApMaterno.getText();
            String departamento = txtDepartamento.getText();
            String correo = txtCorreo.getText();
            String descripcion = txtDescripcion.getText();

            //Consultas que insertan primero que es un nuevo usuario y luego que es profesor
            String consulta = "INSERT INTO usuario VALUES(" + noEmpleado + ","
                    + "'" + nombre + "','" + paterno + "','" + materno + "',"
                    + "'" + correo + "')";
            String consulta2 = "INSERT INTO profesor VALUES(" + noEmpleado + ",'" + descripcion + "',"
                    + "'" + departamento + "','" + txtHorario + "')";

            //Ejecutamos las consultas
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            st.executeUpdate(consulta);
            st2.executeUpdate(consulta2);

            //Mensaje de confirmación
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente",
                    "Información", JOptionPane.INFORMATION_MESSAGE);

            //Limpiamos cajas de texto
            txtNoEmpleado.setText("");
            txtNombre.setText("");
            txtApPaterno.setText("");
            txtApMaterno.setText("");
            txtDepartamento.setText("");
            txtCorreo.setText("");
            txtDescripcion.setText("");
        } catch (Exception e) {
            //Mensaje de error
            JOptionPane.showMessageDialog(null, "Verificar los datos ingresados",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void agregarAlumno(JTextField idAlu, JTextField nom, JTextField apP,
            JTextField apM, JTextField dir, JTextField tel, JTextField prom, JTextField mail,
            JTextField noP, JTextField noM, JTextField sem, JTextField carre) {
        try {
            String id = idAlu.getText();
            String nombre = nom.getText();
            String pater = apP.getText();
            String mater = apM.getText();
            String direcc = dir.getText();
            String tele = tel.getText();
            String prome = prom.getText();
            String correo = mail.getText();
            String nombreP = noP.getText();
            String nombreM = noM.getText();
            String semestre = sem.getText();
            String carrera = carre.getText();

            String consulta = "INSERT INTO usuario VALUES(" + id + ",'" + nombre + "',"
                    + "'" + pater + "','" + mater + "','" + correo + "')";
            String consulta2 = "INSERT INTO alumno VALUES(" + id + ", " + semestre + ", "
                    + "'" + direcc + "' ,'" + carrera + "','" + nombreP + "','" + nombreM
                    + "', " + prome + ", '--', '" + tele + "')";

            //Ejecutamos las consultas
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            st.executeUpdate(consulta);
            st2.executeUpdate(consulta2);
            //Mensaje de confirmación
            JOptionPane.showMessageDialog(null, "Usuario creado exitosamente",
                    "Información", JOptionPane.INFORMATION_MESSAGE);

            //Limpiamos cajas de texto
            idAlu.setText("");
            nom.setText("");
            apP.setText("");
            apM.setText("");
            dir.setText("");
            tel.setText("");
            prom.setText("");
            mail.setText("");
            noP.setText("");
            noM.setText("");
            sem.setText("");
            carre.setText("");
        } catch (Exception e) {
            //Mensaje de error
            JOptionPane.showMessageDialog(null, "Verificar los datos ingresados",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
