import dao.*;
import tables.Login;
import tables.Oferty;
import tables.Uzytkownicy;
import tables.Zamowienia;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.OfertyAdmin.searchOferty;
import static dao.OfertyUser.searchOfertyUs;
import static dao.OfertyUser.szukajOferty;
import static dao.UzytkownicyAdmin.searchUzytkownicy;


/**
 * Klasa RequestHandler odpowiada za komunikacje klient - serwer
 */
public class RequestHandler implements Runnable {
    private final Socket client;

    /**
     * Konstruktor Klasy RequestHandler
     * @param client typu Socket
     */
    public RequestHandler(Socket client) {
            this.client = client;
        }

    /**
     * Metoda pobiera i wysyła informacje do klienta
     */
    @Override
    public void run() {
            try{
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                System.out.println("Watek działa pod nazwa: " + Thread.currentThread().getName());
                String userInput,userInput2;
                userInput = (String) in.readObject();
                userInput2 = (String) in.readObject();
                Object userInput3 = in.readObject();
                System.out.println("Otrzymano wiadomosc od " + Thread.currentThread().getName() +" : " + userInput);
                System.out.println("Otrzymano wiadomosc od " + Thread.currentThread().getName() +" : " + userInput2);

                if(userInput.equals("Oferty") && userInput2.equals("Admin")) {
                    ArrayList<Oferty> of = searchOferty();
                    out.writeObject(of);
                    out.flush();
                    System.out.println(of);
                }else if(userInput.equals("Szukaj") && userInput2.equals("Ofert")) {
                    ArrayList<Oferty> of = szukajOferty((Oferty)userInput3);
                    out.writeObject(of);
                    out.flush();
                    System.out.println(of);
                }
                else if(userInput.equals("Uzytkownicy") && userInput2.equals("Admin")) {
                    ArrayList<Uzytkownicy> of = searchUzytkownicy();
                    out.writeObject(of);
                    out.flush();
                    System.out.println(of);
                }else if(userInput.equals("Zamowienia") && userInput2.equals("Usera")) {
                    ArrayList<Zamowienia> of = ZamowieniaUser.searchZamowienia((Integer)userInput3);
                    out.writeObject(of);
                    out.flush();
                    System.out.println(of);
                }
                else if (userInput.equals("Oferty") && userInput2.equals("Dodaj")){
                    OfertyAdmin.insertoferta((Oferty) userInput3);

                }
                else if (userInput.equals("Oferty") && userInput2.equals("Edytuj")){
                    OfertyAdmin.updateOferta((Oferty) userInput3);

                }
                else if (userInput.equals("Admin") && userInput2.equals("Wpłata")){
                    UzytkownicyAdmin.updateWplata((Uzytkownicy) userInput3);

                }
                else if (userInput.equals("Kup") && userInput2.equals("Oferte")){
                    OfertyUser.addZam((Uzytkownicy) userInput3);

                } else if (userInput.equals("Odejmij") && userInput2.equals("Oferte")){
                    OfertyUser.decreaseIloscMiejsc((Oferty) userInput3);

                }
                else if(userInput.equals("Uzytkownicy") && userInput2.equals("Oferty")) {
                    ArrayList<Oferty> of = searchOfertyUs();
                    out.writeObject(of);
                    out.flush();
                    System.out.println(of);
                }else if(userInput.equals("Uzytkownik") && userInput2.equals("SprLogin")) {
                    Login lg = OfertyUser.checkLogintoLabel((Integer) userInput3);
                    out.writeObject(lg);
                    out.flush();
                    System.out.println(lg);
                }
                else if(userInput.equals("Login") && userInput2.equals("Sprawdz")) {
                    Login lg =LoginDAO.checkLoginAndPassword((Login) userInput3);
                    out.writeObject(lg);
                    out.flush();
                    System.out.println(lg);
                }else if(userInput.equals("Wolny") && userInput2.equals("Login")) {
                    Login lg = Registration.checkLogin((String) userInput3);
                    out.writeObject(lg);
                    out.flush();
                    System.out.println(lg);
                }
                else if(userInput.equals("Pobierz") && userInput2.equals("ImieNazwisko")) {
                    Uzytkownicy uz = EditUser.getImieAndNazwisko((Uzytkownicy) userInput3);
                    out.writeObject(uz);
                    out.flush();
                    System.out.println(uz);
                }
                else if(userInput.equals("Update") && userInput2.equals("Login")) {
                    EditUser.updateLogin((Login) userInput3);
                }
                else if(userInput.equals("Update") && userInput2.equals("Password")) {
                    EditUser.updatePassword((Login) userInput3);
                }
                else if(userInput.equals("updateName")) {
                    EditUser.updateName(userInput2, (Login) userInput3);
                }
                else if(userInput.equals("updateSurname")) {
                    EditUser.updateSurname(userInput2, (Login) userInput3);
                }
                else {
                    Registration.addUser(userInput,userInput2,(Login) userInput3);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
}
