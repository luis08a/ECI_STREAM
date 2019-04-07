var streamModule = (function () {
    
    var url = location.href;

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    var getRoom = function () {
        APIModule.getRoomById( getUrlVars()['id'], function(data) {
            console.log(data);            
            $("#data-teacher").append(data.teacher.name);
            $(".title-room").html(data.information.title);
            getUsers(data.users);
        });
        //setTimeout(getRoom, 1000);
    }

    var getUsers = function (data) {
        console.log(data);
        data.forEach( value => {                
            $("#data-users").append("<tr><td>" + value.name + "</td></tr>");
        });
    }

    return {
        infoRoom: function () {
            getRoom();
        }
    }
})();