package co.edu.proyectoIntegrador1.ATRR;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JButton verListadoDePacientesButton;
    private JButton registrarPacientesButton;
    public JPanel menuPrincipal;
    private JButton volverButton;

    public MenuPrincipal() {
        verListadoDePacientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //VerPacientes verPacientes = new VerPacientes();
                VerPaciente verPacientes = new VerPaciente();
                verPacientes.setVisible(true);
                verPacientes.setContentPane(verPacientes.Tabla);
                verPacientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                verPacientes.pack();
                verPacientes.setExtendedState(JFrame.MAXIMIZED_BOTH);
                MenuPrincipal.this.dispose();

            }

        });

        registrarPacientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegistrarPaciente registrarPaciente = new RegistrarPaciente();

                registrarPaciente.pack();
                registrarPaciente.setVisible(true);
                registrarPaciente.setContentPane(registrarPaciente.RegistrarPaciente);
                registrarPaciente.setExtendedState(MAXIMIZED_BOTH);
                MenuPrincipal.this.dispose();

            }

        });
        //pack();
        //setContentPane(this.menuPrincipal);
        //setVisible(true);
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                IniciarSesión iniciarSesión = new IniciarSesión();
                iniciarSesión.setContentPane(iniciarSesión.IniciarSecion);
                iniciarSesión.setDefaultCloseOperation(EXIT_ON_CLOSE);
                iniciarSesión.setVisible(true);
                iniciarSesión.pack();
                iniciarSesión.setSize(500, 600);
                iniciarSesión.setLocationRelativeTo(null);

                MenuPrincipal.this.dispose();

            }
        });
    }
}
