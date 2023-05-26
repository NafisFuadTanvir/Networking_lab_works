
import java.io.*;
import java.net.Socket;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class client_twoway {
    public static void main(String args[]) throws IOException 
    {
        // We first create the only one socket in the client side
        // this client socket will connect with the IP of the server socket(which is localhost) and its port (which is 5556)
        Socket clientSocket = new Socket("localhost",5556);
        // now we test if the clientsocket is indeed connected with the server's handshaking or communication socket
        System.out.println("Client connected with server at server's port: "+clientSocket.getPort());
        // below we also rpint the port of the client process's socket
        System.out.println("Client side's port number is: "+clientSocket.getLocalPort());
        
        
        DataInputStream messageIn = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream messageOut = new DataOutputStream(clientSocket.getOutputStream());
        
        String outLine = "";
        String inLine = "";
        BufferedReader buffer = null;
        //while(!outLine.equals("class_shesh"))
        //{
            InputStreamReader in = new InputStreamReader(System.in);
            buffer = new BufferedReader(in);
            outLine = buffer.readLine();
            //outLine = "asdhakhd";
            messageOut.writeUTF(outLine);
            
            inLine = messageIn.readUTF();
            System.out.println("Info from server: "+inLine);
        //}
       
        messageOut.close();
        messageIn.close();
        buffer.close();
        clientSocket.close();
    }
}
