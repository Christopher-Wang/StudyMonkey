
var loginRequests = {
    setup: function() {
        $(document).on('click', '#login', function(e) {
            e.preventDefault();
            loginRequests.getAccountByUsername(document.getElementById("username").value);
        });
    }
    ,getAccountByUsername: function(username) {
        console.log("Calling getAddressBook")
        $.ajax({type: 'GET',
            url: `/accounts?username=${username}`,
            timeout: 5000,
            success:function(data, requestStatus, xhrObject){
                console.log(data);
                if(data != null && data != undefined && data != "") {
                    window.location.href = '/';
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
