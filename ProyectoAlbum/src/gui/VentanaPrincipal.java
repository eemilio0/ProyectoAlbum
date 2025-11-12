/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import control.AlbumManager;
import control.Equipo;
import control.Jugador;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private AlbumManager manager;
    private boolean esAdmin;
    private DefaultListModel<String> equiposModel = new DefaultListModel<>();
    private JList<String> listEquipos = new JList<>(equiposModel);

    public VentanaPrincipal(AlbumManager manager, boolean esAdmin) {
        super("Álbum de Estampillas");
        this.manager = manager;
        this.esAdmin = esAdmin;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        init();
        cargarEquipos();
    }

    private void init() {
        // Panel lateral con lista de equipos
        JPanel left = new JPanel(new BorderLayout());
        left.setPreferredSize(new Dimension(220, 0));
        left.add(new JLabel("Equipos:"), BorderLayout.NORTH);
        listEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        left.add(new JScrollPane(listEquipos), BorderLayout.CENTER);

        // Área central para mostrar jugadores
        JPanel center = new JPanel(new BorderLayout());
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        center.add(new JScrollPane(ta), BorderLayout.CENTER);

        // Botonera inferior
        JPanel botones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnConsultar = new JButton("Consultar");
        botones.add(btnAgregar);
        botones.add(btnModificar);
        botones.add(btnEliminar);
        botones.add(btnConsultar);

        if (!esAdmin) { // Si es usuario normal, no puede modificar nada
            btnAgregar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }

        add(left, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // Mostrar jugadores de un equipo
        listEquipos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String sel = listEquipos.getSelectedValue();
                if (sel != null) {
                    Equipo eq = manager.ensureEquipo(sel);
                    StringBuilder sb = new StringBuilder();
                    for (Jugador j : eq.getJugadores())
                        sb.append(j.toString()).append("\n");
                    ta.setText(sb.toString());
                }
            }
        });

        // Botón AGREGAR
        btnAgregar.addActionListener(a -> {
            FormularioJugador f = new FormularioJugador(this);
            f.setVisible(true);
            if (f.isAceptado()) {
                Jugador j = f.getJugadorFromForm();
                if (j == null) {
                    DialogoConEstilo.mostrarError("Datos inválidos.");
                    return;
                }
                boolean ok = manager.agregarJugador(j);
                if (!ok) DialogoConEstilo.mostrarError("Jugador duplicado (mismo equipo y número).");
                else {
                    manager.saveToFile();
                    cargarEquipos();
                    DialogoConEstilo.mostrarInfo("Jugador agregado correctamente.");
                }
            }
        });

        // Botón MODIFICAR
        btnModificar.addActionListener(a -> {
            String sel = listEquipos.getSelectedValue();
            if (sel == null) {
                DialogoConEstilo.mostrarError("Selecciona un equipo primero.");
                return;
            }
            String numStr = JOptionPane.showInputDialog(this, "Número del jugador a modificar:");
            if (numStr == null) return;
            try {
                int num = Integer.parseInt(numStr);
                Jugador existente = manager.buscarJugador(sel, num);
                if (existente == null) {
                    DialogoConEstilo.mostrarError("Jugador no encontrado.");
                    return;
                }
                FormularioJugador f = new FormularioJugador(this);
                f.setJugadorToForm(existente);
                f.setVisible(true);
                if (f.isAceptado()) {
                    Jugador nuevo = f.getJugadorFromForm();
                    manager.modificarJugador(sel, num, nuevo);
                    manager.saveToFile();
                    cargarEquipos();
                    DialogoConEstilo.mostrarInfo("Jugador modificado.");
                }
            } catch (Exception ex) {
                DialogoConEstilo.mostrarError("Número inválido.");
            }
        });

        // Botón ELIMINAR
        btnEliminar.addActionListener(a -> {
            String sel = listEquipos.getSelectedValue();
            if (sel == null) {
                DialogoConEstilo.mostrarError("Selecciona un equipo primero.");
                return;
            }
            String numStr = JOptionPane.showInputDialog(this, "Número del jugador a eliminar:");
            if (numStr == null) return;
            try {
                int num = Integer.parseInt(numStr);
                boolean ok = manager.eliminarJugador(sel, num);
                if (!ok) DialogoConEstilo.mostrarError("Jugador no encontrado.");
                else {
                    manager.saveToFile();
                    cargarEquipos();
                    DialogoConEstilo.mostrarInfo("Jugador eliminado correctamente.");
                }
            } catch (Exception ex) {
                DialogoConEstilo.mostrarError("Número inválido.");
            }
        });

        // Botón CONSULTAR (muestra tarjeta)
        btnConsultar.addActionListener(a -> {
            String sel = listEquipos.getSelectedValue();
            if (sel == null) {
                DialogoConEstilo.mostrarError("Selecciona un equipo primero.");
                return;
            }
            String numStr = JOptionPane.showInputDialog(this, "Número del jugador a consultar:");
            if (numStr == null) return;
            try {
                int num = Integer.parseInt(numStr);
                Jugador j = manager.buscarJugador(sel, num);
                if (j == null) {
                    DialogoConEstilo.mostrarError("Jugador no encontrado.");
                    return;
                }
                TarjetaJugador.mostrarTarjeta(this, j);
            } catch (Exception ex) {
                DialogoConEstilo.mostrarError("Número inválido.");
            }
        });
    }

    private void cargarEquipos() {
        equiposModel.clear();
        List<Equipo> list = manager.getEquipos();
        for (Equipo e : list)
            equiposModel.addElement(e.getNombre());
    }
}
