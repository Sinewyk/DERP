/**
This is the configuration file
**/
var c = {};

c.server_host = "127.0.0.1";
c.server_port = 7776;
c.log_level = "debug";
c.db = "mongodb"; // Not yet used, should be
c.base_node_dir = __dirname;

module.exports = c;
