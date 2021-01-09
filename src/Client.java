import it.unibs.pajc.GraphicsApp;
import it.unibs.pajc.core.BaseModel;
import it.unibs.pajc.model.ModelDama;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    BaseModel model;
    public Client(BaseModel model){
        this.model=model;
    }

    public static void main(String[] args){
        String serverName="localhost";
        int port=1234;

        try(
                Socket server=new Socket(serverName,port);
                PrintWriter out = new PrintWriter(server.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()))
        ) {

            GraphicsApp UI=new GraphicsApp();
            UI.run();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
