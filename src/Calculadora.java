import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton sumaButton;
    private JButton multiplicacionButton;
    private JButton divisionButton;
    private JButton restaButton;
    private JButton potencia2Button;
    private JButton raizButton;
    private JButton senoButton;
    private JButton cosenoButton;
    private JButton tangenteButton;
    private JButton logaritmoButton;
    private JButton factorialButton;
    private JPanel JPanel1;

    public Calculadora(){
        super("Calculadora");
        setContentPane(JPanel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sumaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float numero2 = Float.parseFloat(textField2.getText());
                float resultado = numero1 + numero2;
                showResult(resultado);
            }
        });

        restaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float numero2 = Float.parseFloat(textField2.getText());
                float resultado = numero1 - numero2;
                showResult(resultado);
            }
        });

        multiplicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float numero2 = Float.parseFloat(textField2.getText());
                float resultado = numero1 * numero2;
                showResult(resultado);
            }
        });

        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float numero2 = Float.parseFloat(textField2.getText());
                float resultado = numero2 != 0 ? numero1 / numero2 : Float.NaN;
                showResult(resultado);
            }
        });

        potencia2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float numero2 = Float.parseFloat(textField2.getText());
                float resultado = (float) Math.pow(numero1, numero2);
                showResult(resultado);
            }
        });

        raizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float resultado = (float) Math.sqrt(numero1);
                showResult(resultado);
            }
        });

        senoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float resultado = (float) Math.sin(Math.toRadians(numero1));
                showResult(resultado);
            }
        });

        cosenoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float resultado = (float) Math.cos(Math.toRadians(numero1));
                showResult(resultado);
            }
        });

        tangenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float resultado = (float) Math.tan(Math.toRadians(numero1));
                showResult(resultado);
            }
        });

        logaritmoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float resultado = numero1 > 0 ? (float) Math.log(numero1) : Float.NaN;
                showResult(resultado);
            }
        });

        factorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float numero1 = Float.parseFloat(textField1.getText());
                float resultado = factorial(numero1);
                showResult(resultado);
            }

            private float factorial(float n) {
                if (n < 0) return Float.NaN;
                if (n == 0) return 1;
                float resultado = 1;
                for (int i = 1; i <= n; i++) {
                    resultado *= i;
                }
                return resultado;
            }
        });
    }

    private void showResult(float resultado) {
        textField3.setText(String.valueOf(resultado));
        JOptionPane.showMessageDialog(this, "El resultado de su operacion es: " + resultado);
    }
}


