
var mcOperations = {
    setup: function() {
        $('#questionTypeSelector :radio').change(function() {
            if(this.checked && this.id == "MULTIPLE_CHOICE") {
                mcOperations.appendNewMcOption();
            } else {
                document.getElementById('mcOptions').innerHTML="";
                mcOperations.mcOptions=0;
            }
        })

        document.getElementById('submit').addEventListener("click", function(){
            mcOperations.validateAndSubmit();
        })
    }
    ,mcOptions: 0
    ,appendNewMcOption: function() {
        mcOperations.mcOptions += 1;
        if(mcOperations.mcOptions <= 1) {
            document.getElementById('mcOptions').insertAdjacentHTML("beforeend",
                `<label for='mcOption'>Option${mcOperations.mcOptions}</label><input type='text' class='form-control mcOption' id='mcOption${mcOperations.mcOptions}' placeholder='Enter the option'>`);
            mcOperations.appendNewMcOption();
        } else {
            let options = document.getElementsByClassName('mcOption');

            options[options.length - 1].insertAdjacentHTML("afterend",
                `<br/><label for='mcOption${mcOperations.mcOptions}'>Option${mcOperations.mcOptions}</label><input type='text' class='form-control' id='mcOption${mcOperations.mcOptions}' placeholder='Enter the option'>`);

            if(mcOperations.mcOptions > 2) {
                options = document.getElementsByClassName('mcOption');

                options[options.length - 1].insertAdjacentHTML("afterend", "<button type='button' class='btn btn-danger m-3'>Delete</button>");
            }

            console.log(options[options.length - 1])
            options[options.length - 1].addEventListener("keydown", function (){
                console.log("keydown")
                if(!this.value.trim().length) {
                    mcOperations.appendNewMcOption();
                }
            });
        }


    }
    ,validateAndSubmit: function() {
        if(mcOperations.isMultipleChoiceQuestion()) {
            let options = document.getElementsByClassName('mcOption');

            let numberOfEmptyFields = 0;
            for(elem in options) {
                if(!elem.value.trim().length) {
                    numberOfEmptyFields++;
                }
            }

            if(options.length == 2 && numberOfEmptyFields < 2
                || options.length > 2 && numberOfEmptyFields < options.length - 1) {
                alert("Invalid Submission - a MC option field is empty");
            } else {
                mcOperations.createQuestion();
            }
        }

        if(document.getElementById('question').value.trim().length) {
            alert("Invalid Submission - the question field is empty");
        }
    }
    ,createQuestion: function() {
        let surveyId = document.getElementById('surveyId').value;
        let question = document.getElementById('question').value;
        let questionType = document.querySelector('#questionTypeSelector:checked').value;

        if(mcOperations.isMultipleChoiceQuestion()) {
            let questionMCOptions = document.getElementsByClassName('mcOption').value;
            console.log(questionMCOptions);
        }

        $.ajax({type: 'POST',
            url: `/question?username=${username}&password=${password}`,
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
    ,isMultipleChoiceQuestion: function () {
        let questionType = document.querySelector('#questionTypeSelector:checked').value;
        return questionType == "MULTIPLE_CHOICE";
    }
};

$(document).ready(function () {
    mcOperations.setup();
})
