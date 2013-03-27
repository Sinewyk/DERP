/**
This should have all the parts concerning the handling of the database mongodb (static for now)
**/

var MongoClient = require('mongodb').MongoClient;

function DBManager(db,host,port,dbname) {
    if(typeof db === "undefined") this.db = "mongodb";
    if(typeof host === "undefined") this.host = "localhost";
    if(typeof port === "undefined") this.port = 27017;
    if(typeof dbname === "undefined") this.dbname = "pfe";
    
    var db = null;
    var connected = false;
    this.init();
};

DBManager.prototype.init = function() {
    self = this;
    
    MongoClient.connect(self.connectionString(), function(err, db) {
        if(err) {            
            return global.errorLog.error('failed to connect to database');
        }
        else
        {
            self.db = db;
            self.connected = true;
        }

    });
}

/**
* Generate the connection string for the database
*
* @return String
*/
DBManager.prototype.connectionString = function() {
    return this.db+"://"+this.host+":"+this.port+"/"+this.dbname;
};

/**
* Save a group
*
* @param group Group object
*/
DBManager.prototype.saveGroup = function(group) {

    if(this.connected)
    {
        
        var collection = this.db.collection('Group');
        //create or update?
        collection.findOne({'_id':group._id}, function(err, doc){
            if(doc === null)
            {
                //we create it
                //insert function is more efficient that save function
                //see: http://mongodb.github.com/node-mongodb-native/api-generated/collection.html
                collection.insert(group, {w:1}, function(err, result) {});
            }else
            {
                //we update it 
                collection.save(group, {w:1}, function(err, result) {});
            }
        });
        
    }
    
    
};

/**
* Save a job
* 
* @param job Job object
*/
DBManager.prototype.saveJob = function(job) {
    
    if(this.connected)
    {    
        var collection = this.db.collection('Job');
        
        collection.findOne({'_id':job._id}, function(err, doc){
            if(doc === null)
            {
                //we create it
                collection.insert(job, {w:1}, function(err, result) {});
            }else
            {
                //we update it 
                collection.save(job, {w:1}, function(err, result) {});
            }
        });
        
    }
};

/**
* Save a worker
* @param worker Worker object
*/
DBManager.prototype.saveWorker = function(worker) {
    
    if(this.connected)
    {
        
        var collection = this.db.collection('Worker');
        
        collection.findOne({'_id':worker._id}, function(err, doc){
            if(doc === null)
            {
                //we create it
                collection.insert(worker, {w:1}, function(err, result) {});
            }else
            {
                //we update it 
                collection.save(worker, {w:1}, function(err, result) {});
            }
        });
        
    }
};

/**
* Delete a group
*
* @param group Group object
*/
DBManager.prototype.deleteGroup = function(group) {

    if(this.connected)
    {
        
        var collection = this.db.collection('Group');
        collection.findOne({'_id':group._id}, function(err, doc){
            if(doc === null)
            {
                console.log('we are trying to delete an object which doesn\'t exist in db');
            }else
            {
                console.log('object removed succesfully from db');
                collection.remove(group, {w:1}, function(err, result) {});
            }
        });
        
    }
};

/**
* Delete job
*
* @param job Job object
*/
DBManager.prototype.deleteJob = function(job) {

    if(this.connected)
    {
        
        var collection = this.db.collection('Job');
        collection.findOne({'_id':job._id}, function(err, doc){
            if(doc === null)
            {
                console.log('we are trying to delete an object which doesn\'t exist in db');
            }else
            {
                console.log('object removed succesfully from db');
                collection.remove(job, {w:1}, function(err, result) {});
            }
        });
        
    }
};

/**
* Delete a worker
*
* @param worker Worker object
*/
DBManager.prototype.deleteWorker = function(worker) {

    if(this.connected)
    {
        
        var collection = this.db.collection('Worker');
        collection.findOne({'_id':worker._id}, function(err, doc){
            if(doc === null)
            {
                console.log('we are trying to delete an object which doesn\'t exist in db');
            }else
            {
                console.log('object removed succesfully from db');
                collection.remove(worker, {w:1}, function(err, result) {});
            }
        });
        
    }
};

/**
* Get all groups
*
* @return Array
*/
DBManager.prototype.getAllGroups = function() {

    if(this.connected)
    {
        
        var collection = this.db.collection('Group');
        //get all the documents from the collection
        var cursor = collection.find();
        var list = new Array();
        cursor.each(function(err, item){
            list.push(item);
        });
        return list;
        
    }
};

/**
* Get all jobs
*
* @return Array
*/
DBManager.prototype.getAllJobs = function() {

    if(this.connected)
    {
        
        var collection = this.db.collection('Job');
        var cursor = collection.find();
        var list = new Array();
        cursor.each(function(err, item){
            list.push(item);
        });
        return list;
        
    }
};

/**
* Get all the workers
*
* @return Array
*/
DBManager.prototype.getAllWorkers = function() {

    if(this.connected)
    {
        
        var collection = this.db.collection('Worker');
        var cursor = collection.find();
        var list = new Array();
        cursor.each(function(err, item){
            list.push(item);
        });
        return list;
        
    }
};


module.exports = DBManager;