package co.edu.proyectoIntegrador1.ATRR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RegistrarUsuario extends JFrame{
    private JLabel nombreDeUsuarioLabel;
    private JTextField nombreUsuario;
    private JTextField contraseña;
    private JLabel contraseñaLabel;
    private JButton registrarseButton;
    public JPanel RegistroUsuario;
    private JButton volverButton;

    public RegistrarUsuario() {
        registrarseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreDeUsuario = nombreUsuario.getText();
                String contraseñaUsuario = contraseña.getText();

                // Verificar si el nombre de usuario ya existe
                if (usuarioExiste(nombreDeUsuario)) {
                    // El nombre de usuario ya existe, mostrar un mensaje de error
                    JOptionPane.showMessageDialog(RegistrarUsuario.this, "El nombre de usuario ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(RegistrarUsuario.this, "Registro completado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);

                    IniciarSesión ventanaDeInicioDeSesion = new IniciarSesión();
                    ventanaDeInicioDeSesion.setContentPane(ventanaDeInicioDeSesion.IniciarSecion);
                    ventanaDeInicioDeSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    ventanaDeInicioDeSesion.setVisible(true);
                    ventanaDeInicioDeSesion.pack();
                    ventanaDeInicioDeSesion.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    RegistrarUsuario.this.dispose();


                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true));
                        writer.write(nombreDeUsuario + ":" + contraseñaUsuario + ":" );
                        writer.newLine();
                        writer.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(RegistrarUsuario.this, "Ocurrió un error al guardar la información del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Menu menu = new Menu();
                menu.setContentPane(menu.panel1);
                menu.pack();
                menu.setVisible(true);
                menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
                menu.setSize(500, 600);
                menu.setLocationRelativeTo(null);

                RegistrarUsuario.this.dispose();
            }
        });
    }

    private boolean usuarioExiste(String nombreDeUsuario) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length > 0 && partes[0].equals(nombreDeUsuario)) {
                    // El nombre de usuario ya está registrado
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al leer el archivo de usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // El nombre de usuario no está registrado
        return false;
    }
}