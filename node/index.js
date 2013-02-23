/**
| \ | |         | |
|  \| | ___   __| | ___
| . ` |/ _ \ / _` |/ _ \
| |\  | (_) | (_| |  __/
|_| \_|\___/ \__,_|\___|
**/

/**
Array extension
**/
Array.prototype.remove = function() {
    var what, a = arguments, L = a.length, ax;
    while (L && this.length) {
        what = a[--L];
        while ((ax = this.indexOf(what)) !== -1) {
            this.splice(ax, 1);
        }
    }
    return this;
};

var config = require("./config")
, server = require("./server")
, router = require("./router")
, handle = require("./routes");

//Fixing global access log and error log to log from everywhere
var fs = require('fs')
, Log = require('log')
, accessLog = new Log(config["log_level"], fs.createWriteStream('access.log',{flags:'a'}))
, errorLog = new Log(config["log_level"], fs.createWriteStream('error.log',{flags:'a'}));
global.accessLog = accessLog;
global.errorLog = errorLog;

//If server launched in debug mode, console.log working, else not
if(process.argv.indexOf("-debug") == -1) {
    console.log("Debug mode : OFF");
    console.log = function() {};
} else console.log("Debug mode : ON");

console.log("Started app with config :",config);

global.config = config;

//Start the server
server.start(router.route, handle);