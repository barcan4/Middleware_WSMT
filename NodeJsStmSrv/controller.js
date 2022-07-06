const Materie = require("./Model/Materie");
const Nota = require("./Model/Nota");
const Student = require("./Model/Student");
const DBConnector = require("./Repository/DBConnector");

class Controller {

    constructor() {
        this.db = new DBConnector();
        this.db.connect();
    }

    addStudent(nume, prenume, grupa) {
        var student = new Student(nume, prenume, grupa);
        this.db.addStudent(nume, prenume, grupa);
    }

    editStudent(sID, newNume, newPrenume, newGrupa) {
        this.db.editStudent(sID, newNume, newPrenume, newGrupa);
    }

    remStudent(sID) {
        this.db.remStudent(sID);
    }

    addMaterie(denumire, profesor) {
        var materie = new Materie(denumire, profesor);
        this.addMaterie(denumire, profesor);
    }

    editMaterie(mID, newDenumire, newProfesor) {
        this.editMaterie(mID, newDenumire, newProfesor);
    }

    remMaterie(mID) {
        this.db.remMaterie(mID);
    }

    addNota(mark, descriere, sID, mID) {
        var nota = new Nota(mark, descriere, sID, mID);
        this.db.addNota(mark, descriere, sID, mID);
    }

    editNota(nID, newMark, newDescriere) {
        this.db.editNota(nID, newMark, newDescriere);
    }

    listCatalog() {
        return this.db.listCatalog();
    }

    getDBConnector() {
        return this.db;
    }
}

module.exports = Controller;