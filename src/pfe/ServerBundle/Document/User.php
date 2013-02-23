<?php
// src/pfe/ServerBundle/Document/User.php
namespace pfe\ServerBundle\Document;

use Doctrine\ODM\MongoDB\Mapping\Annotations as MongoDB;

/**
 * @MongoDB\Document
 */
class User
{
    /**
    * @MongoDB\Id
    */
    protected $mongoId;
    
    /**
    * @MongoDB\String
    */
    protected $username;
    
    /**
    * @MongoDB\String
    */
    protected $password;
    
    /**
    * @MongoDB\Boolean
    */
	private $isAdmin;
	
    
    public function __construct()
    {
        
        $this->username = "b";
        $this->isAdmin = true;
    }


    /**
     * Get mongoId
     *
     * @return id $mongoId
     */
    public function getMongoId()
    {
        return $this->mongoId;
    }

    /**
     * Set isAdmin
     *
     * @param boolean $isAdmin
     * @return \User
     */
    public function setIsAdmin($isAdmin)
    {
        $this->isAdmin = $isAdmin;
        return $this;
    }

    /**
     * Get isAdmin
     *
     * @return boolean $isAdmin
     */
    public function getIsAdmin()
    {
        return $this->isAdmin;
    }

    /**
     * Set username
     *
     * @param string $username
     * @return \User
     */
    public function setUsername($username)
    {
        $this->username = $username;
        return $this;
    }

    /**
     * Get username
     *
     * @return string $username
     */
    public function getUsername()
    {
        return $this->username;
    }

    /**
     * Set password
     *
     * @param string $password
     * @return \User
     */
    public function setPassword($password)
    {
        $this->password = $password;
        return $this;
    }

    /**
     * Get password
     *
     * @return string $password
     */
    public function getPassword()
    {
        return $this->password;
    }
}
