<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import QuestionCreator from "../components/QuestionCreator.svelte";
    import {surveyId} from "../stores.js";
</script>

<h1>Survey Creation</h1>
<div>
    <h2>Create a set of questions and answers for the survey></h2>
    <h3>Survey ID: {$surveyId} </h3>
    <br>
</div>
<br>

<div>
    <button>Add Question</button>
</div>

<QuestionCreator/>
<style>
    div, h1{
        text-align: center;
    }
</style>