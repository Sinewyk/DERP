var net = require("net");

/**
* Start the server
* @param route Function
* @param handle Object
*/
function start(route, handle) {
    //'connection' listener
    function onConnection(client) {
        //connection successfully established
        client.on("connect", function() {
            this.header = null;
            this.concatOver = true;
            this.buffer = null;
            
            console.log("New client",client.remoteAddress);
            global.accessLog.info('New client %s',client.remoteAddress);
            //It's the browser, in hope that kaazing gateway change the remoteAddress
            if(client.remoteAddress === "127.0.0.1") {
                console.log("It's the browser !");
                client.type = "browser";
                client.isAuthenticated = true;
            } else { //It's a worker (maybe), need to authenticate or something :o
                client.type = "worker";
                client.isAuthenticated = false;
            }
        });

        client.on("data", function(data) {
            //console.log("Routing");
            route(client,handle,data);
        });
        
        client.on("error", function(err) {
            console.log("error event fired");
            //global.errorLog.warn("Unknown client error %s",err);
            console.log(err);
            //remove worker and stuff
        });
        
        client.on("close", function(had_error) {
            if(had_error) {
                global.errorLog.notice("Crash from client");
                global.accessLog.info("Client closed : crash");
                console.log("close event fired : error");
            } else {
                console.log("close event fired : normal");
            }
        });
        
        client.on("timeout", function() {
            //do stuff timeout, packet revive or something ?
        });

        client.on("end", function() {
            console.log("Received FIN packet, closing");
        });
    }

    var server = net.createServer(onConnection);
    
    server.on("error", function(err) {
        console.log("Server error "+err);
        global.errorLog.error("Server error %s",err);
    });
    
    server.listen(global.config["server_port"],global.config["server_host"], function() { //'listening' listener
        console.log("Server listening on "+global.config["server_host"]+":"+global.config["server_port"]);
    });
}

exports.start = start;
