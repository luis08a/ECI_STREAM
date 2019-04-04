var streamModule = (function () {
    
    var url = location.href;

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }


    return {
        data: function (key) {
            getUrlVars()[key];   
        }
    }
});