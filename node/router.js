var Header = require('./models/Header');

/**
* Routing function, executes the action corresponding to the header while sending the data if there is
* @param client net.Socket objet
* @param handle Object
* @param buffer Buffer object
*/
function route(client, handle, buffer) {
    //console.log(buffer);
    var header = new Header();
    try {
        header.parseHeader(buffer);
        client.header = header;
        //console.log(client.header.toString());
        // HEADER , NO EXCEPTION //
        //Si data
        if(client.header.size > 0) {
            client.buffer = buffer.slice(12);
            
            if(buffer.length == client.header.size+12) {
                if (typeof handle[client.header.type] === 'object' && typeof handle[client.header.type][client.header.num] === 'function') {
                    if (client.isAuthenticated) {
                        handle[client.header.type][client.header.num](client, client.buffer);
                    }
                    //If the client needs to authenticate
                    else if (client.header.type === "\0REQ" && client.header.num === 29) {
                        handle["\0REQ"][29](client, client.buffer);
                    }
                    //If the client needs to authenticate and sent the wrong header
                    else {
                        client.end();
                    }
                }
            } else {
                client.concatOver = false;
            }
        }
        //pas de data
        else {
            if (typeof handle[client.header.type] === 'object' && typeof handle[client.header.type][client.header.num] === 'function') {
                //If the client is authenticated
                if (client.isAuthenticated) {
                    handle[client.header.type][client.header.num](client, null);
                }
                //If the client needs to authenticate and sent the wrong header
                else {
                    client.end();
                }
            }
        }
    }
    //NOT A HEADER
    catch (err) {
        //IF CONCATENATION NOT OVER
        if(client.concatOver === false) {
            client.buffer = Buffer.concat([client.buffer,buffer],client.buffer.length+buffer.length);
            //console.log("Concatenating, download% : "+((client.buffer.length/client.header.size)*100));
            if(client.buffer.length == client.header.size) {
                client.concatOver = true;
                if (typeof handle[client.header.type] === 'object' && typeof handle[client.header.type][client.header.num] === 'function') {
                    if (client.isAuthenticated) {
                        handle[client.header.type][client.header.num](client, client.buffer);
                    }
                    else {
                        client.end();
                    }
                }
            }
        } else {
            client.end();
        }
    }
}

/*function route(client, handle, buffer) {
    console.log(buffer);
    var header = new Header();
    try {
        header.parseHeader(buffer);
        console.log(header.toString());
        //If defined, execute, else it will throw an exception
        if (typeof handle[header.type] === 'object' && typeof handle[header.type][header.num] === 'function') {
            //If the client is authenticated
            if (client.isAuthenticated) {
                //execute the action corresponding to the protocol
                if (header.size > 12) {
                    handle[header.type][header.num](client, buffer.slice(12,12+header.size));
                } else {
                    handle[header.type][header.num](client, null);
                }
            }
            //If the client needs to authenticate
            else if (header.type === "\0REQ" && header.num === 29) {
                handle["\0REQ"][29](client, buffer.slice(12,12+header.size));
            }
            //If the client needs to authenticate and sent the wrong header
            else {
                client.end();
            }
        } else {
            console.log("Wrong header from client "+typeof handle[header.type][header.num]);
            client.end();
        }
    } catch (err) {
        console.log(err);
        global.errorLog.notice("Routing error : %s",err);
        client.end();
    }
}*/

exports.route = route;