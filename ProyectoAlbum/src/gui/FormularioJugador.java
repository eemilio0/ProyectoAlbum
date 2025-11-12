/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import control.Jugador;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FormularioJugador extends JDialog {
    private JTextField tfEquipo, tfNombre, tfPosicion, tfNumero, tfNacionalidad,
            tfEdad, tfDivision, tfRutaJugador, tfRutaEscudo;
    private boolean aceptado = false;

    public FormularioJugador(JFrame parent) {
        super(parent, "Formulario de Jugador", true);
        init();
    }

    private void init() {
        setSize(500, 500);
        setLocationRelativeTo(getOwner());

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        tfEquipo = new JTextField();
        tfNombre = new JTextField();
        tfPosicion = new JTextField();
        tfNumero = new JTextField();
        tfNacionalidad = new JTextField();
        tfEdad = new JTextField();
        tfDivision = new JTextField();
        tfRutaJugador = new JTextField();
        tfRutaEscudo = new JTextField();

        p.add(new JLabel("Equipo:")); p.add(tfEquipo);
        p.add(new JLabel("Nombre:")); p.add(tfNombre);
        p.add(new JLabel("Posición:")); p.add(tfPosicion);
        p.add(new JLabel("Número:")); p.add(tfNumero);
        p.add(new JLabel("Nacionalidad:")); p.add(tfNacionalidad);
        p.add(new JLabel("Edad:")); p.add(tfEdad);
        p.add(new JLabel("División (Varonil/Femenil):")); p.add(tfDivision);

        // imagen de jugador
        p.add(new JLabel("Ruta imagen jugador:"));
        JPanel panelImgJugador = new JPanel(new BorderLayout());
        panelImgJugador.add(tfRutaJugador, BorderLayout.CENTER);
        JButton btnSelJugador = new JButton("Seleccionar...");
        panelImgJugador.add(btnSelJugador, BorderLayout.EAST);
        p.add(panelImgJugador);

        btnSelJugador.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Seleccionar imagen del jugador");
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                tfRutaJugador.setText(file.getAbsolutePath());
            }
        });

        // Escudo de equipo
        p.add(new JLabel("Ruta escudo equipo:"));
        JPanel panelEscudo = new JPanel(new BorderLayout());
        panelEscudo.add(tfRutaEscudo, BorderLayout.CENTER);
        JButton btnSelEscudo = new JButton("Seleccionar...");
        panelEscudo.add(btnSelEscudo, BorderLayout.EAST);
        p.add(panelEscudo);

        btnSelEscudo.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Seleccionar escudo del equipo");
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                tfRutaEscudo.setText(file.getAbsolutePath());
            }
        });

        // Botones
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        JPanel botones = new JPanel();
        botones.add(btnAceptar);
        botones.add(btnCancelar);
        p.add(Box.createVerticalStrut(10));
        p.add(botones);

        btnAceptar.addActionListener(e -> {
            if (tfEquipo.getText().trim().isEmpty() || tfNombre.getText().trim().isEmpty()) {
                DialogoConEstilo.mostrarError("Rellena los campos obligatorios.");
                return;
            }
            try {
                Integer.parseInt(tfNumero.getText().trim());
            } catch (Exception ex) {
                DialogoConEstilo.mostrarError("Número debe ser un número entero.");
                return;
            }

            aceptado = true;
            setVisible(false);
        });

        btnCancelar.addActionListener(e -> {
            aceptado = false;
            setVisible(false);
        });

        add(new JScrollPane(p));
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public Jugador getJugadorFromForm() {
        try {
            return new Jugador(
                tfEquipo.getText().trim(),
                tfNombre.getText().trim(),
                tfPosicion.getText().trim(),
                Integer.parseInt(tfNumero.getText().trim()),
                tfNacionalidad.getText().trim(),
                Integer.parseInt(tfEdad.getText().trim().isEmpty() ? "0" : tfEdad.getText().trim()),
                tfDivision.getText().trim(),
                tfRutaJugador.getText().trim(),
                tfRutaEscudo.getText().trim()
            );
        } catch (Exception ex) {
            return null;
        }
    }

    public void setJugadorToForm(Jugador j) {
        tfEquipo.setText(j.getEquipo());
        tfNombre.setText(j.getNombre());
        tfPosicion.setText(j.getPosicion());
        tfNumero.setText(Integer.toString(j.getNumero()));
        tfNacionalidad.setText(j.getNacionalidad());
        tfEdad.setText(Integer.toString(j.getEdad()));
        tfDivision.setText(j.getDivision());
        tfRutaJugador.setText(j.getRutaImagenJugador());
        tfRutaEscudo.setText(j.getRutaEscudoEquipo());
    }
}
