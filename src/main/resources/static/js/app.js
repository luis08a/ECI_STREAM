
var APIModule = (function () {
    var getRequest = function (url,callback) {
        $.get(url,callback)
    }

    var postRequest= function(url, roomInfo){
        $.post(url,roomInfo);
    }
    return {
        getUsers: function(callback){getRequest("/api/users",callback)} ,
        getRooms: function(callback){getRequest("/api/rooms",callback)},
        getUserByid: function (userId,callback) {getRequest("/users/"+userId,callback)},
        getRoomByid: function (roomId,callback) {getRequest("/rooms/"+roomId,callback)}
    }
})();