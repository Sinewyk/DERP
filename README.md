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
 * [here for windows]((http://www.lmgtfy.com/?q=install+php5+apache2+windows)
 * [and here for mac](http://www.lmgtfy.com/?q=install+php5+apache2+mac)
 
You can stop right here if you have problems following.

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

Coming soon ...

# Status

It works with tons of glue patching everywhere ... consider this as a BETA for now. Working, but needs tons of work.

## Roadmap

  * Finalize CRUD operations for db and in-memory regardings jobs/workers/workloads
  * Shift from standard TCP to TSL/SSL, WS to WSS, and maybe add a password to our database (-.-').
  * When works will come with their own libs and stuff, will need to shift the focus from "one exec" to one a zip containing exec + libs.
  * No idea right now concerning timeouts of tcp sockets.
  * Find a more elegant way to handle the new jobs request, going through kaazing works but ...
  * Tons of more stuff


