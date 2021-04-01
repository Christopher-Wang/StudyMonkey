
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

        document.getElementById('submit').addEventListener("click", function(e){
            e.preventDefault();
            mcOperations.validateAndSubmit();
        })
    }
    ,mcOptions: 0
    ,mcOptionGroups: 0
    ,isLastMcOptionFilled: false
    ,appendNewMcOption: function() {
        mcOperations.mcOptions += 1;
        if(mcOperations.mcOptions <= 1) {
            document.getElementById('mcOptions').insertAdjacentHTML("beforeend",
                `${mcOperations.appendMoreMcOptionLabel()}${mcOperations.appendMoreMcOptionInput()}`);
            mcOperations.appendNewMcOption();
        } else {
            let options = document.getElementsByClassName('mcOption');
            let breakLine = "<br/>";

            if(mcOperations.mcOptions == 2) {
                options[options.length - 1].insertAdjacentHTML("afterend",
                    `${breakLine}${mcOperations.appendMoreMcOptionLabel()}${mcOperations.appendMoreMcOptionInput()}`);
            } else if(mcOperations.mcOptions == 3){
                mcOperations.mcOptionGroups++;
                let trashIcon = 'delete'
                let deleteButton = `<button type='button' class='btn btn-danger mcDeleteButton'>${trashIcon}</button>`;
                options[options.length - 1].insertAdjacentHTML("afterend",
                    `<div class="mcOptionGroup">${breakLine}${mcOperations.appendMoreMcOptionLabel()}<div class="input-group" id="mcOptionGroup${mcOperations.mcOptionGroups}">${mcOperations.appendMoreMcOptionInput()}${deleteButton}</div><div></div>`);

                let mcDeleteButtons = document.getElementsByClassName('mcDeleteButton');
                mcDeleteButtons[mcDeleteButtons.length - 1].addEventListener("click", function () {
                    this.parentElement.parentElement.remove();
                });
            } else {
                mcOperations.mcOptionGroups++;
                let trashIcon = 'delete'
                let deleteButton = `<button type='button' class='btn btn-danger mcDeleteButton'>${trashIcon}</button>`;
                let optionGroups = document.getElementsByClassName('mcOptionGroup');
                optionGroups[optionGroups.length - 1].insertAdjacentHTML("afterend",
                    `<div class="mcOptionGroup">${breakLine}${mcOperations.appendMoreMcOptionLabel()}<div class="input-group" id="mcOptionGroup${mcOperations.mcOptionGroups}">${mcOperations.appendMoreMcOptionInput()}${deleteButton}</div></div>`);

                let mcDeleteButtons = document.getElementsByClassName('mcDeleteButton');
                mcDeleteButtons[mcDeleteButtons.length - 1].addEventListener("click", function (){
                    this.parentElement.parentElement.remove();
                });
            }

            options = document.getElementsByClassName('mcOption');
            mcOperations.isLastMcOptionFilled = false;
            options[options.length - 1].addEventListener("keydown", function (){
                if(!this.value.trim().length && !mcOperations.isLastMcOptionFilled) {
                    mcOperations.isLastMcOptionFilled = true;
                    mcOperations.appendNewMcOption();
                }
            });
        }
    }
    ,appendMoreMcOptionInput: function() {
        return `<input type='text' class='form-control mcOption' id='mcOption${mcOperations.mcOptions}' placeholder='Enter the option'>`
    }
    ,appendMoreMcOptionLabel: function() {
        return `<label htmlFor='mcOption${mcOperations.mcOptions}'>Option ${mcOperations.mcOptions}</label>`;
    }
    ,validateAndSubmit: function() {
        if(mcOperations.validateMC() && mcOperations.validateGeneralFields()) {
            mcOperations.createQuestion();
        }
    }
    ,validateMC: function() {
        if(mcOperations.isMultipleChoiceQuestion()) {
            let options = document.getElementsByClassName('mcOption');

            let numberOfEmptyFields = 0;
            for (i = 0; i < options.length; i++) {
                if (!options[i].value.length || !options[i].value.trim().length) {
                    numberOfEmptyFields++;
                }
            }

            console.log(numberOfEmptyFields);
            if (options.length == 2 && numberOfEmptyFields <= 2
                || options.length > 2 && numberOfEmptyFields - options.length > 1) {
                alert("Invalid Submission - a MC option field is empty");
                return false;
            }
            return true;
        }
    }
    ,validateGeneralFields() {
        let questionField = document.getElementById('question');
        if(!questionField.value.length || !questionField.value.trim().length) {
            alert("Invalid Submission - the question field is empty");
            return false;
        }
        return true;
    }
    ,createQuestion: function() {
        let surveyId = document.getElementById('surveyId').value;
        let questionText = document.getElementById('question').value;
        let questionType = document.querySelector('#questionTypeSelector:checked').value;

        if(mcOperations.isMultipleChoiceQuestion()) {
            let questionMCOptions = document.getElementsByClassName('mcOption').value;
            console.log(questionMCOptions);
        }

        let question = {
            surveyId: surveyId,
            question: questionText,
            questionType: questionType
        };

        var buddyJson = JSON.stringify(BuddyInfo)

        console.log(`Adding new buddy to address book ${bookID} with the following values ${buddyJson}`);

        $.ajax({type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: buddyJson,
            url: `/addressBooks/${bookID}`,
            timeout: 5000,
            success:function(data, requestStatus, xhrObject){
                console.log(data);
                MoviePopup.getAddressBook(bookID);
            },
            error: function(xhrObj, textStatus, exception) { alert('Error!'); }
        });
        return(false);  // prevent default link action
    }
    ,isMultipleChoiceQuestion: function () {
        let questionTypeRadios = document.getElementsByClassName("form-check-input");

        for (i = 0; i < questionTypeRadios.length; i++) {
            if(questionTypeRadios[i].checked) {
                var questionType = questionTypeRadios[i].value;
            }
        }

        return questionType == "MULTIPLE_CHOICE";
    }
};

$(document).ready(function () {
    mcOperations.setup();
})
