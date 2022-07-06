from stompest.config import StompConfig
from stompest.sync import Stomp
from stompest.protocol import StompSpec

from controller import Controller


class ServerStm:
    def __init__(self, host, requestQ, responseQ):
        myServer = Stomp(StompConfig("tcp://" + host + ":61613"))
        myServer.connect()
        myServer.subscribe("/queue/" + requestQ, {StompSpec.ACK_HEADER: StompSpec.ACK_CLIENT_INDIVIDUAL})
        print("Server opened at " + host + ":61613\nRequest: " + requestQ + "\nResponse: " + responseQ)
        self.controller = Controller()

        while True:
            resp = ""
            frame = myServer.receiveFrame()
            myServer.ack(frame)
            msg = frame.body.decode()
            msgArray = (msg + "|0|0|0").split("|")
            if msgArray[0] == "addStudent":
                self.controller.addStudent(msgArray[1], msgArray[2], msgArray[3])
                resp = "creat student cu valori " + msgArray[1] + " " + msgArray[2] + " " + msgArray[3]
            elif msgArray[0] == "editStudent":
                self.controller.editStudent(msgArray[1], msgArray[2], msgArray[3], msgArray[4])
                resp = "modificat student cu valorile " + msgArray[2] + " " + msgArray[3] + " " + msgArray[4]
            elif msgArray[0] == "remStudent":
                self.controller.remStudent(msgArray[1])
                resp = "sters student cu ID-ul " + msgArray[1]
            elif msgArray[0] == "addMaterie":
                self.controller.addMaterie(msgArray[1], msgArray[2])
                resp = "creat materie cu valori " + msgArray[1] + " " + msgArray[2]
            elif msgArray[0] == "editMaterie":
                self.controller.editMaterie(msgArray[1], msgArray[2], msgArray[3])
                resp = "modificat materie cu valorile " + msgArray[2] + " " + msgArray[3]
            elif msgArray[0] == "remMaterie":
                self.controller.remMaterie(msgArray[1])
                resp = "sters materie cu ID-ul " + msgArray[1]
            elif msgArray[0] == "addNota":
                self.controller.addNota(msgArray[1], msgArray[2], msgArray[3], msgArray[4])
                resp = "creat nota cu valori " + msgArray[1] + " " + msgArray[2] + " " + msgArray[3] + " " + msgArray[4]
            elif msgArray[0] == "editNota":
                self.controller.editNota(msgArray[1], msgArray[2], msgArray[3])
                resp = "modificat nota cu valorile " + msgArray[2] + " " + msgArray[3]
            elif msgArray[0] == "listCatalog":
                resp = str(self.controller.listCatalog()) + "\n"
                resp += "s-a listat catalogul"
            elif msgArray[0] == "ceva":
                resp = "s-a trimis ceva"
            elif msgArray[0] == "exit":
                resp = "Inchis client"
            else:
                resp = "Eronated call"
            myServer.send("/queue/" + responseQ, (msg + ": " + resp).encode())
