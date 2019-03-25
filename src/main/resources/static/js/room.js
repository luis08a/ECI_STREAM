var roomModule = (function () {
    
    var createRoom =function () {
        let roomInfo = {
            category:$("#category").val(),
            title:$("#title").val(),
            keyWords:[],
            description:$("#description").val()
        }

        APIModule.getUserById("me",function (data) {
            $.ajax({
                url: '/api/rooms',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                success: function (succes) {
                    console.log('room created');
                },
                data: JSON.stringify({teacher:data,information:roomInfo})
            });
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
        consultInfo: getRooms
    }
})();