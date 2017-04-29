DROP TABLE OFERTY CASCADE CONSTRAINT PURGE;
DROP TABLE ZAMOWIENIA CASCADE CONSTRAINT PURGE;
DROP TABLE UZYTKOWNICY CASCADE CONSTRAINT PURGE;

CREATE TABLE OFERTY (
  ID NUMBER NOT NULL PRIMARY KEY,
  OPIS VARCHAR2(4000) NOT NULL,
  CENA NUMBER(5,2) NOT NULL,
  DATA_POCZ DATE NOT NULL,
  DATA_KONC DATE NOT NULL,
  ILOSC_MIEJSC NUMBER(1) NOT NULL
);
CREATE TABLE UZYTKOWNICY (
  ID NUMBER NOT NULL PRIMARY KEY,
  IMIE VARCHAR2(50) NOT NULL,
  NAZWISKO VARCHAR2(60) NOT NULL,
  LOGIN VARCHAR2(15) NOT NULL,
  HASLO VARCHAR2(15) NOT NULL
);
CREATE TABLE ZAMOWIENIA (
  ID NUMBER NOT NULL PRIMARY KEY,
  ID_UZYTKOWNIKA NUMBER NOT NULL,
  ID_OFERTY NUMBER NOT NULL,
  UBEZPIECZENIE VARCHAR2(3) NOT NULL,
  WPLATA VARCHAR(50) NOT NULL,
  CONSTRAINT FK_UZYTKOWNIK FOREIGN KEY (ID_UZYTKOWNIKA) REFERENCES UZYTKOWNICY(ID),
  CONSTRAINT FK_OFERTA FOREIGN KEY (ID_OFERTY) REFERENCES UZYTKOWNICY(ID)
);