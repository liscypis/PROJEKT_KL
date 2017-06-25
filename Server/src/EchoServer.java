

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa EchoServer zawiera metodę która tworzy wątki i sockety
 */
public class EchoServer {
    /**
     * Metoda tworzy socket i wątki
     * @param args typu String[]
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Serwer włączony na porcie 54321");
        ExecutorService executor = null;
        try {
            ServerSocket serverSocket = new ServerSocket(54321);
            executor = Executors.newFixedThreadPool(10);
            System.out.println("Oczekiwanie na klientow");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Runnable wokrer = new RequestHandler(clientSocket);
                executor.execute(wokrer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Blad podczas odczytu");
        }finally {
            if(executor != null){
                executor.shutdown();
            }
        }
    }
}

