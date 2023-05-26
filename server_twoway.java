
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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
public class server_twoway {
    public static void main(String args[]) throws IOException 
    {
        // Firstly, we create the handshaking socket, named 'handshake'
        // Since this is a special socket, we use ServerSocket class and specify the port at which the socket is opened.
        // Make sure this is the same socket that the client-process socket needs to be opened with.
        // because before anything, handshaking is to be done of the client.
        ServerSocket handshake = new ServerSocket(5556);
        // Server process's handshaking port is correctly opened, this is checked below.
        System.out.println("Server handshaking at port: "+handshake.getLocalPort());
        
        // Now, if the client socket is accepted using the accept() method of the handshaking socket,
        // only then our second socket for server process, the communication socket named 'serverSocket', is created.
        Socket serverSocket = handshake.accept();
        // Below it is tested that the communication socket of server is connected to that client process's socket
        System.out.println("Server ready to talk with client at client's port: "+serverSocket.getPort());
        // Below it is tested the exact port of server's communication socket in the server side itself
        System.out.println("This is the server side's communication port: "+serverSocket.getLocalPort());
        // in this application, it is seen that the handshaking port and communication port of server both are same
        // because we did not give any constraint for them to become separate.
        // so infact, after handshaking, the handshaking socket itself is being converted into the communication socket.
        
        
        // Now since this is a one-way communication, server ONLY reads, nothing to write for the server.
        // For this reason, the following two statements are exactly mirror images of the clinet process.
        
        // We create a variable 'messageIn' of DataInputStream class
        // We construct it with connecting to the communicating socket of the server process, which is 'serverSocket'
        // This is done using the getInputStream() method of the comunicating socket
        // This means that, if anything gets input to this 'serverSocket', this is automatically stored in 'messageIn'
        DataInputStream messageIn = new DataInputStream(serverSocket.getInputStream());
        DataOutputStream messageOut = new DataOutputStream(serverSocket.getOutputStream());
        // Now finally, we read from the 'messageIn' varible using readUTF() method
        // ... and store all the information inside 'messageIn' to a string, which is our final string
        // This final string is now ready to be printed in the server side machine.
        String inLine = "";
        String outLine = "";
        BufferedReader buffer = null;
        //while(!inLine.equals("class_shesh"))
        //{
            inLine = messageIn.readUTF();
            System.out.println("Info from client: "+inLine);
            
            InputStreamReader in = new InputStreamReader(System.in);
            buffer = new BufferedReader(in);
            outLine = buffer.readLine();
            messageOut.writeUTF(outLine);
        //}
        
        
        // Finally, to save all the resources of the OS, we close all buffers and sockets as usual.
        buffer.close();
        messageIn.close();
        messageOut.close();
        handshake.close();
        serverSocket.close();
    }
}
