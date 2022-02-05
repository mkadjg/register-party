$(document).ready(function(){
    $.get({
        url: "/home/users",
        contentType: "application/json",
        success: function(response){
            var length = response.length;
            var content = '';
            for (var i = 0; i < length; i++){
                content += '<tr>';
                content += '<td>' + (i+1) + '</td>';
                content += '<td>' + response[i].username + '</td>';
                content += '<td>' + response[i].email + '</td>';
                content += '<td>' + response[i].fullname + '</td>';
                content += '<td>' + response[i].address + '</td>';
                content += '</tr>';
            }
            $('#users').html(content);
        }
    });
});