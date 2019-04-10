var roomModule = (function () {

    let currentUser= function(){APIModule.getCurrentUserId(function (data) {
            console.log(data)
            return data;
        })}

    var createRoom =function () {
        let roomInfo = {
            category:$("#category").val(),
            title:$("#title").val(),
            keyWords:[],
            description:$("#description").val()
        }
        
        APIModule.getUserById(currentUser.responseText,function (data) {
            let room = {teacher:data,information:roomInfo}
            APIModule.crateRoom(room)
            location.href = "/main";
        });
    };
    
    var getRooms = function () {
        APIModule.getRooms( function(data) {
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