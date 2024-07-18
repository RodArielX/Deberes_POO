package Correccion_EvalucionBII;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrar extends JFrame{
    private JTextField cedu;
    private JTextField numhisto;
    private JTextField nom;
    private JTextField apel;
    private JTextField cel;
    private JTextField eda;
    private JTextField enfer;
    private JButton REGISTRARButton;
    private JButton MENUButton;
    private JPanel panel_registrar;

    public Registrar(){
        super("REGISTRO");
        setContentPane(panel_registrar);
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventana_menu = new Menu();
                ventana_menu.iniciar();
                dispose();
            }
        });
    }
    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }
    public void registrar()throws SQLException{
        String cedula = cedu.getText();
        String historial = numhisto.getText();
        String nombre = nom.getText();
        String apellido = apel.getText();
        String telefono = cel.getText();
        String edad = eda.getText();
        String enfermedad = enfer.getText();

        Connection connection = conexion();
        String sql = "INSERT INTO paciente (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad)values(?,?,?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,cedula);
        pst.setInt(2,Integer.parseInt(historial));
        pst.setString(3,nombre);
        pst.setString(4,apellido);
        pst.setString(5,telefono);
        pst.setInt(6,Integer.parseInt(edad));
        pst.setString(7,enfermedad);

        int row = pst.executeUpdate();
        if (row > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
        }
        pst.close();
        connection.close();

    }

    public void iniciar(){
        setVisible(true);
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
