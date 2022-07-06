from Model.Materie import Materie
from Model.Nota import Nota
from Model.Student import Student
from Repository.DBConnector import DBConnector


class Controller:
    def __init__(self):
        self._dbconnector = DBConnector()
        self._dbconnector.connect()
        
    def __del__(self):
        self._dbconnector.disconnect()

    def addStudent(self, nume, prenume, grupa):
        student = Student(nume, prenume, grupa)
        self._dbconnector.addStudent(nume, prenume, grupa)
    
    def editStudent(self, sID, newNume, newPrenume, newGrupa):
        self._dbconnector.editStudent(sID, newNume, newPrenume, newGrupa)

    def remStudent(self, sID):
        self._dbconnector.remStudent(sID)

    def addMaterie(self, denumire, profesor):
        materie = Materie(denumire, profesor)
        self._dbconnector.addMaterie(denumire, profesor)

    def editMaterie(self, mID, newDenumire, newProfesor):
        self._dbconnector.editMaterie(mID, newDenumire, newProfesor)

    def remMaterie(self, mID):
        self._dbconnector.remMaterie(mID)

    def addNota(self, mark, descriere, sID, mID):
        nota = Nota(mark, descriere, sID, mID)
        self._dbconnector.addNota(mark, descriere, sID, mID)

    def editNota(self, nID, newMark, newDescriere):
        self._dbconnector.editNota(nID, newMark, newDescriere)

    def listCatalog(self):
        return self._dbconnector.listCatalog()

    def getDBConnector(self):
        return self._dbConnector
