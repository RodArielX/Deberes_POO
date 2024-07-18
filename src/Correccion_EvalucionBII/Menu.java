package Correccion_EvalucionBII;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JRadioButton REGISTRARPACIENTESRadioButton;
    private JRadioButton BUSQUEDADEPACIENTESRadioButton;
    private JRadioButton ACTUALIZARREGISTRORadioButton;
    private JRadioButton ELIMINARPACIENTESRadioButton;
    private JButton SALIRButton;
    private JPanel panel_menu;

    public Menu(){
        super("MENU");
        setContentPane(panel_menu);
        REGISTRARPACIENTESRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar ventana_registro = new Registrar();
                ventana_registro.iniciar();
                dispose();
            }
        });
        BUSQUEDADEPACIENTESRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Busqueda ventana_busqueda = new Busqueda();
                ventana_busqueda.iniciar();
                dispose();
            }
        });
        ACTUALIZARREGISTRORadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actualizar ventana_actualizar = new Actualizar();
                ventana_actualizar.iniciar();
                dispose();
            }
        });
        ELIMINARPACIENTESRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eliminar ventana_eliminar = new Eliminar();
                ventana_eliminar.iniciar();
                dispose();
            }
        });
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
