class Nota:
    count = 0

    @classmethod
    def autoIncr(self) -> int:
        self.count += 1
        return self.count

    def __init__(self, mark, descriere, sID, mID):
        self._nID = self.autoIncr()
        self._mark = mark
        self._descriere = descriere
        self._sID = sID
        self._mID = mID

    def getNID(self):
        return self._nID

    def setNID(self, nID):
        self._nID = nID

    def getMark(self):
        return self._mark

    def setMark(self, mark):
        self._mark = mark

    def getDescriere(self):
        return self._descriere

    def setDescriere(self, descriere):
        self._descriere = descriere

    def getSID(self):
        return self._sID

    def setSID(self, sID):
        self._sID = sID

    def getMID(self):
        return self._mID

    def setMID(self, mID):
        self._mID = mID


assert [Nota('', '', '', '').getNID() for _ in range(3)] == [1,2,3]