package Aplicacion_Java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginAdmin extends JFrame {
    private JTextField user;
    private JTextField contra;
    private JButton INICIARSESIONButton;
    private JPanel panel_login;

    public LoginAdmin(){
        super("LOGIN");
        setContentPane(panel_login);
        INICIARSESIONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validarLogin();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public Connection conecta() throws SQLException {
        String url= "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password= "";

        return DriverManager.getConnection(url,user,password);
    }

    public void validarLogin() throws SQLException {
        String usuario = user.getText();
        String password = contra.getText();
        Connection conexion = conecta();
        String query = "SELECT * FROM acceso WHERE usuario = ? AND contraseña = ?";
        PreparedStatement pst = conexion.prepareStatement(query);
        pst.setString(1,usuario);
        pst.setString(2,password);
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            String user =rs.getString("usuario");
            String pass =rs.getString("contraseña");
            if (user.equals(usuario) && pass.equals(password) ){
                Menu menu =new Menu();
                menu.iniciar();
                dispose();
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"ERROR. INTENTE DE NUEVO");
            user.setText("");
            contra.setText("");
        }
        conecta().close();
        rs.close();
        pst.close();

    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
