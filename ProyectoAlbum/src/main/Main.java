/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gui.Login;
import control.AlbumManager;

public class Main {
    public static void main(String[] args) {
        // Cargar datos
        AlbumManager manager = AlbumManager.getInstance();
        manager.loadFromFile();

        // Login
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Login(manager).setVisible(true);
        });
    }
}
