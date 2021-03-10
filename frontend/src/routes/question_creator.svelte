<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import QuestionCreator from "../components/QuestionCreator.svelte";
    let surveyName = "PlaceHolder";
</script>

<h1>Survey Creation</h1>
<div>
    <h3>Survey Name: <input bind:value={surveyName}></h3>
    <br>
</div>
<br>

<div>
    <button>Create Survey</button>
</div>

<QuestionCreator/>
<style>
    h1{
        text-align: center;
    }
</style>