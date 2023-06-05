package co.edu.proyectoIntegrador1.ATRR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Comenzar extends JFrame {
    JPanel Comenzar;
    private JLabel imagenLabel;
    private JButton comenzarButton;

    public Comenzar() {
    comenzarButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
        Menu menu = new Menu();
        menu.setContentPane(menu.panel1);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setVisible(true);
        menu.pack();

        menu.setSize(500, 600);
        menu.setLocationRelativeTo(null);
        Comenzar.this.dispose();

        }
    });
}
}
