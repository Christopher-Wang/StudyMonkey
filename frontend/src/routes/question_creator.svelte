<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import QuestionCreator from "../components/QuestionCreator.svelte";
    import OptionCreator from "../components/OptionCreator.svelte";
    let surveyName = "PlaceHolder";
    let surveyId = 1;
    let options = []

    function handleRegisterSurvey() {
        goto('/question_creator');
    }
</script>

<h1>Add Questions for {surveyName}</h1>
<div>
    <button>Add Question</button>
</div>

<style>
    div, h1{
        text-align: center;
    }
</style>