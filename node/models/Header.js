/**
* Header object constructor
* @param type String Should be either "\0REQ" or "\0RES"
* @param num Number See protocols
* @param size Number Size of the data after the header
*
* @return Header object
*/
var Header = function(type, num, size) {
    if(typeof type === "undefined") this.type = null;
    else this.type = type;
    if(typeof num === "undefined") this.num = null;
    else this.num = num;
    if(typeof size === "undefined") this.size = 0;
    else this.size = size;
}

/**
* Parse the Buffer, and fill the Header object with the correct informations
* @param buffer Buffer object
*
* @return String
*/
Header.prototype.parseHeader = function(buffer) {
    if(buffer.length>=12) {
        this.type = buffer.toString('utf8',0,4);
        this.num = buffer.readInt32BE(4);
        //Check if little endian or big endian
        if(typeof this.num === "number" && this.num > 1000) {
            this.num = buffer.readInt32LE(4);
            this.size = buffer.readInt32LE(8);
        }
        else {
            this.size = buffer.readInt32BE(8);
        }
        if( !(
            (this.type === "\0REQ" || this.type === "\0RES") &&
            typeof this.num === "number" &&
            typeof this.size === "number")
        ) throw "Header : wrong header types";
    }
    else {
        throw "Header too small";
    }
}

/**
* Generate and returns the Buffer corresponding to the header
* @return Buffer object
*/
Header.prototype.createHeader = function() {
    var buffer = new Buffer(12);
    buffer.write(this.type);
    buffer.writeInt32BE(this.num,4);
    buffer.writeInt32BE(this.size,8);
    return buffer;
}

/**
* Append the header to the data
* @param data Buffer object
*
* @return Buffer object
*/
Header.prototype.appendHeader = function(data) {
    this.size = data.length;
    var headerBuffer = this.createHeader();
    data = Buffer.concat(Array(headerBuffer,data),headerBuffer.length+data.length);
    return data;
}

/**
* toString() function
* @return String
*/
Header.prototype.toString = function() { return "Type:"+this.type+" #"+this.num+" Size:"+this.size;}

module.exports = Header;