package it.unibs.pajc;

import it.unibs.pajc.model.Coordinates;
import it.unibs.pajc.model.ModelDama;
import it.unibs.pajc.model.Pezzo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PaintArea extends JPanel implements MouseMotionListener, MouseListener {

    int cellSize;
    Point mousePosition = null;
    Point mouseSelection= null;
    private ArrayList<Coordinates> possiblePositions;
    boolean positionSelected=false;
    private ModelDama modelDama;
    private BufferedImage imgWhitePiece;
    private BufferedImage imgBlackPiece;




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
        try {
            imgWhitePiece=ImageIO.read(new File("resources\\piece_white.png"));
            imgBlackPiece = ImageIO.read(new File("resources\\piece_black.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
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

        initScacchiera(g2);//colora la scacchiera e inserisce tutte le pedine(rimaste)
        if(possiblePositions!=null&&!possiblePositions.isEmpty()){
            for (Coordinates c:possiblePositions) {
                coloraCella(g2,c);
            }
        }

        /*
        for (Pezzo p:modelDama.getListaPezzi()) {
            System.out.printf("[%d,%d]\n",p.posizione.x,p.posizione.y);
        }

         */


        //paintCursor(g2);
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

    private void coloraCella(Graphics2D g, Coordinates coordinates){
        if(modelDama.isPresentPezzoOnPosition(coordinates))
            g.setColor(Color.MAGENTA);
        else
            g.setColor(Color.YELLOW);
        g.setStroke(new BasicStroke(3));
        g.drawRect(coordinates.x*cellSize,coordinates.y*cellSize,cellSize,cellSize);
    }

    //cambiare con position
    private void paintPezzo(Graphics2D g, Pezzo p){
        int x,y;
        //tramuto da coordinate logiche a coordinate fisiche
        x=(cellSize/7+(p.posizione.x)*cellSize)%(cellSize*8);
        y=cellSize/7+(p.posizione.y)*cellSize;

        if(p.getFazione()== Pezzo.Fazione.Bianco)
            g.drawImage(imgWhitePiece,x,y,cellSize*5/7,cellSize*5/7,this);
        else
            g.drawImage(imgBlackPiece,x,y,cellSize*5/7,cellSize*5/7,this);
    }

    private Coordinates calcolaCellaDaPuntatore(){
        int x,y;
        x=(mousePosition.x%(cellSize*8)/cellSize);
        y=(mousePosition.y%(cellSize*8)/cellSize);

        return new Coordinates(x,y);
    }

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
        mouseSelection=e.getPoint();
        Coordinates cellaCliccata=calcolaCellaDaPuntatore();
        boolean success=false;
        if(possiblePositions!=null){
            //System.out.println("possible positions != null");
           // int i=1;
            for (Coordinates possiblePosition:possiblePositions) {
               // System.out.println(possiblePosition+"\n"+i++);
                //System.out.println(cellaCliccata);
                //System.out.println("controllo se cella cliccata è una delle celle illuminate: "+cellaCliccata.equals(possiblePosition));
                if(cellaCliccata.equals(possiblePosition)){
                   // System.out.println("Cliccato un movimento valido");

                    success=modelDama.movePezzo(possiblePositions.get(0),cellaCliccata);
                    possiblePositions=null;
                    //System.out.printf("success: %s\n",success);
                    break;
                }
            }
        }
        if(!success){
            possiblePositions=modelDama.showMosse(cellaCliccata);
            //System.out.println(possiblePositions);
        }
        //aggiungere controllo se posizione premuta è una delle posizioni possibili precedenti
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        // System.out.println("premuto");
        //muoviPezzo();
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

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