
var APIModule = { function () {
        var getRequest = function (url) {
            $.get(url,function (data,status) {
                return data
            })
        }

        var postRequest= function(url, roomInfo){
            $.post(url,roomInfo);
        }
        return {
            getUsers: getRequest("/api/users"),
            getRooms: getRequest(url),
            getUserByid: getRequest(url)
        }
    }
}