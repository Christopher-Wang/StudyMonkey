<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import QuestionCreator from "../components/QuestionCreator.svelte";
    import OptionCreator from "../components/OptionCreator.svelte";

    import {surveyId} from "../stores.js";
    let surveyName = "PlaceHolder";
    let options = []

    function handleRegisterSurvey() {
        goto('/question_creator');
    }
</script>

<h1>Survey Creation</h1>
<div>
    <h2>Create a set of questions and answers for the survey></h2>
    <h3>Survey ID: {$surveyId} </h3>
    <br>
</div>
<br>

<h1>Add Questions for {surveyName}</h1>
<div>
    <button>Add Question</button>
</div>

<style>
    div, h1{
        text-align: center;
    }
</style>