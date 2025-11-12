/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import control.Jugador;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TarjetaJugador {

    public static void mostrarTarjeta(JFrame parent, Jugador j) {
        
        JDialog d = new JDialog(parent, "Tarjeta - " + j.getNombre(), true);
        d.setSize(420, 380);
        d.setLocationRelativeTo(parent);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Información 
        p.add(new JLabel("Equipo: " + j.getEquipo()));
        p.add(new JLabel("Nombre: " + j.getNombre()));
        p.add(new JLabel("Posición: " + j.getPosicion()));
        p.add(new JLabel("Número: " + j.getNumero()));
        p.add(new JLabel("Nacionalidad: " + j.getNacionalidad()));
        p.add(new JLabel("Edad: " + j.getEdad()));
        p.add(new JLabel("División: " + j.getDivision()));

        // Imagen del jugador
        String rutaJugador = j.getRutaImagenJugador();
        if (rutaJugador != null && !rutaJugador.isEmpty()) {
            JLabel lblImg = cargarImagen(rutaJugador, 150, 150, "jugador");
            if (lblImg != null) p.add(lblImg);
            else p.add(new JLabel("(No se encontró imagen del jugador)"));
        }

        // Escudo del equipo
        String rutaEscudo = j.getRutaEscudoEquipo();
        if (rutaEscudo != null && !rutaEscudo.isEmpty()) {
            JLabel lblEscudo = cargarImagen(rutaEscudo, 80, 80, "escudo");
            if (lblEscudo != null) p.add(lblEscudo);
            else p.add(new JLabel("(No se encontró escudo del equipo)"));
        }

        d.add(new JScrollPane(p));
        d.setVisible(true);
    }

   //Cargar imagen desde ruta
    private static JLabel cargarImagen(String ruta, int ancho, int alto, String tipo) {
        try {
            File f = new File(ruta);
            System.out.println("Intentando cargar imagen de " + tipo + ": " + f.getPath());
            System.out.println("¿Existe archivo? " + f.exists());

            // Buscqueda en otra carpeta
            if (!f.exists()) {
                File alt = new File("imagenes/" + ruta);
                System.out.println("Buscando alternativa: " + alt.getPath());
                if (alt.exists()) {
                    f = alt;
                    System.out.println("Encontrada en carpeta imagenes/");
                } else {
                    System.out.println("No se encontró imagen en ninguna ruta");
                    return null;
                }
            }

            ImageIcon ico = new ImageIcon(f.getAbsolutePath());
            Image img = ico.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(img));

        } catch (Exception ex) {
            System.err.println("Error al cargar imagen de " + tipo + ": " + ex.getMessage());
            return null;
        }
    }
}
