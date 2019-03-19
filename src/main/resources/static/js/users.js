/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.getJSON('api/users', function (data) {
        console.log(data);
        jQuery.each(data, function (i ,val) {
            var n = "<td>"+val.name+"</td>";
            var e = "<td>"+val.email+"</td>";
            var p = "<td>"+val.password+"</td>";
            var markup = "<tr>"+n+e+p+"</tr>";
            
            $("table tbody").append(markup);            
        });
    });
    
    
});
