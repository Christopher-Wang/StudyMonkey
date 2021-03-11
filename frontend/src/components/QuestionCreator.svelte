<!-- This page is currently a template for the Question. It is a playground for other developers to play around with some
svelte features. -->

<script>
    let answerType = "TEXT";
    let survey_id;
    let mcQuestion = [{surveyId: 2, question: "Sample Question", questionType: "MULTIPLE_CHOICE", mcOption:[]}];
    function addMC(surveyId, questionTxt, qType) {
        mcQuestion = mcQuestion.concat({surveyId: surveyId, question: questionTxt, questionType: qType, mcOption:[]});
    }
</script>


<!--This code can be used as a template for determining question type using checkboxes-->
<!--<div>-->
<!--    <h2>What type of answer would you like for this question?</h2>-->
<!--    <label>-->
<!--        <input type=radio bind:group={answerType} value={"MULTIPLE_CHOICE"}>-->
<!--        Multiple Choice-->
<!--    </label>-->
<!--    <label>-->
<!--        <input type=radio bind:group={answerType} value={"TEXT"}>-->
<!--        Text-->
<!--    </label>-->
<!--</div>-->

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

