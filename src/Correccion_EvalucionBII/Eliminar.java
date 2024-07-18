package Correccion_EvalucionBII;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Eliminar extends JFrame {
    private JTextField numced;
    private JButton ELIMINARButton;
    private JButton MENUButton;
    private JPanel panel_eliminar;

    public Eliminar(){
        super("ELIMINAR REGISTROS");
        setContentPane(panel_eliminar);
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu ventana_menu = new Menu();
                ventana_menu.iniciar();
                dispose();
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarPacientes();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public Connection conexion()throws SQLException{
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

    public void eliminarPacientes()throws SQLException{
        String numcedula = numced.getText();
        Connection connection = conexion();
        String sql = "DELETE FROM paciente where cedula = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,numcedula);

        int row = pst.executeUpdate();
        if (row > 0){
            JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
        }else {
            JOptionPane.showMessageDialog(null,"No se encontro ningun registro");
        }
        connection.close();
        pst.close();
    }

    public void iniciar(){
        setVisible(true);
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
