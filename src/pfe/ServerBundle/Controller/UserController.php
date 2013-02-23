<?php

// src/pfe/ServerBundle/Controller/UserController.php
namespace pfe\ServerBundle\Controller;
use Symfony\Component\HttpFoundation\Response;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use pfe\ServerBundle\Document\User;

/**
* @Route("/user")
*/
class UserController extends Controller
{
    /**
    * @Route("/create")
    */
    public function createAction()
    {
        $user = new User();
        //$user->setPassword("noob");
        
        /*


        $userManager->updateUser($user);
        /*
        $dm = $this->get('fos_user.user_manager');
        $dm->persist($user);
        $dm->flush();
        
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $dm->persist($user);
        $dm->flush();*/
        
        return new Response('<html><body>'.var_dump($user).'user save</body></html>');
    }
}