var streamModule = (function () {

    navigator.mediaDevices.getDisplayMedia = navigator.mediaDevices.getDisplayMedia ||
        navigator.mediaDevices.webkitGetDisplayMedia ||
        navigator.mediaDevices.mozGetDisplayMedia ||
        null;
    var sala = null;
    var user = null;
    var chatTopic = "/topic/chat.";
    var chatsender = "/app/chat.";
    var videoTopic = "/topic/video.";
    /* https://www.color-hex.com */
    var colors = [
        '2196F3', '32c787', '00BCD4', 'ff5652',
        'ffc107', 'ff85af', 'FF9800', '39bbb0',
        'f3cf55', '26619c'
    ];
    var color = colors[Math.floor(Math.random() * 10)];

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
            vars[key] = value;
        });
        return vars;
    }

    var getRoom = function () {
        APIModule.getRoomById(getUrlVars()['id'], function (data) {
            $("#data-teacher").append(data.teacher.name);
            $(".title-room").html(data.information.title);
            getUsers(data.users);
        });
        //setTimeout(getRoom, 1000);
    };

    var getUsers = function (data) {
        data.forEach(value => {
            $("#data-users").append("<tr><td>" + value.name + "</td></tr>");
        });
    };

    var wsConnect = function () {
        APIModule.getCurrentUserId(function (data) {
            user = data;
            sala = getUrlVars()['id'];
            connectAndSubscribe();
            console.log(user);
        });
    };

    var connectAndSubscribe = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, connectionSuccess);
    };

    function connectionSuccess(frame) {
        console.log('Connected: ' + frame);
        //chat
        stompClient.subscribe(chatTopic + sala, onMessage);
        //Image.
        stompClient.subscribe(videoTopic + sala, function (data) {
            const i = document.querySelector('.external-media');
            i.src = data.body;
        });
        var message = {
            sender: user,
            content: "Join",
            type: 'Join'
        };
        stompClient.send(chatsender + sala + ".newUser", {}, JSON.stringify(message));
    }

    function sendSreenSnapshot() {
        const video = document.querySelector('.local-media');
        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');
        navigator.mediaDevices.getDisplayMedia({ video: true })
            .then(
                function (stream) {
                    video.srcObject = stream;
                    let interval = setInterval(function () {
                        if (!stream.active) {
                            clearInterval(interval);
                        }
                        canvas.width = video.videoWidth;
                        canvas.height = video.videoHeight;
                        ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
                        const imageData = canvas.toDataURL("image/jpeg", 0.4);
                        stompClient.send(videoTopic + sala, {}, imageData);
                    }, 1000);
                }).catch(function (err) {
                    console.log(err.message);
                });
    }

    function sendMessage() {
        var message = {
            sender: user,
            content: document.querySelector('#btn-input').value,
            type: 'Message'
        };
        stompClient.send(chatsender + sala, {}, JSON.stringify(message));
        document.querySelector('#btn-input').value = "";
    }

    function onMessage(event) {
        var body = document.querySelector('.panel-body');
        var message = JSON.parse(event.body);

        var messageElement = document.createElement('li');
        messageElement.classList.add("left");
        messageElement.classList.add("clearfix");

        if (message.type === 'Join') {
            message.content = message.sender + ' has joined to chat';
            $("#data-users").append("<tr><td>" + message.sender + "</td></tr>");
        }
        /* image */
        var span = document.createElement('span');
        span.classList.add("pull-left");

        var img = document.createElement('img');
        var color_text = "fff";
        var text = message.sender.charAt(0).toUpperCase() + message.sender.charAt(1).toUpperCase();
        img.src = "http://placehold.it/50/" + color + "/" + color_text + "&text=" + text;
        img.classList.add("img-circle");

        span.appendChild(img);
        messageElement.appendChild(span);

        var chat_body = document.createElement('div');
        chat_body.classList.add('clearfix');

        var header = document.createElement('div');
        header.classList.add('header');
        var primary_font = document.createElement('strong');
        primary_font.classList.add('primary-font');
        primary_font.innerHTML = message.sender;
        header.appendChild(primary_font);
        chat_body.appendChild(header);

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);
        chat_body.appendChild(textElement);
        messageElement.appendChild(chat_body);
        body.appendChild(messageElement);
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
        },
        sendScreen: function () {
            sendSreenSnapshot();
        }
    };

})();
