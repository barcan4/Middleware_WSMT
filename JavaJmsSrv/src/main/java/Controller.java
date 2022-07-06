import Repository.DBUtils;

import java.sql.SQLException;
import java.util.List;

public class Controller {

    private final DBUtils db;

    public Controller() throws SQLException, ClassNotFoundException {
        this.db = new DBUtils();
    }

    public void addStudent(String nume, String prenume, String grupa) throws SQLException {
        this.db.addStudent(nume, prenume, grupa);
    }

    public void editStudent(int sID, String newNume, String newPrenume, String newGrupa) throws SQLException {
        this.db.editStudent(sID, newNume, newPrenume, newGrupa);
    }

    public void remStudent(int sID) throws SQLException {
        this.db.remStudent(sID);
    }

    public void addMaterie(String denumire, String profesor) throws SQLException {
        this.db.addMaterie(denumire, profesor);
    }

    public void editMaterie(int mID, String newDenumire, String newProfesor) throws SQLException {
        this.db.editMaterie(mID, newDenumire, newProfesor);
    }

    public void remMaterie(int mID) throws SQLException {
        this.db.remMaterie(mID);
    }

    public void addNota(int mark, String descriere, int sID, int mID) throws SQLException {
        this.db.addNota(mark, descriere, sID, mID);
    }

    public void editNota(int nID, int newMark, String newDescriere) throws SQLException {
        this.db.editNota(nID, newMark, newDescriere);
    }

    public String listCatalog() throws SQLException {
        StringBuilder res = new StringBuilder();
        List<String> results = this.db.listCatalog();
        for (String i : results) {
            res.append(i).append("\n");
        }
        return res.toString();
    }
}
