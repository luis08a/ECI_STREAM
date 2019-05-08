
var roomModule = (function () {

    var currentUser;
    APIModule.getCurrentUserId(function (data) {
        console.log(data);
        currentUser=data;
    });

    var createRoom =function () {
        console.log("creando")
        let roomInfo = {
            category:$("#category").val(),
            title:$("#title").val(),
            keyWords:[],
            description:$("#description").val()
        }
        console.log(currentUser)
        APIModule.getUserById(currentUser,function (data) {
            let room = {teacher:data,information:roomInfo}
            APIModule.createRoom(room)
            location.href = "/main";
        });
        getRooms;
    };
    
    var getRooms = function () {
        APIModule.getRooms( function(data) {
            console.log(data)
            //console.log(data);
            $("#data").html("")
            data.forEach( value => {                
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
                    + '<button id="join" class="btn btn-default" onclick="roomModule.toRoom('+ value.id +')">Join Room</button>'
                    + "</td></tr>";
                $("#data").append(markup)
            });
        })
        //setTimeout(getRooms,1000);
    }

    var changeToRoom = function (id) {
        APIModule.joinInARoom(id,currentUser);
        location.href = "/room?id=" + id;
    }

    return{
        create: createRoom,
        consultInfo: function () {
            getRooms();
        },
        toRoom: function (id) {
            changeToRoom(id);
        }
    }
})();