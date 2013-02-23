<?php

// src/pfe/ServerBundle/Document/Worker.php
namespace pfe\ServerBundle\Document;

use Doctrine\ODM\MongoDB\Mapping\Annotations as MongoDB;

/**
 * @MongoDB\Document
 */
class Worker
{
    //TO-DELETE
    public function __construct($name)
    {
        $this->hostname = $name;
        $this->state = "waiting";
        $this->groupName = "p305";
        $this->ipAddress = "127.0.0.1";
        $this->nbOfCore = "2";
        $this->cpuFrequence = "800";
        $this->RAM = "512";
        $this->os = "win";
        $this->archi = "86";
    }
    
    /**
    * @MongoDB\Id
    */
	private $id;
    
    /**
    * @MongoDB\String
    */
	private $hostname;
    
    /**
    * @MongoDB\String
    */
    private $state;
    
    /**
    * @MongoDB\String
    */
    private $groupName;
    
    /**
    * @MongoDB\String
    */
    private $ipAddress;
    
    /**
    * @MongoDB\String
    */
    private $nbOfCore;
    
    /**
    * @MongoDB\Int
    */
    private $cpuFrequence;
    
    /**
    * @MongoDB\String
    */
    private $RAM;
    
    /**
    * @MongoDB\String
    */
    private $os;
    
    /**
    * @MongoDB\String
    */
    private $archi;


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
     * Set hostname
     *
     * @param string $hostname
     * @return \Worker
     */
    public function setHostname($hostname)
    {
        $this->hostname = $hostname;
        return $this;
    }

    /**
     * Get hostname
     *
     * @return string $hostname
     */
    public function getHostname()
    {
        return $this->hostname;
    }

    /**
     * Set state
     *
     * @param string $state
     * @return \Worker
     */
    public function setState($state)
    {
        $this->state = $state;
        return $this;
    }

    /**
     * Get state
     *
     * @return string $state
     */
    public function getState()
    {
        return $this->state;
    }

    /**
     * Set groupName
     *
     * @param string $groupName
     * @return \Worker
     */
    public function setGroupName($groupName)
    {
        $this->groupName = $groupName;
        return $this;
    }

    /**
     * Get groupName
     *
     * @return string $groupName
     */
    public function getGroupName()
    {
        return $this->groupName;
    }

    /**
     * Set ipAddress
     *
     * @param string $ipAddress
     * @return \Worker
     */
    public function setIpAddress($ipAddress)
    {
        $this->ipAddress = $ipAddress;
        return $this;
    }

    /**
     * Get ipAddress
     *
     * @return string $ipAddress
     */
    public function getIpAddress()
    {
        return $this->ipAddress;
    }

    /**
     * Set nbOfCore
     *
     * @param string $nbOfCore
     * @return \Worker
     */
    public function setNbOfCore($nbOfCore)
    {
        $this->nbOfCore = $nbOfCore;
        return $this;
    }

    /**
     * Get nbOfCore
     *
     * @return string $nbOfCore
     */
    public function getNbOfCore()
    {
        return $this->nbOfCore;
    }

    /**
     * Set cpuFrequence
     *
     * @param int $cpuFrequence
     * @return \Worker
     */
    public function setCpuFrequence($cpuFrequence)
    {
        $this->cpuFrequence = $cpuFrequence;
        return $this;
    }

    /**
     * Get cpuFrequence
     *
     * @return int $cpuFrequence
     */
    public function getCpuFrequence()
    {
        return $this->cpuFrequence;
    }

    /**
     * Set RAM
     *
     * @param string $rAM
     * @return \Worker
     */
    public function setRAM($rAM)
    {
        $this->RAM = $rAM;
        return $this;
    }

    /**
     * Get RAM
     *
     * @return string $rAM
     */
    public function getRAM()
    {
        return $this->RAM;
    }

    /**
     * Set os
     *
     * @param string $os
     * @return \Worker
     */
    public function setOs($os)
    {
        $this->os = $os;
        return $this;
    }

    /**
     * Get os
     *
     * @return string $os
     */
    public function getOs()
    {
        return $this->os;
    }

    /**
     * Set archi
     *
     * @param string $archi
     * @return \Worker
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
}
