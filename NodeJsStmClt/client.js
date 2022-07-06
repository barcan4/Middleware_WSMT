var Stomp = require('stomp-client')

class Client {
    constructor(host) {
        this.myClient = new Stomp(host, 61613);
        console.log("\nClient NodeJs sent to " + host + ":61613");
    }

    connect(requestQ, responseQ, stringInput) {
        console.log("Request: " + requestQ + "\nResponse: " + responseQ);
        var client = this.myClient;
        client.connect(function(sessionId) {
            client.subscribe("/queue/" + responseQ, function(body, headers) {
                console.log(body);
                // console.log(headers);
                // myClient.ack(headers['message-id'], subId);
            });
            
            client.publish("/queue/" + requestQ, stringInput);
        });
    }

    disconnect() {
        this.myClient.disconnect();
    }
}

module.exports = Client