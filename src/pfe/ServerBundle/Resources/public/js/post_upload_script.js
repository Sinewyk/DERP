$(document).ready(function() {
    
    var page = $('#pageName').attr('name');
    
    //if we are on the job send page, we do stuff
    if (page == "jobSend")
    {
        console.log('we are in the upload script');
        
        var path = $('#filepath').val();
        var winpath = $('#filepathWin').val();
        var linuxpath = $('#filepathLinux').val();
        var macpath = $('#filepathMac').val();
        var owner = $('#owner').val();
        var name = $('#name').val();
        var minram = $('#minram').val();
        var maxram = $('#maxram').val();
        var mincpu = $('#mincpu').val();
        var filesCreated = $('#filesCreated').val();
        var fnp = $('#fnp').val();
        var isUsingCG = $('#isUsingCG').val();
        var nbrun = $('#nbrun').val();
        var archi = $('#archi').val();
        var priority = $('#priority').val();
        
        var myObject = {};
        if (path !== "") myObject.path = path;
        if (winpath !== "") myObject.winPath = winpath;
        if (linuxpath !== "") myObject.linuxPath = linuxpath;
        if (macpath !== "") myObject.macPath = macpath;
        if (owner !== "") myObject.owner = owner;
        if (name !== "") myObject.name = name;
        if (minram !== "") myObject.minRam = minram;
        if (maxram !== "") myObject.maxRam = maxram;
        if (mincpu !== "") myObject.minCpuFrequence = mincpu;
        if (filesCreated !== "") myObject.filesCreated = filesCreated;
        if (fnp !== "") myObject.filesNamePatterns = fnp;
        if (isUsingCG !== "") myObject.isUsingCG = isUsingCG;
        if (nbrun !== "") myObject.nbRun = nbrun;
        if (archi !== "") myObject.archi = archi;
        if (priority !== "") myObject.priority = priority;
        
        var myParams = new Array();
        
        for(var i=0; i<nbrun; i++)
        {
            var str = "#parameter"+i;
            myParams.push($(str).val());
            console.log(myParams[i]);
        }
        
        if(myParams.length > 0) myObject.parametersList = JSON.stringify(myParams);
        
        /**
        * here, need to change this address to the address of the server
        */
        var ws = new WebSocket("ws://192.168.1.88:7720/");
    
        ws.onopen = function() {
            console.log("Ws open");
            //\0REQ, 3 = SUBMIT_JOB
            var header = new MyApp.Header("\0REQ","3");
            var bufferToSend = header.appendHeader(JSON.stringify(myObject));
            console.log(JSON.stringify(myObject));
            ws.send(bufferToSend,"binary");
        }
        
        ws.onmessage = function(e) {
            console.log(e);
        }
    }
});


























