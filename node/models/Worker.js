/**
This should have all the parts concerning the handling of the workers
**/
var _ = require('underscore')
, Header = require('./Header');

/**
* Worker constructor
* @param object Object
*
* @return Worker object
*/
function Worker(object) {
    this._id = null;
    this.hostname = null;
    this.state = "waiting"; //A voir si j'ai vraiment besoin <=> working
    this.groupName = null;
    this.workload = null;
    this.ipAddress = null;
    this.nbOfCore = "1";
    this.cpuFrequence = null;
    this.RAM = null;
    this.os = "win";
    this.archi = "64";
    this.working = null; //A voir si j'ai vraiment besoin <=> state
    this.socket = null; //Not in database, reference to the socket to send that fucker things
    
    // Kind of copy constructor
    if(typeof object === "object") _.extend(this,object);
};

Worker.prototype.toString = function() {
    return this.hostname+" at ip:"+this.ipAddress;
}


/**
* Asks the dbManager to save the worker
* @param dbManager DBManager object
*/
Worker.prototype.save = function(dbManager) {
    //I don't want to save the socket
    dbManager.saveWorker(_.omit(this,"socket","workload","working"));
}

/**
* Asks the worker its specs
*/
Worker.prototype.specsRequest = function() {
	var self = this;
    var header = new Header(Header.REQ,Header.WORKER_SPECS_REQ,0);
    var finalB = header.createHeader();
    console.log(finalB);
    console.log(finalB.toString());
    self.socket.write(finalB, "binary", function() {console.log("Worker specs req "+self._id);});
}

/**
* Update the specs of the worker
* @param data Buffer object
* @param callback
*/
Worker.prototype.updateSpecs = function(data, callback) {
    try {
        var specs = JSON.parse(data);
        if(typeof specs === "object") _.extend(this,specs);
        callback(null);
    } catch(err) {
        callback(err);
    }
}

Worker.prototype.OS = {
    "Windows 7" : "win",
    "Windows XP": "win",
    "Linux" : "linux",
    "Mac OS X" : "mac",
}

module.exports = Worker;
