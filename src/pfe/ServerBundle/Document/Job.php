<?php

// src/pfe/ServerBundle/Document/Job.php
namespace pfe\ServerBundle\Document;

use Doctrine\ODM\MongoDB\Mapping\Annotations as MongoDB;

/**
 * @MongoDB\Document
 */
class Job
{
    //TO-DELETE
    public function __construct($name)
    {
        $this->name = $name;
        $this->winPath= "pathwindow";
        $this->winLinux= "pathLinux";
        $this->winMac= "pathMac";
        $this->path = "path";
        $this->owner = "owner1";
        $this->minRam = 0.01;
        $this->maxRam= 2364;
        $this->minCpuFrequence = 15.02;
        $this->isUsingCG = false;
        $this->fileCreated= true;
        $this->fileNamePatterns= "filenamepattern";
        $this->archi= 32;
        $this->parameterList= "listedeparams";
        $this->winPath= "pathwindow";
        $this->nbRun = 800;
        $this->priority = 5;
        $this->progress = 50;
        $this->status= "status";
        $this->dir= "dir";
        $this->resultFile= "presultfile";
    }
    
    /**
    * @MongoDB\Id
    */
	private $id;
    
    /**
    * @MongoDB\String
    */
	private $path;
    
    /**
    * @MongoDB\String
    */
    private $winPath;
    
    /**
    * @MongoDB\String
    */
    private $linuxPath;
    
        /**
    * @MongoDB\String
    */
    private $macPath;
    
    /**
    * @MongoDB\String
    */
    private $name;
    
    /**
    * @MongoDB\String
    */
	private $owner;
    
    /**
    * @MongoDB\Int
    */
    private $minRam;
    
    /**
    * @MongoDB\Int
    */
    private $maxRam;
    
    /**
    * @MongoDB\Int
    */
    private $minCpuFrequence;
    
    /**
    * @MongoDB\Boolean
    */
    private $filesCreated;
    
    /**
    * @MongoDB\String
    */
    private $filesNamePatterns;
    
    /**
    * @MongoDB\Boolean
    */
    private $isUsingCG;
    
    /**
    * @MongoDB\Int
    */
    private $nbRun;
    
    /**
    * @MongoDB\Int
    */
    private $archi;
    
    /**
    * @MongoDB\String
    */
    private $parameterList;
    
    /**
    * @MongoDB\Int
    */
    private $priority;
    
    /**
    * @MongoDB\Int
    */
    private $progress;
    
    /**
    * @MongoDB\String
    */
    private $status;
    
    /**
    * @MongoDB\String
    */
    private $dir;
    
    /**
    * @MongoDB\String
    */
    private $resultFile;


    /**
     * Get id
     *
     * @return id $id
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set path
     *
     * @param string $path
     * @return \Job
     */
    public function setPath($path)
    {
        $this->path = $path;
        return $this;
    }

    /**
     * Get path
     *
     * @return string $path
     */
    public function getPath()
    {
        return $this->path;
    }

    /**
     * Set winPath
     *
     * @param string $winPath
     * @return \Job
     */
    public function setWinPath($winPath)
    {
        $this->winPath = $winPath;
        return $this;
    }

    /**
     * Get winPath
     *
     * @return string $winPath
     */
    public function getWinPath()
    {
        return $this->winPath;
    }

    /**
     * Set linuxPath
     *
     * @param string $linuxPath
     * @return \Job
     */
    public function setLinuxPath($linuxPath)
    {
        $this->linuxPath = $linuxPath;
        return $this;
    }

    /**
     * Get linuxPath
     *
     * @return string $linuxPath
     */
    public function getLinuxPath()
    {
        return $this->linuxPath;
    }

    /**
     * Set macPath
     *
     * @param string $macPath
     * @return \Job
     */
    public function setMacPath($macPath)
    {
        $this->macPath = $macPath;
        return $this;
    }

    /**
     * Get macPath
     *
     * @return string $macPath
     */
    public function getMacPath()
    {
        return $this->macPath;
    }

    /**
     * Set name
     *
     * @param string $name
     * @return \Job
     */
    public function setName($name)
    {
        $this->name = $name;
        return $this;
    }

    /**
     * Get name
     *
     * @return string $name
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set owner
     *
     * @param string $owner
     * @return \Job
     */
    public function setOwner($owner)
    {
        $this->owner = $owner;
        return $this;
    }

    /**
     * Get owner
     *
     * @return string $owner
     */
    public function getOwner()
    {
        return $this->owner;
    }

    /**
     * Set minRam
     *
     * @param string $minRam
     * @return \Job
     */
    public function setMinRam($minRam)
    {
        $this->minRam = $minRam;
        return $this;
    }

