package it.unibs.pajc;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PaintArea extends JPanel implements MouseMotionListener {

    private int paintCount = 0;
    int cellSize;
    Point mousePosition = null;
    Graphics2D g2;

    public PaintArea() {
        this.addMouseMotionListener(this);
    }

    protected void paintComponent(Graphics g) {
        //questo  viene spesso chiamato per fare ridipingere il component, a meno che tu stesso non voglia rifarlo
        super.paintComponent(g);
        // graphics esiste sin da java 1.0, in genere il 90% delle volte si fa questo cast
        // per lavorare con graphics2d, che è molto più recente
        g2=(Graphics2D)g;

        int w = getWidth();
        int h = getHeight();
        cellSize= (w < h) ? w / 8 : h / 8;

        /*
		g.drawLine(0, 0, w, h);
		g.setColor(Color.BLUE);
		g.drawRect((w-100)/2, (h-100)/2, 100, 100);
		g.fillRect((w-50)/2, (h-50)/2, 50, 50);
		*/

        coloraScacchiera(g);
       // paintPedina(g,0);

        ModelDama modelDama= new ModelDama();
        for(Pedina p:modelDama.pedine){
            paintPedina(g,p);
        }
        coloraCella(g);
        // disegnare la posizine del mouse
        /*
        if(mousePosition != null) {
            g.setColor(Color.red);
            g.fillOval(mousePosition.x, mousePosition.y, 30, 30);
        }*/
     //   g.drawString("" + cellSize, 10, 10);

       // g.drawString("" + paintCount++, 10, 10);
    }


    private void coloraScacchiera(Graphics g){
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
    }

    private void coloraCella(Graphics g){
        g.setColor(Color.MAGENTA);
        if(mousePosition!=null){
            Position p=calcolaCella();
            g2.setStroke(new BasicStroke(3));
            g.drawRect(p.x,p.y,cellSize,cellSize);
        }
    }

    //cambiare con position
    private void paintPedina(Graphics g, Pedina p){
        g.setColor(p.getColor());
        int x,y;
        x=(cellSize/8+((p.position*2+1))*cellSize)%(cellSize*8);
        int temp=p.position/4;
        if(temp%2!=0)
            x-=cellSize;
        y=cellSize/8+(p.position/4)*cellSize;

        g.fillOval(x,y, cellSize*5/7, cellSize*5/7);
      //  g.setColor(Color.GREEN);
     //   g.drawString("" + x+":"+y , x, y);
      //  g.setColor(Color.CYAN);
     //   g.drawString("" +temp , x+50, y);

    }

    private Position calcolaCella(){
        int x,y;
        x=(mousePosition.x%(cellSize*8)/cellSize)*cellSize;
        y=(mousePosition.y%(cellSize*8)/cellSize)*cellSize;
        return new Position(x,y);
    }

    public void mouseDragged(MouseEvent e) {
        mousePosition = e.getPoint(); // x, y
        this.repaint();
    }

    public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint(); // x, y
        this.repaint();
    }

    private class Position{
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}