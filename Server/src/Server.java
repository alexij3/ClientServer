import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5555;

        try{
            ServerSocket serverSocket = new ServerSocket(port);

            Socket socket = serverSocket.accept();

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String message = null;

            while(true){
                message = in.readUTF();

                System.out.println("Message from client: " + message);
                System.out.println("It has " + message.length() + " characters in it.");

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
