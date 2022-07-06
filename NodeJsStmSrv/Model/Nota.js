class Nota {
    static count = 0;

    static autoIncr() {
        this.count += 1;
        return this.count;
    }

    constructor(mark, descriere, sID, mID) {
        this.nID = Nota.autoIncr();
        this.mark = mark;
        this.descriere = descriere;
        this.sID = sID;
        this.mID = mID;
    }
}

module.exports = Nota;