    /**
     * Get minRam
     *
     * @return string $minRam
     */
    public function getMinRam()
    {
        return $this->minRam;
    }

    /**
     * Set minCpuFrequence
     *
     * @param string $minCpuFrequence
     * @return \Job
     */
    public function setMinCpuFrequence($minCpuFrequence)
    {
        $this->minCpuFrequence = $minCpuFrequence;
        return $this;
    }

    /**
     * Get minCpuFrequence
     *
     * @return string $minCpuFrequence
     */
    public function getMinCpuFrequence()
    {
        return $this->minCpuFrequence;
    }

    /**
     * Set filesCreated
     *
     * @param string $filesCreated
     * @return \Job
     */
    public function setFilesCreated($filesCreated)
    {
        $this->filesCreated = $filesCreated;
        return $this;
    }

    /**
     * Get filesCreated
     *
     * @return string $filesCreated
     */
    public function getFilesCreated()
    {
        return $this->filesCreated;
    }

    /**
     * Set filesNamePatterns
     *
     * @param string $filesNamePatterns
     * @return \Job
     */
    public function setFilesNamePatterns($filesNamePatterns)
    {
        $this->filesNamePatterns = $filesNamePatterns;
        return $this;
    }

    /**
     * Get filesNamePatterns
     *
     * @return string $filesNamePatterns
     */
    public function getFilesNamePatterns()
    {
        return $this->filesNamePatterns;
    }

    /**
     * Set isUsingCG
     *
     * @param boolean $isUsingCG
     * @return \Job
     */
    public function setIsUsingCG($isUsingCG)
    {
        $this->isUsingCG = $isUsingCG;
        return $this;
    }

    /**
     * Get isUsingCG
     *
     * @return boolean $isUsingCG
     */
    public function getIsUsingCG()
    {
        return $this->isUsingCG;
    }

    /**
     * Set nbRun
     *
     * @param int $nbRun
     * @return \Job
     */
    public function setNbRun($nbRun)
    {
        $this->nbRun = $nbRun;
        return $this;
    }

    /**
     * Get nbRun
     *
     * @return int $nbRun
     */
    public function getNbRun()
    {
        return $this->nbRun;
    }

    /**
     * Set archi
     *
     * @param string $archi
     * @return \Job
     */
    public function setArchi($archi)
    {
        $this->archi = $archi;
        return $this;
    }

    /**
     * Get archi
     *
     * @return string $archi
     */
    public function getArchi()
    {
        return $this->archi;
    }

    /**
     * Set parameterList
     *
     * @param string $parameterList
     * @return \Job
     */
    public function setParameterList($parameterList)
    {
        $this->parameterList = $parameterList;
        return $this;
    }

    /**
     * Get parameterList
     *
     * @return string $parameterList
     */
    public function getParameterList()
    {
        return $this->parameterList;
    }

    /**
     * Set priority
     *
     * @param int $priority
     * @return \Job
     */
    public function setPriority($priority)
    {
        $this->priority = $priority;
        return $this;
    }

    /**
     * Get priority
     *
     * @return int $priority
     */
    public function getPriority()
    {
        return $this->priority;
    }

    /**
     * Set progress
     *
     * @param int $progress
     * @return \Job
     */
    public function setProgress($progress)
    {
        $this->progress = $progress;
        return $this;
    }

    /**
     * Get progress
     *
     * @return int $progress
     */
    public function getProgress()
    {
        return $this->progress;
    }

    /**
     * Set dir
     *
     * @param string $dir
     * @return \Job
     */
    public function setDir($dir)
    {
        $this->dir = $dir;
        return $this;
    }

    /**
     * Get dir
     *
     * @return string $dir
     */
    public function getDir()
    {
        return $this->dir;
    }

    /**
     * Set status
     *
     * @param string $status
     * @return \Job
     */
    public function setStatus($status)
    {
        $this->status = $status;
        return $this;
    }

    /**
     * Get status
     *
     * @return string $status
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * Set maxRam
     *
     * @param int $maxRam
     * @return \Job
     */
    public function setMaxRam($maxRam)
    {
        $this->maxRam = $maxRam;
        return $this;
    }

    /**
     * Get maxRam
     *
     * @return int $maxRam
     */
    public function getMaxRam()
    {
        return $this->maxRam;
    }

    /**
     * Set resultFile
     *
     * @param string $resultFile
     * @return \Job
     */
    public function setResultFile($resultFile)
    {
        $this->resultFile = $resultFile;
        return $this;
    }

    /**
     * Get resultFile
     *
     * @return string $resultFile
     */
    public function getResultFile()
    {
        return $this->resultFile;
    }
}
