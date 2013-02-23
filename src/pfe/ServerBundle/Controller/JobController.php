<?php

// src/pfe/ServerBundle/Controller/JobController.php
namespace pfe\ServerBundle\Controller;

use Symfony\Component\HttpFoundation\Response;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use pfe\ServerBundle\Document\Job;

/**
* @Route("/job")
*/
class JobController extends Controller
{
	/**
     * @Route("/new")
     * @Template()
     */
	public function newAction()
	{
        /*
        $job= new Job("jobtest");
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $dm->persist($job);
        $dm->flush();
        */
        
		return array();
	}
    
    /**
    * @Route("/send")
    * @Template()
    */
    public function sendAction()
    {
        $name = null;
        $owner = null;
        $minram = null;
        $maxram = null;
        $mincpu = null;
        $fnp = null;
        $nbrun = null;
        $archi = null;
        $isUsingCG = null;
        $priority = null;
        $filepathWin  = null;
        $filepathLinux = null;
        $filepathMac = null;
        $parameters = null;
        
        if( isset($_POST['name']) )
            $name = $_POST['name'];
            
        if( isset($_POST['owner']) )
            $owner = $_POST['owner'];
            
        if( isset($_POST['minram']) )
            $minram = $_POST['minram'];
            
        if( isset($_POST['maxram']) )
            $maxram = $_POST['maxram'];
            
        if( isset($_POST['mincpu']) )
            $mincpu = $_POST['mincpu'];
            
        if( isset($_POST['fnp']) )
            $fnp = $_POST['fnp'];
            
        if( isset($_POST['nbrun']) )
            $nbrun = $_POST['nbrun'];
            
        if( isset($_POST['archi']) )
            $archi = $_POST['archi'];
            
        if( isset($_POST['isUsingCG']) )
            $nameisUsingCG = $_POST['isUsingCG'];
            
        if( isset($_POST['priority']) )
            $priority = $_POST['priority'];   
            
        $path_destination = __DIR__.'/../../../../upload/';
        $filepath = __DIR__.'/../../../../upload/';
        
        if(isset($_FILES['filenameWin']))
        {
            $path_destination = __DIR__.'/../../../../upload/win/';
            move_uploaded_file($_FILES['filenameWin']['tmp_name'], $path_destination.$_FILES['filenameWin']['name']);
            $filepathWin = $_FILES['filenameWin']['name'];
            }
        
        if(isset($_FILES['filenameLinux']))
        {
            $path_destination = __DIR__.'/../../../../upload/linux/';
            move_uploaded_file($_FILES['filenameLinux']['tmp_name'], $path_destination.$_FILES['filenameLinux']['name']);
            $filepathLinux = $_FILES['filenameLinux']['name'];
            }
        
        if(isset($_FILES['filenameMac']))
        {
            $path_destination = __DIR__.'/../../../../upload/mac/';
            move_uploaded_file($_FILES['filenameMac']['tmp_name'], $path_destination.$_FILES['filenameMac']['name']);
            $filepathMac = $_FILES['filenameMac']['name'];
        }
        
        if( isset($_POST['parameter']) )
        {
            $parameters = $_POST['parameter'];
        }
        
        $data = array(
            'name' => $name,
            'owner' => $owner,
            'minram' => $minram,
            'maxram' => $maxram,
            'mincpu' => $mincpu,
            'fnp' => $fnp,
            'nbrun' => $nbrun,
            'archi' => $archi,
            'isUsingCG' => $isUsingCG,
            'priority' => $priority,
            'filepath' => $filepath,
            'filepathWin' => $filepathWin,
            'filepathLinux' => $filepathLinux,
            'filepathMac' => $filepathMac,
            'parameters' => $parameters
        );
        
        return array( 'data' => $data);
    }
	
	/**
     * @Route("/show/{job_name}")
     * @Template()
     */
	public function showAction($job_name)
	{
        
		return array('job_name' => $job_name);
	}
	
	/**
     * @Route("/list")
     * @Template()
     */
	public function listAction()
	{
        $dm = $this->get('doctrine.odm.mongodb.document_manager');
        $repositery = $this->get('doctrine.odm.mongodb.document_manager')->getRepository('pfeServerBundle:Job');
        $jobs = $repositery->findAll();
        return array('jobs' => $jobs);
		//return new Response('voici la liste des jobs :');
		//va chercher ts les jobs
	}
    
    
    
}