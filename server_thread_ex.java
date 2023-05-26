package tcpserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class server_send extends Thread
{
    private Socket sck;
    public server_send(Socket socket) throws IOException
    {
        sck=socket;
    }
    public void run()
    {
        while(true)
        {
            try {
                DataOutputStream out = new DataOutputStream(sck.getOutputStream());
                String string;
                Scanner strg = new Scanner(System.in);
                string = strg.nextLine();
                out.writeUTF(string);
            } catch (IOException ex) {
            Logger.getLogger(server_send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class server_receive extends Thread
{
    private Socket sck;
    public server_receive(Socket socket) throws IOException
    {
        sck=socket;
    }
    public void run()
    {
        while(true) {
            try {
                DataInputStream in = new DataInputStream(sck.getInputStream());
                String string=in.readUTF();
                System.out.println("Client : "+string) ;
            } catch (IOException ex) {
                Logger.getLogger(server_receive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class server_thread_ex2
{
   public static void main(String [] args) throws IOException
   {
        int port = 5555;
        ServerSocket serverSck = new ServerSocket(port);
        try {
            System.out.println("Client ready on port " +serverSck.getLocalPort() + "...");
            Socket Server = serverSck.accept();
            System.out.println("Client connected to "+ Server.getRemoteSocketAddress());

            Thread thread1=new server_send(Server);
            thread1.start();
            
            Thread thread2=new server_receive(Server);
            thread2.start();


        } catch (IOException ex) {
            Logger.getLogger(server_thread_ex2.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
