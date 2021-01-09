import it.unibs.pajc.core.BaseModel;
import it.unibs.pajc.model.ModelDama;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static BaseModel model;

    public static void main(String[] args){
        int port=1234;



        try {
            ServerSocket server= new ServerSocket(port);
            ExecutorService executor= Executors.newFixedThreadPool(2);
            model = new ModelDama();

            while(true){
                Socket client =server.accept();


            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }

    public BaseModel getModel(){
        return model;
    }
}
