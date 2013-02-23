(function(window,undefined) {
    var Header = function(type, num) {
        this.type = type;
        this.num = num;
        this.size = 0;
    }

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
    
    Header.prototype.appendHeader = function(load) {
        var headerBuffer = new ArrayBuffer(12);
        
        var data = this.str2ab(load);
        
        var typeView = new Uint8Array(headerBuffer,0,4);
        var numView = new Int32Array(headerBuffer,4,1);
        var sizeView = new Int32Array(headerBuffer,8,1);
        
        typeView.set(new Uint8Array(this.str2ab(this.type)));
        numView[0] = this.num;
        sizeView[0] = data.byteLength;
        
        return this.appendBuffer(headerBuffer,data);
    }
    
    //ArrayBuffer to String
    Header.prototype.ab2str = function(buf) {
        return String.fromCharCode.apply(null, new Uint8Array(buf));
    }

    //String to ArrayBuffer
    Header.prototype.str2ab = function(str) {
        var buf = new ArrayBuffer(str.length); // 2 bytes for each char
        var bufView = new Uint8Array(buf);
        for (var i=0, strLen=str.length; i<strLen; i++) {
            bufView[i] = str.charCodeAt(i);
        }
        return buf;
    }
    
    Header.prototype.appendBuffer = function(buffer1, buffer2) {
        var tmp = new Uint8Array( buffer1.byteLength + buffer2.byteLength );
        tmp.set( new Uint8Array( buffer1 ), 0 );
        tmp.set( new Uint8Array( buffer2 ), buffer1.byteLength );
        return tmp.buffer;
    }

    //Header to string, debug purpose
    Header.prototype.toString = function() { return "Type:"+this.type+" #"+this.num+" Size:"+this.size;}
    
    if(typeof window.MyApp === "undefined") window.MyApp = {};
    window.MyApp.Header = Header;
})(window);