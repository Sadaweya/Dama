package it.unibs.pajc;

import javax.swing.*;
import java.awt.*;

public class ServerGraphics {
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerGraphics window = new ServerGraphics();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
