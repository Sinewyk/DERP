$(document).ready(function() 
{
    $("#nbrun").on("change", function(e) 
	{
        var self = $(this);
        var nb = self.val();
        if(nb <= 0) {
            self.val(1);
        }
        if(!isNaN(nb) && nb > 0)
		{
            var list = $('#listOfParameters');
            var children = list.children();
            if(children.length < nb) 
			{
				for(var i=children.length; i<nb; i++)
				{
					list.append("<input placeholder='"+(i+1)+"' type='text' name='parameter[]' id='parameter"+i+ "'>");
				}
            }
            else if(children.length >= nb)
			{
				for(var i = children.length; i > nb; i--) {
                    children.last().remove();
                    children = $('#listOfParameters > input')
                }
            }
        }
    });
});
