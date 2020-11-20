package it.unibs.pajc;

import it.unibs.pajc.model.ModelDama;
import it.unibs.pajc.model.Pezzo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PaintArea extends JPanel implements MouseMotionListener, MouseListener {

    int cellSize;
    Point mousePosition = null;
    private ModelDama modelDama;

   //usando getter e setter posso creare diversi tipi di giochi(con diverse regole), Modeldama può diventare un interfaccia
    //devo quindi rendere tutto quello contenuto qui funzione del model (che può variare) Es la scacchiera 8x8 ma potrebbe cambiare
    public ModelDama getModelDama() {
        return modelDama;
    }
    public void setModelDama(ModelDama modelDama) {
        this.modelDama = modelDama;

    }



    public PaintArea() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

    }

    protected void paintComponent(Graphics g) {
        //questo  viene spesso chiamato per fare ridipingere il component, a meno che tu stesso non voglia rifarlo
        super.paintComponent(g);
        // graphics esiste sin da java 1.0, in genere il 90% delle volte si fa questo cast
        // per lavorare con graphics2d, che è molto più recente
        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        cellSize= (w < h) ? w / 8 : h / 8;

        initScacchiera(g2);
        paintCursor(g2);
        coloraCella(g2);

    }

    private void initScacchiera(Graphics2D g){
        coloraScacchiera(g);
        for(Pezzo p:modelDama.getListaPezzi()){
            paintPezzo(g,p);
        }
    }

    private void paintCursor(Graphics2D g){
        if(mousePosition != null) {
            g.setColor(Color.red);
            g.fillOval(mousePosition.x, mousePosition.y, 30, 30);
        }
    }

    private void coloraScacchiera(Graphics2D g){
        g.setColor(Color.white);
        g.fillRect(0, 0, cellSize * 8, cellSize * 8);
        g.setColor(Color.black);
        int i=0;
        for(int x=0; x<8; x++) {
            for(int y=0; y<8; y++) {
                if((x+y) % 2 != 0) {
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private void coloraCella(Graphics2D g){
        g.setColor(Color.MAGENTA);
        if(mousePosition!=null){
            Coordinates p=calcolaCellaDaPuntatore();
            g.setStroke(new BasicStroke(3));
            try{
                if((p.x+p.y)%2!=0)
                    g.drawRect(p.x*cellSize,p.y*cellSize,cellSize,cellSize);
            }catch (NullPointerException pointerException){

            }
        }
    }

    //cambiare con position
    private void paintPezzo(Graphics2D g, Pezzo p){
        g.setColor(p.getColor());
        int x,y;
        //tramuto da coordinate logiche a coordinate fisiche
        x=(cellSize/7+(p.x)*cellSize)%(cellSize*8);
        y=cellSize/7+(p.y)*cellSize;


        g.fillOval(x,y, cellSize*5/7, cellSize*5/7);


     /*   g.setColor(Color.GREEN);
        g.drawString("" + p.x+":"+p.y , x, y);*/
      //  g.setColor(Color.CYAN);
     //   g.drawString("" +temp , x+50, y);

    }

    private Coordinates calcolaCellaDaPuntatore(){
        int x,y;
        x=(mousePosition.x%(cellSize*8)/cellSize);
        y=(mousePosition.y%(cellSize*8)/cellSize);

        return new Coordinates(x,y);
    }

    /*
    private void muoviPezzo(Graphics g){
        Coordinates temp=calcolaCellaDaPuntatore();
       // System.out.println("entro muovi pezzo");

        Pezzo p=modelDama.isPresentPezzo(temp);

        if(p!=null){
          //  System.out.println("pezzo diverso da nullo");
            if(mousePosition != null) {
                g.setColor(Color.red);
               // System.out.println("posizione mouse diversa da nulla");
                g.fillOval(mousePosition.x, mousePosition.y, cellSize*5/7, cellSize*5/7);
            }
        }
    }*/



    public void mouseDragged(MouseEvent e) {
        mousePosition = e.getPoint(); // x, y
        this.repaint();
    }

    public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint(); // x, y
        this.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePosition = e.getPoint();
        // System.out.println("premuto");
        //muoviPezzo();
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePosition = e.getPoint();
       // System.out.println("rilasciato");
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}