package co.edu.proyectoIntegrador1.ATRR;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Comenzar comenzar = new Comenzar();
                comenzar.setContentPane(comenzar.Comenzar);
                comenzar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                comenzar.pack();
                //comenzar.setExtendedState(JFrame.MAXIMIZED_BOTH);
                comenzar.setVisible(true);
                comenzar.setSize(500, 600);
                comenzar.setLocationRelativeTo(null);
            }
        });
    }

}

