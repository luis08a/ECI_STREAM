
var roomModule = (function () {

    var notification = "/notification";

    var currentUser;
    APIModule.getCurrentUserId(function (data) {
        //console.log(data);
        currentUser = data;
    });

    var createRoom = function () {        
        if ($("#category").val() != "" && $("#title").val() != "" && $("#description").val() != "") {
            let roomInfo = {
                category: $("#category").val(),
                title: $("#title").val(),
                keyWords: [],
                description: $("#description").val()
            }
            //console.log(currentUser)
            APIModule.getUserById(currentUser, function (data) {
                let room = { teacher: data, information: roomInfo };
                console.log("entro al callback " + JSON.stringify(room));
                APIModule.createRoom(room, function (data2) {
                    console.log("datos createRoom " + JSON.stringify(data2));
                    alert("datos del callback" + data2.val);
                    var markup = "<tr><td>"
                    + $("#title").val()
                    + "</td><td>"
                    + $("#category").val()
                    + "</td><td>"
                    + null
                    + "</td><td>"
                    + $("#description").val()
                    + "</td><td>"
                    + '<button id="join" class="btn btn-default" onclick="roomModule.toRoom(' + value.id + ')">Join Room</button>'
                    + "</td></tr>";

                })
                console.log("termino el callback")
            });

            $("#category").val("");
            $("#title").val("");
            $("#description").val("");
        }
    };

    var getRooms = function () {
        APIModule.getRooms(function (data) {
            //console.log(data)
            //console.log(data);
            $("#data").html("")
            data.forEach(value => {
                valueI = value.information
                let category = valueI.category
                let title = valueI.title
                let keyWords = valueI.keywords
                let description = valueI.description
                var markup = "<tr><td>"
                    + title
                    + "</td><td>"
                    + category
                    + "</td><td>"
                    + null
                    + "</td><td>"
                    + description
                    + "</td><td>"
                    + '<button id="join" class="btn btn-default" onclick="roomModule.toRoom(' + value.id + ')">Join Room</button>'
                    + "</td></tr>";
                $("#data").append(markup)
            });
        })
        //setTimeout(getRooms,100);
    }

    var changeToRoom = function (id) {
        APIModule.joinInARoom(id, currentUser);
        location.href = "/room?id=" + id;
    }

    var connectAndSubscribe = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, connectionSuccess);
    };

    function connectionSuccess(frame) {
        console.log('Connected: ' + frame);
        //chat
        stompClient.subscribe("/topic/"+notification, onMessage);
        
        stompClient.send("/app/"+notification , {}, JSON.stringify(message));
    }

    function sendMessage() {
        var message = {           
            type: 'newRoom'
        };
        stompClient.send("/app/"+notification, {}, JSON.stringify(message));
    }

    function onMessage(event) {
        
    }

    return {
        create: createRoom,
        consultInfo: function () {
            getRooms();
        },
        toRoom: function (id) {
            changeToRoom(id);
        }
    }
})();