var roomModule = (function () {
    
    var createRoom =function () {
        $.ajax({
            url: '/api/rooms',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log('room created');
            },
            data: JSON.stringify({user,room})
        });
    }
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