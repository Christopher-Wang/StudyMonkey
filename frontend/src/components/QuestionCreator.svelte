<script>
    let answerType = "TEXT";
    let survey_id;
    let mcQuestion = [{surveyId: 2, question: "Sample Question", questionType: "MULTIPLE_CHOICE", mcOption:[]}];
    function addMC(surveyId, questionTxt, qType) {
        mcQuestion = mcQuestion.concat({surveyId: surveyId, question: questionTxt, questionType: qType, mcOption:[]});
    }
</script>

<div class="mcQuestion">
    <label>
        Question: <input type="text">
    </label>
    <br>
    <label>
        Option: <input type="text">
    </label>
    <br>
    <label>
        Option: <input type="text">
    </label>
    <br>
    <label>
        Option: <input type="text">
    </label>
    <br>
    <label>
        Option: <input type="text">
    </label>

</div>

<p>{answerType}</p>

<style>
    div, p{
        text-align: center;
    }
</style>

