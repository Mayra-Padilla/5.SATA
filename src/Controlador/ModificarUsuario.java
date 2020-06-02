/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.AgregarUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ModificarUsuario {

    //La variable cin realizara la interaccion con la BD
    Connection conn = Controlador.conexion.getConexion();
    //ps sera la variable que utilizaremos para ejecutar consultas
    PreparedStatement ps;

    public int LlenarIdProfesor(String id, JTextField txtNoEmpleado, JTextField txtNombre,
            JTextField txtApPaterno, JTextField txtApMaterno, JTextField txtDepartamento,
            JTextField txtCorreo, JComboBox txtHorario, JTextArea txtDescripcion) {
        try {

            String consulta = "SELECT p.idProfesor, u.nombre, u.apPaterno, u.apMaterno, "
                    + "p.departamento, u.correo, p.horario, p.descripcion "
                    + "FROM profesor p INNER JOIN usuario u ON u.idUsuario = p.idProfesor "
                    + "WHERE idProfesor =" + id;

            Statement st;
            Statement st2;

            st = conn.createStatement();

            ResultSet rs2 = st.executeQuery(consulta);

            while (rs2.next()) {
                txtNoEmpleado.setText(rs2.getString(1));
                txtNombre.setText(rs2.getString(2));
                txtApPaterno.setText(rs2.getString(3));
                txtApMaterno.setText(rs2.getString(4));
                txtDepartamento.setText(rs2.getString(5));
                txtCorreo.setText(rs2.getString(6));
                if (rs2.getString(7) == "M") {
                    txtHorario.setSelectedItem(0);
                } else if (rs2.getString(7) == "V") {
                    txtHorario.setSelectedItem(1);
                } else {
                    txtHorario.setSelectedItem(2);
                }
                txtDescripcion.setText(rs2.getString(8));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int LlenarIdAlumno(String id, JTextField idAlu, JTextField nom, JTextField apP,
            JTextField apM, JTextField dir, JTextField tel, JTextField prom, JTextField mail,
            JTextField noP, JTextField noM, JTextField sem, JTextField carre) {
        try {
            String consulta = "SELECT a.idAlumno, u.nombre, u.apPaterno, u.apMaterno, a.direccion, "
                    + "a.telEmergencia, a.promedioGeneral, u.correo, \n"
                    + "a.nombrePadre, a.nombreMadre, a.semestre, a.carrera FROM alumno a \n"
                    + "INNER JOIN usuario u ON u.idUsuario = a.idAlumno WHERE idAlumno = " + id;

            Statement st;
            st = conn.createStatement();

            ResultSet rs2 = st.executeQuery(consulta);

            while (rs2.next()) {
                idAlu.setText(rs2.getString(1));
                nom.setText(rs2.getString(2));
                apP.setText(rs2.getString(3));
                apM.setText(rs2.getString(4));
                dir.setText(rs2.getString(5));
                tel.setText(rs2.getString(6));
                prom.setText(rs2.getString(7));
                mail.setText(rs2.getString(8));
                noP.setText(rs2.getString(9));
                noM.setText(rs2.getString(10));
                sem.setText(rs2.getString(11));
                carre.setText(rs2.getString(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void mostrarTBPane(JTextField idA, JTextField idP, JTabbedPane tb) {

        if (!"".equals(idA.getText())) {
            System.out.println("tiene datos A");
            tb.setSelectedIndex(1);
        } else if (!"".equals(idP.getText())) {
            System.out.println("tiene datos P");
            tb.setSelectedIndex(0);
        }
    }

    public boolean VerificarIdUser(String id) {
        boolean exist = false;
        try {

            String consulta = "SELECT idUsuario FROM usuario "
                    + "WHERE idUsuario = " + id;
            Statement st;
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            if (rs.next()) {
                exist = true;
            }

            return exist;
        } catch (Exception e) {
            System.out.println(e);
        }
        return exist;
    }

    public void modificarProfesor(JTextField txtNoEmpleado, JTextField txtNombre,
            JTextField txtApPaterno, JTextField txtApMaterno, JTextField txtDepartamento,
            JTextField txtCorreo, JComboBox txtHorario, JTextArea txtDescripcion) {
        try {
            String noEmpleado = txtNoEmpleado.getText();
            String nombre = txtNombre.getText();
            String paterno = txtApPaterno.getText();
            String materno = txtApMaterno.getText();
            String departamento = txtDepartamento.getText();
            String correo = txtCorreo.getText();
            String descripcion = txtDescripcion.getText();
            String combo = (String) txtHorario.getSelectedItem();

            //Consultas que insertan primero que es un nuevo usuario y luego que es profesor
            String consulta = "UPDATE usuario SET nombre = '" + nombre + "', apPaterno = '" + paterno + "', "
                    + "apMaterno = '" + materno + "', correo = '" + correo + "' "
                    + "WHERE idUsuario = " + noEmpleado;
            String consulta2 = "UPDATE profesor SET descripcion = '" + descripcion + "', "
                    + "departamento = '" + departamento + "', horario = '" + combo + "' "
                    + "WHERE idProfesor = " + noEmpleado;

            //Ejecutamos las consultas
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            st.executeUpdate(consulta);
            st2.executeUpdate(consulta2);

            //Mensaje de confirmación
            JOptionPane.showMessageDialog(null, "Actualización exitosa",
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

    public void modificarAlumno(JTextField idAlu, JTextField nom, JTextField apP,
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

            String consulta = "UPDATE usuario SET nombre = '" + nombre + "', "
                    + "apPaterno = '" + pater + "', apMaterno = '" + mater + "', "
                    + "correo = '" + correo + "' "
                    + "WHERE idUsuario = " + id;
            String consulta2 = "UPDATE alumno SET semestre = " + semestre + ", direccion = '" + direcc + "', "
                    + "carrera = '" + carrera + "', nombrePadre = '" + nombreP + "', "
                    + "nombreMadre = '" + nombreM + "', promedioGeneral = '" + prome + "', "
                    + "telEmergencia = '" + tele + "' "
                    + "WHERE idAlumno = " + id;

            //Ejecutamos las consultas
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            st.executeUpdate(consulta);
            st2.executeUpdate(consulta2);
            //Mensaje de confirmación
            JOptionPane.showMessageDialog(null, "Actualización exitosa",
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
