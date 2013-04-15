# Distribution Execution Remote Protocol

Distribution Execution Remote Protocol, aka DERP.
A way to distribute work among heterogen computers.
App to ask work to be done on multiple workers that can pretty much be on any OS (currently Mac, Windows and Linux).

## Authors

D.E.R.P. [ECE](http://www.ece.fr/) PFE Team 2012-2013

  * Christophe Clavier
  * Julien Cornu
  * Serge Havas
  * Arnaud Lebreton
  * Alexis Premet

With the contribution of our mentor
  * Pierre Courbin

## Architecture

    Coming soon ...

# Installation

## Php/Apache2

If you need help ...
 * [click right here for ubuntu](http://www.lmgtfy.com/?q=install+php5+apache2+ubuntu)
 * [here for windows](http://www.lmgtfy.com/?q=install+php5+apache2+windows)
 * [and here for mac](http://www.lmgtfy.com/?q=install+php5+apache2+mac)
 
You can stop right here if you have problems following.

Important tweaks => edit your php.ini (careful, there's 2 of them, one command line, and one for apache usually) and edit this:

    file_uploads = On;
    post_max_size = 20M;
    upload_max_file_size = 20M;
    

## Mongodb and its php extension

For [mongoDB](http://www.mongodb.org/) follow instructions, there's a doc ... [Rtfm](http://www.readthefuckingmanual.com/)

For its php extension I recommend (read : do exactly that or it won't work for now) the 1.2.12.
 * [Windows](https://github.com/downloads/mongodb/mongo-php-driver/php_mongo-1.2.12.zip)
 * [Linux](http://pecl.php.net/get/mongo-1.2.12.tgz)
 * Mac ... do it yourself, no idea where you can find it, just do it.
 
## Symfony2

We'll use the composer.phar script to download everything ... this script itself will try to run git, so install git first (google is your friend, it takes 30 seconds).

    php composer.phar install
    
and we're done ...

And now, classic stuff, allow your web server to have access to the app/logs and app/cache, refer to [this](http://symfony.com/doc/current/book/installation.html#configuration-and-setup) in case of trouble.

Because of symfony's mechanics (more precisely assetic assets linking), you have to link however you want the "vendor\twitter\bootstrap\twitter\bootstrap\js" folder into "vendor\mopa\bootstrap-bundle\Mopa\Bundle\BootstrapBundle\Resources\bootstrap"

and don't forget to install assets ...
    
    php app/console assets:install web --symlink
    
the --symlink is optional ...

## Node.js

[Install](http://nodejs.org/download/) it. Pitfall is just doing

    apt-get install nodejs
    
and after looking why it doesn't work for 3 hours, that the version downloaded from official repositories isn't up to date.

I then recommend following [this](https://github.com/joyent/node/wiki/Installing-Node.js-via-package-manager#ubuntu), which will have you install some useful/needed libraries, add a repo, update your list, and download the last node executable.

Then install npm (bundled with lastest repo) and do npm install while being in #root#/node. It's going to read the package.json and install the correct libraries.

[This](http://nodejs.org/api/) is your new Bible for development.

## Kaazing Websocket Gateway HTML5 edition

Used as a bridge (gateway?) between the Websocket layer (HTTP-protocol based) and the TCP layer. Think of it as a crutch for now, I will try to remove it later.
Nonetheless, this is a configuration example : 

    <service>
      <!-- Accept WS packets on port 7720 →
      <!-- <accept>ws://pfe:7720/</accept> hosts file -->
      <!-- <accept>ws://192.168.0.254:7720/</accept> direct ip -->
      <accept>ws://localhost:7720/</accept> <!-- if used from the server -->
      <!-- Transmit them on port 7776 in TCP -->
      <connect>tcp://localhost:7776/</connect>
      <type>proxy</type> <!-- gateway type -->
      <cross-site-constraint>
      <allow-origin>*</allow-origin>
      </cross-site-constraint>
    </service>
    
## Config points ...

Be careful of your config, I recommend the nodejs server and the web server at the same spot for now (for Kaazing ease of use). Don't forget that the javascript for the upload will be executed from the browser of a client, thus, the ip should be "visible" from where the client is.
Example, having "ws://localhost:7720" as a ip is wrong. Because then the client would try to connect to a websocket on his computer.

# Status

It works with tons of glue patching everywhere ... consider this as a BETA for now. Working, but needs tons of work.

## Roadmap

  * Finalize CRUD operations for db and in-memory regardings jobs/workers/workloads
  * Remove the need of Kaazing
  * Shift from standard TCP to TSL/SSL, WS to WSS, and maybe add a password to our database (-.-').
  * When works will come with their own libs and stuff, will need to shift the focus from "one exec" to one a zip containing exec + libs.
  * No idea right now concerning timeouts of tcp sockets.
  * Find a more elegant way to handle the new jobs request, going through kaazing works but ...
  * Tons of more stuff


