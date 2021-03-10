<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import Questions from "../components/Questions.svelte";
    let surveyName = "Survey-#0";
</script>

<h1>Survey Creation</h1>
<div>
    <h3>Survey Name: <input
            placeholder="What needs to be done?"
            bind:value={surveyName}
    ></h3>
    <br>
    <label>Sample: {surveyName}</label>
</div>
<br>

<div><button>Add Question</button></div>

<Questions/>

<style>
    div, h1{
        text-align: center;
    }
</style>