<?php

// src/pfe/ServerBundle/Document/Group.php
namespace pfe\ServerBundle\Document;

use Doctrine\ODM\MongoDB\Mapping\Annotations as MongoDB;

/**
 * @MongoDB\Document
 */
class Group
{

    //TO-DELETE?
    public function __construct($name)
    {
        $this->_name = $name;
        $this->_numberWin = 0;
        $this->_numberLinux = 0;
        $this->_numberMac = 0;
        $this->_startTime = 22;
        $this->_endTime = 6;
    }
    
    /**
    * @MongoDB\Id
    */
	private $_id;
    
    /**
    * @MongoDB\String
    */
    private $_name;

    /**
    * @MongoDB\Int
    */
    private $_numberWin;
    
    /**
    * @MongoDB\Int
    */
    private $_numberLinux;
    
    /**
    * @MongoDB\Int
    */
    private $_numberMac;
    
    /**
    * @MongoDB\Int
    */
    private $_startTime;
    
    /**
    * @MongoDB\Int
    */
    private $_endTime;

    /**
     * Get _id
     *
     * @return id $id
     */
    public function getId()
    {
        return $this->_id;
    }

    /**
     * Set _name
     *
     * @param string $name
     * @return \Group
     */
    public function setName($name)
    {
        $this->_name = $name;
        return $this;
    }

    /**
     * Get _name
     *
     * @return string $name
     */
    public function getName()
    {
        return $this->_name;
    }

    /**
     * Set _numberWin
     *
     * @param int $numberWin
     * @return \Group
     */
    public function setNumberWin($numberWin)
    {
        $this->_numberWin = $numberWin;
        return $this;
    }

    /**
     * Get _numberWin
     *
     * @return int $numberWin
     */
    public function getNumberWin()
    {
        return $this->_numberWin;
    }

    /**
     * Set _numberLinux
     *
     * @param int $numberLinux
     * @return \Group
     */
    public function setNumberLinux($numberLinux)
    {
        $this->_numberLinux = $numberLinux;
        return $this;
    }

    /**
     * Get _numberLinux
     *
     * @return int $numberLinux
     */
    public function getNumberLinux()
    {
        return $this->_numberLinux;
    }

    /**
     * Set _numberMac
     *
     * @param int $numberMac
     * @return \Group
     */
    public function setNumberMac($numberMac)
    {
        $this->_numberMac = $numberMac;
        return $this;
    }

    /**
     * Get _numberMac
     *
     * @return int $numberMac
     */
    public function getNumberMac()
    {
        return $this->_numberMac;
    }

    /**
     * Set _endTime
     *
     * @param int $endTime
     * @return \Group
     */
    public function setEndTime($endTime)
    {
        $this->_endTime = $endTime;
        return $this;
    }

    /**
     * Get _endTime
     *
     * @return int $endTime
     */
    public function getEndTime()
    {
        return $this->_endTime;
    }

    /**
     * Set _startTime
     *
     * @param int $startTime
     * @return \Group
     */
    public function setStartTime($startTime)
    {
        $this->_startTime = $startTime;
        return $this;
    }

    /**
     * Get _startTime
     *
     * @return int $startTime
     */
    public function getStartTime()
    {
        return $this->_startTime;
    }
}
