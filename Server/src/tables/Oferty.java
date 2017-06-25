package tables;

import javafx.beans.property.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * Klasa Oferty przechowuje dane z tab Oferty
 */
public class Oferty implements Externalizable{
    private IntegerProperty id_oferty;
    private StringProperty opis;
    private DoubleProperty cena;
    private SimpleObjectProperty<Date> data_pocz;
    private SimpleObjectProperty<Date> data_konc;
    private IntegerProperty ilosc_miejsc;

    public Oferty() {
        this.id_oferty = new SimpleIntegerProperty();
        this.opis = new SimpleStringProperty();
        this.cena = new SimpleDoubleProperty();
        this.data_pocz = new SimpleObjectProperty<>();
        this.data_konc = new SimpleObjectProperty<>();
        this.ilosc_miejsc = new SimpleIntegerProperty();
    }

    public double getCena() {
        return cena.get();
    }

    public DoubleProperty cenaProperty() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena.set(cena);
    }

    public int getId_oferty() { return id_oferty.get();}

    public IntegerProperty id_ofertyProperty() {
        return id_oferty;
    }

    public void setId_oferty(int id_oferty) {
        this.id_oferty.set(id_oferty);
    }

    public String getOpis() {
        return opis.get();
    }

    public StringProperty opisProperty() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis.set(opis);
    }

    public Date getData_pocz() {
        return data_pocz.get();
    }

    public SimpleObjectProperty<Date> data_poczProperty() {
        return data_pocz;
    }

    public void setData_pocz(Date data_pocz) {
        this.data_pocz.set(data_pocz);
    }

    public Date getData_konc() {
        return data_konc.get();
    }

    public SimpleObjectProperty<Date> data_koncProperty() {
        return data_konc;
    }

    public void setData_konc(Date data_konc) {
        this.data_konc.set(data_konc);
    }

    public int getIlosc_miejsc() {
        return ilosc_miejsc.get();
    }

    public IntegerProperty ilosc_miejscProperty() {
        return ilosc_miejsc;
    }

    public void setIlosc_miejsc(int ilosc_miejsc) {
        this.ilosc_miejsc.set(ilosc_miejsc);
    }

    /**
     * Metoda zapisuje obiekt do strumienia
     * @param out typu ObjectOutput
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getId_oferty());
        out.writeObject(getOpis());
        out.writeObject(getData_pocz());
        out.writeObject(getData_konc());
        out.writeInt(getIlosc_miejsc());
        out.writeDouble(getCena());
    }

    /**
     * Metoda odczytuje obiekt ze strumienia
     * @param in typu ObjectInput
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId_oferty(in.readInt());
        setOpis((String)in.readObject());
        setData_pocz((Date) in.readObject());
        setData_konc((Date)in.readObject());
        setIlosc_miejsc(in.readInt());
        setCena(in.readDouble());
    }
}
