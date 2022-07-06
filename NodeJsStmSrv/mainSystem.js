const Server = require("./server");

if (process.argv.length > 4) {
    var server = new Server(process.argv[2], process.argv[3], process.argv[4]);
} else {
    var server = new Server("localhost", "myRequest", "myResponse");
}