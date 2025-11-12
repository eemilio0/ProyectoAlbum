/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import control.AlbumManager;
import javax.swing.*;

public class Login extends JFrame {
    private AlbumManager manager;

    public Login(AlbumManager manager) {
        super("Álbum");
        this.manager = manager;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        init();
    }

    private void init() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lUser = new JLabel("Usuario:");
        JTextField tfUser = new JTextField();
        JLabel lPass = new JLabel("Contraseña:");
        JPasswordField pf = new JPasswordField();
        JButton btn = new JButton("Entrar");

        p.add(lUser); p.add(tfUser);
        p.add(Box.createVerticalStrut(8));
        p.add(lPass); p.add(pf);
        p.add(Box.createVerticalStrut(10));
        p.add(btn);

        btn.addActionListener(e -> {
            String pass = new String(pf.getPassword()).trim();
            boolean esAdmin = pass.equals("admin");
            SwingUtilities.invokeLater(() -> new VentanaPrincipal(manager, esAdmin).setVisible(true));
            dispose();
        });

        add(p);
    }
}
