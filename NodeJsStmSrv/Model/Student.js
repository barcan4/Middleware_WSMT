class Student {
    static count = 0;

    static autoIncr() {
        this.count += 1;
        return this.count;
    }
    
    constructor(nume, prenume, grupa) {
        this.sID = Student.autoIncr();
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
    }
}

module.exports = Student;