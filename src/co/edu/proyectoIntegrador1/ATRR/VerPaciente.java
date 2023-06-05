package co.edu.proyectoIntegrador1.ATRR;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class VerPaciente extends JFrame {
    private JTable Pacientes;
    private JButton editarButton;
    JPanel Tabla;
    private JButton volverButton;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JTextField Elemento;
    private JButton mostrarTodosButton;
    private JButton exportarButton;

    public VerPaciente() {
        // Crear el panel de búsqueda

        // Crear el modelo de tabla
        String[] columnNames = {"Nombre", "Apellido", "Edad", "Enfermedad", "Fecha", "Medico a cargo", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Leer el contenido del archivo pacientes.txt y agregarlo al modelo de tabla
        try {
            FileReader fr = new FileReader("pacientes.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] data = linea.split(","); // suponiendo que cada campo está separado por una coma
                tableModel.addRow(data);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Configurar el modelo de tabla en la instancia de la tabla Pacientes que ya tienes en tu formulario
        Pacientes.setModel(tableModel);
        Pacientes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        Pacientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Pacientes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Agregar un ListSelectionListener a la selección de la tabla Pacientes
        Pacientes.getSelectionModel().addListSelectionListener(e -> {
            // Verificar si hay una fila seleccionada en la tabla
            if (Pacientes.getSelectedRow() != -1) {
                // Si hay una fila seleccionada, mostrar los botones editarButton y eliminarButton
                editarButton.setVisible(true);
                eliminarButton.setVisible(true);
            } else {
                // Si no hay ninguna fila seleccionada, ocultar los botones editarButton y eliminarButton
                editarButton.setVisible(false);
                eliminarButton.setVisible(false);
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada en la tabla Pacientes
                int filaSeleccionada = Pacientes.getSelectedRow();

                // Verificar si hay una fila seleccionada
                if (filaSeleccionada != -1) {
                    // Si hay una fila seleccionada, obtener los datos de la fila
                    String nombre = (String) Pacientes.getValueAt(filaSeleccionada, 0);
                    String apellido = (String) Pacientes.getValueAt(filaSeleccionada, 1);
                    String edad = (String) Pacientes.getValueAt(filaSeleccionada, 2);
                    String enfermedad = (String) Pacientes.getValueAt(filaSeleccionada, 3);
                    String fecha = (String) Pacientes.getValueAt(filaSeleccionada, 4);
                    String medicoACargo = (String) Pacientes.getValueAt(filaSeleccionada, 5);
                    String estado = (String) Pacientes.getValueAt(filaSeleccionada, 6);

                    // Crear una instancia de la ventana registrarPacientes
                    RegistrarPaciente ventana = new RegistrarPaciente();

                    // Indicar que se está editando un paciente existente
                    ventana.setEditandoPacienteExistente(true);
                    ventana.setIndicePacienteExistente(filaSeleccionada);

                    // Llenar los campos de texto en la ventana con los datos del paciente
                    ventana.nombreTextField.setText(nombre);
                    ventana.apellidoTextField.setText(apellido);
                    ventana.edadTextField.setText(edad);
                    ventana.enfermedadTextField.setText(enfermedad);
                    ventana.diaMesAñoTextField.setText(fecha);
                    ventana.medicoACargoTextField.setText(medicoACargo);
                    ventana.Estado.setSelectedItem(estado);

                    // Mostrar la ventana
                    ventana.setContentPane(ventana.RegistrarPaciente);
                    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    ventana.pack();
                    ventana.setVisible(true);
                    ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);

                    VerPaciente.this.dispose();
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener las filas seleccionadas en la tabla Pacientes
                int[] filasSeleccionadas = Pacientes.getSelectedRows();

                // Verificar si hay filas seleccionadas
                if (filasSeleccionadas.length > 0) {
                    // Si hay filas seleccionadas, eliminarlas del modelo de la tabla
                    DefaultTableModel tableModel = (DefaultTableModel) Pacientes.getModel();
                    for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                        tableModel.removeRow(filasSeleccionadas[i]);
                    }

                    // Actualizar el archivo pacientes.txt
                    try {
                        FileWriter fw = new FileWriter("pacientes.txt", false);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for (int i = 0; i < tableModel.getRowCount(); i++) {
                            String nombre = (String) tableModel.getValueAt(i, 0);
                            String apellido = (String) tableModel.getValueAt(i, 1);
                            String edad = (String) tableModel.getValueAt(i, 2);
                            String enfermedad = (String) tableModel.getValueAt(i, 3);
                            String fecha = (String) tableModel.getValueAt(i, 4);
                            String medicoACargo = (String) tableModel.getValueAt(i, 5);
                            String estado = (String) tableModel.getValueAt(i, 6);
                            bw.write(nombre + "," + apellido + "," + edad + "," + enfermedad + "," + fecha + "," + medicoACargo + "," + estado);
                            bw.newLine();
                        }
                        bw.close();

                        // Mostrar el mensaje de eliminación
                        JOptionPane.showMessageDialog(VerPaciente.this, "Eliminado correctamente");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBuscado = Elemento.getText().toLowerCase();

                // Crear el modelo de tabla
                String[] columnNames = {"Nombre", "Apellido", "Edad", "Enfermedad", "Fecha", "Medico a cargo", "Estado"};
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

                // Leer el contenido del archivo pacientes.txt y agregar al modelo de tabla solo las filas que coincidan con el nombre buscado
                try {
                    FileReader fr = new FileReader("pacientes.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] data = linea.split(","); // suponiendo que cada campo está separado por una coma
                        if (data[0].toLowerCase().contains(nombreBuscado)) {
                            tableModel.addRow(data);
                        }
                    }
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Configurar el modelo de tabla en la instancia de la tabla Pacientes que ya tienes en tu formulario
                Pacientes.setModel(tableModel);
            }
        });

        mostrarTodosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });
        editarButton.setVisible(false);
        eliminarButton.setVisible(false);
        volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setContentPane(menuPrincipal.menuPrincipal);
                menuPrincipal.setDefaultCloseOperation(EXIT_ON_CLOSE);
                menuPrincipal.setVisible(true);
                menuPrincipal.pack();
                menuPrincipal.setSize(500, 600);
                menuPrincipal.setLocationRelativeTo(null);
                VerPaciente.this.dispose();

            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un JFileChooser para seleccionar la ubicación del archivo exportado
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Exportar pacientes");
                int userSelection = fileChooser.showSaveDialog(VerPaciente.this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Si el usuario seleccionó una ubicación, obtener la ruta del archivo seleccionado
                    File fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".csv");

                    // Leer el contenido del archivo pacientes.txt y escribirlo en el archivo seleccionado por el usuario
                    try {
                        FileWriter fw = new FileWriter(fileToSave);
                        BufferedWriter bw = new BufferedWriter(fw);

                        // Escribir la fila de encabezados con los nombres de las columnas
                        bw.write("Nombre,Apellido,Edad,Enfermedad,Fecha,Medico a cargo,Estado");
                        bw.newLine();

                        // Leer el contenido del archivo pacientes.txt y escribirlo en el archivo seleccionado por el usuario
                        List<String> lineas = Files.readAllLines(Paths.get("pacientes.txt"));
                        for (String linea : lineas) {
                            bw.write(linea);
                            bw.newLine();
                        }
                        bw.close();

                        // Mostrar el mensaje de éxito
                        JOptionPane.showMessageDialog(VerPaciente.this, "Exportado correctamente");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


    }

    private void actualizarTabla() {
        // Crear el modelo de tabla
        String[] columnNames = {"Nombre", "Apellido", "Edad", "Enfermedad", "Fecha", "Medico a cargo", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Leer el contenido del archivo pacientes.txt y agregarlo al modelo de tabla
        try {
            FileReader fr = new FileReader("pacientes.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] data = linea.split(","); // suponiendo que cada campo está separado por una coma
                tableModel.addRow(data);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Configurar el modelo de tabla en la instancia de la tabla Pacientes que ya tienes en tu formulario
        Pacientes.setModel(tableModel);
    }
}
