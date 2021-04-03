function setUp(){
    $('#MULTIPLE_CHOICE').on('change', function() {
        let option = 0;
        $("#questionButtons").prepend("<button type=\"button\" class=\"btn btn-primary m-3\" id=\"addOption\">Add Option</button>");
        $("#addOption").on("click", function () {
            $("#questionTypeSpecific").append(`<input type=\"text\" class=\"form-control\" name=\"option\" placeholder=\"Option ${option}\"> <br/>`);
            option++;
        });
    });

    $('#TEXT').on('change', function() {
      $("#addOption").off().remove();
      $("#questionTypeSpecific").empty();
    });
}

$(document).ready(function () {
    $(document).on('submit', '#question-form', function(e) {
        let body = {
            "surveyId": $("#surveyId").val(),
            "question":  $("#question").val(),
            "questionType": $("input:radio[name='questionType']:checked").val()
        };

        if (body["questionType"] == "MULTIPLE_CHOICE"){
            body["options"] = $("input[name=\"option\"]").map(function () { return $(this).val()}).get();
        }

        $.ajax({
                type: "POST",
                url: `/question?questionType=${body["questionType"]}`,
                timeout: 5000,
                contentType: "application/json; charset=utf-8",
                dataType: "html",
                data: JSON.stringify(body),
                success: function(data, requestStatus, xhrObject){
                    if(xhrObject.status == 200) {
                        let body = data.substring(data.indexOf("<body>")+6,data.indexOf("</body>"));
                        $("body").html(body);
                        setUp();
                    } else {
                        alert("Request Failed");
                    }
                 },
                error: function(xhrObj, textStatus, exception) {
                    alert(`Exception ${exception}`);
                }
            });

        e.preventDefault();
    });

    setUp()
});
