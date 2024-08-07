package Correccion_EvalucionBII;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JTextField user;
    private JTextField contra;
    private JButton INGRESARButton;
    private JPanel panel_login;

    public Login(){
        super("INICIO DE SESION");
        setContentPane(panel_login);

        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inicioSesion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uceiprz5lnyxwcun:2MEuIBNxrt0DUkvVbQaE@bi7ej80dgemnxzfdcj4h-mysql.services.clever-cloud.com:3306/bi7ej80dgemnxzfdcj4h";
        String user = "uceiprz5lnyxwcun";
        String password = "2MEuIBNxrt0DUkvVbQaE";

        return DriverManager.getConnection(url,user,password);
    }

    public void inicioSesion()throws SQLException{
        String usuario = user.getText();
        String contraseña = contra.getText();
        Connection connection = conexion();
        String sql = "SELECT * FROM acceso where usuario = ? AND password = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,usuario);
        pst.setString(2,contraseña);
        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            String usuario1 = rs.getString("usuario");
            String contraseña1 = rs.getString("password");
            if (usuario1.equals(usuario) && contraseña1.equals(contraseña)){
                Menu ventana_menu = new Menu();
                ventana_menu.iniciar();
                dispose();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta. Intente de nuevo","ERROR",JOptionPane.ERROR_MESSAGE);
            user.setText("");
            contra.setText("");
        }
        pst.close();
        connection.close();
    }

    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
