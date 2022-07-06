from stompest.config import StompConfig
from stompest.sync import Stomp
from stompest.protocol import StompSpec

class ClientStm:
    def __init__(self, host, requestQ, responseQ):
        print("Client sent to " + host + ":61613\nRequest: " + requestQ + "\nResponse: " + responseQ)
        myClient = Stomp(StompConfig("tcp://" + host + ":61613"))
        myClient.connect()
        myClient.subscribe("/queue/" + responseQ, {StompSpec.ACK_HEADER: StompSpec.ACK_CLIENT_INDIVIDUAL})
        while True:
            stringTrimis = ""
            stringTrimis = str(input("> "))
            myClient.send("/queue/" + requestQ, stringTrimis.encode())
            frame = myClient.receiveFrame()
            print(frame.body.decode())
            myClient.ack(frame)
            if stringTrimis == "exit":
                myClient.disconnect()
                break
