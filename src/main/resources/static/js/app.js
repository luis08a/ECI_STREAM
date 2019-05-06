var APIModule = (function () {
    let apiVersion = "v1";
    let usersUrl = "/api/"+apiVersion+"/users/"
    let roomsUrl = "/api/"+apiVersion+"/rooms/"
    var getRequest = function (url,callback) {
        $.get(url,callback)
    }

    var postRequest= function(url, object){
        //debugger
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success: function (msg) {
                console.log(msg);
            },
            data: JSON.stringify(object)
        });
    }
    return {
        // Get from the api
        getUsers: function(callback){getRequest(usersUrl,callback)} ,
        getRooms: function(callback){getRequest(roomsUrl,callback)},
        getUserById: function (userId,callback) {getRequest(usersUrl+userId,callback)},
        getRoomById: function (roomId,callback) {getRequest(roomsUrl+roomId,callback)},
        getCurrentUserId: function (callback) {getRequest(usersUrl+"me",callback)},
        // Post to the api
        crateRoom: function (object,callback) {},
        crateRoom: function (object) {postRequest(roomsUrl,object)},
        joinInARoom: function (roomId,user) {
            postRequest(roomsUrl+roomId,user)
        },
        register: function (user) {
            postRequest(usersUrl,user)
        }
    }
})();