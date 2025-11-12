/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivos;

import control.Jugador;
import control.Equipo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoManager {
    private static final String RUTA = "./data/players.txt";

    public static void guardarJugadores(List<Equipo> equipos) {
        try {
            File folder = new File("./data");
            if (!folder.exists()) folder.mkdirs();
            try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA))) {
                for (Equipo e : equipos) {
                    for (Jugador j : e.getJugadores()) {
                        pw.println(j.toCSV());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<Jugador> cargarJugadores() {
        List<Jugador> list = new ArrayList<>();
        File f = new File(RUTA);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Jugador j = Jugador.fromCSV(line);
                if (j != null) list.add(j);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
