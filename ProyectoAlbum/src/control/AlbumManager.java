/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import archivos.ArchivoManager;
import java.util.ArrayList;
import java.util.List;

public class AlbumManager {
    private static AlbumManager instance = new AlbumManager();
    private List<Equipo> equipos = new ArrayList<>();

    private AlbumManager() {}
    public static AlbumManager getInstance() { return instance; }

    public List<Equipo> getEquipos() { return equipos; }

    public Equipo ensureEquipo(String nombre) {
        for (Equipo e : equipos)
            if (e.getNombre().equalsIgnoreCase(nombre))
                return e;
        Equipo e = new Equipo(nombre);
        equipos.add(e);
        return e;
    }

    public boolean agregarJugador(Jugador j) {
        Equipo e = ensureEquipo(j.getEquipo());
        if (e.findByNumero(j.getNumero()) != null) return false;
        e.addJugador(j);
        return true;
    }

    public Jugador buscarJugador(String equipo, int numero) {
        for (Equipo e : equipos)
            if (e.getNombre().equalsIgnoreCase(equipo))
                return e.findByNumero(numero);
        return null;
    }

    public boolean eliminarJugador(String equipo, int numero) {
        Jugador j = buscarJugador(equipo, numero);
        if (j == null) return false;
        Equipo e = ensureEquipo(equipo);
        e.removeJugador(j);
        return true;
    }
    
    public boolean modificarJugador(String equipo, int numero, Jugador nuevo) {
    Jugador existente = buscarJugador(equipo, numero);
    if (existente == null) return false;

    // Actualizar 
    existente.setNombre(nuevo.getNombre());
    existente.setPosicion(nuevo.getPosicion());
    existente.setNumero(nuevo.getNumero());
    existente.setNacionalidad(nuevo.getNacionalidad());
    existente.setEdad(nuevo.getEdad());
    existente.setDivision(nuevo.getDivision());
    existente.setRutaImagenJugador(nuevo.getRutaImagenJugador());
    existente.setRutaEscudoEquipo(nuevo.getRutaEscudoEquipo());

    // Moverlo si se cambio de eq
    if (!existente.getEquipo().equalsIgnoreCase(nuevo.getEquipo())) {
        eliminarJugador(equipo, numero);
        agregarJugador(nuevo);
    }

    return true;
}


    public void saveToFile() { ArchivoManager.guardarJugadores(equipos); }
    public void loadFromFile() {
        List<Jugador> list = ArchivoManager.cargarJugadores();
        if (list == null) return;
        equipos.clear();
        for (Jugador j : list)
            ensureEquipo(j.getEquipo()).addJugador(j);
    }
}
