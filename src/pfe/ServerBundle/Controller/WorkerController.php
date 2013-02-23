<?php

// src/pfe/ServerBundle/Controller/WorkerController.php
namespace pfe\ServerBundle\Controller;
use Symfony\Component\HttpFoundation\Response;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use pfe\ServerBundle\Document\Worker;

/**
* @Route("/worker")
*/
class WorkerController extends Controller
{
    /**
    * @Route("/new")
    * @Template()
    */
    public function newAction()
    {
        /*
        $worker = new Worker("worker_win_0001");
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $dm->persist($worker);
        $dm->flush();
        */
        
        return array();
    }
    
    /**
    * @Route("/list")
    * @Template();
    */
    public function listAction()
    {
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $repositery = $this->get('doctrine.odm.mongodb.document_manager')->getRepository('pfeServerBundle:Worker');
        $workers = $repositery->findAll();
        
        return array('workers' => $workers);
    }
    
    /**
    * @Route("/update/{id}")
    */
    public function updateAction($id)
    {
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $repositery = $this->get('doctrine.odm.mongodb.document_manager')->getRepository('pfeServerBundle:Worker');
        $worker = $repositery->find($id);
        $worker->setName("worker2v2");//TO-MODIFY
        $dm->flush();
        
        return new Response('<html><body> worker updated </body></html>');
    }
}