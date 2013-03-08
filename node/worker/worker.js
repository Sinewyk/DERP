var net = require("net"),
Header = require('../models/Header');

function start(route, handle) {
    var client = net.connect(global.config["server_port"],global.config["server_host"], function() {
        this.isAuthenticated = true;
        var header = new Header(Header.REQ,Header.WORKER_CONNEC_REQ);
        var data = new Buffer(5);
        data.write("logon");
        var finalBuffer = header.appendHeader(data);
        console.log(header.createHeader());
        console.log(data);
        console.log(finalBuffer);
        client.write(finalBuffer);
        console.log("Worker connected");
    });

    client.on("data", function(data) {
        console.log(data);
        console.log(data.toString());
        route(client,handle,data);
    });

    client.on("error", function(err) {
        console.log(err);
    });

    client.on("end", function() {
        console.log("end");
    });
    
    client.on("close", function() {
        console.log("closing ...");
    });
}

exports.start = start;

//var client = net.connect(7776,"127.0.0.1", function() {//'connect' listener
//    console.log('Connected');
//    
////    console.log("Sending FIN packet");
////    client.end();
//});
