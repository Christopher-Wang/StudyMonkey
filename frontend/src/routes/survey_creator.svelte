<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import SurveyCreator from "../components/SurveyCreator.svelte";
    import {goto} from '@sapper/app';
    
    let surveyName = "Your Survey Name";
    function handleRegisterSurvey() {
        alert("here");
        goto('/question_creator');
    }
</script>

<h1>Survey Creation</h1>
<div>
    <h3>Survey Name: <input bind:value={surveyName}></h3>
    <br>
</div>
<br>

<div>
    <button on:click={handleRegisterSurvey}>Register Survey</button>
</div>

<style>
    div, h1{
        text-align: center;
    }
</style>