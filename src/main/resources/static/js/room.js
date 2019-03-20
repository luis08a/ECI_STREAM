var roomModule = (function () {
    let category = $("#title").value()
    let title = $("#title").value()
    let keyWords = $("#title").value()
    let description = $("#description").value()

    let roomInfo = {
        category: category
    }
    let user = $.get("/login")

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
            $.get("api/roms",function (data,status) {
                array.forEach(data => {
                    let category = data.category
                    let title = data.title
                    let keyWords = data.keywords
                    let description = data.description
                    
                    $("table tbody").append(markup);
                });
                
            })
        }

return{
    create: createRoom
}
})();