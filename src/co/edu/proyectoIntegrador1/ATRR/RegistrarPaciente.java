package co.edu.proyectoIntegrador1.ATRR;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class RegistrarPaciente extends JFrame {
    JTextField nombreTextField;
    JTextField apellidoTextField;
    JTextField edadTextField;
    JTextField medicoACargoTextField;
    JTextField enfermedadTextField;
    JTextField diaMesAñoTextField;

    private JButton guardarButton;
    private JTable Pacientes;
    private JLabel apellidoLabel;
    private JLabel edadLabel;
    private JLabel fechaLabel;
    private JLabel estadoLabel;
    private JLabel nombreLabel;
    private JLabel medicoACargoLabel;
    private JLabel enfermedadLabel;
    JComboBox Estado;
    public JPanel RegistrarPaciente;
    private JButton volverButton;

    private boolean editandoPacienteExistente = false;
    private int indicePacienteExistente = -1;

    public RegistrarPaciente() {
        LocalDate fechaActual = LocalDate.now();
        diaMesAñoTextField.setText(fechaActual.toString());
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombreTextField.getText().isEmpty() ||
                        apellidoTextField.getText().isEmpty() ||
                        edadTextField.getText().isEmpty() ||
                        enfermedadTextField.getText().isEmpty() ||
                        diaMesAñoTextField.getText().isEmpty() ||
                        medicoACargoTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(RegistrarPaciente.this, "Por favor complete todos los campos.");
                } else {
                    guardarEnArchivo();
                    if (editandoPacienteExistente) {
                        JOptionPane.showMessageDialog(RegistrarPaciente.this, "El paciente ha sido actualizado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(RegistrarPaciente.this, "El paciente ha sido registrado correctamente.");
                    }

                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                    menuPrincipal.setVisible(true);
                    menuPrincipal.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    menuPrincipal.setContentPane(menuPrincipal.menuPrincipal);
                    menuPrincipal.pack();
                    menuPrincipal.setSize(650, 770);
                    menuPrincipal.setLocationRelativeTo(null);

                    RegistrarPaciente.this.dispose();



                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                MenuPrincipal menu = new MenuPrincipal();
                menu.setContentPane(menu.menuPrincipal);
                menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
                menu.pack();
                menu.setVisible(true);
                menu.setSize(500, 600);
                menu.setLocationRelativeTo(null);
                RegistrarPaciente.this.dispose();
            }
        });
    }

    public void setEditandoPacienteExistente(boolean editandoPacienteExistente) {
        this.editandoPacienteExistente = editandoPacienteExistente;
    }

    public void setIndicePacienteExistente(int indicePacienteExistente) {
        this.indicePacienteExistente = indicePacienteExistente;
    }

    private void guardarEnArchivo() {
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String edad = edadTextField.getText();
        String enfermedad = enfermedadTextField.getText();
        String fecha = diaMesAñoTextField.getText();
        String medicoACargo = medicoACargoTextField.getText();

        Object selectedItem = Estado.getSelectedItem();
        String selectedEstado = "";
        if (selectedItem != null) {
            selectedEstado = selectedItem.toString();
        }

        try {
            if (editandoPacienteExistente) {
                // Actualizar la información del paciente existente
                List<String> lineas = Files.readAllLines(Paths.get("pacientes.txt"));
                lineas.set(indicePacienteExistente, nombre + "," + apellido + "," + edad + "," + enfermedad + "," + fecha + "," + medicoACargo + "," + selectedEstado);
                Files.write(Paths.get("pacientes.txt"), lineas);
            } else {
                // Agregar un nuevo paciente
                FileWriter fw = new FileWriter("pacientes.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(nombre + "," + apellido + "," + edad + "," + enfermedad + "," + fecha + "," + medicoACargo + "," + selectedEstado);
                bw.newLine();
                bw.close();
            }
            // Actualizar la tabla Pacientes
            Pacientes = new JTable();

            DefaultTableModel model = (DefaultTableModel) Pacientes.getModel();
            model.addRow(new Object[]{nombre, apellido, edad, enfermedad, fecha, medicoACargo, selectedEstado});
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
