package Aplicacion_Java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class estudiantes extends JFrame{
    private JPanel panel_estudiante;
    private JTextField nom;
    private JTextField apel;
    private JTextField direc;
    private JTextField eda;
    private JTextField cel;
    private JTextField corre;
    private JTextField not1;
    private JTextField not2;
    private JButton REGISTRARButton;
    private JButton VOLVERButton;

    public estudiantes(){
        super("REGISTRO");
        setContentPane(panel_estudiante);
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    RegistrarEstudiantes();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        VOLVERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventana_menu = new Menu();
                ventana_menu.iniciar();
                dispose();
            }
        });
    }
    public Connection conexion()throws SQLException{
        String url = "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

    public void RegistrarEstudiantes() throws SQLException{
        String nombre = nom.getText();
        String apellido = apel.getText();
        String direccion = direc.getText();
        String edad = eda.getText();
        String telefono = cel.getText();
        String correo = corre.getText();
        String nota1 = not1.getText();
        String nota2 = not2.getText();

        Connection conec = conexion();
        String sql = "INSERT INTO estudiantes(nombre, apellido, direccion, edad, telefono, correo, nota1, nota2) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conec.prepareStatement(sql);
        pstmt.setString(1,nombre);
        pstmt.setString(2,apellido);
        pstmt.setString(3,direccion);
        pstmt.setInt(4,Integer.parseInt(edad));
        pstmt.setInt(5,Integer.parseInt(telefono));
        pstmt.setString(6,correo);
        pstmt.setInt(7,Integer.parseInt(nota1));
        pstmt.setInt(8,Integer.parseInt(nota2));

        int rows = pstmt.executeUpdate();
        if (rows > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
        }
        pstmt.close();
        conec.close();
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
