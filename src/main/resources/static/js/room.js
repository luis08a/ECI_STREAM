var roomModule = (function () {
    
    var createRoom =function () {
        let roomInfo = {
            category:$("#category").val(),
            title:$("#title").val(),
            keyWords:[],
            description:$("#description").val()
        }
        //console.log("data: "+roomInfo.title+" "+ roomInfo.category +" "+ roomInfo.description)
        /*let roomInfo={ category:"maths",title:"room2",keyWords:[],description:"d"}*/
        event.preventDefault();
        APIModule.getUserById("user1",function (data) {
            $.ajax({
                url: '/api/rooms',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                success: function (succes) {
                    console.log('room created');
                },
                data: JSON.stringify({users:null,teacher:data,id:null,roomInfo:roomInfo})
            });
            console.log(JSON.stringify({users:null,teacher:data,id:null,roomInfo:roomInfo}));
        });
        
    };
    var getRooms = function () {
        APIModule.getRooms( function(data) {
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
                $("#data").html(markup)
            });
        })
    }
    return{
        create: createRoom,
        consultInfo: getRooms
    }
})();