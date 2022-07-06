var Stomp = require('stomp-client');
const Controller = require('./controller');

class Server {
    constructor(host, requestQ, responseQ) {
        console.log("JavaScript Server opened at " + host + ":61613\nRequest: " + requestQ + "\nResponse: " + responseQ);
        var myServer = new Stomp(host, 61613);
        var controller = new Controller();
        myServer.connect(function(sessionId) {
            myServer.subscribe("/queue/" + requestQ, function(body, headers) {
                var msg = (body + "|0|0|0").split("|");
                var res = "";
                switch(msg[0]) {
                    case "addStudent":
                        controller.addStudent(msg[1], msg[2], msg[3]);
                        res = "creat student cu valori " + msg[1] + " " + msg[2] + " " + msg[3];
                        break;
                    case "editStudent":
                        controller.editStudent(msg[1], msg[2], msg[3], msg[4]);
                        res = "modificat student cu valorile " + msg[2] + " " + msg[3] + " " + msg[4];
                        break;
                    case "remStudent":
                        controller.addStudent(msg[1]);
                        res = "sters student cu ID-ul " + msg[1];
                        break;
                    case "addMaterie":
                        controller.addMaterie(msg[1], msg[2]);
                        res = "creat materie cu valori " + msg[1] + " " + msg[2];
                        break;
                    case "editMaterie":
                        controller.editMaterie(msg[1], msg[2], msg[3]);
                        res = "modificat materie cu valorile " + msg[2] + " " + msg[3];
                        break;
                    case "remMaterie":
                        controller.remMaterie(msg[1]);
                        res = "sters materie cu ID-ul " + msg[1];
                        break;
                    case "addNota":
                        res = "creat nota cu valorile" + msg[1] + " " + msg[2] + " " + msg[3] + " " + msg[4];
                        break;
                    case "editNota":
                        controller.editNota(msg[1], msg[2], msg[3]);
                        res = "modificat nota cu valorile " + msg[2] + " " + msg[3];
                        break;
                    case "listCatalog":
                        controller.listCatalog().then(function(result) {
                            res = JSON.stringify(result) + "\n";
                            res += "s-a listat catalogul";
                            myServer.publish("/queue/" + responseQ, body + ": " + res);
                        });
                        break;
                    case "ceva":
                        res = "s-a trimis ceva";
                        break;
                    case "exit":
                        res = "Inchis client";
                        break;
                    default:
                        res = "Erronated call";
                        break;
                }
                if (msg[0] != "listCatalog")
                    myServer.publish("/queue/" + responseQ, body + ": " + res);
            });
        });
    }
}

module.exports = Server;