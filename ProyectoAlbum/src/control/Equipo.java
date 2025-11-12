/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private List<Jugador> jugadores = new ArrayList<>();

    public Equipo(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }
    public List<Jugador> getJugadores() { return jugadores; }

    public void addJugador(Jugador j) { jugadores.add(j); }
    public void removeJugador(Jugador j) { jugadores.remove(j); }

    public Jugador findByNumero(int numero) {
        for (Jugador j : jugadores)
            if (j.getNumero() == numero) return j;
        return null;
    }
}
