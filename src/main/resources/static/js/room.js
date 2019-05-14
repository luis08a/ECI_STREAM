
var roomModule = (function () {

    var notification = "/topic/notification";

    var currentUser;
    APIModule.getCurrentUserId(function (data) {
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
            APIModule.getUserById(currentUser, function (data) {
                let room = { teacher: data, information: roomInfo };
                APIModule.createRoom(room, function (data2) {
                    sendMessage();
                    $("#category").val("");
                    $("#title").val("");
                    $("#description").val("");
                    changeToRoom(data2.id);
                });
            });
        }
        else
            alert("A input is null");
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
        console.log(id)
        APIModule.getUserById(currentUser, function (data) {
            console.log(data)
            APIModule.joinInARoom(id, data,
                () => location.href = "/room?id=" + id
            );
        });
    }

    var connectAndSubscribe = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, connectionSuccess);
    };

    function connectionSuccess(frame) {
        console.log('Connected: ' + frame);
        // public connection
        stompClient.subscribe(notification, onMessage);
    }

    function sendMessage() {
        var message = {
            type: 'newRoom'
        };
        stompClient.send(notification, {}, JSON.stringify(message));
    }

    function onMessage(event) {
        getRooms();
    }

    return {
        create: createRoom,
        consultInfo: function () {
            getRooms();
            connectAndSubscribe();
        },
        toRoom: function (id) {
            changeToRoom(id);
        }
    }
})();