$(document).ready(function(){

    var page = $('#pageName').attr('name');
    
    if(page == 'newGroup')
    {
        var name = $('#groupName').val();
        
        $.ajax({
            type: "POST",
            datatype : 'json',
            data: { 'name' : name },
            url: "pfe_server_group_check",
            cache: false,
            success: function(data){
           }
        })
    }
});