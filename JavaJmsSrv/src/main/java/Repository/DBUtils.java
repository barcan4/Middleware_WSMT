package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    private Connection connection;

    public DBUtils() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studenti_db",
                "florin",
                "florin1");
    }

    public void addStudent(String nume, String prenume, String grupa) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("INSERT INTO student(nume, prenume, grupa) " +
                "VALUES (" + nume + ", " + prenume + ", " + grupa + ");");
    }

    public void editStudent(int sID, String newNume, String newPrenume, String newGrupa) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("UPDATE student SET nume = " + newNume +
                ", prenume = " + newPrenume +
                ", grupa = " + newGrupa +
                " where sID = " + sID + ";");
    }

    public void remStudent(int sID) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("DELETE FROM student where sID = " + sID + ";");
    }

    public void addMaterie(String denumire, String profesor) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("INSERT INTO materie(denumire, profesor) " +
                "VALUES (" + denumire + ", " + profesor + ");");
    }

    public void editMaterie(int mID, String newDenumire, String newProfesor) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("UPDATE materie SET denumire = " + newDenumire +
                ", profesor = " + newProfesor +
                " where mID = " + mID + ";");
    }

    public void remMaterie(int mID) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("DELETE from materie where mID = " + mID + ";");
    }

    public void addNota(int mark, String descriere, int sID, int mID) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("INSERT INTO nota(mark, descriere, sID, mID) " +
                "VALUES (" + mark + ", " + descriere + ", " + sID + ", " + mID + ")");
    }

    public void editNota(int nID, int newMark, String newDescriere) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("UPDATE nota SET mark = " + newMark +
                ", descriere = " + newDescriere +
                " where nID = " + nID + ";");
    }

    public List<String> listCatalog() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT m.denumire, m.profesor, n.mark, n.descriere, s.nume, s.prenume, s.grupa" +
                        " FROM nota n INNER JOIN student s ON n.sID = s.sID" +
                        " INNER JOIN materie m ON n.mID = m.mID");
        List<String> resultArray = new ArrayList<>();
        while (resultSet.next()) {
            String result = resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getInt(3) + " " +
                    resultSet.getString(4) + " " +
                    resultSet.getString(5) + " " +
                    resultSet.getString(6) + " " +
                    resultSet.getString(7);
            resultArray.add(result);
        }

        return resultArray;
    }
}
