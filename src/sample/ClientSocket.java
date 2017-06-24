package sample;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import tables.Login;
import tables.Oferty;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Wojtek on 23.06.2017.
 */
public class ClientSocket {
    public static Object connectToSerwer(String a, String b, Object ob) throws IOException {
        Socket socket = new Socket("localhost",54321);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try{
            if(a == "Oferty" && b =="Admin") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }else if(a == "Uzytkownicy" && b =="Admin") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }else if(a == "Admin" && b =="Wpłata") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            } else if(a == "Oferty" && b =="Dodaj") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }else if(a == "Oferty" && b =="Edytuj") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }
            else if(a == "Kup" && b =="Oferte") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }
            else if(a == "Odejmij" && b =="Oferte") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }
            else if(a == "Login" && b =="Sprawdz") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }else if(a == "Uzytkownicy" && b =="Oferty") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }
            else if(a == "Zamowienia" && b =="Usera") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }
            else if(a == "Uzytkownik" && b =="SprLogin") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }else if(a == "Wolny" && b =="Login") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }
            else if(a == "Sprawdz" && b =="Haslo") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }
            else if(a == "Pobierz" && b =="ImieNazwisko") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
                ob = in.readObject();
            }
            else if(a == "Update" && b =="Login") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }
            else if(a == "Update" && b =="Password") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }
            else if(a == "updateName") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }
            else if(a == "updateSurname") {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }
            /*else {
                out.writeObject(a);
                out.flush();
                out.writeObject(b);
                out.flush();
                out.writeObject(ob);
                out.flush();
            }*/
        in.close();
        out.close();
        socket.close();
        }catch (UnknownHostException e) {
            System.err.println("Bład serwer jest nie osiągalny");
            System.exit(1);
        }catch (IOException e) {
            e.printStackTrace();
            System.err.println("Bład połączenia");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ob;
    }

}
