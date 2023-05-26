/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsocket;

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
  
// Client class 
public class client_thread_basic  
{ 
    public static void main(String[] args) throws IOException  
    { 
        try
        { 
            Scanner scn = new Scanner(System.in); 
             
      
            // establish the connection with server port 5056 
            Socket clientSocket = new Socket("localhost", 5058); 
      
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream()); 
      
            // the following loop performs the exchange of 
            // information between client_thread_basic and client_thread_basic handler 
            while (true)  
            { 
                String inLine = dis.readUTF();
                System.out.println(inLine); 
                String outLine = scn.nextLine(); 
                dos.writeUTF(outLine); 
                  
                // If client_thread_basic sends exit,close this connection  
                // and then break from the while loop 
                if(outLine.equals("Exit")) 
                { 
                    System.out.println("Closing this connection : " + clientSocket); 
                    clientSocket.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // printing date or time as requested by client_thread_basic 
                String received = dis.readUTF(); 
                System.out.println(received); 
            } 
              
            // closing resources 
            scn.close(); 
            dis.close(); 
            dos.close(); 
            clientSocket.close();
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    } 
} 
