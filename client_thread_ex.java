package tcpclient;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class client_send extends Thread
{
    private Socket sck;
    public client_send(Socket socket) throws IOException
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
                Scanner str = new Scanner(System.in);
                string = str.nextLine();
                out.writeUTF(string);
            } catch (IOException ex) {
            Logger.getLogger(client_send.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}



class client_receive extends Thread
{
    private Socket sck;
    public client_receive(Socket socket) throws IOException
    {
        sck=socket;
    }
    public void run()
    {
        while(true) {
            try {
                DataInputStream in = new DataInputStream(sck.getInputStream());
                String string=in.readUTF();
                System.out.println("Server : "+string) ;
            } catch (IOException ex) {
                Logger.getLogger(client_receive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


public class client_thread_ex2
{
   public static void main(String [] args) throws IOException
   {
      String serverName = "localhost" ;
	  int port = 5555 ;
          Socket client = new Socket(serverName, port);
	  System.out.println("Connecting to server" + serverName+ " on port " + port);
	  System.out.println("Client connected at " + client.getLocalPort());

      Thread thread1=new client_send(client);
      thread1.start();
      
      Thread thread2=new client_receive(client);
      thread2.start();

   }
}
