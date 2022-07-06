class Materie:
    count = 0

    @classmethod
    def autoIncr(self):
        self.count += 1
        return self.count

    def __init__(self, denumire, profesor):
        self._mID = self.autoIncr()
        self._denumire = denumire
        self._profesor = profesor

    def getMID(self):
        return self._mID

    def setMID(self, mID):
        self._mID = mID

    def getDenumire(self):
        return self._denumire

    def setDenumire(self, denumire):
        self._denumire = denumire

    def getProfesor(self):
        return self._profesor

    def setProfesor(self, profesor):
        self._profesor = profesor