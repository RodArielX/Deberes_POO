package Aplicacion_Java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class busqueda extends JFrame {
    private JButton BUSCARButton;
    private JTextField codigo;
    private JButton volverButton;
    private JLabel nom;
    private JLabel apel;
    private JLabel direc;
    private JLabel edad;
    private JLabel cel;
    private JLabel email;
    private JLabel not1;
    private JLabel not2;
    private JPanel panel_busqueda;

    public busqueda() {
        super("BUSQUEDA");
        setContentPane(panel_busqueda);
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    busquedaEstu();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventana_menu = new Menu();
                ventana_menu.iniciar();
                dispose();
            }
        });
    }

    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    public void busquedaEstu() throws SQLException {
        String codigo_matricula = codigo.getText();
        Connection conectar = conexion();
        String sql = "SELECT * FROM estudiantes WHERE codigo_matricula = ?";
        PreparedStatement strm = conectar.prepareStatement(sql);
        strm.setString(1, codigo_matricula);
        ResultSet rs = strm.executeQuery();
        if (rs.next()) {
            String busqueda = rs.getString("codigo_matricula");
            if (busqueda.equals(codigo_matricula)) {
                nom.setText(rs.getString("nombre"));
                apel.setText(rs.getString("apellido"));
                direc.setText(rs.getString("direccion"));
                edad.setText(rs.getString("edad"));
                cel.setText(rs.getString("telefono"));
                email.setText(rs.getString("correo"));
                not1.setText(rs.getString("nota1"));
                not2.setText(rs.getString("nota2"));
            }
        }
    }

    public void iniciar() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
    }
}





