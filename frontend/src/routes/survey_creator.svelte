<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import Questions from "../components/Questions.svelte";
    let surveyName = "Survey-#0";
</script>

<h1>Survey Creation</h1>
<div>
    <input
            placeholder="What needs to be done?"
            bind:value={surveyName}
    >
    <p>Sample: {surveyName}</p>
</div>

<Questions/>