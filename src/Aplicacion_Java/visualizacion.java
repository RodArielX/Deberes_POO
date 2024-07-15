package Aplicacion_Java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class visualizacion extends JFrame{
    private JButton visualizarButton;
    private JButton volverButton;
    private JLabel visualizar;
    private JPanel panel_visualizacion;

    public visualizacion() {
        super("VISUALIZACION");
        setContentPane(panel_visualizacion);

        visualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrar();
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
        String password="";

        return DriverManager.getConnection(url,user,password);
    }

    public void mostrar() throws SQLException {
        Connection conectar = conexion();
        String sql = "SELECT * FROM estudiantes";
        Statement stm = conectar.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        StringBuilder strg = new StringBuilder("<html>");
        while (rs.next()){
            strg.append("<b>Nombre: </b>").append(rs.getString("nombre")).append("<br>");
            strg.append("<b>Apellido: </b>").append(rs.getString("apellido")).append("<br>");
            strg.append("<b>Direccion: </b>").append(rs.getString("direccion")).append("<br>");
            strg.append("<b>Edad: </b>").append(rs.getString("edad")).append("<br>");
            strg.append("<b>Telefono: </b>").append(rs.getString("telefono")).append("<br>");
            strg.append("<b>Correo: </b>").append(rs.getString("correo")).append("<br>");
            strg.append("<b>Nota 1: </b>").append(rs.getString("nota1")).append("<br>");
            strg.append("<b>Nota 2: </b>").append(rs.getString("nota2")).append("<br>");
        }
        strg.append("<html>");
        visualizar.setText(strg.toString());
        conectar.close();
        stm.close();
    }
    public void iniciar(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400,500);
    }
}

