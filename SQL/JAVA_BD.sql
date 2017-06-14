DROP TABLE OFERTY CASCADE CONSTRAINT PURGE;
DROP TABLE ZAMOWIENIA CASCADE CONSTRAINT PURGE;
DROP TABLE UZYTKOWNICY CASCADE CONSTRAINT PURGE;
DROP SEQUENCE oferty_seq;
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

insert into oferty values(oferty_seq.nextval,'Tropical Islands',432.43,'2017-06-02','2017-06-17',5);
insert into oferty values(oferty_seq.nextval,'Legoland',130,'2017-05-04','2017-05-05',5);
insert into oferty values(oferty_seq.nextval,'Berlin - Festiwal Piwa',99,'2017-06-05','2017-06-07',4);
insert into oferty values(oferty_seq.nextval,'Ostrawa',109,'2017-04-05','2022-04-05',2);
insert into oferty values(oferty_seq.nextval,'Drezno',179,'2017-07-05','2017-07-07',3);
insert into oferty values(oferty_seq.nextval,'Bratysława',432.43,'2017-07-11','2017-08-01',2);
insert into oferty values(oferty_seq.nextval,'Budapeszt',479,'2017-08-01','2017-09-01',7);
insert into oferty values(oferty_seq.nextval,'Wiedeń, Bratysława, Brno',529,'2018-01-05','2018-01-20',6);
insert into oferty values(oferty_seq.nextval,'Austria - Weekend w Wiedniu',609,'2017-09-08','2017-09-10',4);
insert into oferty values(oferty_seq.nextval,'Paryż 6 Dni - Komfort',709,'2018-12-05','2019-01-01',2);
insert into oferty values(oferty_seq.nextval,'Tropical Islands',432.43,'2017-07-07','2017-08-01',7);
insert into oferty values(oferty_seq.nextval,'Włoski Niezbędnik',809,'2017-11-11','2017-11-22',4);
insert into oferty values(oferty_seq.nextval,'Hotel Palmera Beach',809.67,'2017-10-02','2017-10-10',1);
insert into oferty values(oferty_seq.nextval,'Hotel Castillo San Jorge - Antigua',100,'2018-07-05','2018-07-10',3);
insert into oferty values(oferty_seq.nextval,'Grecja',700.50,'2017-09-01','2017-09-11',2);
insert into oferty values(oferty_seq.nextval,'Chorwacja',400,'2017-12-12','2017-12-24',7);
insert into oferty values(oferty_seq.nextval,'City Break - Magia Sztokholmu',960,'2018-05-04','2018-05-15',3);
insert into oferty values(oferty_seq.nextval,'Bułgaria, Rumunia - Bałkańskie Czary',467,'2017-11-01','2017-11-15',3);
insert into oferty values(oferty_seq.nextval,'City Break - Ateny',947,'2017-07-02','2017-07-10',6);
insert into oferty values(oferty_seq.nextval,'Hotel Tina - Tunezja',500,'2017-07-04','2017-07-20',4);

insert into uzytkownicy values(1,'Damian','Kowalski','Magnuu','admin');
insert into uzytkownicy values(2,'Dorota','Mąka','DorotaBest','zDD7as');
insert into uzytkownicy values(3,'Marek','Mostowiak','Marecki','FHe445');
insert into uzytkownicy values(4,'Agnieszka','Kolanowska','Agnieszka_Kow','CVV74A');
insert into uzytkownicy values(5,'Dorota','Mirecka','Dorota_s','zDD7as');
insert into uzytkownicy values(6,'Agnieszka','Kotecka','Magnuu','1123_KS');
insert into uzytkownicy values(7,'Maciej','Baranowski','pliss','ASls$23');
insert into uzytkownicy values(8,'Adam','Pakulski','Domcia','aaa445GzZ');
insert into uzytkownicy values(9,'Ewelina','Lis','Emilly_girl','asloOO991');
insert into uzytkownicy values(10,'Krzysztof','Kwiatkowski','Krzysiu_arczi','avatarypw');
insert into uzytkownicy values(11,'Szymon','Lącki','Ciapek','dupek');
insert into uzytkownicy values(12,'Arkadiusz','Barczyński','Arczi96','hasloHaslo');
insert into uzytkownicy values(13,'Piotr','Składowski','PiterPa','helloKitty55');
insert into uzytkownicy values(14,'Szymon','Ryszowski','SzymonWinn','aa0001lDD');
insert into uzytkownicy values(15,'Grzegorz','Stańco','Grzesiek','bestbesty');
insert into uzytkownicy values(16,'Agnieszka','Rolewska','AgnieszkaNowa','asf55');
insert into uzytkownicy values(17,'Marcin','Romanowski','Marcinek_m','fgdhgf67');
insert into uzytkownicy values(18,'Szymon','Kokon','Szymon93','sdfdsffff');
insert into uzytkownicy values(19,'Anna','Rabczyńska','AnnaKowalska','asdww2231');
insert into uzytkownicy values(20,'Rafał','Wierczyński','Rafaello','fffghyt@1!');

insert into zamowienia values(1,1,3,'Tak','Nie');
insert into zamowienia values(2,2,2,'Tak','Nie');
insert into zamowienia values(3,3,1,'Tak','Nie');
insert into zamowienia values(4,4,4,'Tak','Nie');
insert into zamowienia values(5,5,5,'Tak','Nie');
insert into zamowienia values(6,6,10,'Tak','Nie');
insert into zamowienia values(7,10,11,'Tak','Nie');
insert into zamowienia values(8,7,6,'Tak','Nie');
insert into zamowienia values(9,11,8,'Tak','Nie');
insert into zamowienia values(10,8,7,'Tak','Nie');
insert into zamowienia values(11,9,9,'Tak','Nie');
insert into zamowienia values(12,15,12,'Tak','Nie');
insert into zamowienia values(13,13,13,'Tak','Nie');
insert into zamowienia values(14,14,14,'Tak','Nie');
insert into zamowienia values(15,12,15,'Tak','Nie');
insert into zamowienia values(16,16,16,'Tak','Nie');
insert into zamowienia values(17,17,17,'Tak','Nie');
insert into zamowienia values(18,20,19,'Tak','Nie');
insert into zamowienia values(19,19,18,'Tak','Nie');
insert into zamowienia values(20,18,20,'Tak','Nie');

