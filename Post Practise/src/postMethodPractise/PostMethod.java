package postMethodPractise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PostMethod {

    public static void main(String[] args) throws MalformedURLException,IOException {
         //opening my url where i can post by URL class
        URL myurl= new URL("https://jsonplaceholder.typicode.com/posts");
        //opening connection by using HttURLconnection class.

        //(HttpURLConnection) typecasting the url in  HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();

        //setting what type of request i want to made in this link
        conn.setRequestMethod("POST");

       conn.setDoOutput(true);
        OutputStream out= conn.getOutputStream();
         String postedString="my name is nafis fuad tanvir and i am learning post method";
         out.write(postedString.getBytes());

         //getting and storing the response code
         int responsecode= conn.getResponseCode();

        System.out.println("value of httpcreated is:- "+conn.HTTP_CREATED+"\n");

        if(responsecode==conn.HTTP_CREATED){

            System.out.println("response code is:- "+responsecode+"\n");
            System.out.println("respone message from server:- "+conn.getResponseMessage()+"\n");

        }

        else {
            System.out.println("go home everyone");
        }


        //for reading what i have posted
        InputStreamReader input= new InputStreamReader(conn.getInputStream());
        BufferedReader buffer= new BufferedReader(input); //buffer can read from the url now
        StringBuffer fromserver= new StringBuffer();

        String eachline= null;

        while((eachline= buffer.readLine())!=null){

            fromserver.append(eachline);
            fromserver.append(System.lineSeparator());

        }

        buffer.close();


        System.out.println("here is my posted content"+fromserver+"\n");
    }
}
