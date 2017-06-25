DROP TABLE OFERTY CASCADE CONSTRAINT PURGE;
DROP TABLE ZAMOWIENIA CASCADE CONSTRAINT PURGE;
DROP TABLE UZYTKOWNICY CASCADE CONSTRAINT PURGE;
DROP SEQUENCE oferty_seq;
DROP SEQUENCE zamowienia_seq;
DROP SEQUENCE uzytkownicy_seq;
CREATE TABLE OFERTY (
  ID_OFERTY NUMBER NOT NULL PRIMARY KEY,
  OPIS VARCHAR2(4000) NOT NULL,
  CENA NUMBER(5,2) NOT NULL,
  DATA_POCZ DATE NOT NULL,
  DATA_KONC DATE NOT NULL,
  ILOSC_MIEJSC NUMBER(1) NOT NULL
);
CREATE TABLE UZYTKOWNICY (
  ID_UZYTKOWNIKA NUMBER NOT NULL PRIMARY KEY,
  IMIE VARCHAR2(50) NOT NULL,
  NAZWISKO VARCHAR2(60) NOT NULL,
  LOGIN VARCHAR2(15) NOT NULL,
  HASLO VARCHAR2(15) NOT NULL
);
CREATE TABLE ZAMOWIENIA (
  ID_ZAMOWIENIA NUMBER NOT NULL PRIMARY KEY,
  ID_UZYTKOWNIKA NUMBER NOT NULL,
  ID_OFERTY NUMBER NOT NULL,
  UBEZPIECZENIE VARCHAR2(3) NOT NULL,
  WPLATA VARCHAR2(50) NOT NULL,
  CONSTRAINT FK_UZYTKOWNIK FOREIGN KEY (ID_UZYTKOWNIKA) REFERENCES UZYTKOWNICY(ID_UZYTKOWNIKA),
  CONSTRAINT FK_OFERTA FOREIGN KEY (ID_OFERTY) REFERENCES OFERTY(ID_OFERTY)
);

CREATE SEQUENCE oferty_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 200;

CREATE SEQUENCE zamowienia_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 200;

 CREATE SEQUENCE uzytkownicy_seq
  START WITH 1
  INCREMENT BY 1
  CACHE 200;

insert into oferty values(oferty_seq.nextval,'Tropical Islands [Warszawa - Berlin]',432.43,'2017-06-02','2017-06-17',0);
insert into oferty values(oferty_seq.nextval,'Legoland [Krak�w - Billund]',130,'2017-05-04','2017-05-05',5);
insert into oferty values(oferty_seq.nextval,'Festiwal Piwa [Warszawa - Hamburg]',99,'2017-06-05','2017-06-07',4);
insert into oferty values(oferty_seq.nextval,'Ostrawa [��d� - Czechy]',109,'2017-04-05','2022-04-05',2);
insert into oferty values(oferty_seq.nextval,'[Kielce - Drezno]',179,'2017-07-05','2017-07-07',3);
insert into oferty values(oferty_seq.nextval,'[Warszawa - Bratys�awa]',432.43,'2017-07-11','2017-08-01',2);
insert into oferty values(oferty_seq.nextval,'[Krak�w - Budapeszt]',479,'2017-08-01','2017-09-01',7);
insert into oferty values(oferty_seq.nextval,'[Wiede� - Bratys�awa - Brno]',529,'2018-01-05','2018-01-20',6);
insert into oferty values(oferty_seq.nextval,'Austria - Weekend w Wiedniu',609,'2017-09-08','2017-09-10',4);
insert into oferty values(oferty_seq.nextval,'Pary� 6 Dni - Komfort [Gda�sk - Pary�]',709,'2018-12-05','2019-01-01',2);
insert into oferty values(oferty_seq.nextval,'[��d� - Sztokholm]',432.43,'2017-07-07','2017-08-01',7);
insert into oferty values(oferty_seq.nextval,'W�oski Niezb�dnik [Warszawa - Rzym - Mediolan - Neapol]',809,'2017-11-11','2017-11-22',4);
insert into oferty values(oferty_seq.nextval,'Hotel Paerma Beach[Warszawa - Sycylia]',809.67,'2017-10-02','2017-10-10',1);
insert into oferty values(oferty_seq.nextval,'Hotel Castillo San Jorge [Warszawa -Las Palmas]',100,'2018-07-05','2018-07-10',3);
insert into oferty values(oferty_seq.nextval,'[Krak�w - Ateny]',700.50,'2017-09-01','2017-09-11',2);
insert into oferty values(oferty_seq.nextval,'[Wroc�aw - Zagrzeb]',400,'2017-12-12','2017-12-24',7);
insert into oferty values(oferty_seq.nextval,'City Break - Magia Sztokholmu [Wroc�aw - Sztokholm]',960,'2018-05-04','2018-05-15',3);
insert into oferty values(oferty_seq.nextval,'Ba�ka�skie Czary [Bu�garia - Rumunia]',467,'2017-11-01','2017-11-15',3);
insert into oferty values(oferty_seq.nextval,'City Break [Pozna� - Ateny]',947,'2017-07-02','2017-07-10',6);
insert into oferty values(oferty_seq.nextval,'Hotel Tina [Katowice- Tunezja]',500,'2017-07-04','2017-07-20',4);

insert into uzytkownicy values(uzytkownicy_seq.nextval,'Damian','Kowalski','admin','admin');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Dorota','M�ka','DorotaBest','zDD7as');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Marek','Mostowiak','Marecki','FHe445');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Agnieszka','Kolanowska','Agnieszka_Kow','CVV74A');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Dorota','Mirecka','Dorota_s','zDD7as');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Agnieszka','Kotecka','Magnuu','1123_KS');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Maciej','Baranowski','pliss','ASls$23');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Adam','Pakulski','Domcia','aaa445GzZ');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Ewelina','Lis','Emilly_girl','asloOO991');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Krzysztof','Kwiatkowski','Krzysiu_arczi','avatarypw');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Szymon','L�cki','Ciapek','dupek');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Arkadiusz','Barczy�ski','Arczi96','hasloHaslo');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Piotr','Sk�adowski','PiterPa','helloKitty55');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Szymon','Ryszowski','SzymonWinn','aa0001lDD');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Grzegorz','Sta�co','Grzesiek','bestbesty');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Agnieszka','Rolewska','AgnieszkaNowa','asf55');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Marcin','Romanowski','Marcinek_m','fgdhgf67');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Szymon','Kokon','Szymon93','sdfdsffff');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Anna','Rabczy�ska','AnnaKowalska','asdww2231');
insert into uzytkownicy values(uzytkownicy_seq.nextval,'Rafa�','Wierczy�ski','Rafaello','fffghyt@1!');

insert into zamowienia values(zamowienia_seq.nextval,1,3,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,2,2,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,3,1,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,4,4,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,5,5,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,6,10,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,10,11,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,7,6,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,11,8,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,8,7,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,9,9,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,15,12,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,13,13,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,14,14,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,12,15,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,16,16,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,17,17,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,20,19,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,19,18,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,18,20,'Tak','Nie');
insert into zamowienia values(zamowienia_seq.nextval,18,20,'Tak','Nie');


SELECT * FROM oferty;