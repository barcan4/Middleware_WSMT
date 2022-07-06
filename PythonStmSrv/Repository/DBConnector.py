import mysql.connector
from mysql.connector import Error
from Model.Materie import Materie
from Model.Nota import Nota

from Model.Student import Student

class DBConnector:
    def __init__(self):
        self.connection = None
        self.cursor = None

    def connect(self):
        try:
            self.connection = mysql.connector.connect(host='localhost', database='studenti_db', user='florin', password='florin1')
            
            if self.connection.is_connected():
                dbInfo = self.connection.get_server_info()
                print("Connected to MySql Server version ", dbInfo)
                self.cursor = self.connection.cursor()
                self.cursor.execute("select database();")
                record = self.cursor.fetchone()
                print("Connect to the database ", record)

                queryS = ("SELECT * FROM student ORDER BY sID DESC LIMIT 1")
                self.cursor.execute(queryS)
                resultS = self.cursor.fetchall()
                if len(resultS) == 1:
                    resultS = resultS[0][0]
                    Student.count = resultS

                    
                queryM = ("SELECT * FROM materie ORDER BY mID DESC LIMIT 1")
                self.cursor.execute(queryM)
                resultM = self.cursor.fetchall()
                if len(resultM) == 1:
                    resultM = resultM[0][0]
                    Materie.count = resultM

                    
                queryN = ("SELECT * FROM nota ORDER BY nID DESC LIMIT 1")
                self.cursor.execute(queryN)
                resultN = self.cursor.fetchall()
                if len(resultN) == 1:
                    resultN = resultN[0][0]
                    Nota.count = resultN

        except Error as e:
                print("Error while connecting to MySql", e)
        

    def disconnect(self):
        self.connection.close()
        self.cursor.close()
        print("Disconnected from the MySql Server")

    def addStudent(self, nume, prenume, grupa):
        try:
            dataQuery = {
                'nume': nume,
                'prenume': prenume,
                'grupa': grupa
            }
            query = ("INSERT INTO student (nume, prenume, grupa) VALUES (%(nume)s, %(prenume)s, %(grupa)s)")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
            print(self.cursor.rowcount, " record inserted")
        except Error as e:
            print("Failed to insert into table {}".format(e))

    def editStudent(self, sID, newNume, newPrenume, newGrupa):
        try:
            dataQuery = {
                'sID': sID,
                'newNume': newNume,
                'newPrenume': newPrenume,
                'newGrupa': newGrupa
            }
            query = ("UPDATE student SET nume = %(newNume)s, prenume = %(newPrenume)s, grupa = %(newGrupa)s where sID = %(sID)s")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
            print(self.cursor.rowcount, " record(s) affected")
        except Error as e:
            print("Failed to update table {}".format(e))

    def remStudent(self, sID):
        try:
            dataQuery = {
                'sID': sID
            }
            query = ("DELETE FROM student where sID = %(sID)s")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
            print(self.cursor.rowcount, " record(s) deleted")
        except Error as e:
            print("Failed to remove from table {}".format(e))

    def addMaterie(self, denumire, profesor):
        try:
            dataQuery = {
                'denumire': denumire,
                'profesor': profesor
            }
            query = ("INSERT INTO materie (denumire, profesor) VALUES (%(denumire)s, %(profesor)s)")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
            print(self.cursor.rowcount, " record inserted")
        except Error as e:
            print("Failed to insert into table {}".format(e))

    def editMaterie(self, mID, newDenumire, newProfesor):
        try:
            dataQuery = {
                'mID': mID,
                'newDenumire': newDenumire,
                'newProfesor': newProfesor
            }
            query = ("UPDATE materie SET denumire = %(newDenumire)s, profesor = %(newProfesor)s where mID = %(mID)s")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
            print(self.cursor.rowcount, " record(s) affected")
        except Error as e:
            print("Failed to update table {}".format(e))

    def remMaterie(self, mID):
        try:
            dataQuery = {
                'mID': mID
            }
            query = ("DELETE FROM materie where mID = %(mID)s")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
            print(self.cursor.rowcount, " record(s) deleted")
        except Error as e:
            print("Failed to remove from table {}".format(e))

    def addNota(self, mark, descriere, sID, mID):
        try:
            dataQuery = {
                'mark': mark,
                'descriere': descriere,
                'sID': sID,
                'mID': mID
            }
            query = ("INSERT INTO nota (mark, descriere, sID, mID) VALUES (%(mark)s, %(descriere)s, %(sID)s, %(mID)s)")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
        except Error as e:
            print("Failed to insert into table {}".format(e))
        pass

    def editNota(self, nID, newMark, newDescriere):
        try:
            dataQuery = {
                'nID': nID,
                'newMark': newMark,
                'newDescriere': newDescriere
            }
            query = ("UPDATE nota SET mark = %(newMark)s, profesor = %(newDescriere)s where nID = %(nID)s")
            self.cursor.execute(query, dataQuery)
            self.connection.commit()
            print(self.cursor.rowcount, " record(s) affected")
        except Error as e:
            print("Failed to update table {}".format(e))

    def listCatalog(self):
        try:
            query = "SELECT m.denumire, m.profesor, n.mark, n.descriere, s.nume, s.prenume, s.grupa \
                    FROM nota n INNER JOIN student s ON n.sID = s.sID \
                    INNER JOIN materie m ON n.mID = m.mID"
            self.cursor.execute(query)
            results = self.cursor.fetchall()
            for nota in results:
                print(nota)
            return results
        except Error as e:
            print("Failed to select on join {}".format(e))