/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    $('#SignIn').click(function(){
        
        var email = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        
        alert('Email: ' + email + ' Password: ' + password);
        
    });
});