const mysql = require('mysql');

class DBConnector {
    
    constructor() {
        this.db = null;
    }

    connect() {
        this.db = mysql.createConnection({
            host: "localhost",
            user: "florin",
            password: "florin1",
            database: "studenti_db"
        });

        this.db.connect(function(err, result) {
            if (err) throw err;
            console.log("Result: " + JSON.stringify(result));
        })
    }

    disconnect() {
        this.db.end(function (err) {
            if (err) throw err;
            console.log("Disconnected from DB");
        })
    }

    addStudent(nume, prenume, grupa) {
        var query = "INSERT INTO student (nume, prenume, grupa) VALUES ?";
        var values = [[nume, prenume, grupa]];
        this.db.query(query, values, function (err, result) {
            if (err) throw err;
            console.log("Number of records inserted: " + result.affectedRows);
        });
    }

    editStudent(sID, newNume, newPrenume, newGrupa) {
        var query = "UPDATE student SET nume = ?, prenume = ?, grupa = ? where sID = ?";
        var values = [newNume, newPrenume, newGrupa, sID];
        this.db.query(query, values, function (err, result) {
            if (err) throw err;
            console.log("Number of records updated: " + result.affectedRows);
        });
    }

    remStudent(sID) {
        var query = "DELETE FROM student where sID = ?";
        var values = [sID];
        this.db.query(query, values, function(err, result) {
            if (err) throw err;
            console.log("Number of records deleted: " + result.affectedRows);
        });
    }

    addMaterie(denumire, profesor) {
        var query = "INSERT INTO materie (denumire, profesor) VALUES ?";
        var values = [[denumire, profesor]];
        this.db.query(query, values, function(err, result) {
            if (err) throw err;
            console.log("Number of records inserted: " + result.affectedRows);
        });
    }

    editMaterie(mID, newDenumire, newProfesor) {
        var query = "UPDATE materie SET denumire = ?, profesor = ? where mID = ?";
        var values = [newDenumire, newProfesor, mID];
        this.db.query(query, values, function(err, result) {
            if (err) throw err;
            console.log("Number of records updated: " + result.affectedRows);
        });
    }

    remMaterie(mID) {
        var query = "DELETE FROM materie where mID = ?";
        var values = [mID];
        this.db.query(query, values, function(err, result) {
            if (err) throw err;
            console.log("Number of records deleted: " + result.affectedRows);
        });
    }

    addNota(mark, descriere, sID, mID) {
        var query = "INSERT INTO nota (mark, descriere, sID, mID) VALUES ?";
        var values = [[mark, descriere, sID, mID]];
        this.db.query(query, values, function(err, result) {
            if (err) throw err;
            console.log("Number of records deleted: " + result.affectedRows);
        });
    }

    editNota(nID, mark, descriere) {
        var query = "UPDATE nota SET mark = ?, profesor = ? where nID = ?";
        var values = [mark, descriere, nID];
        this.db.query(query, values, function(err, result) {
            if (err) throw err;
            console.log("Number of records deleted: " + result.affectedRows);
        });
    }

    listCatalog() {
        var res = [];
        var query = "SELECT m.denumire, m.profesor, n.mark, n.descriere, s.nume, s.prenume, s.grupa \
                    FROM nota n INNER JOIN student s ON n.sID = s.sID \
                    INNER JOIN materie m ON n.mID = m.mID";
        var db = this.db;
        var promise = new Promise(function(resolve, reject) { 
            var qres;
            db.query(query, function(err, result) {
                if (err) throw err;
                qres = result;
                resolve(qres);
            });
        });
        return promise;
        // promise.then(function(result) {
        //     console.log("prelucrat listCatalog");
        //     console.log(result);
        //     res = result;
        //     console.log(result);
        //     return res;
        // });
    }
}

module.exports = DBConnector;