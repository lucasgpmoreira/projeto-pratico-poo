package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.inter.FlatInterFont;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { public void run() {
            FlatInterFont.install();
            FlatLaf.setPreferredFontFamily(FlatInterFont.FAMILY);
            FlatDarkLaf.setup();
            UIManager.put("Button.arc", 20);
            UIManager.put("TextComponent.arc", 20);
            System.out.println("Hello World!");
            Login l = new Login();
            l.setVisible(true);
        }});
    }
}