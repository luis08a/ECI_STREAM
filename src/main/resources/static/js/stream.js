var streamModule = (function () {
    
    var sala = null;
    var user = null;

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    var getRoom = function () {
        APIModule.getRoomById( getUrlVars()['id'], function(data) {
            $("#data-teacher").append(data.teacher.name);
            $(".title-room").html(data.information.title);
            getUsers(data.users);
        });
        //setTimeout(getRoom, 1000);
    };

    var getUsers = function (data) {
        data.forEach( value => {
            $("#data-users").append("<tr><td>" + value.name + "</td></tr>");
        });
    };
    
    var wsConnect = function () {
        APIModule.getCurrentUserId(function (data) {
            user = data;
        });
        sala = "/topic/chat." + getUrlVars()['id'];
        connectAndSubscribe();
    };
    
    var connectAndSubscribe = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        
        stompClient.connect({}, connectionSuccess);
    };
    
    function connectionSuccess(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(sala, onMessage);
        var message = {
            author: user.name,
            type: 'Join'
        };
        stompClient.send(sala, {}, JSON.stringify(message));
    }
    
    function sendMessage() {
        var message = {
            author: user.name,
            content: document.querySelector('#chatMessage').value,
            type: 'Message'
        };
        stompClient.send(sala, {}, JSON.stringify(message));
    }
    
    function onMessage(event){
        var message = JSON.parse(event.body);
        if (message.type === 'Join')
            getRoom();        
        Console.log(message);
    }

    return {
        infoRoom: function () {
            getRoom();
        },
        init: function () {
            wsConnect();
            getRoom();
        },
        send: function () {
            sendMessage();
        }
    };
})();