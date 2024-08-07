package Correccion_EvalucionBII;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Busqueda extends JFrame {
    private JTextField cedula;
    private JButton BUSCARButton;
    private JLabel numCedu;
    private JLabel nom;
    private JLabel cel;
    private JLabel numHisto;
    private JLabel apel;
    private JLabel años;
    private JLabel enfer;
    private JButton MENUButton;
    private JPanel panel_busqueda;
    private JButton LIMPIARButton;

    public Busqueda(){
        super("BUSQUEDA DE PACIENTES");
        setContentPane(panel_busqueda);
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    busquedaPacientes();
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
        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarRegistro();
            }
        });
    }

    public Connection conexion()throws SQLException{
        String url = "jdbc:mysql://uceiprz5lnyxwcun:2MEuIBNxrt0DUkvVbQaE@bi7ej80dgemnxzfdcj4h-mysql.services.clever-cloud.com:3306/bi7ej80dgemnxzfdcj4h";
        String user = "uceiprz5lnyxwcun";
        String password = "2MEuIBNxrt0DUkvVbQaE";

        return DriverManager.getConnection(url, user, password);
    }

    public void busquedaPacientes()throws SQLException{
        String numcedulaa = cedula.getText();
        Connection connection = conexion();
        String sql = "SELECT * FROM PACIENTE where cedula = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,Integer.parseInt(numcedulaa));
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            String buscar = rs.getString("cedula");
            if (buscar.equals(numcedulaa)){
                numCedu.setText(rs.getString("cedula"));
                numHisto.setText(rs.getString("n_historial_clinico"));
                nom.setText(rs.getString("nombre"));
                apel.setText(rs.getString("apellido"));
                cel.setText(rs.getString("telefono"));
                años.setText(rs.getString("edad"));
                enfer.setText(rs.getString("descripcion_enfermedad"));
            }
        }else {
            JOptionPane.showMessageDialog(null, "No se encontro el registro del paciente","ERROR",JOptionPane.ERROR_MESSAGE);
            cedula.setText("");
        }

    }
    public void limpiarRegistro(){
        cedula.setText("");
        numCedu.setText("");
        numHisto.setText("");
        nom.setText("");
        apel.setText("");
        cel.setText("");
        años.setText("");
        enfer.setText("");
    }

    public void iniciar(){
        setVisible(true);
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
