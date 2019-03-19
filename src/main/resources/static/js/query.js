var queryModule = {
    function () {
        var getUsers = function() {
            var users = APIModule.getUsers();
            let name = user.name
            let email = user.email
            var markup ="<tr><td><input type='checkbox' name='record'></td><td>" + name + "</td><td>" + email + "</td></tr>";
            $("#table tbdoy").append(markup)
        }
        return {
            queryUsers: getUsers
        }
    }
}