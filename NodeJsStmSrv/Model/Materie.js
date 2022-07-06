class Materie {
    static count = 0;

    static autoIncr() {
        this.count += 1;
        return this.count;
    }

    constructor(denumire, profesor) {
        this.mID = Materie.autoIncr();
        this.denumire = denumire;
        this.profesor = profesor;
    }
}


module.exports = Materie;