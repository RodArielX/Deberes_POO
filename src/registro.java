import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class registro extends JFrame{
    private JTextField nom;
    private JTextField apel;
    private JButton REGISTRARButton;
    private JButton ELIMINARButton;
    private JButton ACTUALIZARButton;
    private JButton BUSCARButton;
    private JButton LIMPIARButton;
    private JPanel panel_registro;
    private JTextField numid;

    public registro(){
        super("REGISTRO");
        setContentPane(panel_registro);
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
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
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarRegistro();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpieza();
            }
        });
    }
    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://uev5hhbaykeqnood:wBK7JJIEZeUJRdMQ0Huv@bof0urc92tnnf9s8rqwf-mysql.services.clever-cloud.com:3306/bof0urc92tnnf9s8rqwf";
        String user = "uev5hhbaykeqnood";
        String password = "wBK7JJIEZeUJRdMQ0Huv";

        return DriverManager.getConnection(url, user,password);
    }

    public void registrar()throws SQLException{
        String nombre = nom.getText();
        String apellido = apel.getText();
        String cedula = numid.getText();
        Connection connection = conexion();
        String sql = "INSERT INTO registro(id, nombre, apellido)values(?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,nombre);
        pst.setString(2,apellido);
        pst.setString(3,cedula);

        int row = pst.executeUpdate();
        if (row > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");

        }
        pst.close();
        connection.close();
    }
    public void actualizarDatos()throws SQLException{
        String nombre = nom.getText();
        String apellido = apel.getText();
        String cedula = numid.getText();
        Connection connection = conexion();
        String sql ="UPDATE registro set id =?, nombre = ?, apellido = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,nombre);
        pst.setString(2,apellido);
        pst.setInt(3,Integer.parseInt(cedula));

        int row = pst.executeUpdate();
        if (row >0){
            JOptionPane.showMessageDialog(null,"Paciente actualizado");
        }

    }

    public void buscarRegistro()throws SQLException{
        String cedula = numid.getText();
        Connection connection = conexion();
        String sql = "SELECT * FROM registro where id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1,Integer.parseInt(cedula));
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            String buscar = rs.getString("id");
            if (buscar.equals(cedula)){
                nom.setText(rs.getString("nombre"));
                apel.setText(rs.getString("apellido"));
                numid.setText(rs.getString("id"));
            }
        }else {
            JOptionPane.showMessageDialog(null,"No se encontro el registro");
        }
    }
    public void eliminar()throws SQLException{
        String cedula = numid.getText();
        Connection connection = conexion();
        String sql = "DELETE FROM registro where id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1,cedula);

        int row = pst.executeUpdate();
        if (row > 0){
            JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
        }
        connection.close();
        pst.close();
    }

    public void limpieza(){
        nom.setText("");
        apel.setText("");
        numid.setText("");
    }

    public void iniciar(){
        setVisible(true);
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
