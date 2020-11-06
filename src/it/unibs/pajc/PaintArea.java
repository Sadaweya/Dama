package it.unibs.pajc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PaintArea extends JPanel implements MouseMotionListener {

    private int paintCount = 0;

    public PaintArea() {
        this.addMouseMotionListener(this);
    }

    public void paintPedina(Graphics g, int posizione){
        g.setColor(Color.RED);
        int x=100+posizione*100;
        int y=0;
        g.fillOval(x,y, 30, 30);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

		/*
		g.drawLine(0, 0, w, h);
		g.setColor(Color.BLUE);
		g.drawRect((w-100)/2, (h-100)/2, 100, 100);
		g.fillRect((w-50)/2, (h-50)/2, 50, 50);
		*/



        int cellSize = (w < h) ? w / 8 : h / 8;

        g.setColor(Color.white);
        g.fillRect(0, 0, cellSize * 8, cellSize * 8);
        g.setColor(Color.black);
        for(int x=0; x<8; x++) {
            for(int y=0; y<8; y++) {
                if((x+y) % 2 != 0) {
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }

        ModelDama modelDama= new ModelDama();
        for(Pedina p:modelDama.pedine){
            paintPedina(g,p.position);
        }



        // disegnare la posizine del mouse
        if(mousePosition != null) {
          //  g.setColor(Color.red);
          //  g.fillOval(mousePosition.x, mousePosition.y, 30, 30);
        }

       // g.drawString("" + paintCount++, 10, 10);
    }



    public void mouseDragged(MouseEvent e) {

    }


    Point mousePosition = null;
    public void mouseMoved(MouseEvent e) {
      //  mousePosition = e.getPoint(); // x, y
     //   this.repaint();

    }

}