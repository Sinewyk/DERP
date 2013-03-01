var net = require("net")
, Header = require('../models/Header')
, router = require("./../router")
, handle = require("./../workerRoutes");

var client = net.connect(7776,"127.0.0.1", function() {//'connect' listener
    console.log('Connected');
    var header = new Header(Header.REQ,Header.WORKER_CONNEC_REQ);
    var data = new Buffer(5);
    data.write("logon");
    var finalBuffer = header.appendHeader(data);
    console.log(header.createHeader());
    console.log(data);
    console.log(finalBuffer);
    client.write(finalBuffer);
//    console.log("Sending FIN packet");
//    client.end();
});

client.on("error", function(err) {
    console.log(err);
});

client.on("data", function(data) {
    router.route(this,handle,data);
    console.log(data);
});

client.on("end", function() {
    console.log("socket closed");
});
