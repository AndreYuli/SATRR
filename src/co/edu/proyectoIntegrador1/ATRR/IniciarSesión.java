package co.edu.proyectoIntegrador1.ATRR;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IniciarSesión extends JFrame {
        public JPanel IniciarSecion;
        private JTextField nombre;
        private JButton iniciarSecionButton;
        private JButton registrarmeButton;
        private JLabel contraseñaLabel;
    private JPasswordField contraseñaPasswordField;
        private JButton volverButton;
    private JLabel nombreLabel;

    private boolean verificarUsuario(String nombreDeUsuario, String contraseña) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length > 0) {
                    if (partes[0].equals(nombreDeUsuario) && partes[1].equals(contraseña)) {
                        return true;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al leer el archivo de usuarios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }



        public IniciarSesión() {
            iniciarSecionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String nombreDeUsuario = nombre.getText();
                    String contraseña = contraseñaPasswordField.getText();

                    // Verificar si el nombre de usuario y la contraseña no están vacíos
                    if (nombreDeUsuario.isEmpty() || contraseña.isEmpty()) {
                        // Mostrar un mensaje de error indicando que los campos son obligatorios
                        JOptionPane.showMessageDialog(IniciarSesión.this, "Debes ingresar un nombre de usuario y una contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Los campos no están vacíos, continuar con la verificación del usuario
                        if (verificarUsuario(nombreDeUsuario, contraseña)) {

                            MenuPrincipal menuPrincipal = new MenuPrincipal();
                            menuPrincipal.setContentPane(menuPrincipal.menuPrincipal);
                            menuPrincipal.setVisible(true);
                            menuPrincipal.pack();
                            menuPrincipal.setSize(500, 600);
                            menuPrincipal.setLocationRelativeTo(null);
                            IniciarSesión.this.dispose();


                        } else {
                            // El usuario o la contraseña son incorrectos, mostrar un mensaje de error
                            JOptionPane.showMessageDialog(IniciarSesión.this, "El usuario o la contraseña son incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });


            volverButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    //IniciarSesión.this.dispose();
                    Menu menu = new Menu();
                    menu.setContentPane(menu.panel1);
                    menu.pack();
                    menu.setVisible(true);
                    menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        menu.setSize(500, 600);
                        menu.setLocationRelativeTo(null);
                    IniciarSesión.this.dispose();
                }
            });
        }
}
