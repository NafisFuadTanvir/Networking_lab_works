package get_practice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Get {

         
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL amarUrl = new URL("https://www.google.com/");                       // this is my first step
        
        HttpURLConnection amarConn = (HttpURLConnection) amarUrl.openConnection(); //  this is my second step
        
        amarConn.setRequestMethod("GET");                                       // this is the third step
        
        int response = amarConn.getResponseCode();
        if(response == amarConn.HTTP_OK)
        {
            System.out.println("The response message and code from website is: "+amarConn.getResponseMessage()+" "+amarConn.getResponseCode());
            
            InputStreamReader in = new InputStreamReader(amarConn.getInputStream());
            BufferedReader buff = new BufferedReader(in);
            
            StringBuffer jaPrintKorbo = new StringBuffer();
            String everyLine = null;
            
            while((everyLine=buff.readLine()) != null)
            {
                jaPrintKorbo.append(everyLine);
                jaPrintKorbo.append(System.lineSeparator());
            }
            buff.close();
            
            System.out.print("Now we get our website:\n"+jaPrintKorbo);
        }
    }
    
}
