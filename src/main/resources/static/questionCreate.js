
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
                    mcOperations.mcOptions--;
                    mcOperations.mcOptionGroups--;
                    this.parentElement.parentElement.remove();
                });
            } else {
                mcOperations.mcOptionGroups++;
                let deleteButton = `<button type='button' class='btn btn-danger mcDeleteButton'>delete</button>`;
                let optionGroups = document.getElementsByClassName('mcOptionGroup');
                optionGroups[optionGroups.length - 1].insertAdjacentHTML("afterend",
                    `<div class="mcOptionGroup">${breakLine}${mcOperations.appendMoreMcOptionLabel()}<div class="input-group" id="mcOptionGroup${mcOperations.mcOptionGroups}">${mcOperations.appendMoreMcOptionInput()}${deleteButton}</div></div>`);

                let mcDeleteButtons = document.getElementsByClassName('mcDeleteButton');
                mcDeleteButtons[mcDeleteButtons.length - 1].addEventListener("click", function (){
                    mcOperations.mcOptions--;
                    mcOperations.mcOptionGroups--;
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
        let mcValues = null;

        if(!mcOperations.validateGeneralFields()) {
            return;
        }

        if(mcOperations.isMultipleChoiceQuestion()) {
            mcValues = mcOperations.getMcValues();
            let mcFieldLength = mcValues.mcText.length + mcValues.numOfEmptyFields;
            if(!mcOperations.validateMC(mcFieldLength, mcValues.numOfEmptyFields)) {
                return;
            }
            mcValues = mcValues.mcText;
        }

        mcOperations.createQuestion(mcValues);
    }
    ,validateMC: function(numberOfFields, numberOfEmptyFields) {
        if (numberOfEmptyFields != 0 && (numberOfFields == 2
                || numberOfFields > 2 && numberOfEmptyFields > 1)) {
                alert("Invalid Submission - a MC option field is empty");
                return false;
            }
        return true;
    }
    ,validateGeneralFields() {
        let questionField = document.getElementById('question');
        if(!questionField.value.length || !questionField.value.trim().length) {
            alert("Invalid Submission - the question field is empty");
            return false;
        }
        return true;
    }
    ,getMcValues: function() {
        let options = document.getElementsByClassName('mcOption');
        let values = []
        let numberOfEmptyFields = 0;
        for (i = 0; i < options.length; i++) {
            if (!options[i].value.length || !options[i].value.trim().length) {
                numberOfEmptyFields++;
            } else {
                values.push(options[i].value);
            }
        }

        return {
            mcText: values,
            numOfEmptyFields: numberOfEmptyFields
        }
    }
    ,createQuestion: function(mcValues) {
        let surveyId = document.getElementById('surveyId').value;
        let questionText = document.getElementById('question').value;
        let questionType = mcOperations.getQuestionType();

        if(mcOperations.isMultipleChoiceQuestion()) {
            var questionWrapper = {
                surveyId: surveyId,
                question: {
                    question: questionText,
                    questionType: questionType,
                    mcOption: mcValues
                }
            };
            var url = "/questionJson/mc"
        } else {
            var questionWrapper = {
                surveyId: surveyId,
                question: {
                    question: questionText,
                    questionType: questionType
                }
            };
            var url = "/questionJson/text"
        }

        let questionWrapperJson = JSON.stringify(questionWrapper)
        console.log(`Adding new question to survey ${surveyId} with the following values ${questionWrapperJson}`);

        $.ajax({type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: questionWrapperJson,
            url: url,
            timeout: 5000,
            statusCode: {
                201: function(data, requestStatus, xhrObject) {
                    window.location.href = `/question?surveyId=${surveyId}`;
                },
                404: function(xhrObj, textStatus, exception) {
                    alert(`Error!`);
                },
                500: function(xhrObj, textStatus, exception) {
                    alert(`Error!`);
                }
            }
        });
        return(false);  // prevent default link action
    }
    ,isMultipleChoiceQuestion: function () {
        return mcOperations.getQuestionType() == "MULTIPLE_CHOICE";
    }
    ,getQuestionType: function () {
        let questionTypeRadios = document.getElementsByClassName("form-check-input");

        for (i = 0; i < questionTypeRadios.length; i++) {
            if(questionTypeRadios[i].checked) {
                return questionTypeRadios[i].value;
            }
        }
    }
};

$(document).ready(function () {
    mcOperations.setup();
})
