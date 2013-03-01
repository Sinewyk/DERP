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
                if (typeof handle[client.header.type] === 'object'
                && typeof handle[client.header.type][client.header.num] === 'function') {
                    if (client.isAuthenticated) {
                        handle[client.header.type][client.header.num](client, client.buffer);
                    }
                    //If the client needs to authenticate
                    else if (client.header.type === Header.REQ
                    && client.header.num === Header.WORKER_CONNEC_REQ) {
                        handle[Header.REQ][Header.WORKER_CONNEC_REQ](client, client.buffer);
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
            if (typeof handle[client.header.type] === 'object'
            && typeof handle[client.header.type][client.header.num] === 'function') {
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
                if (typeof handle[client.header.type] === 'object'
                && typeof handle[client.header.type][client.header.num] === 'function') {
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

exports.route = route;
