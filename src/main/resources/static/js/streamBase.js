var stompClient = null;

/** @type {RTCConfiguration} */
const config = { // eslint-disable-line no-unused-vars
  'iceServers': [{
    'urls': ['stun:stun.l.google.com:19302']
  }]
};

var connectAndSubscribe = function () {
	console.info('Connecting to WS...');
	var socket = new SockJS('/stompendpoint');
	stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    /*
    const peerConnection = new RTCPeerConnection(config);
    peerConnection.addStream(video.srcObject);
    peerConnection.createOffer()
    .then(sdp => peerConnection.setLocalDescription(sdp))
    .then(function () {
      socket.emit('offer', id, peerConnection.localDescription);
    });
    peerConnection.onicecandidate = function(event) {
      if (event.candidate) {
        socket.emit('candidate', id, event.candidate);
      }
    };
    */
		stompClient.subscribe("/topic/newVideo", function (data) {
      var i = $("img")
      i.src = data.image;
		});
	});

};

function broadcast() {  // eslint-disable-line no-unused-vars
	const video = document.querySelector('video');
	const canvas = document.createElement('canvas');
  const ctx = canvas.getContext('2d');
  navigator.getUserMedia = navigator.getUserMedia ||
                         navigator.webkitGetUserMedia ||
                         navigator.mozGetUserMedia;
	navigator.getUserMedia({ video : true },
	function(stream) {
		video.srcObject = stream;
		setInterval(function() {
			canvas.width = video.videoWidth;
			canvas.height = video.videoHeight;
			ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
			const imageData = canvas.toDataURL("image/jpeg", 0.4);
			stompClient.send("topic/newVideo",{},imageData);
		}, 100);
	},function(err) {
    console.log(err.name + ": " + err.message);
  });
}

//const video = document.querySelector('video'); // eslint-disable-line no-unused-vars

/*window.onunload = window.onbeforeunload = function() {
	socket.close();
};*/
