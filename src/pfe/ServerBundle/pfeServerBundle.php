<?php

namespace pfe\ServerBundle;

use Symfony\Component\HttpKernel\Bundle\Bundle;

class pfeServerBundle extends Bundle
{
    public function getParent()
    {
        return 'MopaBootstrapBundle';
    }
}
