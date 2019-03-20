/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function info() {
    $.getJSON('api/users', function (data) {
        //console.log(data);
        var res = "";
        jQuery.each(data, function (i ,val) {
            var n = "<td>"+val.name+"</td>";
            var e = "<td>"+val.email+"</td>";
            var p = "<td>"+val.password+"</td>";
            var markup = "<tr>"+n+e+p+"</tr>";
            
            res += markup;
            
            //$("table tbody").append(markup);
        });
        document.getElementById("data").innerHTML = res;
        setTimeout(info,1000);
    })};

$(document).ready(function() {
    info();
});