package Aplicacion_Java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JRadioButton busquedaDeEstudiantesRadioButton;
    private JRadioButton registrarEstudiantesRadioButton;
    private JRadioButton visualizarTodosLosRegistrosRadioButton;
    private JButton SALIRButton;
    private JPanel Panel_Menu;

    public Menu(){
        super("Menu");
        setContentPane(Panel_Menu);
        registrarEstudiantesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estudiantes ventana_estudiantes = new estudiantes();
                ventana_estudiantes.iniciar();
                dispose();
            }
        });
        busquedaDeEstudiantesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busqueda ventana_search = new busqueda();
                ventana_search.iniciar();
                dispose();
            }
        });
        visualizarTodosLosRegistrosRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizacion ventana_ver = new visualizacion();
                ventana_ver.iniciar();
                dispose();
            }
        });
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginAdmin ventana_login = new LoginAdmin();
                ventana_login.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
