import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int port = 5555;
        String address = "127.0.0.1";

        InputStream sin;
        OutputStream sout;
        DataInputStream in;
        DataOutputStream out;

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, port);

            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            String message = null;

            BufferedReader keyboard = new BufferedReader(
                    new InputStreamReader(System.in));

            //System.out.println("Now you can enter your messages below.");

            while (true) {
                System.out.println("Enter your message: ");
                message = keyboard.readLine();

                out.writeUTF(message);
                out.flush();

                message = in.readUTF();

                System.out.println(message);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
