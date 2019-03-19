/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var registerModule = (function () {
        var nameF = $("#name").value;
        var emailF = $("#email").value;
        var passwordF = $("#password").value;
        
        //alert('Email: ' + emailF + ' Password: ' + passwordF);
        var registerUser=function() {
            $.post('/api/users',
                JSON.stringify({ name : nameF, email : emailF, password : passwordF }),
                function(data, status){
                    alert("Data: " + data + "\nStatus: " + status);
                }, "json");
               
        }
        console.log("termino");
    return{
        register : registerUser }
})();
/*
$(document).ready(function() {

    $('#Register').click(function(){
        
        var nameF = document.getElementById('name').value;
        var emailF = document.getElementById('email').value;
        var passwordF = document.getElementById('password').value;
        
        //alert('Email: ' + emailF + ' Password: ' + passwordF);
        
        $.post('/api/users',
        {
            name : nameF,
            email : emailF,
            password : passwordF
        },
        function(data, status){
            alert("Data: " + data + "\nStatus: " + status);
        }, "json");
        
        /*$.ajax({
            type: "POST",
            url: "/api/users",           
            user : { "name" : nameF, "email" : emailF, "password" : passwordF }
            ,
            success: function(user) {
                console.log('done');
            }
        });
        
        console.log("termino");
    });
});*/