package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Wojtek on 18.06.2017.
 */
public class Server {
    public static void startSerwer() throws IOException {
        //utworzenie serwera
        ServerSocket s = new ServerSocket(54321);

        try {
            while (true) {
                //oczekiwanie na nadejscie polaczenia
                Socket socket = s.accept();
                try {
                    //utworzenie watka serwera
                    new ServerThread(socket);
                } catch (IOException e) {
                    //zamkniecie polaczenia w przypadku wystapienia bledu
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }

}
