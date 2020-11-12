package it.unibs.pajc;

import java.util.ArrayList;
import java.util.List;

public class ModelDama {
    ArrayList<Pedina> pedine=new ArrayList<>();
    int idCount;

    ModelDama(){
        idCount=0;

        //conto come posizioni possibili solo le caselle nere
        for(int i=0;i<12;i++){
            addPedina(idCount,true,i);
        }
        for(int i=0;i<12;i++){
           addPedina(idCount,false,20+i);
        }
    }

    public void addPedina(int id,boolean isBianco, int position ){
        pedine.add(id,new Pedina(false,isBianco,position,id));
    }
}
