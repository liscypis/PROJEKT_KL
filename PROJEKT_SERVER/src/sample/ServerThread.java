package sample;

import oracle.ConnectToDatabase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Wojtek on 18.06.2017.
 */
    public class ServerThread extends Thread {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        public ServerThread(Socket s) throws IOException {
            socket = s;
                //utworzenie strumieni
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
             //wystartowanie watka
            start();
        }
        public void run() {
            try {
                //glowna petla watka
                while (true) {

                   // System.out.println(in.readObject());
                    new Thread(() ->{
                        String sd = null;
                        try {
                            sd = (String)in.readObject();
                            ResultSet rs = ConnectToDatabase.executeSelect(sd);
                            out.writeObject(rs);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        System.out.println(sd);
                    }){{start();}}.join();
                }
            } catch (Exception e) {
            } finally {
                try {
                //zamkniecie polaczenia
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


