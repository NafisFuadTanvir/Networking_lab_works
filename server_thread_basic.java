/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsocket;

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 
  
// Server class 
public class server_thread_basic {

    public static void main(String[] args) throws IOException  
    { 
        // server_thread_basic is listening on port 5056 
        ServerSocket handshake = new ServerSocket(5058); 
          
        // running infinite loop for getting  client request 
        while (true)  
        { 
            Socket comm_socket = null; 
              
            try 
            { 
                // socket object to receive incoming client requests 
                comm_socket = handshake.accept(); 
                  
                System.out.println("A new client is connected : " + comm_socket); 
                  
                // obtaining input and out streams 
                DataInputStream dis_server = new DataInputStream(comm_socket.getInputStream()); 
                DataOutputStream dos_server = new DataOutputStream(comm_socket.getOutputStream()); 
                  
                System.out.println("Assigning new thread for this client"); 
  
                // create a new thread object 
                Thread new_tunnel = new ClientHandler(comm_socket, dis_server, dos_server); 
  
                // Invoking the start() method for starting the execution of thread
                new_tunnel.start(); 
                  
            } 
            catch (Exception e){ 
              System.out.println(e); 
            } 
        } 
    } 
}

// ClientHandler class to build up a tunnel
class ClientHandler extends Thread  
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis_tunnel; 
    final DataOutputStream dos_tunnel; 
    final Socket comm_tunnel; 
      
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.comm_tunnel = s; 
        this.dis_tunnel = dis; 
        this.dos_tunnel = dos; 
    } 
  
    @Override
    public void run()  
    { 
        String received; 
        String toreturn; 
        while (true)  
        { 
            try { 
  
                // Ask user what he wants 
                dos_tunnel.writeUTF("What do you want?[Date | Time]..\n"+ 
                            "Type Exit to terminate connection."); 
                  
                // receive the answer from client 
                received = dis_tunnel.readUTF(); 
                  
                if(received.equals("Exit")) 
                {  
                    System.out.println("Client " + this.comm_tunnel + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.comm_tunnel.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // creating Date object 
                Date date = new Date(); 
                  
                // write on output stream based on the 
                // answer from the client 
                switch (received) { 
                  
                    case "Date" : 
                        toreturn = fordate.format(date); 
                        dos_tunnel.writeUTF(toreturn); 
                        break; 
                          
                    case "Time" : 
                        toreturn = fortime.format(date); 
                        dos_tunnel.writeUTF(toreturn); 
                        break; 
                          
                    default: 
                        dos_tunnel.writeUTF("Invalid input"); 
                        break; 
                } 
            } catch (IOException e) { 
                System.out.println(e); 
            } 
        } 
          
        try
        { 
            // closing resources 
            this.dis_tunnel.close(); 
            this.dos_tunnel.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 
