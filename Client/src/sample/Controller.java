package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    boolean inputIsReady;
    int port = 5555;
    String address = "127.0.0.1";

    InputStream sin;
    OutputStream sout;
    DataInputStream in;
    DataOutputStream out;

    @FXML
    private TextField messageInput;

    @FXML
    private Button enterButton;

    @FXML
    void sendMessage(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            inputIsReady = true;
        }

    }

    void establish(){
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, port);

            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            while (true) {
                if (inputIsReady) {
                    out.writeUTF(messageInput.getText());
                    inputIsReady = false;
                }
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        establish();
    }

}
