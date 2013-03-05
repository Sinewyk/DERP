var net = require("net");

function start(route, handle) {
    var client = net.connect(global.config["server_port"],global.config["server_host"], function() {
        console.log("Worker connected");
    });

    client.on("data", function(data) {
        console.log(data);
        route(client,handle,data);
    });

    client.on("error", function(err) {
        console.log(err);
    });

    console.log("end", function() {
        
    });
}

exports.start = start;

//var client = net.connect(7776,"127.0.0.1", function() {//'connect' listener
//    console.log('Connected');
//    var header = new Header(Header.REQ,Header.WORKER_CONNEC_REQ);
//    var data = new Buffer(5);
//    data.write("logon");
//    var finalBuffer = header.appendHeader(data);
//    console.log(header.createHeader());
//    console.log(data);
//    console.log(finalBuffer);
//    client.write(finalBuffer);
////    console.log("Sending FIN packet");
////    client.end();
//});
