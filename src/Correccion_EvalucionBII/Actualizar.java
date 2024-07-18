package Correccion_EvalucionBII;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Actualizar extends JFrame {
    private JTextField numCed;
    private JButton MENUButton;
    private JPanel panel_actualizar;
    private JTextField numHisto;
    private JTextField nom;
    private JTextField apel;
    private JTextField cel;
    private JTextField años;
    private JTextField enfer;
    private JButton ACTUALIZARButton;

    public Actualizar(){
        super("ACTUALIZAR");
        setContentPane(panel_actualizar);
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventana_menu = new Menu();
                ventana_menu.iniciar();
                dispose();
            }
        });
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarRegistro();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public Connection conxion()throws SQLException{
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

    public void actualizarRegistro()throws SQLException{
        String numcedula = numCed.getText();
        String historial = numHisto.getText();
        String nombre = nom.getText();
        String apellido = apel.getText();
        String telefono = cel.getText();
        String edad = años.getText();
        String enfermedad = enfer.getText();
        Connection connection = conxion();
        String checkSql = "SELECT COUNT(*) FROM paciente WHERE cedula = ?";
        PreparedStatement checkPst = connection.prepareStatement(checkSql);
        checkPst.setString(1, numcedula);
        ResultSet rs = checkPst.executeQuery();
        if (rs.next() && rs.getInt(1) > 0){
            String sql = "UPDATE paciente set n_historial_clinico = ?, nombre = ?, apellido = ?, telefono = ?, edad = ?, descripcion_enfermedad = ? where cedula = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(historial));
            pst.setString(2, nombre);
            pst.setString(3, apellido);
            pst.setString(4,telefono);
            pst.setInt(5,Integer.parseInt(edad));
            pst.setString(6,enfermedad);
            pst.setString(7,numcedula);


            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Paciente actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar paciente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún paciente con la cédula especificada.");
        }
    }

    public void iniciar(){
        setVisible(true);
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
