/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var loginModule = (function () {
    var logIn = function () {
        let email = $("#inputEmail")

        console.log("This is the login information",email)
        alert(email)
    }
    return{
        log: logIn
    }    
})();

