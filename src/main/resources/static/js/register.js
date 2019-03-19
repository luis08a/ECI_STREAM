/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {

    $('#Register').click(function(){
        
        var nameF = document.getElementById('name').value;
        var emailF = document.getElementById('email').value;
        var passwordF = document.getElementById('password').value;
        
        var user = { name : nameF, email : emailF, password : passwordF };
        
        //alert('Email: ' + emailF + ' Password: ' + passwordF);
        
        $.ajax({
            url: '/api/users',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log('done, ' + data);
            },
            data: JSON.stringify(user)
        });                
        alert("Data send");
        //console.log("termino");
        location.href = "main.html";        
    });
});