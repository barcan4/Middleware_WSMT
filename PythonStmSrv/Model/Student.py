class Student:
    count = 0

    @classmethod
    def autoIncr(self):
        self.count += 1
        return self.count

    def __init__(self, nume, prenume, grupa):
        self._sID = self.autoIncr()
        self._nume = nume
        self._prenume = prenume
        self._grupa = grupa

    def getSID(self):
        return self._sID

    def setSID(self, sID):
        self._sID = sID

    def getNume(self):
        return self._nume

    def setNume(self, nume):
        self._nume = nume

    def getPrenume(self):
        return self._prenume

    def setPrenume(self, prenume):
        self._prenume = prenume

    def getGrupa(self):
        return self._grupa

    def setGrupa(self, grupa):
        self._grupa = grupa