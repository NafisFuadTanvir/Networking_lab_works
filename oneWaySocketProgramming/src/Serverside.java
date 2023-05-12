import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverside {

    public static void main(String[] args) throws IOException {


        ServerSocket handshake= new ServerSocket(5556);

        System.out.println("server handshaking at port:- "+handshake.getLocalPort());

         Socket serversocket = handshake.accept();

        System.out.println("server ready to connect at clients port:- "+ serversocket.getPort());

        DataInputStream messageIn= new DataInputStream(serversocket.getInputStream());

        String inLine="";

        while(!inLine.equals("the end")){

            inLine=messageIn.readUTF();
            System.out.println("message from client:- "+inLine);
        }

          messageIn.close();
        handshake.close();
        serversocket.close();

        }


    }

