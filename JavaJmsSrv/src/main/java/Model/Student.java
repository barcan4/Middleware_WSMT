package Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Student {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int sID;
    private String nume;
    private String prenume;
    private String grupa;

    public Student(String nume, String prenume, String grupa) {
        this.sID = count.incrementAndGet();
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }
}
