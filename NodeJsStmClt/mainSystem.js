var readlineSync = require('readline-sync');
const Client = require('./client');

async function parseCmd(localhost, myRequest, myResponse) {
    
    while (true) {
        var client = new Client(localhost);
        var stringInput = readlineSync.question('> ');
        client.connect(myRequest, myResponse, stringInput);
        if (stringInput == "exit") {
            break;
        }
    }
    const delay = ms => new Promise(resolve => setTimeout(resolve,ms));
    await delay(5000);
    client.disconnect();
}



if (process.argv.length > 4) {
    parseCmd(process.argv[2], process.argv[3], process.argv[4]);
}
else {
    parseCmd("localhost", "myRequest", "myResponse");
}