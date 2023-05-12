import java.io.*;
import java.net.Socket;

public class Clientside {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 5556);

        System.out.println("client remote port- " + clientSocket.getPort());

        System.out.println("client connected at port:-" + clientSocket.getLocalPort());

        DataOutputStream messageout = new DataOutputStream(clientSocket.getOutputStream());

        String outLine = "";

        BufferedReader buffer = null;

        while (!outLine.equals("the end")) {


            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buff = new BufferedReader(in);

            outLine= buff.readLine();
            messageout.writeUTF(outLine);
        }

        messageout.close();
        buffer.close();
        clientSocket.close();
    }
}