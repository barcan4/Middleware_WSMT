package Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Materie {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int mID;
    private String denumire;
    private String profesor;


    public Materie(String denumire, String profesor) {
        this.mID = count.incrementAndGet();
        this.denumire = denumire;
        this.profesor = profesor;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
