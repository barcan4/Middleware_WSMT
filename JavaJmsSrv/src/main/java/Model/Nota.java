package Model;

import java.util.concurrent.atomic.AtomicInteger;

public class Nota {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int nID;
    private int mark;
    private String descriere;
    private int sID;
    private int mID;


    public Nota(int mark, String descriere, int sID, int mID) {
        this.nID = count.incrementAndGet();
        this.mark = mark;
        this.descriere = descriere;
        this.sID = sID;
        this.mID = mID;
    }

    public int getnID() {
        return nID;
    }

    public void setnID(int nID) {
        this.nID = nID;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }
}
