<?php

// src/pfe/ServerBundle/Controller/GroupController.php
namespace pfe\ServerBundle\Controller;

use Symfony\Component\HttpFoundation\Response;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use pfe\ServerBundle\Document\Group;

/**
* @Route("/group")
*/
class GroupController extends Controller
{

	/**
     * @Route("/new")
     * @Template()
     */
    public function newAction()
    {   
        /*
        $group= new Group($name);
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $dm->persist($group);
        $dm->flush();
        */
        
        return array();
    }
    
    /**
    * @Route("/list")
    * @Template()
    */
    public function listAction()
    {
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $repositery = $this->get('doctrine.odm.mongodb.document_manager')->getRepository('pfeServerBundle:Group');
        $groups = $repositery->findAll();
        
        return array('groups' => $groups);
    }
    
    /**
    * @Route("/check")
    */
    public function checkAction(Request $request)
    {
        echo("hey");
        $file = fopen('hello.txt', 'a+');
        fwrite($file, "yooooo");
        fclose($file);
        $return = array("responseCode" => 200, "heyho" => "You have to write your name!");
        $return = json_encode($return);
        return new Response($return);
    }


    /**
    * @Route("/modify/{name}")
    * @Template()
    */
    public function modifyAction($name)
    {
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $group = $this->get('doctrine.odm.mongodb.document_manager')
            ->createQueryBuilder('pfeServerBundle:Group')
            ->field('_name')->equals($name)
            ->getQuery()
            ->execute();
        return array('group' => $group);
    }


    /**
    * @Route("/save")
    * @Template()
    */
    public function saveAction()
    {
        //using post method to save in db
        if(isset( $_POST['originalGroupName'] ))
        {
            $groupName = $_POST['originalGroupName'];
            
            if($_POST['originalGroupName'] != $_POST['groupName'] && $_POST['groupName'] != null)
            {
                $groupName = $_POST['groupName'];
            }
                    
            //we check that hours posted are numeric             
            if(is_numeric($_POST['startTime']) && is_numeric($_POST['endTime']))
            {
                $this->get('doctrine.odm.mongodb.document_manager')
                    ->createQueryBuilder('pfeServerBundle:Group')
                    // Find the group
                    ->findAndUpdate()
                    ->returnNew()
                    ->field('_name')->equals($_POST['originalGroupName'])

                    // Update found group
                    ->update()
                    ->field('_name')->set($groupName)
                    ->field('_startTime')->set($_POST['startTime'])
                    ->field('_endTime')->set($_POST['endTime'])
                    ->getQuery()
                    ->execute();
            }
        }
        
        return array();
    }




}