package it.unibs.pajc;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class GraphicsApp extends JFrame {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GraphicsApp window = new GraphicsApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public GraphicsApp() {
        ModelDama dama= new ModelDama();
        frame=new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 600, 600);
        PaintArea paintArea = new PaintArea();
        frame.add(paintArea);





    }

}