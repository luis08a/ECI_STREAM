var stompClient = null;

/** @type {RTCConfiguration} */
const config = { // eslint-disable-line no-unused-vars
  'iceServers': [{
    'urls': ['stun:stun.l.google.com:19302']
  }]
};

var connectAndSubscribe = function () {
	console.info('Connecting to WS...');
	var socket = new SockJS('/ws');
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
      console.log("success")
      const i = document.querySelector('img');
      console.log(i)
      console.log("----------------------")
      console.log(data)
      i.src = data.body;
		});
	});

};

function broadcast() {  // eslint-disable-line no-unused-vars
	const video = document.querySelector('video');
	const canvas = document.createElement('canvas');
  const ctx = canvas.getContext('2d');
  /*navigator.mediaDevices.getUserMedia = navigator.mediaDevices.getUserMedia ||
                         navigator.mediaDevices.webkitGetUserMedia ||
                         navigator.mediaDevices.mozGetUserMedia ||
                        null;*/
  navigator.mediaDevices.getDisplayMedia({ video: true})
  .then(
	function(stream) {
		video.srcObject = stream;
		setInterval(function() {
			canvas.width = video.videoWidth;
			canvas.height = video.videoHeight;
			ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
      const imageData = canvas.toDataURL("image/jpeg", 0.3);
      //console.log(imageData);
			stompClient.send("/topic/newVideo",{},imageData);
    }, 10);
	}).catch(function(err) {
    console.log(err.message);
  });
}

//const video = document.querySelector('video'); // eslint-disable-line no-unused-vars

/*window.onunload = window.onbeforeunload = function() {
	socket.close();
};*/
