var APIModule = (function () {
    let apiVersion = "v1";
    let usersUrl = "/api/"+apiVersion+"/users/"
    let roomsUrl = "/api/"+apiVersion+"/rooms/"
    var getRequest = function (url,callback) {
        $.get(url,callback)
    }

    var postRequest= function(url, object, callback){
        //debugger
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            complete: callback,
            success: function(data){
                console.log(callback)
                callback(data);
                console.log(data);
            },
            error: function(data){
                console.log("error: "+data);
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
        createRoom: function (object,callback) {postRequest(roomsUrl,object,callback)},
        joinInARoom: function (roomId,user) {
            postRequest(roomsUrl+roomId,user)
        },
        register: function (user) {
            postRequest(usersUrl,user)

        }
    }
})();