
var loginRequests = {
    setup: function() {
        $(document).on('click', '#login', function(e) {
            e.preventDefault();
            let username = document.getElementById("username").value;
            let password = document.getElementById("password").value;
            loginRequests.getAccountByUsername(username,password);
        });
    }
    ,getAccountByUsername: function(username,password) {
        $.ajax({type: 'GET',
            url: `/accounts?username=${username}&password=${password}`,
            timeout: 5000,
            success:function(data, requestStatus, xhrObject){
                if(xhrObject.status == 200) {
                    window.location.href = '/home';
                } else {
                    alert("invalid credentials");
                }
            },
            error: function(xhrObj, textStatus, exception) { alert('Error!'); }
        });
        return(false);  // prevent default link action
    }
};

$(document).ready(function () {
    loginRequests.setup();
})
