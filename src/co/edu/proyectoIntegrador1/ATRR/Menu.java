package co.edu.proyectoIntegrador1.ATRR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    JPanel panel1;
    private JButton iniciarSeciónButton;
    private JButton registrarseButton;
    private JButton volverButton;

    public Menu() {
        iniciarSeciónButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarIniciarSesión();
            }
        });

        registrarseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarRegistrarUsuario();
            }
        });

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarVolver();

            }
        });
    }

    /**
     * Muestra la ventana de inicio de sesión
     */
    private void mostrarIniciarSesión() {
        try {
            IniciarSesión iniciarSesión = new IniciarSesión();
            iniciarSesión.setContentPane(iniciarSesión.IniciarSecion);
            iniciarSesión.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            iniciarSesión.setVisible(true);
            iniciarSesión.pack();
            iniciarSesión.setSize(500, 600);
            iniciarSesión.setLocationRelativeTo(null);


            Menu.this.dispose();
        } catch (Exception e) {
            // Manejo de errores
            e.printStackTrace();
        }
    }

    /**
     * Muestra la ventana de registro de usuario
     */
    private void mostrarRegistrarUsuario() {
        try {
            RegistrarUsuario registrarUsuario = new RegistrarUsuario();
            registrarUsuario.setContentPane(registrarUsuario.RegistroUsuario);
            registrarUsuario.setDefaultCloseOperation(EXIT_ON_CLOSE);
            registrarUsuario.setVisible(true);
            registrarUsuario.pack();
            registrarUsuario.setSize(500, 600);
            registrarUsuario.setLocationRelativeTo(null);
            Menu.this.dispose();
        } catch (Exception e) {
            // Manejo de errores
            e.printStackTrace();
        }
    }
    private void mostrarVolver() {
        try {
            Comenzar comenzar = new Comenzar();
            comenzar.setContentPane(comenzar.Comenzar);
            comenzar.setDefaultCloseOperation(EXIT_ON_CLOSE);
            comenzar.setVisible(true);
            comenzar.pack();
            comenzar.setSize(500, 600);
            comenzar.setLocationRelativeTo(null);
            Menu.this.dispose();

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
