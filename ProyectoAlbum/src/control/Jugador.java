/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

public class Jugador {
    private String equipo;
    private String nombre;
    private String posicion;
    private int numero;
    private String nacionalidad;
    private int edad;
    private String division;
    private String rutaImagenJugador;
    private String rutaEscudoEquipo;

    public Jugador(String equipo, String nombre, String posicion, int numero,
                   String nacionalidad, int edad, String division,
                   String rutaImagenJugador, String rutaEscudoEquipo) {
        this.equipo = equipo;
        this.nombre = nombre;
        this.posicion = posicion;
        this.numero = numero;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.division = division;
        this.rutaImagenJugador = rutaImagenJugador;
        this.rutaEscudoEquipo = rutaEscudoEquipo;
    }

    // Getters / Setters
    public String getEquipo() { return equipo; }
    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }
    public int getNumero() { return numero; }
    public String getNacionalidad() { return nacionalidad; }
    public int getEdad() { return edad; }
    public String getDivision() { return division; }
    public String getRutaImagenJugador() { return rutaImagenJugador; }
    public String getRutaEscudoEquipo() { return rutaEscudoEquipo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPosicion(String pos) { this.posicion = pos; }
    public void setNumero(int n) { this.numero = n; }
    public void setNacionalidad(String nat) { this.nacionalidad = nat; }
    public void setEdad(int e) { this.edad = e; }
    public void setDivision(String d) { this.division = d; }
    public void setRutaImagenJugador(String r) { this.rutaImagenJugador = r; }
    public void setRutaEscudoEquipo(String r) { this.rutaEscudoEquipo = r; }

    // Serialización
    public String toCSV() {
        return String.join("|", equipo, nombre, posicion, Integer.toString(numero),
                nacionalidad, Integer.toString(edad), division,
                rutaImagenJugador, rutaEscudoEquipo);
    }

    public static Jugador fromCSV(String line) {
        String[] p = line.split("\\|");
        if (p.length < 9) return null;
        return new Jugador(p[0], p[1], p[2], Integer.parseInt(p[3]), p[4],
                Integer.parseInt(p[5]), p[6], p[7], p[8]);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (#%d) [%s] %s años - %s",
                equipo, nombre, numero, posicion, edad, nacionalidad);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Jugador)) return false;
        Jugador j = (Jugador) o;
        return this.equipo.equalsIgnoreCase(j.equipo) && this.numero == j.numero;
    }

    @Override
    public int hashCode() {
        return (equipo.toLowerCase() + numero).hashCode();
    }
}
