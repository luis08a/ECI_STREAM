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
            $("#data").html("")
            data.forEach( value => {
                value = value.information
                let category = value.category
                let title = value.title
                let keyWords = value.keywords
                let description = value.description
                var markup = "<tr><td>"
                    + title
                    + "</td><td>" 
                    + category
                    + "</td><td>"
                    + null
                    + "</td><td>"
                    + description
                    + "</td></tr>";
                $("#data").append(markup)
            });
        })
    }
    return{
        create: createRoom,
        consultInfo: function () {
            if (currentUser.responseText=="anonymousUser") {
                location.href = "/";
            }
            else{
                getRooms()
            }
        }
    }
})();